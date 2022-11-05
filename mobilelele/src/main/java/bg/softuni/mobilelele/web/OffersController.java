package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.exceptions.ObjectNotFoundException;
import bg.softuni.mobilelele.model.dto.offer.AddOfferInputModel;
import bg.softuni.mobilelele.model.dto.offer.SearchOfferDTO;
import bg.softuni.mobilelele.services.brand.IBrandService;
import bg.softuni.mobilelele.services.offer.IOfferService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class OffersController {
    private final IOfferService offerService;
    private final IBrandService brandService;

    public OffersController(IOfferService offerService, IBrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public String allOffers(Model model, @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {

        model.addAttribute("offers", this.offerService.getAllOffers(pageable));

        return "offers";
    }

    @GetMapping("/add")
    public String add(Model model) {
        if (!model.containsAttribute("addOfferModel")) {
             model.addAttribute("addOfferModel", new AddOfferInputModel());
        }

        model.addAttribute("brands", this.brandService.getAllBrands());

        return "offer-add";
    }

    @PostMapping("/add")
    public String add(
            @Valid AddOfferInputModel addOfferModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal UserDetails userDetails) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferModel", addOfferModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.addOfferModel", bindingResult);

            return "redirect:/offers/add";
        }

        this.offerService.createOffer(addOfferModel, userDetails);

        return "redirect:/offers/all";
    }

    @GetMapping("/{id}")
    public String getOfferDetails(@PathVariable("id") UUID id, Model model) {
        var offer = this.offerService.findOfferById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Offer with id " + id + " not found"));

        model.addAttribute("offer", offer);

        return "details";
    }

    @PreAuthorize("@offerService.isOwner(#principal.name, #id)")
    @DeleteMapping("/{id}")
    public String deleteOffer(Principal principal, @PathVariable("id") UUID id) {
        this.offerService.deleteOfferById(id);

        return "redirect:/offers/all";
    }

    @GetMapping("/search")
    public String search(@Valid SearchOfferDTO searchOfferDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("searchOfferModel", searchOfferDTO);
            model.addAttribute("org.springframework.validation.BindingResult.searchOfferModel", bindingResult);

            return "offer-search";
        }

        if (!model.containsAttribute("searchOfferModel")) {
            model.addAttribute("searchOfferModel", searchOfferDTO);
        }

        if (!searchOfferDTO.isEmpty()) {
            model.addAttribute("offers", this.offerService.searchOffer(searchOfferDTO));
        }

        return "offer-search";
    }
}

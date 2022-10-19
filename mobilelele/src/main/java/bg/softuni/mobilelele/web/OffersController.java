package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.dto.offer.AddOfferInputModel;
import bg.softuni.mobilelele.services.brand.IBrandService;
import bg.softuni.mobilelele.services.offer.IOfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
    public String allOffers() {
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
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferModel", addOfferModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.addOfferModel", bindingResult);

            return "redirect:/offers/add";
        }

        this.offerService.createOffer(addOfferModel);

        return "redirect";
    }
}

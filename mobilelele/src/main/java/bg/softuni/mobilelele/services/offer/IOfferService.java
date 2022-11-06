package bg.softuni.mobilelele.services.offer;

import bg.softuni.mobilelele.model.dto.offer.AddOfferInputModel;
import bg.softuni.mobilelele.model.dto.offer.EditOfferDTO;
import bg.softuni.mobilelele.model.dto.offer.OfferDetailsDTO;
import bg.softuni.mobilelele.model.dto.offer.SearchOfferDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IOfferService {
    void createOffer(AddOfferInputModel offerModel, UserDetails userDetails);

    Page<OfferDetailsDTO> getAllOffers(Pageable pageable);

    List<OfferDetailsDTO> searchOffer(SearchOfferDTO searchOfferDTO);

    Optional<OfferDetailsDTO> findOfferById(UUID offerId);

    void deleteOfferById(UUID offerId);

    boolean isOwner(String userName, UUID offerId);

    Optional<EditOfferDTO> getEditOfferDetails(UUID offerId);
}

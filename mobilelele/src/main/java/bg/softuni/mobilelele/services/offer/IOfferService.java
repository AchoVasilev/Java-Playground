package bg.softuni.mobilelele.services.offer;

import bg.softuni.mobilelele.model.dto.offer.AddOfferInputModel;
import org.springframework.security.core.userdetails.UserDetails;

public interface IOfferService {
    void createOffer(AddOfferInputModel offerModel, UserDetails userDetails);
}

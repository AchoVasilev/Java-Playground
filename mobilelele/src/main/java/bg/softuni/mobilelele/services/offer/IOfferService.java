package bg.softuni.mobilelele.services.offer;

import bg.softuni.mobilelele.model.dto.offer.AddOfferInputModel;

public interface IOfferService {
    void createOffer(AddOfferInputModel offerModel);
}

package bg.softuni.mobilelele.services.offer;

import bg.softuni.mobilelele.model.dto.offer.AddOfferInputModel;
import bg.softuni.mobilelele.services.brand.IBrandService;
import org.springframework.stereotype.Service;

@Service
public class OfferService implements IOfferService {
    private IBrandService brandService;

    public OfferService(IBrandService brandService) {
        this.brandService = brandService;
    }

    public void createOffer(AddOfferInputModel offerModel) {

    }
}

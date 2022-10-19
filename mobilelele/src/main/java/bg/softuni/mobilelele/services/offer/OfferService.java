package bg.softuni.mobilelele.services.offer;

import bg.softuni.mobilelele.model.dto.offer.AddOfferInputModel;
import bg.softuni.mobilelele.model.mapper.OfferMapper;
import bg.softuni.mobilelele.repositories.IModelRepository;
import bg.softuni.mobilelele.repositories.IOfferRepository;
import bg.softuni.mobilelele.repositories.IUserRepository;
import bg.softuni.mobilelele.user.CurrentUser;
import org.springframework.stereotype.Service;

@Service
public class OfferService implements IOfferService {

    private final IOfferRepository offerRepository;
    private final IUserRepository userRepository;
    private final IModelRepository modelRepository;
    private final CurrentUser currentUser;
    private final OfferMapper offerMapper;

    public OfferService(IOfferRepository offerRepository,
                        IUserRepository userRepository,
                        IModelRepository modelRepository,
                        CurrentUser currentUser,
                        OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.currentUser = currentUser;
        this.offerMapper = offerMapper;
    }

    public void createOffer(AddOfferInputModel offerModel) {
        var offer = this.offerMapper
                .addOfferInputModelToOfferEntity(offerModel);

        var user = this.userRepository
                .findByEmail(this.currentUser.getEmail())
                .orElseThrow();

        var model = this.modelRepository
                .findById(offerModel.getModelId())
                .orElseThrow();

        offer.setModel(model)
                .setSeller(user);

        this.offerRepository.save(offer);
    }
}

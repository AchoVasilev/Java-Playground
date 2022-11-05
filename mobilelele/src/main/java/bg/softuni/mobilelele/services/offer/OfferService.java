package bg.softuni.mobilelele.services.offer;

import bg.softuni.mobilelele.model.dto.offer.AddOfferInputModel;
import bg.softuni.mobilelele.model.dto.offer.OfferDetailsDTO;
import bg.softuni.mobilelele.model.dto.offer.SearchOfferDTO;
import bg.softuni.mobilelele.model.mapper.OfferMapper;
import bg.softuni.mobilelele.repositories.IModelRepository;
import bg.softuni.mobilelele.repositories.IOfferRepository;
import bg.softuni.mobilelele.repositories.IUserRepository;
import bg.softuni.mobilelele.repositories.OfferSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService implements IOfferService {

    private final IOfferRepository offerRepository;
    private final IUserRepository userRepository;
    private final IModelRepository modelRepository;
    private final OfferMapper offerMapper;

    public OfferService(IOfferRepository offerRepository,
                        IUserRepository userRepository,
                        IModelRepository modelRepository,
                        OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.offerMapper = offerMapper;
    }

    public void createOffer(AddOfferInputModel offerModel, UserDetails userDetails) {
        var offer = this.offerMapper
                .addOfferInputModelToOfferEntity(offerModel);

        var user = this.userRepository
                .findByEmail(userDetails.getUsername())
                .orElseThrow();

        var model = this.modelRepository
                .findById(offerModel.getModelId())
                .orElseThrow();

        offer.setModel(model)
                .setSeller(user);

        this.offerRepository.save(offer);
    }

    public Page<OfferDetailsDTO> getAllOffers(Pageable pageable) {
        return this.offerRepository.findAll(pageable)
                .map(this.offerMapper::offerEntityToOfferDetailsDTO);
    }

    public List<OfferDetailsDTO> searchOffer(SearchOfferDTO searchOfferDTO) {
        return this.offerRepository.findAll(new OfferSpecification(searchOfferDTO))
                .stream()
                .map(offer -> offerMapper.offerEntityToOfferDetailsDTO(offer))
                .collect(Collectors.toList());
    }
}

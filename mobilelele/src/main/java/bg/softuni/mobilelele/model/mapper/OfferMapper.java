package bg.softuni.mobilelele.model.mapper;

import bg.softuni.mobilelele.model.dto.offer.AddOfferInputModel;
import bg.softuni.mobilelele.model.dto.offer.OfferDetailsDTO;
import bg.softuni.mobilelele.model.entity.OfferEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    OfferEntity addOfferInputModelToOfferEntity(AddOfferInputModel addOfferInputModel);

    OfferDetailsDTO offerEntityToOfferDetailsDTO(OfferEntity offerEntity);
}

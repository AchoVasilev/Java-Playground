package bg.softuni.mobilelele.repositories;

import bg.softuni.mobilelele.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IOfferRepository extends JpaRepository<OfferEntity, UUID>, JpaSpecificationExecutor<OfferEntity> {

}

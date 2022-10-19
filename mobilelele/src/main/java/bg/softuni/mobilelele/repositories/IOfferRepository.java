package bg.softuni.mobilelele.repositories;

import bg.softuni.mobilelele.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOfferRepository extends JpaRepository<OfferEntity, Long> {

}

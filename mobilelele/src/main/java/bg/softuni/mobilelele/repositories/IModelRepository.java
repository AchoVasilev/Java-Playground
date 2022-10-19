package bg.softuni.mobilelele.repositories;

import bg.softuni.mobilelele.model.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IModelRepository extends JpaRepository<ModelEntity, Long> {

}

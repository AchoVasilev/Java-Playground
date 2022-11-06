package bg.softuni.mobilelele.repositories;

import bg.softuni.mobilelele.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
}

package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.dto.user.MobileleUserDetails;
import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.entity.UserRoleEntity;
import bg.softuni.mobilelele.model.enums.UserRoleEnum;
import bg.softuni.mobilelele.repositories.IUserRepository;
import bg.softuni.mobilelele.services.user.MobileleUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MobileleUserDetailsServiceTest {
    @Mock
    private IUserRepository userRepository;

    private MobileleUserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        this.userDetailsService = new MobileleUserDetailsService(this.userRepository);
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        var userEntity = new UserEntity()
                .setEmail("email@example.com")
                .setPassword("12345")
                .setFirstName("Gosho")
                .setLastName("Peshev")
                .setActive(true)
                .setImageUrl("imgUrl")
                .setUserRoles(
                        List.of(
                                new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN),
                                new UserRoleEntity().setUserRole(UserRoleEnum.USER)
                        )
                );

        when(this.userRepository.findByEmail(userEntity.getEmail()))
                .thenReturn(Optional.of(userEntity));

        var userDetails = (MobileleUserDetails) this.userDetailsService.loadUserByUsername(userEntity.getEmail());

        Assertions.assertEquals(userEntity.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(userEntity.getFirstName(), userDetails.getFirstName());
        Assertions.assertEquals(userEntity.getLastName(), userDetails.getLastName());
        Assertions.assertEquals(userEntity.getPassword(), userDetails.getPassword());

        Assertions.assertEquals(2, userDetails.getAuthorities().size());
    }

    @Test
    void testLoadUserByUsername_UserDoesNotExists() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> this.userDetailsService.loadUserByUsername("non-existant@example.com")
        );
    }
}

package bg.softuni.mobilelele.services.user;

import bg.softuni.mobilelele.model.dto.user.UserLoginInputModel;
import bg.softuni.mobilelele.model.dto.user.UserRegisterInputModel;
import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.mapper.UserMapper;
import bg.softuni.mobilelele.repositories.IUserRepository;
import bg.softuni.mobilelele.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {
    private final Logger logger = LoggerFactory.getLogger(IUserService.class);
    private final IUserRepository userRepository;
    private final CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(IUserRepository userRepository, CurrentUser currentUser, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public void registerAndLogin(UserRegisterInputModel model) {
        var user = this.userMapper.userDtoToUserEntity(model);
        user.setPassword(this.passwordEncoder.encode(model.getPassword()));

        user = this.userRepository.save(user);

        this.login(user);
    }

    public boolean login(UserLoginInputModel model) {
        var user = this.userRepository.findByEmail(model.getUsername());
        if (user.isEmpty()) {
            this.logger.debug("User with name [{}] not found.", model.getUsername());

            return false;
        }

        var rawPassword = model.getPassword();
        var hashedPassword = user.get().getPassword();

        var success = this.passwordEncoder.matches(rawPassword, hashedPassword);

        if (success) {
            this.login(user.get());
        } else {
            this.logout();
        }

        return success;
    }

    public void logout() {
        this.currentUser.clear();
    }

    private void login(UserEntity entity) {
        currentUser.setLoggedIn(true)
                .setName(entity.getFirstName() + " " + entity.getLastName());
    }
}

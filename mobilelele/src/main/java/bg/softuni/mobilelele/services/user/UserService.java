package bg.softuni.mobilelele.services.user;

import bg.softuni.mobilelele.model.dto.user.UserRegisterInputModel;
import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.mapper.UserMapper;
import bg.softuni.mobilelele.repositories.IUserRepository;
import bg.softuni.mobilelele.services.mail.IEmailService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;


@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserDetailsService userDetailsService;
    private final IEmailService emailService;

    public UserService(IUserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserMapper userMapper,
                       UserDetailsService userDetailsService,
                       IEmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userDetailsService = userDetailsService;
        this.emailService = emailService;
    }

    public void createUserIfNotExist(String email) {
        var userOpt = this.userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            var newUser = new UserEntity()
                    .setEmail(email)
                    .setPassword(null)
                    .setFirstName("New")
                    .setLastName("User")
                    .setUserRoles(List.of());

            this.userRepository.save(newUser);
        }
    }

    public void registerAndLogin(UserRegisterInputModel model, Locale preferredLocale) {
        var user = this.userMapper.userDtoToUserEntity(model);
        user.setPassword(this.passwordEncoder.encode(model.getPassword()));

        user = this.userRepository.save(user);

        this.login(user.getEmail());

        this.emailService.sendRegistrationEmail(user.getEmail(), user.getFirstName() + " " + user.getLastName(), preferredLocale);
    }

    public void login(String userEmail) {
       var userDetails = this.userDetailsService.loadUserByUsername(userEmail);

       var auth = new UsernamePasswordAuthenticationToken(
               userDetails,
               userDetails.getPassword(),
               userDetails.getAuthorities()
       );

        SecurityContextHolder.getContext()
                .setAuthentication(auth);
    }
}

package bg.softuni.mobilelele.services.user;

import bg.softuni.mobilelele.model.dto.user.UserRegisterInputModel;
import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.mapper.UserMapper;
import bg.softuni.mobilelele.repositories.IUserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserDetailsService userDetailsService;

    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userDetailsService = userDetailsService;
    }

    public void registerAndLogin(UserRegisterInputModel model) {
        var user = this.userMapper.userDtoToUserEntity(model);
        user.setPassword(this.passwordEncoder.encode(model.getPassword()));

        user = this.userRepository.save(user);

        this.login(user);
    }

    private void login(UserEntity entity) {
       var userDetails = this.userDetailsService.loadUserByUsername(entity.getEmail());

       var auth = new UsernamePasswordAuthenticationToken(
               userDetails,
               userDetails.getPassword(),
               userDetails.getAuthorities()
       );

        SecurityContextHolder.getContext()
                .setAuthentication(auth);
    }
}

package com.example.PathFinder.services.auth;

import com.example.PathFinder.models.User;
import com.example.PathFinder.models.enums.Level;
import com.example.PathFinder.repositories.IUserRepository;
import com.example.PathFinder.viewModels.user.UserProfileViewModel;
import com.example.PathFinder.viewModels.user.UserRegistrationInputModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService{
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(UserRegistrationInputModel userModel) {
        if (!userModel.getPassword().equals(userModel.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match!");
        }

        var userByEmail = this.userRepository.findByEmail(userModel.getEmail());
        if (userByEmail.isPresent()) {
            throw new RuntimeException("Email is already taken!");
        }

        var user = new User();

        user.setEmail(userModel.getEmail())
                .setPassword(this.passwordEncoder.encode(userModel.getPassword()))
                .setUsername(userModel.getUsername())
                .setFullName(userModel.getFullName())
                .setAge(userModel.getAge());

        this.userRepository.save(user);
    }

    public UserProfileViewModel getUserByUserName(String username) {
        return this.userRepository.findByUsername(username)
                .map(u -> new UserProfileViewModel(
                        username,
                        u.getEmail(),
                        u.getFullName(),
                        u.getAge(),
                        u.getLevel() != null ? u.getLevel().name() : Level.BEGINNER.name()
                ))
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found"));
    }
}

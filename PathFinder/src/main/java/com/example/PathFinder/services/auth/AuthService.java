package com.example.PathFinder.services.auth;

import com.example.PathFinder.models.User;
import com.example.PathFinder.repositories.IUserRepository;
import com.example.PathFinder.viewModels.user.UserRegistrationInputModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService{
    private IUserRepository userRepository;

    @Autowired
    public AuthService(IUserRepository userRepository) {
        this.userRepository = userRepository;
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
                .setPassword(userModel.getPassword())
                .setUsername(userModel.getUsername())
                .setFullName(userModel.getFullName())
                .setAge(userModel.getAge());

        this.userRepository.save(user);
    }
}

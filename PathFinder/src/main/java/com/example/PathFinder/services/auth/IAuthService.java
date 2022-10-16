package com.example.PathFinder.services.auth;

import com.example.PathFinder.viewModels.user.UserRegistrationInputModel;

public interface IAuthService {
    void register(UserRegistrationInputModel userModel);
}

package com.example.PathFinder.web;

import com.example.PathFinder.services.auth.IAuthService;
import com.example.PathFinder.viewModels.user.UserRegistrationInputModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class AuthController {
    private final IAuthService authService;

    @Autowired
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("userModel")
    public UserRegistrationInputModel initForm() {
        return new UserRegistrationInputModel();
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @Valid UserRegistrationInputModel userModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.UserRegistrationInputModel", bindingResult);

            return "redirect:/users/register";
        }

        this.authService.register(userModel);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        var userName = principal.getName();

        var user = this.authService.getUserByUserName(userName);

        model.addAttribute("user", user);

        return "profile";
    }
}

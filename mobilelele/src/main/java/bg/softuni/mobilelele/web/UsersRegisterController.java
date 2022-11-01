package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.dto.user.UserRegisterInputModel;
import bg.softuni.mobilelele.services.user.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersRegisterController {
    private final IUserService userService;
    private final LocaleResolver localeResolver;

    public UsersRegisterController(IUserService userService, LocaleResolver localeResolver) {
        this.userService = userService;
        this.localeResolver = localeResolver;
    }

    @ModelAttribute("userModel")
    public UserRegisterInputModel initUserModel() {
        return new UserRegisterInputModel();
    }

    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterInputModel userModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);

            return "redirect:/users/register";
        }

        this.userService.registerAndLogin(userModel, this.localeResolver.resolveLocale(request));

        return "redirect:/";
    }
}

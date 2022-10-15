package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.dto.UserLoginInputModel;
import bg.softuni.mobilelele.dto.UserRegisterInputModel;
import bg.softuni.mobilelele.services.user.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {
	private final IUserService userService;

	public UsersController(IUserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login")
	public String login() {
		return "auth-login";
	}

	@PostMapping("/login")
	public String login(UserLoginInputModel model) {
		this.userService.login(model);

		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout() {
		this.userService.logout();

		return "redirect:/";
	}
}

package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.dto.UserLoginInputModel;
import bg.softuni.mobilelele.services.user.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {
	private final IUserService userService;

	public UserLoginController(IUserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users/login")
	public String login() {
		return "auth-login";
	}

	@PostMapping("users/login")
	public String login(UserLoginInputModel model) {
		this.userService.login(model);

		return "redirect:/";
	}

	@GetMapping("/users/logout")
	public String logout() {
		this.userService.logout();

		return "redirect:/";
	}
}

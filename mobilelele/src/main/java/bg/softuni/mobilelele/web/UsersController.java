package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.dto.user.UserLoginInputModel;
import bg.softuni.mobilelele.services.user.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

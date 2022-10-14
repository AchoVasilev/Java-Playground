package bg.softuni.mobilelele.services.user;

import bg.softuni.mobilelele.dto.UserLoginInputModel;
import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.repositories.IUserRepository;
import bg.softuni.mobilelele.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {
	private Logger logger = LoggerFactory.getLogger(IUserService.class);
	private IUserRepository userRepository;
	private CurrentUser currentUser;

	public UserService(IUserRepository userRepository, CurrentUser currentUser) {
		this.userRepository = userRepository;
		this.currentUser = currentUser;
	}

	public boolean login(UserLoginInputModel model) {
		var user = this.userRepository.findByEmail(model.getUsername());
		if (user.isEmpty()) {
			this.logger.debug("User with name [{}] not found.", model.getUsername());

			return false;
		}

		var success = user.get().getPassword().equals(model.getPassword());

		if (success) {
			this.login(user.get());
		} else {
			this.logout();
		}

		return success;
	}

	public void logout() {
		this.currentUser.clear();
	}

	private void login(UserEntity entity) {
		currentUser.setLoggedIn(true)
				.setName(entity.getFirstName() + " " + entity.getLastName());
	}
}

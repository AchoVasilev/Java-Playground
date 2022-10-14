package bg.softuni.mobilelele.services.user;

import bg.softuni.mobilelele.dto.UserLoginInputModel;
import bg.softuni.mobilelele.repositories.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {
	private Logger logger = LoggerFactory.getLogger(IUserService.class);
	private IUserRepository userRepository;

	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public boolean login(UserLoginInputModel model) {
		var user = this.userRepository.findByEmail(model.getUsername());
		if (user.isEmpty()) {
			this.logger.debug("User with name [{}] not found.", model.getUsername());

			return false;
		}

		return user.get().getPassword().equals(model.getPassword());
	}
}

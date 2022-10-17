package bg.softuni.mobilelele.services.user;

import bg.softuni.mobilelele.model.dto.user.UserLoginInputModel;
import bg.softuni.mobilelele.model.dto.user.UserRegisterInputModel;

public interface IUserService {
	boolean login(UserLoginInputModel model);

	void registerAndLogin(UserRegisterInputModel model);

	void logout();
}

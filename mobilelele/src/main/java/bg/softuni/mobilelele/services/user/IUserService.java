package bg.softuni.mobilelele.services.user;

import bg.softuni.mobilelele.dto.UserLoginInputModel;
import bg.softuni.mobilelele.dto.UserRegisterInputModel;

public interface IUserService {
	boolean login(UserLoginInputModel model);

	void registerAndLogin(UserRegisterInputModel model);

	void logout();
}

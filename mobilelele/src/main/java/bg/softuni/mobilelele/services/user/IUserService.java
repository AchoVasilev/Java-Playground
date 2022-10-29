package bg.softuni.mobilelele.services.user;

import bg.softuni.mobilelele.model.dto.user.UserRegisterInputModel;

public interface IUserService {

	void registerAndLogin(UserRegisterInputModel model);
}

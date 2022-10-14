package bg.softuni.mobilelele.services.user;

import bg.softuni.mobilelele.dto.UserLoginInputModel;

public interface IUserService {
	boolean login(UserLoginInputModel model);
}

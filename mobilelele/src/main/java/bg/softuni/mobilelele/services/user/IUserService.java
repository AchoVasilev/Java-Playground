package bg.softuni.mobilelele.services.user;

import bg.softuni.mobilelele.model.dto.user.UserRegisterInputModel;

import java.util.Locale;

public interface IUserService {

	void registerAndLogin(UserRegisterInputModel model, Locale preferredLocale);
}

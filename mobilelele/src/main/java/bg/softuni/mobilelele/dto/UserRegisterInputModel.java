package bg.softuni.mobilelele.dto;

public class UserRegisterInputModel {
	public String email;

	private String firstName;

	private String lastName;

	private String password;

	private String confirmPassword;

	public String getEmail() {
		return email;
	}

	public UserRegisterInputModel setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public UserRegisterInputModel setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public UserRegisterInputModel setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserRegisterInputModel setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public UserRegisterInputModel setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
		return this;
	}
}

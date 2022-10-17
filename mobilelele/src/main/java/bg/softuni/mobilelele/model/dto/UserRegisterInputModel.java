package bg.softuni.mobilelele.model.dto;

import bg.softuni.mobilelele.model.validation.UniqueUserEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegisterInputModel {
	@NotEmpty(message = "User email should be provided")
	@Email(message = "User email should be valid")
	@UniqueUserEmail(message = "User email should be unique.")
	public String email;

	@NotEmpty
	@Size(min = 2, max = 20)
	private String firstName;

	@NotEmpty
	@Size(min = 2, max = 20)
	private String lastName;

	@NotEmpty
	@Size(min = 5)
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

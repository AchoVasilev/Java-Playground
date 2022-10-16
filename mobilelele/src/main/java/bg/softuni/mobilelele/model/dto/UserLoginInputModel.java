package bg.softuni.mobilelele.model.dto;

public class UserLoginInputModel {
	private String username;

	private String password;

	public String getUsername() {
		return username;
	}

	public UserLoginInputModel setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserLoginInputModel setPassword(String password) {
		this.password = password;
		return this;
	}

	@Override
	public String toString() {
		return "UserLoginInputModel{" +
				"username='" + username + '\'' +
				", password='" + (password != null ? "[PROVIDED]" : null) + '\'' +
				'}';
	}
}

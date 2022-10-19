package bg.softuni.mobilelele.user;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {
	private String name;

	private String email;

	private boolean loggedIn;

	public String getName() {
		return name;
	}

	public CurrentUser setName(String name) {
		this.name = name;
		return this;
	}

	public boolean isAnonymous() {
		return !this.isLoggedIn();
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public CurrentUser setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
		return this;
	}

	public void clear() {
		email = null;
		loggedIn = false;
		name = null;
	}

	public String getEmail() {
		return email;
	}

	public CurrentUser setEmail(String email) {
		this.email = email;
		return this;
	}
}

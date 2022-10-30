package bg.softuni.mobilelele.config;

import bg.softuni.mobilelele.repositories.IUserRepository;
import bg.softuni.mobilelele.services.user.MobileleUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new Pbkdf2PasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.antMatchers("/", "/users/login", "/users/register").permitAll()
				.antMatchers("/offers/all").permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage("/users/login")
				.usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
				.passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
				.defaultSuccessUrl("/")
				.failureForwardUrl("/users/login-error")
				.and()
				.logout()
				.logoutUrl("/users/logout")
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID");

		return httpSecurity.build();
	}

	@Bean
	public UserDetailsService userDetailsService(IUserRepository userRepository) {
		return new MobileleUserDetailsService(userRepository);
	}
}

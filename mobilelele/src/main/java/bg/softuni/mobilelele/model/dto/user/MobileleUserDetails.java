package bg.softuni.mobilelele.model.dto.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MobileleUserDetails implements UserDetails {

    private final String password;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final Collection<GrantedAuthority> authorities;

    public MobileleUserDetails(
            String password,
            String username,
            String firstName,
            String lastName,
            Collection<GrantedAuthority> authorities) {

        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorities = authorities;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFullName() {
        var fullNameSb = new StringBuilder();
        if (this.getFirstName() != null) {
            fullNameSb.append(this.getFirstName());
        }

        if (this.getLastName() != null) {
            if (!fullNameSb.isEmpty()) {
                fullNameSb.append(" ");
            }

            fullNameSb.append(this.getLastName());
        }

        return fullNameSb.toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

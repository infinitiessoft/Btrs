package security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import entity.UserRole;
import entity.UserShared;

public class UserDetail implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String password;
	private Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(0);

	public UserDetail() {

	}

	public UserDetail(UserShared user) {
		username = user.getUsername();
		password = user.getPassword();
		id = user.getId();
		Set<UserRole> roles = user.getUser().getUserRole();
		for (UserRole userRole : roles) {
			authorities.add(new SimpleGrantedAuthority(userRole.getRole()
					.getValue()));
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	@XmlTransient
	public String getPassword() {
		return password;
	}

	@Override
	@XmlTransient
	public String getUsername() {
		return username;
	}

	@Override
	@XmlTransient
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@XmlTransient
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@XmlTransient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@XmlTransient
	public boolean isEnabled() {
		return true;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "UserDetail [id=" + id + ", username=" + username
				+ ", password=" + password + ", authorities=" + authorities
				+ "]";
	}

}

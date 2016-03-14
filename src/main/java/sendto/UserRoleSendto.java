package sendto;

import java.io.Serializable;
import java.util.Date;

public class UserRoleSendto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static class Role implements Serializable {
		private static final long serialVersionUID = 1L;
		private Long id;

		public Role() {
		}

		public Role(Long id) {
			this.id = id;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

	}

	public static class User implements Serializable {
		private static final long serialVersionUID = 1L;
		private Long id;
		private Date lastLogin;
		private Long userSharedId;

		public User(Long id, Date lastLogin, Long userSharedId) {
			this.id = id;
			this.lastLogin = lastLogin;
			this.userSharedId = userSharedId;
		}

		public User() {
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Date getLastLogin() {
			return lastLogin;
		}

		public void setLastLogin(Date lastLogin) {
			this.lastLogin = lastLogin;
		}

		public Long getUserSharedId() {
			return userSharedId;
		}

		public void setUserSharedId(Long userSharedId) {
			this.userSharedId = userSharedId;
		}

	}

	private Long id;
	private User user;
	private Role role;

	public UserRoleSendto(Long id, User user, Role role) {
		this.id = id;
		this.user = user;
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UserRoleSendto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserRoleSendto(Long id) {
		this.id = id;
	}

}

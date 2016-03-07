package sendto;

import java.io.Serializable;
import java.util.Date;

public class UserRoleSendto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static class Role implements Serializable {
		private static final long serialVersionUID = 1L;
		private Long id;

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
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserRoleSendto(Long id) {
		super();
		this.id = id;
	}

}

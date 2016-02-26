package sendto;

import java.util.Date;

public class UserSendto {

	private Long id;
	private Date lastLogin;
	private Long userSharedId;

	public UserSendto() {
		super();
	}

	public UserSendto(Long id, Date lastLogin, Long userSharedId) {
		this.id = id;
		this.lastLogin = lastLogin;
		this.userSharedId = userSharedId;
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

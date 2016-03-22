package sendto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import entity.UserRole;

public class UserSendto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static class Department implements Serializable {

		private static final long serialVersionUID = 1L;
		private Long id;

		private boolean isIdSet;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			isIdSet = true;
			this.id = id;
		}

		@XmlTransient
		public boolean isIdSet() {
			return isIdSet;
		}

		@XmlTransient
		public void setIdSet(boolean isIdSet) {
			this.isIdSet = isIdSet;
		}
	}

	public static class UserShared implements Serializable {

		public static class Type implements Serializable {
			private static final long serialVersionUID = 1L;
			private Long id;

			public Long getId() {
				return id;
			}

			public void setId(Long id) {

				this.id = id;
			}
		}

		private static final long serialVersionUID = 1L;
		private Long id;

		private boolean isIdSet;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			isIdSet = true;
			this.id = id;
		}

		@XmlTransient
		public boolean isIdSet() {
			return isIdSet;
		}

		@XmlTransient
		public void setIdSet(boolean isIdSet) {
			this.isIdSet = isIdSet;
		}

	}

	private Long id;
	private Date lastLogin;
	private UserShared userShared;
	private Department department;
	private List<UserRole> userRole;

	private boolean isLastLoginSet;
	private boolean isUserSharedSet;
	private boolean isDepartmentSet;

	public UserSendto() {
		super();
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
		isLastLoginSet = true;
		this.lastLogin = lastLogin;
	}

	public UserShared getUserShared() {
		return userShared;
	}

	public void setUserShared(UserShared userShared) {
		this.userShared = userShared;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@XmlTransient
	public List<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}

	@XmlTransient
	public boolean isLastLoginSet() {
		return isLastLoginSet;
	}

	@XmlTransient
	public void setLastLoginSet(boolean isLastLoginSet) {
		this.isLastLoginSet = isLastLoginSet;
	}

	@XmlTransient
	public boolean isUserSharedSet() {
		return isUserSharedSet;
	}

	@XmlTransient
	public void setUserSharedSet(boolean isUserSharedSet) {
		if (userShared != null) {
			isUserSharedSet = true;
		}
		this.isUserSharedSet = isUserSharedSet;
	}

	@XmlTransient
	public boolean isDepartmentSet() {
		return isDepartmentSet;
	}

	@XmlTransient
	public void setDepartmentSet(boolean isDepartmentSet) {
		if (department != null) {
			isDepartmentSet = true;
		}
		this.isDepartmentSet = isDepartmentSet;
	}

}

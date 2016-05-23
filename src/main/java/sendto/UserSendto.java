package sendto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import entity.StatusChanges;
import entity.UserRole;

public class UserSendto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static class Report implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long id;

		private boolean isIdSet;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			if (id != null) {
				isIdSet = true;
			}
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

	public static class Department implements Serializable {

		private static final long serialVersionUID = 1L;
		private Long id;

		private boolean isIdSet;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			if (id != null) {
				isIdSet = true;
			}
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
			if (id != null) {
				isIdSet = true;
			}
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
	private List<UserRole> userRole = new ArrayList<UserRole>(0);
	private List<Report> incomingReport;// = new ArrayList<Report>(0);
	private List<Report> outgoingReport = new ArrayList<Report>(0);
	private List<StatusChanges> status = new ArrayList<StatusChanges>(0);

	private boolean isLastLoginSet;
	private boolean isUserSharedSet;
	private boolean isDepartmentSet;
	private boolean isUserRoleSet;
	private boolean isIncomingReportSet;
	private boolean isOutgoingReportSet;
	private boolean isStatusSet;

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
		if (lastLogin != null) {
			isLastLoginSet = true;
		}
		this.lastLogin = lastLogin;
	}

	public UserShared getUserShared() {
		return userShared;
	}

	public void setUserShared(UserShared userShared) {
		if (userShared != null) {
			isUserSharedSet = true;
		}
		this.userShared = userShared;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		if (department != null) {
			isDepartmentSet = true;
		}
		this.department = department;
	}

	@XmlTransient
	public List<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRole> userRole) {
		if (userRole != null) {
			isUserRoleSet = true;
		}
		this.userRole = userRole;
	}

	@XmlTransient
	public boolean isUserRoleSet() {
		return isUserRoleSet;
	}

	@XmlTransient
	public void setUserRoleSet(boolean isUserRoleSet) {
		this.isUserRoleSet = isUserRoleSet;
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
		this.isUserSharedSet = isUserSharedSet;
	}

	@XmlTransient
	public boolean isDepartmentSet() {
		return isDepartmentSet;
	}

	@XmlTransient
	public void setDepartmentSet(boolean isDepartmentSet) {
		this.isDepartmentSet = isDepartmentSet;

	}

	@XmlTransient
	public List<Report> getIncomingReport() {
		return incomingReport;
	}

	public void setIncomingReport(List<Report> incomingReport) {
		if (incomingReport != null) {
			isIncomingReportSet = true;
		}
		this.incomingReport = incomingReport;
	}

	@XmlTransient
	public List<Report> getOutgoingReport() {
		return outgoingReport;
	}

	public void setOutgoingReport(List<Report> outgoingReport) {
		if (outgoingReport != null) {
			isOutgoingReportSet = true;
		}
		this.outgoingReport = outgoingReport;
	}

	@XmlTransient
	public boolean isIncomingReportSet() {
		return isIncomingReportSet;
	}

	@XmlTransient
	public void setIncomingReportSet(boolean isIncomingReportSet) {
		this.isIncomingReportSet = isIncomingReportSet;
	}

	@XmlTransient
	public boolean isOutgoingReportSet() {
		return isOutgoingReportSet;
	}

	@XmlTransient
	public void setOutgoingReportSet(boolean isOutgoingReportSet) {
		this.isOutgoingReportSet = isOutgoingReportSet;
	}

	@XmlTransient
	public List<StatusChanges> getStatus() {
		return status;
	}

	public void setStatus(List<StatusChanges> status) {
		if (status != null) {
			isStatusSet = true;
		}
		this.status = status;
	}

	@XmlTransient
	public boolean isStatusSet() {
		return isStatusSet;
	}

	@XmlTransient
	public void setStatusSet(boolean isStatusSet) {
		this.isStatusSet = isStatusSet;
	}

}

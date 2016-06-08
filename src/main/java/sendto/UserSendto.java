package sendto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

import com.google.common.base.Strings;

public class UserSendto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static class Department implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Long id;
		private String name;

		private boolean isIdSet;

		public Department() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			setIdSet(true);
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Long id;
		private String username;
		private String password;
		private String jobTitle;
		private String email;
		private String gender;
		private String firstName;
		private String lastName;
		private Date dateOfBirth;
		private Date createdDate;
		private Boolean enabled;

		private boolean isUsernameSet;
		private boolean isPasswordSet;
		private boolean isJobTitleSet;
		private boolean isEmailSet;
		private boolean isGenderSet;
		private boolean isFirstNameSet;
		private boolean isLastNameSet;
		private boolean isDateOfBirthSet;
		private boolean isCreatedDateSet;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			if (!Strings.isNullOrEmpty(username)) {
				isUsernameSet = true;
			}
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			if (!Strings.isNullOrEmpty(password)) {
				isPasswordSet = true;
			}
			this.password = password;
		}

		public String getJobTitle() {
			return jobTitle;
		}

		public void setJobTitle(String jobTitle) {
			if (!Strings.isNullOrEmpty(jobTitle)) {
				isJobTitleSet = true;
			}
			this.jobTitle = jobTitle;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			if (!Strings.isNullOrEmpty(email)) {
				isEmailSet = true;
			}
			this.email = email;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			if (!Strings.isNullOrEmpty(gender)) {
				isGenderSet = true;

			}
			this.gender = gender;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			if (!Strings.isNullOrEmpty(firstName)) {
				isFirstNameSet = true;
			}
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			if (!Strings.isNullOrEmpty(lastName)) {
				isLastNameSet = true;
			}
			this.lastName = lastName;
		}

		public Date getDateOfBirth() {
			return dateOfBirth;
		}

		public void setDateOfBirth(Date dateOfBirth) {
			if (dateOfBirth != null) {
				isDateOfBirthSet = true;
			}
			this.dateOfBirth = dateOfBirth;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			if (createdDate != null) {
				isCreatedDateSet = true;
			}
			this.createdDate = createdDate;
		}

		public Boolean getEnabled() {
			return enabled;
		}

		public void setEnabled(Boolean enabled) {
			this.enabled = enabled;
		}

		@XmlTransient
		public boolean isUsernameSet() {
			return isUsernameSet;
		}

		@XmlTransient
		public void setUsernameSet(boolean isUsernameSet) {

			this.isUsernameSet = isUsernameSet;
		}

		@XmlTransient
		public boolean isPasswordSet() {
			return isPasswordSet;
		}

		@XmlTransient
		public void setPasswordSet(boolean isPasswordSet) {
			this.isPasswordSet = isPasswordSet;
		}

		@XmlTransient
		public boolean isJobTitleSet() {
			return isJobTitleSet;
		}

		@XmlTransient
		public void setJobTitleSet(boolean isJobTitleSet) {
			this.isJobTitleSet = isJobTitleSet;
		}

		@XmlTransient
		public boolean isEmailSet() {
			return isEmailSet;
		}

		@XmlTransient
		public void setEmailSet(boolean isEmailSet) {
			this.isEmailSet = isEmailSet;
		}

		@XmlTransient
		public boolean isGenderSet() {
			return isGenderSet;
		}

		@XmlTransient
		public void setGenderSet(boolean isGenderSet) {
			this.isGenderSet = isGenderSet;
		}

		@XmlTransient
		public boolean isFirstNameSet() {
			return isFirstNameSet;
		}

		@XmlTransient
		public void setFirstNameSet(boolean isFirstNameSet) {
			this.isFirstNameSet = isFirstNameSet;
		}

		@XmlTransient
		public boolean isLastNameSet() {
			return isLastNameSet;
		}

		@XmlTransient
		public void setLastNameSet(boolean isLastNameSet) {
			this.isLastNameSet = isLastNameSet;
		}

		@XmlTransient
		public boolean isDateOfBirthSet() {
			return isDateOfBirthSet;
		}

		@XmlTransient
		public void setDateOfBirthSet(boolean isDateOfBirthSet) {
			this.isDateOfBirthSet = isDateOfBirthSet;
		}

		@XmlTransient
		public boolean isCreatedDateSet() {
			return isCreatedDateSet;
		}

		@XmlTransient
		public void setCreatedDateSet(boolean isCreatedDateSet) {
			this.isCreatedDateSet = isCreatedDateSet;

		}

	}

	private Long id;
	private UserShared userShared;
	private Department department;
	// private List<UserRole> userRole = new ArrayList<UserRole>(0);
	// private List<Report> incomingReport;// = new ArrayList<Report>(0);
	// private List<Report> outgoingReport = new ArrayList<Report>(0);
	// private List<StatusChanges> status = new ArrayList<StatusChanges>(0);

	private boolean isUserSharedSet;
	private boolean isDepartmentSet;

	// private boolean isUserRoleSet;

	// private boolean isIncomingReportSet;
	// private boolean isOutgoingReportSet;
	// private boolean isStatusSet;

	public UserSendto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	// @XmlTransient
	// public List<UserRole> getUserRole() {
	// return userRole;
	// }
	//
	// public void setUserRole(List<UserRole> userRole) {
	// if (userRole != null) {
	// isUserRoleSet = true;
	// }
	// this.userRole = userRole;
	// }
	//
	// @XmlTransient
	// public boolean isUserRoleSet() {
	// return isUserRoleSet;
	// }
	//
	// @XmlTransient
	// public void setUserRoleSet(boolean isUserRoleSet) {
	// this.isUserRoleSet = isUserRoleSet;
	// }

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

	// @XmlTransient
	// public List<Report> getIncomingReport() {
	// return incomingReport;
	// }
	//
	// public void setIncomingReport(List<Report> incomingReport) {
	// if (incomingReport != null) {
	// isIncomingReportSet = true;
	// }
	// this.incomingReport = incomingReport;
	// }
	//
	// @XmlTransient
	// public List<Report> getOutgoingReport() {
	// return outgoingReport;
	// }
	//
	// public void setOutgoingReport(List<Report> outgoingReport) {
	// if (outgoingReport != null) {
	// isOutgoingReportSet = true;
	// }
	// this.outgoingReport = outgoingReport;
	// }

	// @XmlTransient
	// public boolean isIncomingReportSet() {
	// return isIncomingReportSet;
	// }
	//
	// @XmlTransient
	// public void setIncomingReportSet(boolean isIncomingReportSet) {
	// this.isIncomingReportSet = isIncomingReportSet;
	// }
	//
	// @XmlTransient
	// public boolean isOutgoingReportSet() {
	// return isOutgoingReportSet;
	// }
	//
	// @XmlTransient
	// public void setOutgoingReportSet(boolean isOutgoingReportSet) {
	// this.isOutgoingReportSet = isOutgoingReportSet;
	// }

	// @XmlTransient
	// public List<StatusChanges> getStatus() {
	// return status;
	// }
	//
	// public void setStatus(List<StatusChanges> status) {
	// if (status != null) {
	// isStatusSet = true;
	// }
	// this.status = status;
	// }
	//
	// @XmlTransient
	// public boolean isStatusSet() {
	// return isStatusSet;
	// }
	//
	// @XmlTransient
	// public void setStatusSet(boolean isStatusSet) {
	// this.isStatusSet = isStatusSet;
	// }

}

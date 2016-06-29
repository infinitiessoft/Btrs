package sendto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

public class UserSendto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static class JobTitle implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Long id;
		private String name;

		private boolean isIdSet;

		public JobTitle() {
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

	public static class Role {

		private Long id;
		private String value;

		private boolean isIdSet;

		public Role() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			setIdSet(true);
			this.id = id;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
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
	private JobTitle jobTitle;

	private String username;
	private String email;
	private String gender;
	private String name;
	private Date dateofjoined;
	private List<Role> roles = new ArrayList<Role>(0);

	private boolean isJobTitleSet;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateofjoined() {
		return dateofjoined;
	}

	public void setDateofjoined(Date dateofjoined) {
		this.dateofjoined = dateofjoined;
	}

	public UserSendto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JobTitle getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(JobTitle jobTitle) {
		if (jobTitle != null) {
			isJobTitleSet = true;
		}
		this.jobTitle = jobTitle;
	}

	@XmlTransient
	public boolean isJobTitleSet() {
		return isJobTitleSet;
	}

	@XmlTransient
	public void setJobTitleSet(boolean isJobTitleSet) {
		this.isJobTitleSet = isJobTitleSet;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}

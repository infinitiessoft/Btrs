package sendto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

import com.google.common.base.Strings;

public class UserSharedSendto implements Serializable {

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

	public UserSharedSendto() {
		super();
	}

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

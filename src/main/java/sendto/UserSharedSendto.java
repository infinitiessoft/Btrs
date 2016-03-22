package sendto;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import enumpackage.GenderEnum;

public class UserSharedSendto {

	private Long id;
	private String username;
	private String password;
	private String jobTitle;
	private String email;
	private GenderEnum gender;
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
		isUsernameSet = true;
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
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

	@XmlElement(name = "password")
	public void setPasswordSet(boolean isPasswordSet) {
		isPasswordSet = true;
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

package attendance.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "employee")
public class Employee extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1154129853752708026L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "responseto", nullable = true)
	private Employee employee;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;

	@Column(name = "name", nullable = false, length = 20)
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name = "dateofjoined", nullable = false, length = 13)
	private Date dateofjoined;

	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@Column(name = "username", unique = true, nullable = false, length = 20)
	private String username;

	@Column(name = "email", unique = true, nullable = false, length = 40)
	private String email;

	@Column(name = "gender", nullable = false, length = 6)
	private String gender;

	@Column(name = "comment")
	private String comment;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	private Set<Employee> employees = new HashSet<Employee>(0);
	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeByPermicPersonId")
	// private Set<AttendRecord> attendRecordsForPermicPersonId = new
	// HashSet<AttendRecord>(
	// 0);
	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeByEmployeeId")
	// private Set<AttendRecord> attendRecordsForEmployeeId = new
	// HashSet<AttendRecord>(
	// 0);

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "employee", cascade = CascadeType.REMOVE)
	private Set<EmployeeRole> employeeRoles = new HashSet<EmployeeRole>(0);

	@Column(name = "lastlogin")
	private Date lastLogin;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.REMOVE)
	private Set<AttendRecord> attendRecords = new HashSet<AttendRecord>(0);

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	@XmlTransient
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlTransient
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@XmlTransient
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@XmlTransient
	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@XmlTransient
	public Set<EmployeeRole> getEmployeeRoles() {
		return employeeRoles;
	}

	public void setEmployeeRoles(Set<EmployeeRole> employeeRoles) {
		this.employeeRoles = employeeRoles;
	}

	@XmlTransient
	public Set<AttendRecord> getAttendRecords() {
		return attendRecords;
	}

	public void setAttendRecords(Set<AttendRecord> attendRecords) {
		this.attendRecords = attendRecords;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", username="
				+ username + ", email=" + email + "]";
	}

}

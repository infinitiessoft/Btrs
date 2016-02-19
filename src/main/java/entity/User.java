package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 7526471155622776147L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_Login", nullable = false)
	private Date lastLogin;

	@Column(name = "userSharedId", nullable = false)
	private Long userSharedId;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserRole> userRole;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<StatusChanges> statusChanges = new HashSet<StatusChanges>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL)
	private Set<Report> outgoingReports = new HashSet<Report>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewer", cascade = CascadeType.ALL)
	private Set<Report> incomingReports = new HashSet<Report>(0);

	public User() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", department=" + department + ", lastLogin=" + lastLogin + ", userSharedId="
				+ userSharedId + ", userRole=" + userRole + ", statusChanges=" + statusChanges + ", outgoingReports="
				+ outgoingReports + ", incomingReports=" + incomingReports + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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

	public List<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}

	public Set<StatusChanges> getStatusChanges() {
		return statusChanges;
	}

	public void setStatusChanges(Set<StatusChanges> statusChanges) {
		this.statusChanges = statusChanges;
	}

	public Set<Report> getOutgoingReports() {
		return outgoingReports;
	}

	public void setOutgoingReports(Set<Report> outgoingReports) {
		this.outgoingReports = outgoingReports;
	}

	public Set<Report> getIncomingReports() {
		return incomingReports;
	}

	public void setIncomingReports(Set<Report> incomingReports) {
		this.incomingReports = incomingReports;
	}

}

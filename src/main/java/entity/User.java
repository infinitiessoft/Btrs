package entity;

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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "user_shared_id"))
public class User extends AbstractEntity {
	private static final long serialVersionUID = 7711505597348200997L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jobtitle_id")
	private JobTitle jobTitle;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_Login")
	private Date lastLogin;

	@Column(name = "user_shared_id", nullable = false, unique = true)
	private Long userSharedId;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.REMOVE)
	private Set<UserRole> userRole = new HashSet<UserRole>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
	private Set<StatusChange> statusChanges = new HashSet<StatusChange>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.REMOVE)
	private Set<Report> outgoingReports = new HashSet<Report>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewer", cascade = CascadeType.REMOVE)
	private Set<Report> incomingReports = new HashSet<Report>(0);

	public User() {
		super();

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

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public Set<StatusChange> getStatusChanges() {
		return statusChanges;
	}

	public void setStatusChanges(Set<StatusChange> statusChanges) {
		this.statusChanges = statusChanges;
	}

	public Set<Report> getOutgoingReports() {
		if (outgoingReports == null) {
			outgoingReports = new HashSet<Report>(0);
		}
		return this.outgoingReports;
	}

	public void setOutgoingReports(Set<Report> outgoingReports) {
		this.outgoingReports = outgoingReports;
	}

	public Set<Report> getIncomingReports() {
		if (incomingReports == null) {
			incomingReports = new HashSet<Report>(0);
		}
		return this.incomingReports;
	}

	public void setIncomingReports(Set<Report> incomingReports) {
		this.incomingReports = incomingReports;
	}

	public JobTitle getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Long getUserSharedId() {
		return userSharedId;
	}

	public void setUserSharedId(Long userSharedId) {
		this.userSharedId = userSharedId;
	}

}

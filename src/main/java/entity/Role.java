package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import enumpackage.RoleEnum;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

	private static final long serialVersionUID = 7526471155622776147L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private RoleEnum value;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "role", cascade = CascadeType.ALL)
	private List<UserRole> userRole = new ArrayList<UserRole>(0);;

	public Role() {

	}

	@Override
	public String toString() {
		return "Role [ value=" + value + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleEnum getValue() {
		return value;
	}

	public void setValue(RoleEnum value) {
		this.value = value;
	}

	public List<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}

}

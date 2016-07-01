package resources.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

import org.springframework.data.jpa.domain.Specification;

import entity.Role;
import entity.RoleEnum;
import entity.User;
import entity.UserRole;

public class UserRoleSpecification implements Specification<UserRole> {

	private Long id;
	@QueryParam("userId")
	private Long userId;
	@QueryParam("roleId")
	private Long roleId;

	private RoleEnum roleName;

	@Override
	public Predicate toPredicate(Root<UserRole> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (userId != null) {
			predicates.add(cb.equal(root.<User> get("user").<Long> get("id"),
					userId));
		}
		if (roleId != null) {
			predicates.add(cb.equal(root.<Role> get("role").<Long> get("id"),
					roleId));
		}
		if (roleName != null) {
			predicates.add(cb.equal(
					root.<Role> get("role").<RoleEnum> get("value"), roleName));
		}
		if (id != null) {
			predicates.add(cb.equal(root.<Long> get("id"), id));
		}
		if (predicates.isEmpty()) {
			return null;
		}
		return cb.and(predicates.toArray(new Predicate[0]));
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public RoleEnum getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleEnum roleName) {
		this.roleName = roleName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

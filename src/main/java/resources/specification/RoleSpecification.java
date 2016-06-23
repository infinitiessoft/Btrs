package resources.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

import org.springframework.data.jpa.domain.Specification;

import entity.Role;
import entity.RoleEnum;

public class RoleSpecification implements Specification<Role> {

	@QueryParam("value")
	private RoleEnum value;

	@Override
	public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (value != null) {
			return cb.equal(root.<RoleEnum> get("value"), value);
		}
		return null;
	}

	public RoleEnum getValue() {
		return value;
	}

	public void setValue(RoleEnum value) {
		this.value = value;
	}

}

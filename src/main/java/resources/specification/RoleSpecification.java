package resources.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

import org.springframework.data.jpa.domain.Specification;

import com.google.common.base.Strings;

import entity.Role;

public class RoleSpecification implements Specification<Role> {

	@QueryParam("value")
	private String value;

	@Override
	public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (!Strings.isNullOrEmpty(value)) {
			return cb.like(root.<String> get("value"), "%" + value + "%");
		}
		return null;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

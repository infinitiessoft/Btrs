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

public class RoleSpecification implements Specification<Role> {

	private Long id;
	@QueryParam("value")
	private RoleEnum value;

	@Override
	public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (value != null) {
			predicates.add(cb.equal(root.<RoleEnum> get("value"), value));
		}
		if (id != null) {
			predicates.add(cb.equal(root.<Long> get("id"), id));
		}

		if (predicates.isEmpty()) {
			return null;
		}

		return cb.and(predicates.toArray(new Predicate[0]));
	}

	public RoleEnum getValue() {
		return value;
	}

	public void setValue(RoleEnum value) {
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

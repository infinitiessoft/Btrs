package resources.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

import org.springframework.data.jpa.domain.Specification;

import com.google.common.base.Strings;

import entity.TypeParameter;

public class TypeParameterSpecification implements Specification<TypeParameter> {

	private Long id;
	@QueryParam("value")
	private String value;

	@Override
	public Predicate toPredicate(Root<TypeParameter> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!Strings.isNullOrEmpty(value)) {
			predicates.add(cb.like(root.<String> get("value"), "%" + value
					+ "%"));
		}
		if (id != null) {
			predicates.add(cb.equal(root.<Long> get("id"), id));
		}

		if (predicates.isEmpty()) {
			return null;
		}

		return cb.and(predicates.toArray(new Predicate[0]));
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

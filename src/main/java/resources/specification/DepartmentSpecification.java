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

import entity.Department;

public class DepartmentSpecification implements Specification<Department> {

	@QueryParam("name")
	private String name;
	@QueryParam("comment")
	private String comment;

	@Override
	public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!Strings.isNullOrEmpty(name)) {
			predicates
					.add(cb.like(root.<String> get("name"), "%" + name + "%"));
		}
		if (!Strings.isNullOrEmpty(comment)) {
			predicates.add(cb.like(root.<String> get("comment"), "%" + comment
					+ "%"));
		}

		if (predicates.isEmpty()) {
			return null;
		}

		return cb.and(predicates.toArray(new Predicate[0]));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
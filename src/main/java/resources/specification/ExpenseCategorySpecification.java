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

import entity.ExpenseCategory;

public class ExpenseCategorySpecification implements
		Specification<ExpenseCategory> {

	private Long id;
	@QueryParam("name_key")
	private String name_key;
	@QueryParam("code")
	private String code;

	@Override
	public Predicate toPredicate(Root<ExpenseCategory> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!Strings.isNullOrEmpty(name_key)) {
			predicates.add(cb.like(root.<String> get("name_key"), "%"
					+ name_key + "%"));
		}
		if (!Strings.isNullOrEmpty(code)) {
			predicates
					.add(cb.like(root.<String> get("code"), "%" + code + "%"));
		}

		if (id != null) {
			predicates.add(cb.equal(root.<Long> get("id"), id));
		}

		if (predicates.isEmpty()) {
			return null;
		}

		return cb.and(predicates.toArray(new Predicate[0]));
	}

	public String getName_key() {
		return name_key;
	}

	public void setName_key(String name_key) {
		this.name_key = name_key;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

package resources.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

import org.springframework.data.jpa.domain.Specification;

import com.google.common.base.Strings;

import entity.ExpenseCategory;

public class ExpenseCategorySpecification implements Specification<ExpenseCategory> {

	@QueryParam("name_key")
	private String name_key;
	@QueryParam("code")
	private String code;

	@Override
	public Predicate toPredicate(Root<ExpenseCategory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (!Strings.isNullOrEmpty(name_key)) {
			return cb.like(root.<String> get("name_key"), "%" + name_key + "%");
		}
		if (!Strings.isNullOrEmpty(code)) {
			return cb.like(root.<String> get("code"), "%" + code + "%");
		}
		return null;

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

}

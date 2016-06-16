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

import entity.Expense;
import entity.ParameterValue;

public class ParameterValueSpecification implements
		Specification<ParameterValue> {

	@QueryParam("expenseId")
	private Long expenseId;
	@QueryParam("typeParameterId")
	private Long typeParameterId;
	@QueryParam("value")
	private String value;

	@Override
	public Predicate toPredicate(Root<ParameterValue> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (expenseId != null) {
			predicates.add(cb.equal(
					root.<Expense> get("expense").<Long> get("id"), expenseId));
		}
		if (typeParameterId != null) {
			predicates.add(cb.equal(root.<ParameterValue> get("typeParameter")
					.<Long> get("id"), typeParameterId));
		}
		if (!Strings.isNullOrEmpty(value)) {
			predicates.add(cb.like(root.<String> get("value"), "%" + value
					+ "%"));
		}
		if (predicates.isEmpty()) {
			return null;
		}

		return cb.and(predicates.toArray(new Predicate[0]));
	}

	public Long getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Long expenseId) {
		this.expenseId = expenseId;
	}

	public Long getTypeParameterId() {
		return typeParameterId;
	}

	public void setTypeParameterId(Long typeParameterId) {
		this.typeParameterId = typeParameterId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

package resources.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import entity.ExpenseType;
import entity.ExpenseTypePara;
import entity.ParameterValue;

public class ExpenseTypeParaSpecification implements Specification<ExpenseTypePara> {

	private Long expenseTypeId;
	private Long parameterValueId;

	@Override
	public Predicate toPredicate(Root<ExpenseTypePara> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (expenseTypeId != null) {
			predicates.add(cb.equal(root.<ExpenseType> get("expenseType").<Long> get("id"), expenseTypeId));
		}
		if (parameterValueId != null) {
			predicates.add(cb.equal(root.<ParameterValue> get("parameterValue").<Long> get("id"), parameterValueId));
		}
		if (predicates.isEmpty()) {
			return null;
		}

		return cb.and(predicates.toArray(new Predicate[0]));
	}

	public Long getExpenseTypeId() {
		return expenseTypeId;
	}

	public void setExpenseTypeId(Long expenseTypeId) {
		this.expenseTypeId = expenseTypeId;
	}

	public Long getParameterValueId() {
		return parameterValueId;
	}

	public void setParameterValueId(Long parameterValueId) {
		this.parameterValueId = parameterValueId;
	}

}

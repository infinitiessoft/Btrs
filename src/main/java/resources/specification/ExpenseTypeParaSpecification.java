package resources.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

import org.springframework.data.jpa.domain.Specification;

import entity.ExpenseType;
import entity.ExpenseTypePara;
import entity.ParameterValue;

public class ExpenseTypeParaSpecification implements
		Specification<ExpenseTypePara> {

	private Long id;
	@QueryParam("expenseTypeId")
	private Long expenseTypeId;
	@QueryParam("typeParameterId")
	private Long typeParameterId;

	@Override
	public Predicate toPredicate(Root<ExpenseTypePara> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (expenseTypeId != null) {
			predicates.add(cb.equal(root.<ExpenseType> get("expenseType")
					.<Long> get("id"), expenseTypeId));
		}
		if (typeParameterId != null) {
			predicates.add(cb.equal(root.<ParameterValue> get("typeParameter")
					.<Long> get("id"), typeParameterId));
		}
		if (id != null) {
			predicates.add(cb.equal(root.<Long> get("id"), id));
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

	public Long getTypeParameterId() {
		return typeParameterId;
	}

	public void setTypeParameterId(Long typeParameterId) {
		this.typeParameterId = typeParameterId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

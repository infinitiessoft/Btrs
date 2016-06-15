//package resources.specification;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import javax.ws.rs.QueryParam;
//
//import org.springframework.data.jpa.domain.Specification;
//
//import entity.ExpenseCateType;
//import entity.ExpenseCategory;
//import entity.ExpenseType;
//
//public class ExpenseCateTypeSpecification implements Specification<ExpenseCateType> {
//	@QueryParam("expenseTypeId")
//	private Long expenseTypeId;
//	@QueryParam("expenseCategoryId")
//	private Long expenseCategoryId;
//
//	@Override
//	public Predicate toPredicate(Root<ExpenseCateType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//		List<Predicate> predicates = new ArrayList<Predicate>();
//		if (expenseTypeId != null) {
//			predicates.add(cb.equal(root.<ExpenseType> get("expenseType").<Long> get("id"), expenseTypeId));
//		}
//		if (expenseCategoryId != null) {
//			predicates.add(cb.equal(root.<ExpenseCategory> get("expenseCategory").<Long> get("id"), expenseCategoryId));
//		}
//		if (predicates.isEmpty()) {
//			return null;
//		}
//
//		return cb.and(predicates.toArray(new Predicate[0]));
//	}
//
//	public Long getExpenseTypeId() {
//		return expenseTypeId;
//	}
//
//	public void setExpenseTypeId(Long expenseTypeId) {
//		this.expenseTypeId = expenseTypeId;
//	}
//
//	public Long getExpenseCategoryId() {
//		return expenseCategoryId;
//	}
//
//	public void setExpenseCategoryId(Long expenseCategoryId) {
//		this.expenseCategoryId = expenseCategoryId;
//	}
//
//}

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

public class ExpenseSpecification implements Specification<Expense> {

	private Long id;
	@QueryParam("comment")
	private String comment;
	@QueryParam("totalAmount")
	private Integer totalAmount;
	@QueryParam("taxAmount")
	private Integer taxAmount;

	@Override
	public Predicate toPredicate(Root<Expense> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!Strings.isNullOrEmpty(comment)) {
			predicates.add(cb.like(root.<String> get("comment"), "%" + comment
					+ "%"));
		}
		if (totalAmount != null) {
			predicates.add(cb.equal(root.<Integer> get("totalAmount"),
					totalAmount));
		}
		if (taxAmount != null) {
			predicates
					.add(cb.equal(root.<Integer> get("taxAmount"), taxAmount));
		}

		if (id != null) {
			predicates.add(cb.equal(root.<Long> get("id"), id));
		}
		if (predicates.isEmpty()) {
			return null;
		}

		return cb.and(predicates.toArray(new Predicate[0]));
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Integer taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

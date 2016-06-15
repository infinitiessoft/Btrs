//package entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
//
//@Entity
//@Table(name = "exp_category_exp_type", uniqueConstraints = @UniqueConstraint(columnNames = { "category_id",
//		"type_id" }) )
//public class ExpenseCateType extends AbstractEntity {
//	private static final long serialVersionUID = 7711505597348200997L;
//
//	@Id
//	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "category_id", nullable = false)
//	private ExpenseCategory expenseCategory;
//
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "type_id", nullable = false)
//	private ExpenseType expenseType;
//
//	public ExpenseCateType() {
//		super();
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public ExpenseCategory getExpenseCategory() {
//		return expenseCategory;
//	}
//
//	public void setExpenseCategory(ExpenseCategory expenseCategory) {
//		this.expenseCategory = expenseCategory;
//	}
//
//	public ExpenseType getExpenseType() {
//		return expenseType;
//	}
//
//	public void setExpenseType(ExpenseType expenseType) {
//		this.expenseType = expenseType;
//	}
//
//}

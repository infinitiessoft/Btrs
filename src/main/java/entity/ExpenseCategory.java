package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "expense_categories")
public class ExpenseCategory implements Serializable {

	private static final long serialVersionUID = 7526471155622776147L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "code", nullable = false)
	private String code;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "expenseCategory", cascade = CascadeType.ALL)
	private List<ExpenseCateType> expenseCateType = new ArrayList<ExpenseCateType>(0);

	public ExpenseCategory() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<ExpenseCateType> getExpenseCateType() {
		return expenseCateType;
	}

	public void setExpenseCateType(List<ExpenseCateType> expenseCateType) {
		this.expenseCateType = expenseCateType;
	}

}

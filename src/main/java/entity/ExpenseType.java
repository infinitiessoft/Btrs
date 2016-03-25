package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@Table(name = "expense_types")
public class ExpenseType extends AbstractEntity {
	private static final long serialVersionUID = 7711505597348200997L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "expenseCategory", cascade = CascadeType.ALL)
	private List<ExpenseCateType> expenseCateType = new ArrayList<ExpenseCateType>(0);

	@Column(name = "value", nullable = false)
	private String value;

	@Column(name = "tax_percent", nullable = false)
	private Double taxPercent;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "expenseType", cascade = CascadeType.ALL)
	private Set<Expense> expenses = new HashSet<Expense>(0);

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "expenseType", cascade = CascadeType.ALL)
	private List<ExpenseTypePara> expenseTypePara = new ArrayList<ExpenseTypePara>(0);

	@Override
	public String toString() {
		return "ExpenseType [id=" + id + ", value=" + value + ", taxPercent=" + taxPercent + ", expenses=" + expenses
				+ "]";
	}

	public ExpenseType() {

		this.taxPercent = 0.0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Double getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(Double taxPercent) {
		this.taxPercent = taxPercent;
	}

	public List<ExpenseCateType> getExpenseCateType() {
		return expenseCateType;
	}

	public void setExpenseCateType(List<ExpenseCateType> expenseCateType) {
		this.expenseCateType = expenseCateType;
	}

	public Set<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(Set<Expense> expenses) {
		this.expenses = expenses;
	}

	public List<ExpenseTypePara> getExpenseTypePara() {
		return expenseTypePara;
	}

	public void setExpenseTypePara(List<ExpenseTypePara> expenseTypePara) {
		this.expenseTypePara = expenseTypePara;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExpenseType other = (ExpenseType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

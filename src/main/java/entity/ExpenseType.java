package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import enumpackage.ExpenseTypeEnum;

@Entity
@Table(name = "expense_types")
public class ExpenseType implements Serializable {

	private static final long serialVersionUID = 7526471155622776147L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "expenseCategory", cascade = CascadeType.ALL)
	private List<ExpenseCateType> expenseCateType = new ArrayList<ExpenseCateType>(0);

	@Enumerated(EnumType.STRING)
	private ExpenseTypeEnum value;

	@Column(name = "taxPercent", nullable = false)
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

	public ExpenseTypeEnum getValue() {
		return value;
	}

	public void setValue(ExpenseTypeEnum value) {
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

}

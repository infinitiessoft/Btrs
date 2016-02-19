package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "expenses")
public class Expense implements Serializable {

	private static final long serialVersionUID = 7526471155622776147L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id", nullable = false)
	private ExpenseType expenseType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "report_id", nullable = false)
	private Report report;

	@Column(length = 4000, name = "comment", nullable = false)
	private String comment;

	@Column(name = "totalAmount", nullable = false)
	private Integer totalAmount;

	@Column(name = "taxAmount", nullable = false)
	private Integer taxAmount;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "expense", cascade = CascadeType.ALL)
	private List<ParameterValue> parameterValues;

	public Expense() {

	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", expenseType=" + expenseType + ", report=" + report + ", comment=" + comment
				+ ", totalAmount=" + totalAmount + ", taxAmount=" + taxAmount + ", parameterValues=" + parameterValues
				+ "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
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

	public List<ParameterValue> getParameterValue() {
		return parameterValues;
	}

	public void setParameterValue(List<ParameterValue> parameterValues) {
		this.parameterValues = parameterValues;
	}

}

package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "parameters_values", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "parameter_id", "expense_id" }) })
public class ParameterValue implements Serializable {

	private static final long serialVersionUID = 7526471155622776147L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parameter_id", nullable = false)
	private TypeParameter typeParameter;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "expense_id", nullable = false)
	private Expense expense;

	@Column(name = "value", nullable = false)
	private String value;

	@Override
	public String toString() {
		return "ParameterValue [id=" + id + ", typeParameter=" + typeParameter + ", expense=" + expense + ", value="
				+ value + "]";
	}

	public ParameterValue() {

	}

	public ParameterValue(TypeParameter typeParameter, Expense expense) {
		super();
		this.typeParameter = typeParameter;
		this.expense = expense;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TypeParameter getTypeParameter() {
		return typeParameter;
	}

	public void setTypeParameter(TypeParameter typeParameter) {
		this.typeParameter = typeParameter;
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

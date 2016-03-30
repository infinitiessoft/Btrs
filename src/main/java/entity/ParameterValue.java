package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "parameters_values")
public class ParameterValue extends AbstractEntity {
	private static final long serialVersionUID = 7711505597348200997L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parameter_id")
	private TypeParameter typeParameter;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "expense_id")
	private Expense expense;

	@Column(name = "value")
	private String value;

	@Override
	public String toString() {
		return "ParameterValue [id=" + id + ", typeParameter=" + typeParameter + ", expense=" + expense + ", value="
				+ value + "]";
	}

	public ParameterValue() {
		super();
	}

	public ParameterValue(TypeParameter typeParameter, Expense expense) {
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
		ParameterValue other = (ParameterValue) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

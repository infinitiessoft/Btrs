package entity;

import java.util.HashSet;
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
@Table(name = "type_parameters")
public class TypeParameter extends AbstractEntity {
	private static final long serialVersionUID = 7711505597348200997L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "value", nullable = false)
	private String value;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "typeParameter", cascade = CascadeType.ALL)
	private Set<ParameterValue> parameterValues = new HashSet<ParameterValue>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "typeParameter", cascade = CascadeType.ALL)
	private Set<ExpenseTypePara> expenseTypeParas = new HashSet<ExpenseTypePara>(0);

	public TypeParameter() {
		super();

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
		TypeParameter other = (TypeParameter) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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

	public void setParameterValues(Set<ParameterValue> parameterValues) {
		this.parameterValues = parameterValues;
	}

	public Set<ExpenseTypePara> getExpenseTypeParas() {
		return expenseTypeParas;
	}

	public void setExpenseTypeParas(Set<ExpenseTypePara> expenseTypeParas) {
		this.expenseTypeParas = expenseTypeParas;
	}

	public Set<ParameterValue> getParameterValues() {
		return parameterValues;
	}

	@Override
	public String toString() {
		return "TypeParameter [value=" + value + "]";
	}

}

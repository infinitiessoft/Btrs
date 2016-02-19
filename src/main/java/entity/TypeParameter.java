package entity;

import java.io.Serializable;
import java.util.HashSet;
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

import enumpackage.ParameterEnum;

@Entity
@Table(name = "type_parameters")
public class TypeParameter implements Serializable {

	private static final long serialVersionUID = 7526471155622776147L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private ParameterEnum value;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "typeParameter", cascade = CascadeType.ALL)
	private Set<ExpenseTypePara> expenseTypePara = new HashSet<ExpenseTypePara>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "typeParameter", cascade = CascadeType.ALL)
	private Set<ParameterValue> parameterValues = new HashSet<ParameterValue>(0);

	public TypeParameter() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ParameterEnum getValue() {
		return value;
	}

	public void setValue(ParameterEnum value) {
		this.value = value;
	}

	public Set<ExpenseTypePara> getExpenseTypePara() {
		return expenseTypePara;
	}

	public void setExpenseTypePara(Set<ExpenseTypePara> expenseTypePara) {
		this.expenseTypePara = expenseTypePara;
	}

	public Set<ParameterValue> getParameterValues() {
		return parameterValues;
	}

	public void setParameterValues(Set<ParameterValue> parameterValues) {
		this.parameterValues = parameterValues;
	}

	@Override
	public String toString() {
		return "TypeParameter [value=" + value + "]";
	}

}

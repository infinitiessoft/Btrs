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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "exp_types_type_parameters", uniqueConstraints = @UniqueConstraint(columnNames = { "type_id",
		"parameter_id" }) )
public class ExpenseTypePara extends AbstractEntity {
	private static final long serialVersionUID = 7711505597348200997L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id", nullable = false)
	private ExpenseType expenseType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parameter_id", nullable = false)
	private ParameterValue parameterValue;

	public ExpenseTypePara() {
		super();

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

	public ParameterValue getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(ParameterValue parameterValue) {
		this.parameterValue = parameterValue;
	}

}

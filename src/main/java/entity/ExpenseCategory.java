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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "expense_categories", uniqueConstraints = @UniqueConstraint(columnNames = "name_key") )
public class ExpenseCategory extends AbstractEntity {
	private static final long serialVersionUID = 7711505597348200997L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name_key", nullable = false)
	private String name_key;

	@Column(name = "code", nullable = false)
	private String code;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "expenseCategory", cascade = CascadeType.ALL)
	private Set<ExpenseCateType> expenseCateType = new HashSet<ExpenseCateType>(0);

	public ExpenseCategory() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName_key() {
		return name_key;
	}

	public void setName_key(String name_key) {
		this.name_key = name_key;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<ExpenseCateType> getExpenseCateType() {
		return expenseCateType;
	}

	public void setExpenseCateType(Set<ExpenseCateType> expenseCateType) {
		this.expenseCateType = expenseCateType;
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
		ExpenseCategory other = (ExpenseCategory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

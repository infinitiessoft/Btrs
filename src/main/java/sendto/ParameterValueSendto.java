package sendto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlTransient;

public class ParameterValueSendto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static class Expense implements Serializable {

		private static final long serialVersionUID = 1L;
		private Long id;

		private boolean isIdSet;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			isIdSet = true;
			this.id = id;
		}

		@XmlTransient
		public boolean isIdSet() {
			return isIdSet;
		}

		@XmlTransient
		public void setIdSet(boolean isIdSet) {
			this.isIdSet = isIdSet;
		}
	}

	public static class TypeParameter implements Serializable {

		public static class Type implements Serializable {
			private static final long serialVersionUID = 1L;
			private Long id;

			public Long getId() {
				return id;
			}

			public void setId(Long id) {

				this.id = id;
			}
		}

		private static final long serialVersionUID = 1L;
		private Long id;

		private boolean isIdSet;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			isIdSet = true;
			this.id = id;
		}

		@XmlTransient
		public boolean isIdSet() {
			return isIdSet;
		}

		@XmlTransient
		public void setIdSet(boolean isIdSet) {
			this.isIdSet = isIdSet;
		}

	}

	private Long id;
	private String value;
	private TypeParameter typeParameter;
	private Expense expense;

	private boolean isTypeParameterSet;
	private boolean isExpenseSet;
	private boolean isValueSet;

	public ParameterValueSendto() {
		super();
	}

	@XmlTransient
	public boolean isTypeParameterSet() {
		return isTypeParameterSet;
	}

	@XmlTransient
	public void setTypeParameterSet(boolean isTypeParameterSet) {
		this.isTypeParameterSet = isTypeParameterSet;
	}

	@XmlTransient
	public boolean isExpenseSet() {
		return isExpenseSet;
	}

	@XmlTransient
	public void setExpenseSet(boolean isExpenseSet) {
		this.isExpenseSet = isExpenseSet;
	}

	@XmlTransient
	public boolean isValueSet() {
		return isValueSet;
	}

	@XmlTransient
	public void setValueSet(boolean isValueSet) {
		this.isValueSet = isValueSet;
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
		this.isValueSet = true;
		this.value = value;
	}

	public TypeParameter getTypeParameter() {
		return typeParameter;
	}

	public void setTypeParameter(TypeParameter typeParameter) {
		if (typeParameter != null) {
			this.isTypeParameterSet = true;
		}
		this.typeParameter = typeParameter;
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		if (expense != null) {
			this.isExpenseSet = true;
		}
		this.expense = expense;
	}

}

package sendto;

import javax.xml.bind.annotation.XmlTransient;

public class ExpenseTypeParaSendto {

	public static class ExpenseType {

		private Long id;
		private boolean isIdSet = false;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
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

	public static class TypeParameter {

		private Long id;
		private boolean isIdSet = false;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
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
	private ExpenseType expenseType = new ExpenseType();
	private TypeParameter typeParameter = new TypeParameter();

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}

	public TypeParameter getTypeParameter() {
		return typeParameter;
	}

	public void setTypeParameter(TypeParameter typeParameter) {
		this.typeParameter = typeParameter;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

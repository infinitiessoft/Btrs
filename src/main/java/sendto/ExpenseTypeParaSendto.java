package sendto;

import java.io.Serializable;

public class ExpenseTypeParaSendto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static class ExpenseType implements Serializable {
		private static final long serialVersionUID = 1L;
		private Long id;
		private Double taxPercent;

		public ExpenseType(Long id, Double taxPercent) {
			this.id = id;
			this.taxPercent = taxPercent;
		}

		public ExpenseType() {
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Double getTaxPercent() {
			return taxPercent;
		}

		public void setTaxPercent(Double taxPercent) {
			this.taxPercent = taxPercent;
		}

	}

	public static class ParameterValue implements Serializable {
		private static final long serialVersionUID = 1L;
		private Long id;
		private String value;

		public ParameterValue() {
		}

		public ParameterValue(Long id, String value) {
			this.id = id;
			this.value = value;
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

	}

	private Long id;
	private ExpenseType expenseType;
	private ParameterValue parameterValue;

	public ExpenseTypeParaSendto() {

	}

	public ExpenseTypeParaSendto(Long id, ExpenseType expenseType, ParameterValue parameterValue) {
		this.id = id;
		this.expenseType = expenseType;
		this.parameterValue = parameterValue;
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

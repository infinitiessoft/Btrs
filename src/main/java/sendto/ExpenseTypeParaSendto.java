package sendto;

import java.io.Serializable;

public class ExpenseTypeParaSendto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static class ExpenseType implements Serializable {
		private static final long serialVersionUID = 1L;
		private Long id;
		private Double taxPercent;

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
	private ExpenseType expType;
	private ParameterValue parameterValue;

	public ExpenseTypeParaSendto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ExpenseType getExpType() {
		return expType;
	}

	public void setExpType(ExpenseType expType) {
		this.expType = expType;
	}

	public ParameterValue getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(ParameterValue parameterValue) {
		this.parameterValue = parameterValue;
	}

}

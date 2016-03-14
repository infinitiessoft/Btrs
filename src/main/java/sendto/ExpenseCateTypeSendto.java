package sendto;

import java.io.Serializable;

public class ExpenseCateTypeSendto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static class ExpenseCategory implements Serializable {

		private static final long serialVersionUID = 1L;
		private Long id;
		private String name;
		private String code;

		public ExpenseCategory() {

		}

		public ExpenseCategory(Long id, String name, String code) {
			this.id = id;
			this.name = name;
			this.code = code;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
	}

	public static class ExpenseType implements Serializable {
		private static final long serialVersionUID = 1L;
		private Long id;
		private Double taxPercent;

		public ExpenseType(Long id, Double taxPercent) {
			this.id = id;
			this.taxPercent = taxPercent;
		}

		public ExpenseType() {
			// TODO Auto-generated constructor stub
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

	private Long id;
	private ExpenseCategory expCate;
	private ExpenseType expType;

	public ExpenseCateTypeSendto() {
		// TODO Auto-generated constructor stub
	}

	public ExpenseCateTypeSendto(Long id, ExpenseCategory expCate, ExpenseType expType) {
		this.id = id;
		this.expCate = expCate;
		this.expType = expType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ExpenseCategory getExpCate() {
		return expCate;
	}

	public void setExpCate(ExpenseCategory expCate) {
		this.expCate = expCate;
	}

	public ExpenseType getExpType() {
		return expType;
	}

	public void setExpType(ExpenseType expType) {
		this.expType = expType;
	}

}

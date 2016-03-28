package sendto;

import javax.xml.bind.annotation.XmlTransient;

import com.google.common.base.Strings;

public class ExpenseSendto {

	private Long id;
	private Report report;
	private String comment;
	private Integer totalAmount;
	private Integer taxAmount;
	private ExpenseType expenseType;

	private boolean isCommentSet;
	private boolean isTotalAmountSet;
	private boolean isTaxAmountSet;
	private boolean isReportSet;
	private boolean isExpenseTypeSet;

	public ExpenseSendto() {
		super();
	}

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		if (expenseType != null) {
			isExpenseTypeSet = true;
		}
		this.expenseType = expenseType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		if (report != null) {
			isReportSet = true;
		}
		this.report = report;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		if (!Strings.isNullOrEmpty(comment)) {
			isCommentSet = true;
		}
		this.comment = comment;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		if (totalAmount != null) {
			isTotalAmountSet = true;
		}
		this.totalAmount = totalAmount;
	}

	public Integer getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Integer taxAmount) {
		if (taxAmount != null) {
			isTaxAmountSet = true;
		}
		this.taxAmount = taxAmount;
	}

	@XmlTransient
	public boolean isCommentSet() {
		return isCommentSet;
	}

	@XmlTransient
	public boolean isTotalAmountSet() {
		return isTotalAmountSet;
	}

	@XmlTransient
	public boolean isTaxAmountSet() {
		return isTaxAmountSet;
	}

	@XmlTransient
	public boolean isReportSet() {
		return isReportSet;
	}

	@XmlTransient
	public boolean isExpenseTypeSet() {
		return isExpenseTypeSet;
	}

	public static class Report {
		private Long id;

		private boolean isIdSet;

		public Report() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			if (id != null) {
				isIdSet = true;
			}
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

	public static class ExpenseType {
		private Long id;

		private boolean isIdSet;

		public ExpenseType() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			if (id != null) {
				setIdSet(true);
			}
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

}

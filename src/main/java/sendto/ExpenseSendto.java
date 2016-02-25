package sendto;

import entity.Report;

public class ExpenseSendto {

	private Long id;
	private Report report;
	private String comment;
	private Integer totalAmount;
	private Integer taxAmount;

	public ExpenseSendto() {
		super();
	}

	public ExpenseSendto(Long id, Report report, String comment, Integer totalAmount, Integer taxAmount) {
		this.id = id;
		this.report = report;
		this.comment = comment;
		this.totalAmount = totalAmount;
		this.taxAmount = taxAmount;
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
		this.report = report;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Integer taxAmount) {
		this.taxAmount = taxAmount;
	}

}

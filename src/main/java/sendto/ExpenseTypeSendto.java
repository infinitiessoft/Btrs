package sendto;

public class ExpenseTypeSendto {
	private Long id;
	private Double taxPercent;

	public ExpenseTypeSendto() {

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

	public ExpenseTypeSendto(Long id, Double taxPercent) {
		super();
		this.id = id;
		this.taxPercent = taxPercent;
	}

}

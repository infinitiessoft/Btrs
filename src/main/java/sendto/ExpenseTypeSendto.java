package sendto;

import javax.xml.bind.annotation.XmlTransient;

public class ExpenseTypeSendto {
	private Long id;
	private Double taxPercent;

	private boolean isTaxPercentSet;
	private boolean isValueSet;
	public String Value;

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		setValueSet(true);
		Value = value;
	}

	public ExpenseTypeSendto() {
		super();
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
		setTaxPercentSet(true);
		this.taxPercent = taxPercent;
	}

	@XmlTransient
	public boolean isTaxPercentSet() {
		return isTaxPercentSet;
	}

	@XmlTransient
	public void setTaxPercentSet(boolean isTaxPercentSet) {
		this.isTaxPercentSet = isTaxPercentSet;
	}

	@XmlTransient
	public boolean isValueSet() {
		return isValueSet;
	}

	@XmlTransient
	public void setValueSet(boolean isValueSet) {
		this.isValueSet = isValueSet;
	}

}

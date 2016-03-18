package sendto;

import javax.xml.bind.annotation.XmlTransient;

import enumpackage.ExpenseTypeEnum;

public class ExpenseTypeSendto {
	private Long id;
	private Double taxPercent;

	private boolean isTaxPercentSet;
	private boolean isValueSet;
	public ExpenseTypeEnum Value;

	public ExpenseTypeEnum getValue() {
		return Value;
	}

	public void setValue(ExpenseTypeEnum value) {
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

}

package sendto;

import javax.xml.bind.annotation.XmlTransient;

import com.google.common.base.Strings;

public class ExpenseTypeSendto {
	private Long id;
	private Double taxPercent;
	public String value;

	private boolean isTaxPercentSet;
	private boolean isValueSet;

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
		if (taxPercent != null) {
			isTaxPercentSet = true;
		}
		this.taxPercent = taxPercent;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		if (!Strings.isNullOrEmpty(value)) {
			isValueSet = true;
		}
		this.value = value;
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

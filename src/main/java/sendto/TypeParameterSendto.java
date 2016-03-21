package sendto;

import javax.xml.bind.annotation.XmlTransient;

public class TypeParameterSendto {

	private Long id;
	private String value;

	private boolean isValueSet;

	public TypeParameterSendto() {
	}

	public TypeParameterSendto(Long id) {
		this.id = id;
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
		this.isValueSet = true;
		this.value = value;
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

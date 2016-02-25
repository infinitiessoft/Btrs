package sendto;

public class ParameterValueSendto {

	private Long id;
	private String value;

	public ParameterValueSendto() {
		super();
	}

	public ParameterValueSendto(Long id, String value) {
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

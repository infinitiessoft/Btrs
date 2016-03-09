package sendto;

public class DepartmentSendto {

	private Long id;
	private String name;
	private String comment;

	public DepartmentSendto() {

	}

	public DepartmentSendto(Long id, String name, String comment) {
		this.id = id;
		this.name = name;
		this.comment = comment;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}

package sendto;

import java.util.Date;

public class StatusChangesSendto {
	private Long id;
	private String comment;
	private Date createdDate;

	public StatusChangesSendto() {
		super();
	}

	public StatusChangesSendto(Long id, String comment, Date createdDate) {
		super();
		this.id = id;
		this.comment = comment;
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}

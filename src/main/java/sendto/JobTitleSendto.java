package sendto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlTransient;

public class JobTitleSendto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String comment;

	public JobTitleSendto() {
		super();
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
		isNameSet = true;
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		isCommentSet = true;
		this.comment = comment;
	}

	private boolean isNameSet;
	private boolean isCommentSet;

	@XmlTransient
	public boolean isNameSet() {
		return isNameSet;
	}

	@XmlTransient
	public void setNameSet(boolean isNameSet) {
		this.isNameSet = isNameSet;
	}

	@XmlTransient
	public boolean isCommentSet() {
		return isCommentSet;
	}

	@XmlTransient
	public void setCommentSet(boolean isCommentSet) {
		this.isCommentSet = isCommentSet;
	}

}

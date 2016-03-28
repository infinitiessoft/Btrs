package sendto;

import javax.xml.bind.annotation.XmlTransient;

import com.google.common.base.Strings;

public class DepartmentSendto {

	private Long id;
	private String name;
	private String comment;
	private boolean isIdSet;

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
		if (id != null) {
			this.isIdSet = true;
		}
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (!Strings.isNullOrEmpty(name)) {
			isNameSet = true;
		}
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		if (!Strings.isNullOrEmpty(comment)) {
			isCommentSet = true;
		}
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

	@XmlTransient
	public boolean isIdSet() {
		return isIdSet;
	}

	@XmlTransient
	public void setIdSet(boolean isIdSet) {
		this.isIdSet = isIdSet;
	}

}

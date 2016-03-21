package sendto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

import enumpackage.StatusEnum;

public class StatusChangesSendto {
	private Long id;
	private String comment;
	private Date createdDate;
	private User user;
	private StatusEnum value;
	private Report report;

	private boolean isCommentSet;
	private boolean isCreatedDateSet;
	private boolean isUserSet;
	private boolean isValueSet;
	private boolean isReportSet;

	public StatusChangesSendto() {
		super();
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
		isCommentSet = true;
		this.comment = comment;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		isCreatedDateSet = true;
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public StatusEnum getValue() {
		return value;
	}

	public void setValue(StatusEnum value) {
		this.value = value;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
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
	public boolean isCreatedDateSet() {
		return isCreatedDateSet;
	}

	@XmlTransient
	public void setCreatedDateSet(boolean isCreatedDateSet) {
		this.isCreatedDateSet = isCreatedDateSet;
	}

	@XmlTransient
	public boolean isUserSet() {
		return isUserSet;
	}

	@XmlTransient
	public void setUserSet(boolean isUserSet) {
		this.isUserSet = isUserSet;
	}

	@XmlTransient
	public boolean isValueSet() {
		return isValueSet;
	}

	@XmlTransient
	public void setValueSet(boolean isValueSet) {
		this.isValueSet = isValueSet;
	}

	@XmlTransient
	public boolean isReportSet() {
		return isReportSet;
	}

	@XmlTransient
	public void setReportSet(boolean isReportSet) {
		this.isReportSet = isReportSet;
	}

	public static class User {
		private Long revisor_id;

		private Boolean isIdSet;

		public User() {
			super();
		}

		public Long getRevisor_id() {
			return revisor_id;
		}

		public void setRevisor_id(Long revisor_id) {
			setIdSet(true);
			this.revisor_id = revisor_id;
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

	public static class Report implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long id;

		private Boolean isIdSet;

		public Report() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			setIdSet(true);
			this.id = id;
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

}

package sendto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

import com.google.common.base.Strings;

public class StatusChangeSendto {
	private Long id;
	private String comment;
	private Date createdDate;
	private User user;
	private String value;
	private Report report;

	private boolean isCommentSet;
	private boolean isCreatedDateSet;
	private boolean isUserSet;
	private boolean isValueSet;
	private boolean isReportSet;

	public StatusChangeSendto() {
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
		if (!Strings.isNullOrEmpty(comment)) {
			isCommentSet = true;
		}
		this.comment = comment;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		if (createdDate != null) {
			isCreatedDateSet = true;
		}
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		if (user != null) {
			isUserSet = true;
		}
		this.user = user;
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

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		if (report != null) {
			isReportSet = true;
		}
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
		private Long id;
		private String firstname;
		private String lastname;

		public User() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFirstname() {
			return firstname;
		}

		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}

		public String getLastname() {
			return lastname;
		}

		public void setLastname(String lastname) {
			this.lastname = lastname;
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
			if (id != null) {
				isIdSet = true;
			}
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

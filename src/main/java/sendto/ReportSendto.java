package sendto;

import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

import com.google.common.base.Strings;

import entity.User;

public class ReportSendto {

	private Long id;
	private Long maxIdLastMonth;
	private Long attendanceRecordId;
	private String reason;
	private String route;
	private Date startDate;
	private Date endDate;
	private String comment;
	private Date createdDate;
	private Date lastUpdatedDate;
	private User owner;
	private User reviewer;
	private String currentStatus;

	public ReportSendto() {
		super();
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		if (owner != null) {
			isOwnerIdSet = true;
		}
		this.owner = owner;
	}

	public User getReviewer() {
		return reviewer;
	}

	public void setReviewer(User reviewer) {
		if (reviewer != null) {
			isReviewerIdSet = true;
		}
		this.reviewer = reviewer;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		if (!Strings.isNullOrEmpty(currentStatus)) {
			isCurrentStatusSet = true;
		}
		this.currentStatus = currentStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMaxIdLastMonth() {
		return maxIdLastMonth;
	}

	public void setMaxIdLastMonth(Long maxIdLastMonth) {
		if (maxIdLastMonth != null) {
			isMaxIdLastMonthSet = true;
		}
		this.maxIdLastMonth = maxIdLastMonth;
	}

	public Long getAttendanceRecordId() {
		return attendanceRecordId;
	}

	public void setAttendanceRecordId(Long attendanceRecordId) {
		if (attendanceRecordId != null) {
			isAttendanceRecordIdSet = true;
		}
		this.attendanceRecordId = attendanceRecordId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		if (!Strings.isNullOrEmpty(reason)) {
			isReasonSet = true;
		}
		this.reason = reason;
	}

	@XmlTransient
	public void setReasonSet(boolean isReasonSet) {
		this.isReasonSet = isReasonSet;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		if (!Strings.isNullOrEmpty(route)) {
			isRouteSet = true;
		}
		this.route = route;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		if (startDate != null) {
			isStartDateSet = true;
		}
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		if (startDate != null) {
			isStartDateSet = true;
		}
		this.endDate = endDate;
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

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		if (lastUpdatedDate != null) {
			isLastUpdatesDateSet = true;
		}
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public boolean isReasonSet;

	@XmlTransient
	public boolean isReasonSet() {
		return isReasonSet;
	}

	private boolean isRouteSet;

	@XmlTransient
	public boolean isRouteSet() {
		return isRouteSet;
	}

	private boolean isStartDateSet;

	@XmlTransient
	public boolean isStartDateSet() {
		return isStartDateSet;
	}

	private boolean isEndDateSet;

	@XmlTransient
	public boolean isEndDateSet() {
		return isEndDateSet;
	}

	private boolean isCommentSet;

	@XmlTransient
	public boolean isCommentSet() {
		return isCommentSet;
	}

	private boolean isCreatedDateSet;

	@XmlTransient
	public boolean isCreatedDateSet() {
		return isCreatedDateSet;
	}

	public boolean isLastUpdatesDateSet;

	@XmlTransient
	public boolean isLastUpdatesDateSet() {
		return isLastUpdatesDateSet;
	}

	private boolean isCurrentStatusSet;

	@XmlTransient
	public boolean isCurrentStatusSet() {
		return isCurrentStatusSet;
	}

	private boolean isUserSet;

	@XmlTransient
	public boolean isUserSet() {
		return isUserSet;
	}

	private boolean isOwnerIdSet;
	private boolean isReviewerIdSet;

	@XmlTransient
	public boolean isOwnerIdSet() {
		return isOwnerIdSet;
	}

	@XmlTransient
	public boolean isReviewerIdSet() {
		return isReviewerIdSet;
	}

	private boolean isMaxIdLastMonthSet;

	@XmlTransient
	public boolean isMaxIdLastMonthSet() {
		return isMaxIdLastMonthSet;
	}

	private boolean isAttendanceRecordIdSet;

	@XmlTransient
	public boolean isAttendanceRecordIdSet() {
		return isAttendanceRecordIdSet;
	}

	@XmlTransient
	public void setRouteSet(boolean isRouteSet) {
		this.isRouteSet = isRouteSet;
	}

	@XmlTransient
	public void setStartDateSet(boolean isStartDateSet) {
		this.isStartDateSet = isStartDateSet;
	}

	@XmlTransient
	public void setEndDateSet(boolean isEndDateSet) {
		this.isEndDateSet = isEndDateSet;
	}

	@XmlTransient
	public void setCommentSet(boolean isCommentSet) {
		this.isCommentSet = isCommentSet;
	}

	@XmlTransient
	public void setCreatedDateSet(boolean isCreatedDateSet) {
		this.isCreatedDateSet = isCreatedDateSet;
	}

	@XmlTransient
	public void setLastUpdatesDateSet(boolean isLastUpdatesDateSet) {
		this.isLastUpdatesDateSet = isLastUpdatesDateSet;
	}

	@XmlTransient
	public void setCurrentStatusSet(boolean isCurrentStatusSet) {
		this.isCurrentStatusSet = isCurrentStatusSet;
	}

	@XmlTransient
	public void setUserSet(boolean isUserSet) {
		this.isUserSet = isUserSet;
	}

	@XmlTransient
	public void setOwnerIdSet(boolean isOwnerIdSet) {
		this.isOwnerIdSet = isOwnerIdSet;
	}

	@XmlTransient
	public void setReviewerIdSet(boolean isReviewerIdSet) {
		this.isReviewerIdSet = isReviewerIdSet;
	}

	@XmlTransient
	public void setMaxIdLastMonthSet(boolean isMaxIdLastMonthSet) {
		this.isMaxIdLastMonthSet = isMaxIdLastMonthSet;
	}

	@XmlTransient
	public void setAttendanceRecordIdSet(boolean isAttendanceRecordIdSet) {
		this.isAttendanceRecordIdSet = isAttendanceRecordIdSet;
	}

}

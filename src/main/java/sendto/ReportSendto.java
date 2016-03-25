package sendto;

import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

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
		this.owner = owner;
	}

	public User getReviewer() {
		return reviewer;
	}

	public void setReviewer(User reviewer) {
		this.reviewer = reviewer;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
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
		this.maxIdLastMonth = maxIdLastMonth;
	}

	public Long getAttendanceRecordId() {
		return attendanceRecordId;
	}

	public void setAttendanceRecordId(Long attendanceRecordId) {
		this.attendanceRecordId = attendanceRecordId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		setReasonSet(true);
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
		this.route = route;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
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

}

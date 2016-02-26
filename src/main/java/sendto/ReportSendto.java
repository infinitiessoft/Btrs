package sendto;

import java.util.Date;

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

	public ReportSendto() {
		super();

	}

	public ReportSendto(Long id, Long maxIdLastMonth, Long attendanceRecordId, String reason, String route,
			Date startDate, Date endDate, String comment, Date createdDate, Date lastUpdatedDate) {
		this.id = id;
		this.maxIdLastMonth = maxIdLastMonth;
		this.attendanceRecordId = attendanceRecordId;
		this.reason = reason;
		this.route = route;
		this.startDate = startDate;
		this.endDate = endDate;
		this.comment = comment;
		this.createdDate = createdDate;
		this.lastUpdatedDate = lastUpdatedDate;
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
		this.reason = reason;
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

}

package sendto;

import java.io.Serializable;
import java.util.Date;

public class ReportSummarySendto {

	private Long id;

	private Long attendanceRecordId;

	private String route;

	private Date startDate;

	private Date endDate;

	private Date createdDate;

	private String currentStatus;

	private User owner;

	private String firmOrProject;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAttendanceRecordId() {
		return attendanceRecordId;
	}

	public void setAttendanceRecordId(Long attendanceRecordId) {
		this.attendanceRecordId = attendanceRecordId;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getFirmOrProject() {
		return firmOrProject;
	}

	public void setFirmOrProject(String firmOrProject) {
		this.firmOrProject = firmOrProject;
	}

	public static class User implements Serializable {

		private static final long serialVersionUID = 1L;
		private Long id;
		private String Name;

		public User() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

	}

}
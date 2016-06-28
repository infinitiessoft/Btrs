package util;

import attendance.dao.EmployeeDao;
import attendance.entity.Employee;
import entity.Report;

public class MailWritterImpl implements MailWritter {

	private String header;
	private String footer;
	private String systemUrl;
	private EmployeeDao employeeDao;

	public MailWritterImpl(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.MailWritterI#buildSubject(entity.AttendRecord)
	 */
	@Override
	public String buildSubmittedSubject(Report report) {
		Employee owner = employeeDao.findOne(report.getOwner().getUserSharedId());
		String name = owner.getName();
		String subject = name
				+ " applied for a new business trip report notice";
		return subject;
	}

	@Override
	public String buildReviewedSubject(Report report) {
		String subject = "business trip report #" + report.getId() + " "
				+ report.getCurrentStatus().name().toLowerCase() + "  notice";
		return subject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.MailWritterI#buildBody(entity.AttendRecord)
	 */
	@Override
	public String buildSubmittedBody(Report report) {
		Employee owner = employeeDao.findOne(report.getOwner().getUserSharedId());
		String name = owner.getName();
		String route = report.getRoute();
		String reason = report.getReason();

		String body = header
				+ "\n"
				+ name
				+ " applied for a new business trip report"
				+ ", please confirm it in the BTRS system: "
				+ systemUrl
				+ "\n"
				+ "Route : "
				+ route
				+ "\nReason : "
				+ reason
				+ "\n--------------------------------------------------------------------\n"
				+ footer;
		return body;
	}

	@Override
	public String buildReviewedBody(Report report) {
		String route = report.getRoute();
		String reason = report.getReason();

		String body = header
				+ "\n"
				+ " your business trip report"
				+ " #"
				+ report.getId()
				+ " have been "
				+ report.getCurrentStatus().name()
				+ ", please confirm it in the BTRS system: "
				+ systemUrl
				+ "\n"
				+ "Route : "
				+ route
				+ "\nReason : "
				+ reason
				+ "\n--------------------------------------------------------------------\n"
				+ footer;
		return body;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public String getSystemUrl() {
		return systemUrl;
	}

	public void setSystemUrl(String systemUrl) {
		this.systemUrl = systemUrl;
	}

}

package serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.ReportDao;
import entity.Report;
import exceptions.ReportNotFoundException;
import sendto.ReportSendto;
import service.ReportService;

public class ReportServiceImpl implements ReportService {

	private ReportDao reportDao;

	public ReportServiceImpl(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

	@Override
	public ReportSendto retrieve(long id) {
		Report report = reportDao.findOne(id);
		if (report == null) {
			throw new ReportNotFoundException(id);
		}
		return toReportSendto(report);
	}

	private ReportSendto toReportSendto(Report report) {
		ReportSendto ret = new ReportSendto();
		ret.setId(report.getId());
		ret.setAttendanceRecordId(report.getAttendanceRecordId());
		ret.setComment(report.getComment());
		ret.setCreatedDate(report.getCreatedDate());
		ret.setEndDate(report.getEndDate());
		ret.setLastUpdatedDate(report.getLastUpdatedDate());
		ret.setMaxIdLastMonth(report.getMaxIdLastMonth());
		ret.setReason(report.getReason());
		ret.setRoute(report.getRoute());
		ret.setStartDate(report.getStartDate());
		return ret;
	}

	@Override
	public void delete(long id) {
		reportDao.delete(id);

	}

	@Override
	public ReportSendto save(ReportSendto report) {
		report.setId(null);
		Report rpt = new Report();
		rpt = reportDao.save(rpt);
		return toReportSendto(rpt);
	}

	@Override
	public Collection<ReportSendto> findAll() {
		List<ReportSendto> sendto = new ArrayList<ReportSendto>();
		Collection<Report> report = reportDao.findAll();
		for (Report reports : report) {
			sendto.add(toReportSendto(reports));
		}
		return sendto;
	}

	@Override
	public ReportSendto update(long id, ReportSendto report) {
		Report rpt = reportDao.findOne(id);
		if (rpt == null) {
			throw new ReportNotFoundException(id);
		}
		return toReportSendto(reportDao.save(report));

	}
}

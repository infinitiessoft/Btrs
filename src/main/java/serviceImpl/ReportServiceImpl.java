package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import dao.ReportDao;
import dao.UserDao;
import entity.Report;
import exceptions.ReportNotFoundException;
import sendto.ReportSendto;
import service.ReportService;

public class ReportServiceImpl implements ReportService {

	private ReportDao reportDao;
	private UserDao userDao;

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
		ret.setCurrentStatus(report.getCurrent_status());
		return ret;
	}

	@Override
	public void delete(long id) {
		reportDao.delete(id);

	}

	@Override
	public ReportSendto save(ReportSendto report) {
		report.setId(1L);
		Report rpt = new Report();
		setUpReport(report, rpt);
		rpt = reportDao.save(rpt);
		return toReportSendto(rpt);
	}

	@Override
	public Page<ReportSendto> findAll(Specification<Report> spec, Pageable pageable) {
		List<ReportSendto> sendto = new ArrayList<ReportSendto>();
		Page<Report> reports = reportDao.findAll(spec, pageable);
		for (Report report : reports) {
			sendto.add(toReportSendto(report));
		}
		Page<ReportSendto> rets = new PageImpl<ReportSendto>(sendto, pageable, reports.getTotalElements());
		return rets;
	}

	@Override
	public ReportSendto update(long id, ReportSendto updated) {
		Report rpt = reportDao.findOne(id);
		if (rpt == null) {
			throw new ReportNotFoundException(id);
		}
		setUpReport(updated, rpt);
		return toReportSendto(reportDao.save(rpt));

	}

	private void setUpReport(ReportSendto sendto, Report newEntry) {
		if (sendto.isMaxIdLastMonthSet()) {
			newEntry.setMaxIdLastMonth(sendto.getMaxIdLastMonth());
		}
		if (sendto.isAttendanceRecordIdSet()) {
			newEntry.setAttendanceRecordId(sendto.getAttendanceRecordId());
		}
		if (sendto.isOwnerIdSet()) {
			newEntry.setOwner(sendto.getOwner());
		}
		if (sendto.isReviewerIdSet()) {
			newEntry.setReviewer(sendto.getReviewer());
		}
		if (sendto.isReasonSet()) {
			newEntry.setReason(sendto.getReason());
		}
		if (sendto.isRouteSet()) {
			newEntry.setRoute(sendto.getRoute());
		}
		if (sendto.isStartDateSet()) {
			newEntry.setStartDate(sendto.getStartDate());
		}
		if (sendto.isEndDateSet()) {
			newEntry.setEndDate(sendto.getEndDate());
		}
		if (sendto.isCommentSet()) {
			newEntry.setComment(sendto.getComment());
		}
		if (sendto.isCreatedDateSet()) {
			newEntry.setCreatedDate(sendto.getCreatedDate());
		}
		if (sendto.isLastUpdatesDateSet()) {
			newEntry.setLastUpdatedDate(sendto.getLastUpdatedDate());
		}
		if (sendto.isCurrentStatusSet()) {
			newEntry.setCurrent_status(sendto.getCurrentStatus());
		}
	}
}

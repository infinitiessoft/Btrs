package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import sendto.ReportSendto;
import service.ReportService;
import dao.ReportDao;
import dao.UserDao;
import entity.Report;
import exceptions.ReportNotFoundException;
import exceptions.UserNotFoundException;

public class ReportServiceImpl implements ReportService {

	private ReportDao reportDao;
	private UserDao userDao;

	public ReportServiceImpl(ReportDao reportDao, UserDao userDao) {
		this.reportDao = reportDao;
		this.userDao = userDao;

	}

	@Transactional("transactionManager")
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

		ReportSendto.User usr = new ReportSendto.User();
		usr.setId(report.getOwner().getId());
		ret.setUserOwner(usr);

		usr.setId(report.getReviewer().getId());
		ret.setUserReviewer(usr);

		return ret;
	}

	@Transactional("transactionManager")
	@Override
	public void delete(long id) {
		try {
			Report report = reportDao.findOne(id);
			if (report == null) {
				throw new ReportNotFoundException(id);
			}
			reportDao.delete(report);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			throw new ReportNotFoundException(id);
		}
	}

	@Transactional("transactionManager")
	@Override
	public ReportSendto save(ReportSendto report) {
		report.setId(null);
		Report newEntry = new Report();
		setUpReport(report, newEntry);
		return toReportSendto(reportDao.save(newEntry));
	}

	@Transactional("transactionManager")
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

	@Transactional("transactionManager")
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
			if (sendto.getUserOwner().isIdSet()) {
				entity.User user = userDao.findOne(sendto.getUserOwner().getId());
				if (user == null) {
					throw new UserNotFoundException(sendto.getUserOwner().getId());
				}
				newEntry.setOwner(user);
			}
		}

		if (sendto.isReviewerIdSet()) {
			if (sendto.getUserReviewer().isIdSet()) {
				entity.User usr = userDao.findOne(sendto.getUserReviewer().getId());
				if (usr == null) {
					throw new UserNotFoundException(sendto.getUserReviewer().getId());
				}
				newEntry.setReviewer(usr);
			}

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

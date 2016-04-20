package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import dao.ReportDao;
import dao.UserDao;
import entity.Report;
import exceptions.ReportNotFoundException;
import exceptions.UserNotFoundException;
import sendto.ReportSendto;
import service.ReportService;

public class ReportServiceImpl implements ReportService {

	private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
	private ReportDao reportDao;

	private UserDao userDao;

	public ReportServiceImpl(ReportDao reportDao, UserDao userDao) {
		this.reportDao = reportDao;
		this.userDao = userDao;

	}

	@Transactional
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

		ReportSendto.User user = new ReportSendto.User();
		user.setOwner_id(report.getOwner().getId());
		user.setReviewer_id(report.getReviewer().getId());
		ret.setOwner(user);
		ret.setReviewer(user);

		return ret;
	}

	@Transactional
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

	@Transactional
	@Override
	public ReportSendto save(ReportSendto report) {
		report.setId(1L);
		Report newEntry = new Report();
		setUpReport(report, newEntry);
		return toReportSendto(reportDao.save(newEntry));
	}

	@Transactional
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

	@Transactional
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
			if (sendto.getOwner().isOwnerIdSet()) {
				logger.debug("find user in setUpReport id: {}", sendto.getOwner().getOwner_id());
				entity.User user = userDao.findOne(sendto.getOwner().getOwner_id());
				if (user == null) {
					throw new UserNotFoundException(sendto.getOwner().getOwner_id());
				}
				newEntry.setOwner(user);
			}
		}
		if (sendto.isReviewerIdSet()) {
			if (sendto.getReviewer().isReviewerIdSet()) {
				entity.User user = userDao.findOne(sendto.getReviewer().getReviewer_id());
				if (user == null) {
					throw new UserNotFoundException(sendto.getReviewer().getReviewer_id());
				}
				newEntry.setReviewer(user);
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

	@Override
	public ReportSendto findByOwnerId(long owner_id) {
		return toReportSendto(reportDao.findByOwnerId(owner_id));
	}
}

package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import dao.ReportDao;
import dao.StatusChangesDao;
import dao.UserDao;
import entity.StatusChanges;
import exceptions.ReportNotFoundException;
import exceptions.StatusChangesNotFoundException;
import exceptions.UserNotFoundException;
import resources.specification.StatusChangesSpecification;
import sendto.StatusChangesSendto;
import service.StatusChangesService;

public class StatusChangesServiceImpl implements StatusChangesService {

	private static final Logger logger = LoggerFactory.getLogger(StatusChangesServiceImpl.class);
	private StatusChangesDao statusChangesDao;
	private ReportDao reportDao;
	private UserDao userDao;

	public StatusChangesServiceImpl(StatusChangesDao statusDao, ReportDao reportDao, UserDao userDao) {
		this.statusChangesDao = statusDao;
		this.reportDao = reportDao;
		this.userDao = userDao;
	}

	@Transactional
	@Override
	public StatusChangesSendto retrieve(long id) {
		StatusChanges status = statusChangesDao.findOne(id);
		if (status == null) {
			throw new StatusChangesNotFoundException(id);
		}
		return toStatusChangesSendto(status);
	}

	private StatusChangesSendto toStatusChangesSendto(StatusChanges status) {
		StatusChangesSendto ret = new StatusChangesSendto();
		ret.setId(status.getId());
		ret.setCreatedDate(status.getCreatedDate());
		ret.setComment(status.getComment());
		ret.setValue(status.getValue());

		StatusChangesSendto.Report rpt = new StatusChangesSendto.Report();
		rpt.setId(status.getReport().getId());
		ret.setReport(rpt);

		StatusChangesSendto.User user = new StatusChangesSendto.User();
		user.setRevisor_id(status.getUser().getId());
		ret.setUser(user);

		return ret;
	}

	@Transactional
	@Override
	public void delete(long id) {
		try {
			StatusChanges statusChanges = statusChangesDao.findOne(id);
			if (statusChanges == null) {
				throw new StatusChangesNotFoundException(id);
			}
			statusChangesDao.delete(statusChanges);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			throw new StatusChangesNotFoundException(id);
		}
	}

	@Transactional
	@Override
	public StatusChangesSendto save(StatusChangesSendto statusChanges) {
		statusChanges.setId(null);
		StatusChanges newEntry = new StatusChanges();
		setUpStatusChanges(statusChanges, newEntry);
		return toStatusChangesSendto(statusChangesDao.save(newEntry));
	}

	@Transactional
	@Override
	public Page<StatusChangesSendto> findAll(StatusChangesSpecification spec, Pageable pageable) {
		List<StatusChangesSendto> sendto = new ArrayList<StatusChangesSendto>();
		Page<StatusChanges> status = statusChangesDao.findAll(spec, pageable);
		for (StatusChanges statusChanges : status) {
			sendto.add(toStatusChangesSendto(statusChanges));
		}
		Page<StatusChangesSendto> rets = new PageImpl<StatusChangesSendto>(sendto, pageable, status.getTotalElements());
		return rets;
	}

	@Transactional
	@Override
	public StatusChangesSendto update(long id, StatusChangesSendto updated) {
		StatusChanges status = statusChangesDao.findOne(id);
		if (status == null) {
			throw new StatusChangesNotFoundException(id);
		}
		setUpStatusChanges(updated, status);
		return toStatusChangesSendto(statusChangesDao.save(status));
	}

	private void setUpStatusChanges(StatusChangesSendto sendto, StatusChanges newEntry) {
		if (sendto.isCommentSet()) {
			newEntry.setComment(sendto.getComment());
		}
		if (sendto.isCreatedDateSet()) {
			newEntry.setCreatedDate(sendto.getCreatedDate());
		}
		if (sendto.isReportSet()) {
			if (sendto.getReport().isIdSet()) {
				entity.Report report = reportDao.findOne(sendto.getReport().getId());
				if (report == null) {
					throw new ReportNotFoundException(sendto.getReport().getId());
				}
				newEntry.setReport(report);
			}
		}
		if (sendto.isUserSet()) {
			if (sendto.getUser().isIdSet()) {
				logger.debug("find user in setUpStatusChanges id: {}", sendto.getUser().getRevisor_id());
				entity.User user = userDao.findOne(sendto.getUser().getRevisor_id());
				if (user == null) {
					throw new UserNotFoundException(sendto.getUser().getRevisor_id());
				}
				newEntry.setUser(user);
			}
		}
		if (sendto.isValueSet()) {
			newEntry.setValue(sendto.getValue());
		}
	}

}

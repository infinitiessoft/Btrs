package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import resources.specification.StatusChangeSpecification;
import sendto.StatusChangeSendto;
import service.StatusChangeService;
import dao.ReportDao;
import dao.StatusChangeDao;
import dao.UserDao;
import entity.StatusChange;
import entity.StatusEnum;
import exceptions.ReportNotFoundException;
import exceptions.StatusChangesNotFoundException;

public class StatusChangeServiceImpl implements StatusChangeService {

	private static final Logger logger = LoggerFactory
			.getLogger(StatusChangeServiceImpl.class);
	private StatusChangeDao statusChangesDao;
	private ReportDao reportDao;
	private UserDao userDao;

	public StatusChangeServiceImpl(StatusChangeDao statusDao,
			ReportDao reportDao, UserDao userDao) {
		this.statusChangesDao = statusDao;
		this.reportDao = reportDao;
		this.userDao = userDao;
	}

	@Transactional("transactionManager")
	@Override
	public StatusChangeSendto retrieve(long id) {
		StatusChange status = statusChangesDao.findOne(id);
		if (status == null) {
			throw new StatusChangesNotFoundException(id);
		}
		return toStatusChangesSendto(status);
	}

	private StatusChangeSendto toStatusChangesSendto(StatusChange status) {
		StatusChangeSendto ret = new StatusChangeSendto();
		ret.setId(status.getId());
		ret.setCreatedDate(status.getCreatedDate());
		ret.setComment(status.getComment());
		ret.setValue(status.getValue().name());

		StatusChangeSendto.Report rpt = new StatusChangeSendto.Report();
		rpt.setId(status.getReport().getId());
		ret.setReport(rpt);

		StatusChangeSendto.User user = new StatusChangeSendto.User();
		user.setId(status.getUser().getId());
		user.setFirstname(status.getUser().getUserShared().getFirstName());
		user.setLastname(status.getUser().getUserShared().getLastName());
		ret.setUser(user);

		return ret;
	}

	@Transactional("transactionManager")
	@Override
	public void delete(long id) {
		try {
			StatusChange statusChanges = statusChangesDao.findOne(id);
			if (statusChanges == null) {
				throw new StatusChangesNotFoundException(id);
			}
			statusChangesDao.delete(statusChanges);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			throw new StatusChangesNotFoundException(id);
		}
	}

	@Transactional("transactionManager")
	@Override
	public StatusChangeSendto save(StatusChangeSendto statusChanges) {
		statusChanges.setId(null);
		StatusChange newEntry = new StatusChange();
		setUpStatusChanges(statusChanges, newEntry);
		return toStatusChangesSendto(statusChangesDao.save(newEntry));
	}

	@Transactional("transactionManager")
	@Override
	public Page<StatusChangeSendto> findAll(StatusChangeSpecification spec,
			Pageable pageable) {
		List<StatusChangeSendto> sendto = new ArrayList<StatusChangeSendto>();
		Page<StatusChange> status = statusChangesDao.findAll(spec, pageable);
		for (StatusChange statusChanges : status) {
			sendto.add(toStatusChangesSendto(statusChanges));
		}
		Page<StatusChangeSendto> rets = new PageImpl<StatusChangeSendto>(
				sendto, pageable, status.getTotalElements());
		return rets;
	}

	@Transactional("transactionManager")
	@Override
	public StatusChangeSendto update(long id, StatusChangeSendto updated) {
		StatusChange status = statusChangesDao.findOne(id);
		if (status == null) {
			throw new StatusChangesNotFoundException(id);
		}
		setUpStatusChanges(updated, status);
		return toStatusChangesSendto(statusChangesDao.save(status));
	}

	private void setUpStatusChanges(StatusChangeSendto sendto,
			StatusChange newEntry) {
		if (sendto.isCommentSet()) {
			newEntry.setComment(sendto.getComment());
		}
		if (sendto.isCreatedDateSet()) {
			newEntry.setCreatedDate(sendto.getCreatedDate());
		}
		if (sendto.isReportSet()) {
			if (sendto.getReport().isIdSet()) {
				entity.Report report = reportDao.findOne(sendto.getReport()
						.getId());
				if (report == null) {
					throw new ReportNotFoundException(sendto.getReport()
							.getId());
				}
				newEntry.setReport(report);
			}
		}
		// if (sendto.isUserSet()) {
		// if (sendto.getUser().isIdSet()) {
		// logger.debug("find user in setUpStatusChanges id: {}", sendto
		// .getUser().getRevisor_id());
		// entity.User user = userDao.findOne(sendto.getUser()
		// .getRevisor_id());
		// if (user == null) {
		// throw new UserNotFoundException(sendto.getUser()
		// .getRevisor_id());
		// }
		// newEntry.setUser(user);
		// }
		// }
		if (sendto.isValueSet()) {
			newEntry.setValue(StatusEnum.valueOf(sendto.getValue()));
		}
	}

}

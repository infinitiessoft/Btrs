package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import resources.specification.StatusChangeSpecification;
import sendto.StatusChangeSendto;
import service.StatusChangeService;
import attendance.dao.EmployeeDao;
import attendance.entity.Employee;
import dao.StatusChangeDao;
import entity.StatusChange;
import exceptions.StatusChangesNotFoundException;

public class StatusChangeServiceImpl implements StatusChangeService {

	// private static final Logger logger = LoggerFactory
	// .getLogger(StatusChangeServiceImpl.class);
	private StatusChangeDao statusChangesDao;
	// private ReportDao reportDao;
	private EmployeeDao employeeDao;

	public StatusChangeServiceImpl(StatusChangeDao statusDao,
			EmployeeDao employeeDao) {
		this.statusChangesDao = statusDao;
		this.employeeDao = employeeDao;
	}

	@Transactional("transactionManager")
	@Override
	public StatusChangeSendto retrieve(StatusChangeSpecification spec) {
		StatusChange status = statusChangesDao.findOne(spec);
		if (status == null) {
			throw new StatusChangesNotFoundException();
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
		Employee owner = employeeDao
				.findOne(status.getUser().getUserSharedId());
		String name = owner.getName();
		user.setId(status.getUser().getId());
		user.setName(name);
		ret.setUser(user);

		return ret;
	}

	// @Transactional("transactionManager")
	// @Override
	// public void delete(StatusChangeSpecification spec) {
	// try {
	// StatusChange statusChanges = statusChangesDao.findOne(spec);
	// if (statusChanges == null) {
	// throw new StatusChangesNotFoundException(id);
	// }
	// statusChangesDao.delete(statusChanges);
	// } catch (org.springframework.dao.EmptyResultDataAccessException e) {
	// throw new StatusChangesNotFoundException(id);
	// }
	// }

	// @Transactional("transactionManager")
	// @Override
	// public StatusChangeSendto save(StatusChangeSendto statusChanges) {
	// statusChanges.setId(null);
	// StatusChange newEntry = new StatusChange();
	// setUpStatusChanges(statusChanges, newEntry);
	// return toStatusChangesSendto(statusChangesDao.save(newEntry));
	// }

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

	// @Transactional("transactionManager")
	// @Override
	// public StatusChangeSendto update(long id, StatusChangeSendto updated) {
	// StatusChange status = statusChangesDao.findOne(id);
	// if (status == null) {
	// throw new StatusChangesNotFoundException(id);
	// }
	// setUpStatusChanges(updated, status);
	// return toStatusChangesSendto(statusChangesDao.save(status));
	// }

	// private void setUpStatusChanges(StatusChangeSendto sendto,
	// StatusChange newEntry) {
	// if (sendto.isCommentSet()) {
	// newEntry.setComment(sendto.getComment());
	// }
	// }

}

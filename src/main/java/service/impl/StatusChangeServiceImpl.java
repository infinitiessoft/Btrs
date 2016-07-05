package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

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
	public StatusChangeSendto retrieve(Specification<StatusChange> spec) {
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

	@Transactional("transactionManager")
	@Override
	public Page<StatusChangeSendto> findAll(Specification<StatusChange> spec,
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

}

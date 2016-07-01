package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import resources.specification.AttendRecordSpecification;
import resources.specification.ReportSpecification;
import sendto.AttendRecordSendto;
import service.AttendRecordService;
import attendance.dao.AttendRecordDao;
import attendance.entity.AttendRecord;
import dao.ReportDao;
import dao.UserDao;
import entity.Report;
import entity.User;
import exceptions.AttendRecordNotFoundException;
import exceptions.UserNotFoundException;

public class DBAttendRecordService implements AttendRecordService {

	private UserDao userDao;
	private AttendRecordDao attendRecordDao;
	private ReportDao reportDao;

	public DBAttendRecordService(AttendRecordDao attendRecordDao,
			ReportDao reportDao, UserDao userDao) {
		this.attendRecordDao = attendRecordDao;
		this.reportDao = reportDao;
		this.userDao = userDao;
	}

	// @Transactional("transactionManagerAttendance")
	// @Override
	// public AttendRecordSendto retrieve(long id) {
	// AttendRecord record = attendRecordDao.findOne(id);
	//
	// if (record == null) {
	// throw new AttendRecordNotFoundException(id);
	// }
	// record.getEmployee().getId();
	// return AttendRecordSendto.toAttendRecordSendto(record);
	// }

	@Transactional("transactionManagerAttendance")
	@Override
	public AttendRecordSendto retrieve(AttendRecordSpecification spec) {
		if (spec.getApplicantId() != null) {
			User user = userDao.findOne(spec.getApplicantId());
			if (user == null) {
				throw new UserNotFoundException(spec.getApplicantId());
			}
			spec.setApplicantId(user.getUserSharedId());
		}
		AttendRecord record = attendRecordDao.findOne(spec);
		if (record == null) {
			throw new AttendRecordNotFoundException();
		}
		return AttendRecordSendto.toAttendRecordSendto(record);
	}

	@Transactional("transactionManagerAttendance")
	@Override
	public Page<AttendRecordSendto> findAll(AttendRecordSpecification spec,
			Pageable pageable) {
		if (spec.getApplicantId() != null) {
			User user = userDao.findOne(spec.getApplicantId());
			if (user == null) {
				throw new UserNotFoundException(spec.getApplicantId());
			}
			spec.setApplicantId(user.getUserSharedId());
		}

		List<AttendRecordSendto> transfers = new ArrayList<AttendRecordSendto>();
		Page<AttendRecord> attendRecords = attendRecordDao.findAll(spec,
				pageable);
		for (AttendRecord attendRecord : attendRecords) {
			transfers
					.add(AttendRecordSendto.toAttendRecordSendto(attendRecord));
		}
		Page<AttendRecordSendto> rets = new PageImpl<AttendRecordSendto>(
				transfers, pageable, attendRecords.getTotalElements());
		return rets;
	}

	@Transactional("transactionManagerAttendance")
	@Override
	public Page<AttendRecordSendto> findAllAvailableRecords(
			AttendRecordSpecification spec, Pageable pageable) {

		ReportSpecification reportSpec = new ReportSpecification();
		reportSpec.setApplicantId(spec.getApplicantId());
		List<Report> reports = reportDao.findAll(reportSpec);
		List<Long> ids = new ArrayList<Long>();
		for (Report report : reports) {
			if (report.getAttendanceRecordId() != null) {
				ids.add(report.getAttendanceRecordId());
			}
		}
		if (spec.getApplicantId() != null) {
			User user = userDao.findOne(spec.getApplicantId());
			if (user == null) {
				throw new UserNotFoundException(spec.getApplicantId());
			}
			spec.setApplicantId(user.getUserSharedId());
		}
		spec.setExclusion(ids);
		
		List<AttendRecordSendto> transfers = new ArrayList<AttendRecordSendto>();
		Page<AttendRecord> attendRecords = attendRecordDao.findAll(spec,
				pageable);
		for (AttendRecord attendRecord : attendRecords) {
			transfers
					.add(AttendRecordSendto.toAttendRecordSendto(attendRecord));
		}
		Page<AttendRecordSendto> rets = new PageImpl<AttendRecordSendto>(
				transfers, pageable, attendRecords.getTotalElements());
		return rets;
	}

}

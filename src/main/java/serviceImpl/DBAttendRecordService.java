package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import resources.specification.AttendRecordSpecification;
import resources.specification.ReportSpecification;
import sendto.AttendRecordSendto;
import service.AttendRecordService;
import attendance.dao.AttendRecordDao;
import attendance.entity.AttendRecord;
import dao.ReportDao;
import entity.Report;
import exceptions.AttendRecordNotFoundException;

public class DBAttendRecordService implements AttendRecordService {

	private AttendRecordDao attendRecordDao;
	private ReportDao reportDao;

	public DBAttendRecordService(AttendRecordDao attendRecordDao,
			ReportDao reportDao) {
		this.attendRecordDao = attendRecordDao;
		this.reportDao = reportDao;
	}

	@Transactional("transactionManagerAttendance")
	@Override
	public AttendRecordSendto retrieve(long id) {
		AttendRecord record = attendRecordDao.findOne(id);

		if (record == null) {
			throw new AttendRecordNotFoundException(id);
		}
		record.getEmployee().getId();
		return AttendRecordSendto.toAttendRecordSendto(record);
	}

	@Transactional("transactionManagerAttendance")
	@Override
	public AttendRecordSendto retrieve(Specification<AttendRecord> spec) {
		AttendRecord record = attendRecordDao.findOne(spec);
		if (record == null) {
			throw new AttendRecordNotFoundException();
		}
		return AttendRecordSendto.toAttendRecordSendto(record);
	}

	@Transactional("transactionManagerAttendance")
	@Override
	public Page<AttendRecordSendto> findAll(Specification<AttendRecord> spec,
			Pageable pageable) {
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
			ids.add(report.getAttendanceRecordId());
		}
		spec.setExclusion(ids);
		return findAll(spec, pageable);
	}

}

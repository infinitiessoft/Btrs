package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import resources.specification.AttendRecordSpecification;
import sendto.AttendRecordSendto;
import attendance.entity.AttendRecord;

public interface AttendRecordService {

	public AttendRecordSendto retrieve(long id);

	public AttendRecordSendto retrieve(Specification<AttendRecord> spec);

	public Page<AttendRecordSendto> findAll(Specification<AttendRecord> spec,
			Pageable pageable);

	public Page<AttendRecordSendto> findAllAvailableRecords(
			AttendRecordSpecification spec, Pageable pageable);

}

package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import resources.specification.AttendRecordSpecification;
import sendto.AttendRecordSendto;

public interface AttendRecordService {

	// public AttendRecordSendto retrieve(long id);

	public AttendRecordSendto retrieve(AttendRecordSpecification spec);

	public Page<AttendRecordSendto> findAll(AttendRecordSpecification spec,
			Pageable pageable);

	public Page<AttendRecordSendto> findAllAvailableRecords(
			AttendRecordSpecification spec, Pageable pageable);

}

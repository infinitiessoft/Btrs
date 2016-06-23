package attendance.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import attendance.entity.AttendRecordType;

public interface AttendRecordTypeDao extends
		PagingAndSortingRepository<AttendRecordType, Long>,
		JpaSpecificationExecutor<AttendRecordType> {

}

package attendance.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import attendance.entity.AttendRecord;

public interface AttendRecordDao extends
		PagingAndSortingRepository<AttendRecord, Long>,
		JpaSpecificationExecutor<AttendRecord> {

}

package attendance.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import attendance.entity.Employee;

public interface EmployeeDao extends
		PagingAndSortingRepository<Employee, Long>,
		JpaSpecificationExecutor<Employee> {
	Employee findByUsername(String username);
}

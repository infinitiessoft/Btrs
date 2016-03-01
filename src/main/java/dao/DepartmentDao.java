
package dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import entity.Department;
import sendto.DepartmentSendto;

public interface DepartmentDao extends JpaSpecificationExecutor<Department> {

	Department findOne(long id);

	void delete(long id);

	Department findByName(String name);

	Department save(Department dept);

	Collection<Department> findAll();

	Department save(DepartmentSendto department);

}

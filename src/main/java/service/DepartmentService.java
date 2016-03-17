package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import entity.Department;
import sendto.DepartmentSendto;

public interface DepartmentService {

	public DepartmentSendto retrieve(long id);

	public void delete(long id);

	public DepartmentSendto findByName(String name);

	public DepartmentSendto save(DepartmentSendto department);

	DepartmentSendto update(long id, DepartmentSendto department);

	Page<DepartmentSendto> findAll(Specification<Department> spec, Pageable pageable);

}

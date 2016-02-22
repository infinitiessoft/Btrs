package service;

import java.util.Collection;

import entity.Department;

public interface DepartmentService {

	public Department retrieve(long id);

	public void delete(long id);

	public void save(Department department);

	public Collection<Department> findAll();

	public void update(long id, Department department);

}

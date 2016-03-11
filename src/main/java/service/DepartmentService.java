package service;

import java.util.Collection;

import sendto.DepartmentSendto;

public interface DepartmentService {

	public DepartmentSendto retrieve(long id);

	public void delete(long id);

	public DepartmentSendto findByName(String name);

	public DepartmentSendto save(DepartmentSendto department);

	public DepartmentSendto update(long id);

	public Collection<DepartmentSendto> findAll();

}

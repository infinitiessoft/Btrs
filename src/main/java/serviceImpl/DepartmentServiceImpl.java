package serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.DepartmentDao;
import entity.Department;
import exceptions.DepartmentNotFoundException;
import sendto.DepartmentSendto;
import service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentDao departmentDao;

	public DepartmentServiceImpl(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	public DepartmentSendto retrieve(long id) {
		Department department = departmentDao.findOne(id);
		if (department == null) {
			throw new DepartmentNotFoundException(id);
		}
		return toDepartmentSendto(department);
	}

	private DepartmentSendto toDepartmentSendto(Department department) {
		DepartmentSendto ret = new DepartmentSendto();
		ret.setId(department.getId());
		ret.setName(department.getName());
		ret.setComment(department.getComment());
		return ret;
	}

	@Override
	public void delete(long id) {
		departmentDao.delete(id);

	}

	@Override
	public DepartmentSendto findByName(String name) {
		return toDepartmentSendto(departmentDao.findByName(name));
	}

	@Override
	public DepartmentSendto save(DepartmentSendto department) {
		department.setId(null);
		Department dept = new Department();
		dept = departmentDao.save(dept);
		return toDepartmentSendto(dept);
	}

	@Override
	public Collection<DepartmentSendto> findAll() {
		List<DepartmentSendto> sendto = new ArrayList<DepartmentSendto>();
		Collection<Department> department = departmentDao.findAll();
		for (Department department1 : department) {
			sendto.add(toDepartmentSendto(department1));
		}
		return sendto;
	}

	@Override
	public DepartmentSendto update(long id, DepartmentSendto department) {
		Department dept = departmentDao.findOne(id);
		if (dept == null) {
			throw new DepartmentNotFoundException(id);
		}
		return toDepartmentSendto(departmentDao.save(department));
	}

}
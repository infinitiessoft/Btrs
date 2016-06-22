package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import sendto.DepartmentSendto;
import service.DepartmentService;
import dao.DepartmentDao;
import entity.Department;
import exceptions.DepartmentNotFoundException;

public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentDao departmentDao;

	public DepartmentServiceImpl(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Transactional("transactionManager")
	@Override
	public DepartmentSendto retrieve(long id) {
		Department department = departmentDao.findOne(id);
		if (department == null) {
			throw new DepartmentNotFoundException(id);
		}
		return toDepartmentSendto(department);
	}

	@Transactional("transactionManager")
	@Override
	public void delete(long id) {
		try {
			Department department = departmentDao.findOne(id);
			if (department == null) {
				throw new DepartmentNotFoundException(id);
			}
			departmentDao.delete(department);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			throw new DepartmentNotFoundException(id);
		}

	}

	@Transactional("transactionManager")
	@Override
	public DepartmentSendto findByName(String name) {
		return toDepartmentSendto(departmentDao.findByName(name));
	}

	@Transactional("transactionManager")
	@Override
	public DepartmentSendto save(DepartmentSendto department) {
		department.setId(null);
		Department newEntry = new Department();
		setUpDepartment(department, newEntry);
		return toDepartmentSendto(departmentDao.save(newEntry));
	}

	@Transactional("transactionManager")
	@Override
	public Page<DepartmentSendto> findAll(Specification<Department> spec, Pageable pageable) {
		List<DepartmentSendto> sendtos = new ArrayList<DepartmentSendto>();
		Page<Department> departments = departmentDao.findAll(spec, pageable);
		for (Department department : departments) {
			sendtos.add(toDepartmentSendto(department));
		}
		Page<DepartmentSendto> rets = new PageImpl<DepartmentSendto>(sendtos, pageable, departments.getTotalElements());
		return rets;
	}

	@Transactional("transactionManager")
	@Override
	public DepartmentSendto update(long id, DepartmentSendto updated) {
		Department dept = departmentDao.findOne(id);
		if (dept == null) {
			throw new DepartmentNotFoundException(id);
		}
		setUpDepartment(updated, dept);
		return toDepartmentSendto(departmentDao.save(dept));
	}

	private void setUpDepartment(DepartmentSendto sendto, Department newEntry) {
		if (sendto.isNameSet()) {
			newEntry.setName(sendto.getName());
		}

		if (sendto.isCommentSet()) {
			newEntry.setComment(sendto.getComment());
		}

	}

	private DepartmentSendto toDepartmentSendto(Department department) {
		DepartmentSendto ret = new DepartmentSendto();
		ret.setId(department.getId());
		ret.setName(department.getName());
		ret.setComment(department.getComment());
		return ret;
	}

}
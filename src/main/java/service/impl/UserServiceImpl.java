package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import resources.specification.UserSpecification;
import sendto.UserSendto;
import service.UserService;
import attendance.dao.EmployeeDao;
import attendance.entity.Employee;
import dao.JobTitleDao;
import dao.RoleDao;
import dao.UserDao;
import dao.UserRoleDao;
import entity.JobTitle;
import entity.Role;
import entity.RoleEnum;
import entity.User;
import entity.UserRole;
import exceptions.JobTitleNotFoundException;
import exceptions.UserNotFoundException;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private EmployeeDao employeeDao;
	private JobTitleDao jobTitleDao;
	private RoleDao roleDao;
	private UserRoleDao userRoleDao;

	public UserServiceImpl(UserDao userDao, JobTitleDao jobTitleDao,
			EmployeeDao employeeDao, RoleDao roleDao, UserRoleDao userRoleDao) {
		this.userDao = userDao;
		this.jobTitleDao = jobTitleDao;
		this.employeeDao = employeeDao;
		this.roleDao = roleDao;
		this.userRoleDao = userRoleDao;
	}

	@Override
	public UserSendto retrieve(long id) {
		Employee employee = employeeDao.findOne(id);
		if (employee == null) {
			throw new UserNotFoundException(id);
		}
		User user = findOrSave(id);
		return toUserSendto(employee, user);
	}

	@Override
	public synchronized User findOrSave(long userSharedId) {
		User user = userDao.findByUserSharedId(userSharedId);
		if (user == null) {
			UserSendto userSendto = new UserSendto();
			UserSendto.JobTitle jobTitle = new UserSendto.JobTitle();
			jobTitle.setId(1L);
			userSendto.setJobTitle(jobTitle);
			user = save(userSharedId, userSendto);
		}
		return user;
	}

	private UserSendto toUserSendto(Employee employee, User user) {
		UserSendto ret = new UserSendto();
		ret.setId(employee.getId());

		UserSendto.JobTitle jobTitle = new UserSendto.JobTitle();
		jobTitle.setId(user.getJobTitle().getId());
		jobTitle.setName(user.getJobTitle().getName());
		ret.setJobTitle(jobTitle);

		ret.setEmail(employee.getEmail());
		ret.setDateofjoined(employee.getDateofjoined());
		ret.setName(employee.getName());
		ret.setGender(employee.getGender());
		ret.setUsername(employee.getUsername());

		for (UserRole userRole : user.getUserRole()) {
			UserSendto.Role role = new UserSendto.Role();
			role.setId(userRole.getRole().getId());
			role.setValue(userRole.getRole().getValue().name());
			ret.getRoles().add(role);
		}

		return ret;
	}

	private User save(long userSharedId, UserSendto user) {
		Employee employee = employeeDao.findOne(userSharedId);
		if (employee == null) {
			throw new UserNotFoundException(userSharedId);
		}

		User newEntry = new User();
		setUpUser(user, newEntry);
		newEntry.setUserSharedId(employee.getId());
		newEntry = userDao.save(newEntry);

		Role role = roleDao.findByValue(RoleEnum.EMPLOYEE);
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(newEntry);
		userRoleDao.save(userRole);
		newEntry.getUserRole().add(userRole);
		role.getUserRole().add(userRole);

		return newEntry;
	}

	private void setUpUser(UserSendto user, User newEntry) {
		if (user.isJobTitleSet() && user.getJobTitle().isIdSet()) {
			JobTitle jobTitle = jobTitleDao.findOne(user.getJobTitle().getId());
			if (jobTitle == null) {
				throw new JobTitleNotFoundException(user.getJobTitle().getId());
			}
			newEntry.setJobTitle(jobTitle);
		}
	}

	@Override
	public Page<UserSendto> findAll(UserSpecification spec, Pageable pageable) {
		List<UserSendto> sendto = new ArrayList<UserSendto>();
		Page<Employee> employees = employeeDao.findAll(spec, pageable);
		for (Employee employee : employees) {
			User user = findOrSave(employee.getId());
			sendto.add(toUserSendto(employee, user));
		}
		Page<UserSendto> rets = new PageImpl<UserSendto>(sendto, pageable,
				employees.getTotalElements());
		return rets;
	}

	@Override
	public UserSendto update(long id, UserSendto updated) {
		Employee employee = employeeDao.findOne(id);
		if (employee == null) {
			throw new UserNotFoundException(id);
		}
		User user = findOrSave(id);
		setUpUser(updated, user);
		return toUserSendto(employee, userDao.save(user));
	}

}
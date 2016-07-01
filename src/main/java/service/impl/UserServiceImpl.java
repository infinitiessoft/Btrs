package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import sendto.UserSendto;
import service.UserService;
import attendance.dao.EmployeeDao;
import attendance.entity.Employee;
import dao.JobTitleDao;
import dao.UserDao;
import entity.JobTitle;
import entity.User;
import entity.UserRole;
import exceptions.JobTitleNotFoundException;
import exceptions.UserNotFoundException;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private EmployeeDao employeeDao;
	private JobTitleDao jobTitleDao;

	public UserServiceImpl(UserDao userDao, JobTitleDao jobTitleDao,
			EmployeeDao employeeDao) {
		this.userDao = userDao;
		this.jobTitleDao = jobTitleDao;
		this.employeeDao = employeeDao;
	}

	@Transactional("transactionManager")
	@Override
	public UserSendto retrieve(Specification<User> spec) {
		User user = userDao.findOne(spec);
		if (user == null) {
			throw new UserNotFoundException();
		}
		Employee employee = employeeDao.findOne(user.getUserSharedId());
		if (employee == null) {
			throw new UserNotFoundException();
		}

		return toUserSendto(employee, user);
	}

	private UserSendto toUserSendto(Employee employee, User user) {
		UserSendto ret = new UserSendto();
		ret.setId(user.getId());

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

	private void setUpUser(UserSendto user, User newEntry) {
		if (user.isJobTitleSet() && user.getJobTitle().isIdSet()) {
			JobTitle jobTitle = jobTitleDao.findOne(user.getJobTitle().getId());
			if (jobTitle == null) {
				throw new JobTitleNotFoundException(user.getJobTitle().getId());
			}
			newEntry.setJobTitle(jobTitle);
		}
	}

	@Transactional("transactionManager")
	@Override
	public Page<UserSendto> findAll(Specification<User> spec, Pageable pageable) {
		List<UserSendto> sendto = new ArrayList<UserSendto>();
		Page<User> users = userDao.findAll(spec, pageable);
		for (User user : users) {
			Employee employee = employeeDao.findOne(user.getUserSharedId());
			if (employee != null) {
				sendto.add(toUserSendto(employee, user));
			}
		}
		Page<UserSendto> rets = new PageImpl<UserSendto>(sendto, pageable,
				users.getTotalElements());
		return rets;
	}

	@Transactional("transactionManager")
	@Override
	public UserSendto update(Specification<User> spec, UserSendto updated) {
		User user = userDao.findOne(spec);
		if (user == null) {
			throw new UserNotFoundException();
		}
		Employee employee = employeeDao.findOne(user.getUserSharedId());
		if (employee == null) {
			throw new UserNotFoundException();
		}
		setUpUser(updated, user);
		return toUserSendto(employee, userDao.save(user));
	}

}
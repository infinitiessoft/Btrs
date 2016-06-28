package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import resources.specification.UserSpecification;
import sendto.UserSendto;
import service.UserService;
import attendance.dao.EmployeeDao;
import attendance.entity.Employee;
import dao.JobTitleDao;
import dao.UserDao;
import entity.JobTitle;
import entity.User;
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
	public UserSendto retrieve(long id) {
		Employee employee = employeeDao.findOne(id);
		if (employee == null) {
			throw new UserNotFoundException(id);
		}
		User user = userDao.findByUserSharedId(id);
		if (user == null) {
			UserSendto userSendto = new UserSendto();
			UserSendto.JobTitle jobTitle = new UserSendto.JobTitle();
			jobTitle.setId(1L);
			userSendto.setJobTitle(jobTitle);
			user = save(id, userSendto);
		}
		return toUserSendto(employee, user);
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

		return ret;
	}

	@Transactional("transactionManager")
	@Override
	public User save(long userSharedId, UserSendto user) {
		Employee employee = employeeDao.findOne(userSharedId);
		if (employee == null) {
			throw new UserNotFoundException(userSharedId);
		}
		User newEntry = new User();
		setUpUser(user, newEntry);

		return userDao.save(newEntry);
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
	public Page<UserSendto> findAll(UserSpecification spec, Pageable pageable) {
		List<UserSendto> sendto = new ArrayList<UserSendto>();
		Page<Employee> employees = employeeDao.findAll(spec, pageable);
		for (Employee employee : employees) {
			User user = userDao.findByUserSharedId(employee.getId());
			if (user == null) {
				UserSendto userSendto = new UserSendto();
				UserSendto.JobTitle jobTitle = new UserSendto.JobTitle();
				jobTitle.setId(1L);
				userSendto.setJobTitle(jobTitle);
				user = save(employee.getId(), userSendto);
			}
			sendto.add(toUserSendto(employee, user));
		}
		Page<UserSendto> rets = new PageImpl<UserSendto>(sendto, pageable,
				employees.getTotalElements());
		return rets;
	}

	@Transactional("transactionManager")
	@Override
	public UserSendto update(long id, UserSendto updated) {
		Employee employee = employeeDao.findOne(id);
		if (employee == null) {
			throw new UserNotFoundException(id);
		}
		User user = userDao.findByUserSharedId(id);
		if (user == null) {
			UserSendto userSendto = new UserSendto();
			UserSendto.JobTitle jobTitle = new UserSendto.JobTitle();
			jobTitle.setId(1L);
			userSendto.setJobTitle(jobTitle);
			user = save(id, userSendto);
		}
		setUpUser(updated, user);
		return toUserSendto(employee, userDao.save(user));
	}

}
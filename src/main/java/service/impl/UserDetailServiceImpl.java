package service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import security.UserDetail;
import sendto.UserSendto;
import service.UserService;
import attendance.dao.EmployeeDao;
import attendance.entity.Employee;
import dao.UserDao;
import entity.User;

public class UserDetailServiceImpl implements UserDetailsService {

	private EmployeeDao employeeDao;
	private UserDao userDao;

	private UserService userService;

	public UserDetailServiceImpl(EmployeeDao employeeDao, UserDao userDao,
			UserService userService) {
		this.employeeDao = employeeDao;
		this.userDao = userDao;
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Employee employee = employeeDao.findByUsername(username);
		if (null == employee) {
			throw new UsernameNotFoundException("The employee with username "
					+ username + " was not found");
		}

		User user = userDao.findByUserSharedId(employee.getId());
		if (null == user) {
			UserSendto userSendto = new UserSendto();
			UserSendto.JobTitle jobTitle = new UserSendto.JobTitle();
			jobTitle.setId(1L);
			userSendto.setJobTitle(jobTitle);
			user = userService.save(employee.getId(), userSendto);
		}
		return new UserDetail(employee.getUsername(), employee.getPassword(),
				user);
	}

}

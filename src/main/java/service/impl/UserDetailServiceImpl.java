package service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import security.UserDetail;
import service.UserService;
import attendance.dao.EmployeeDao;
import attendance.entity.Employee;
import entity.User;

public class UserDetailServiceImpl implements UserDetailsService {

	private EmployeeDao employeeDao;
//	private UserDao userDao;

	private UserService userService;

	public UserDetailServiceImpl(EmployeeDao employeeDao,
			UserService userService) {
		this.employeeDao = employeeDao;
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

		User user = userService.findOrSave(employee.getId());
		return new UserDetail(employee.getUsername(), employee.getPassword(),
				user);
	}

}

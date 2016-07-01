package service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import resources.specification.JobTitleSpecification;
import security.UserDetail;
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

public class UserDetailServiceImpl implements UserDetailsService {

	private EmployeeDao employeeDao;
	private UserDao userDao;
	private RoleDao roleDao;
	private UserRoleDao userRoleDao;
	private JobTitleDao jobTitleDao;

	public UserDetailServiceImpl(EmployeeDao employeeDao, UserDao userDao,
			RoleDao roleDao, UserRoleDao userRoleDao, JobTitleDao jobTitleDao) {
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.userRoleDao = userRoleDao;
		this.employeeDao = employeeDao;
		this.jobTitleDao = jobTitleDao;
	}

	@Transactional("transactionManager")
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Employee employee = employeeDao.findByUsername(username);
		if (null == employee) {
			throw new UsernameNotFoundException("The employee with username "
					+ username + " was not found");
		}
		User user = userDao.findByUserSharedId(employee.getId());

		// create new user
		if (user == null) {
			user = new User();
			user.setUserSharedId(employee.getId());
			JobTitle jobTitle = jobTitleDao
					.findOne(new JobTitleSpecification());
			user.setJobTitle(jobTitle);
			user = userDao.save(user);

			Role role = roleDao.findByValue(RoleEnum.EMPLOYEE);
			UserRole userRole = new UserRole();
			userRole.setRole(role);
			userRole.setUser(user);
			userRoleDao.save(userRole);
			user.getUserRole().add(userRole);
			role.getUserRole().add(userRole);
		}

		return new UserDetail(employee.getUsername(), employee.getPassword(),
				user);
	}
}

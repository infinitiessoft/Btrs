package service.impl;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;

import service.impl.UserDetailServiceImpl;
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

public class UserDetailServiceImplTest extends ServiceTest {

	private EmployeeDao employeeDao;
	private UserDao userDao;
	private RoleDao roleDao;
	private UserRoleDao userRoleDao;
	private JobTitleDao jobTitleDao;
	private UserDetailServiceImpl userDetailService;

	private User user;
	private Employee employee;
	private JobTitle jobTitle;
	private Role role;

	@Before
	public void setUp() throws Exception {
		userDao = context.mock(UserDao.class);
		employeeDao = context.mock(EmployeeDao.class);
		roleDao = context.mock(RoleDao.class);
		userRoleDao = context.mock(UserRoleDao.class);
		jobTitleDao = context.mock(JobTitleDao.class);
		userDetailService = new UserDetailServiceImpl(employeeDao, userDao,
				roleDao, userRoleDao, jobTitleDao);

		user = new User();
		user.setId(1L);
		user.setUserSharedId(1L);

		employee = new Employee();
		employee.setId(1L);
		employee.setName("name");
		employee.setUsername("username");
		employee.setPassword("password");

		jobTitle = new JobTitle();
		jobTitle.setId(1L);
		jobTitle.setName("name");

		role = new Role();
		role.setId(1L);
		role.setValue(RoleEnum.EMPLOYEE);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoadUserByUsername() {
		context.checking(new Expectations() {

			{
				exactly(1).of(employeeDao).findByUsername(
						employee.getUsername());
				will(returnValue(employee));

				exactly(1).of(userDao).findByUserSharedId(employee.getId());
				will(returnValue(user));
			}
		});
		UserDetails ret = userDetailService.loadUserByUsername(employee
				.getUsername());
		assertEquals(employee.getUsername(), ret.getUsername());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCreateUser() {
		final User user = new User();
		final UserRole userRole = new UserRole();
		context.checking(new Expectations() {

			{
				exactly(1).of(employeeDao).findByUsername(
						employee.getUsername());
				will(returnValue(employee));

				exactly(1).of(userDao).findByUserSharedId(employee.getId());
				will(returnValue(null));

				exactly(1).of(userDao).save(with(any(User.class)));
				will(returnValue(user));

				exactly(1).of(jobTitleDao).findOne(
						with(any(Specification.class)));
				will(returnValue(jobTitle));

				exactly(1).of(roleDao).findByValue(RoleEnum.EMPLOYEE);
				will(returnValue(role));

				exactly(1).of(userRoleDao).save(with(any(UserRole.class)));
				will(returnValue(userRole));
			}
		});
		UserDetails ret = userDetailService.loadUserByUsername(employee
				.getUsername());
		assertEquals(employee.getUsername(), ret.getUsername());
		assertEquals(employee.getPassword(), ret.getPassword());
	}
}

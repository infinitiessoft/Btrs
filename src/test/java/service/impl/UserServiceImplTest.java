package service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;

import resources.specification.SimplePageRequest;
import resources.specification.UserSpecification;
import sendto.UserSendto;
import service.impl.UserServiceImpl;
import attendance.dao.EmployeeDao;
import attendance.entity.Employee;
import dao.JobTitleDao;
import dao.UserDao;
import entity.JobTitle;
import entity.User;

public class UserServiceImplTest extends ServiceTest {

	private UserDao userDao;
	private EmployeeDao employeeDao;
	private JobTitleDao jobTitleDao;
	private UserServiceImpl userService;
	private Specification<User> spec;
	private User user;
	private Employee employee;
	private JobTitle jobTitle;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		userDao = context.mock(UserDao.class);
		spec = context.mock(Specification.class);
		employeeDao = context.mock(EmployeeDao.class);
		jobTitleDao = context.mock(JobTitleDao.class);
		userService = new UserServiceImpl(userDao, jobTitleDao, employeeDao);
		user = new User();
		user.setId(1L);
		user.setUserSharedId(1L);

		employee = new Employee();
		employee.setId(1L);
		employee.setName("name");

		jobTitle = new JobTitle();
		jobTitle.setId(1L);
		jobTitle.setName("name");
		user.setJobTitle(jobTitle);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieve() {
		context.checking(new Expectations() {

			{
				exactly(1).of(userDao).findOne(spec);
				will(returnValue(user));

				exactly(1).of(employeeDao).findOne(user.getUserSharedId());
				will(returnValue(employee));
			}
		});
		UserSendto ret = userService.retrieve(spec);
		assertEquals(1l, ret.getId().longValue());

	}

	@Test
	public void testUpdate() {
		final UserSendto newEntry = new UserSendto();

		context.checking(new Expectations() {

			{
				exactly(1).of(userDao).save(user);
				will(returnValue(user));

				exactly(1).of(userDao).findOne(spec);
				will(returnValue(user));

				exactly(1).of(employeeDao).findOne(user.getUserSharedId());
				will(returnValue(employee));
			}
		});
		UserSendto ret = userService.update(spec, newEntry);
		assertEquals(1l, ret.getId().longValue());

	}

	@Test
	public void testFindAll() {
		final UserSpecification spec = new UserSpecification();
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id",
				"ASC");
		final List<User> users = new ArrayList<User>();
		users.add(user);
		final Page<User> page = new PageImpl<User>(users);
		context.checking(new Expectations() {

			{
				exactly(1).of(userDao).findAll(spec, pageable);
				will(returnValue(page));

				exactly(1).of(employeeDao).findOne(user.getUserSharedId());
				will(returnValue(employee));
			}
		});
		Page<UserSendto> rets = userService.findAll(spec, pageable);
		assertEquals(1, rets.getTotalElements());
		UserSendto ret = rets.iterator().next();
		assertEquals(user.getId(), ret.getId());
		// assertEquals(user.getLastLogin(), ret.getLastLogin());
	}
}

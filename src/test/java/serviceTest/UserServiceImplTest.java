package serviceTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import dao.DepartmentDao;
import dao.ReportDao;
import dao.UserDao;
import dao.UserSharedDao;
import entity.Department;
import entity.User;
import entity.UserShared;
import resources.specification.SimplePageRequest;
import resources.specification.UserSpecification;
import sendto.UserSendto;
import serviceImpl.UserServiceImpl;

public class UserServiceImplTest extends ServiceTest {

	private UserDao userDao;
	private DepartmentDao departmentDao;
	private UserSharedDao userSharedDao;
	private UserServiceImpl userService;
	private ReportDao reportDao;

	private User user;
	private Department department;
	private UserShared userShared;

	@Before
	public void setUp() throws Exception {
		userDao = context.mock(UserDao.class);
		departmentDao = context.mock(DepartmentDao.class);
		userSharedDao = context.mock(UserSharedDao.class);
		userService = new UserServiceImpl(userDao, departmentDao, userSharedDao);
		user = new User();
		user.setId(1L);

		user.setDepartment(department);
		user.setUserShared(userShared);

		department = new Department();
		department.setId(1L);
		user.setDepartment(department);

		userShared = new UserShared();
		userShared.setId(1L);
		user.setUserShared(userShared);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieve() {
		context.checking(new Expectations() {

			{
				exactly(1).of(userDao).findOne(1L);
				will(returnValue(user));
			}
		});
		UserSendto ret = userService.retrieve(1);
		assertEquals(1l, ret.getId().longValue());
		assertEquals(new Date(), ret.getLastLogin());
		assertEquals(department.getId().longValue(), ret.getDepartment().getId().longValue());
		assertEquals(userShared.getId().longValue(), ret.getUserShared().getId().longValue());
	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {

			{
				exactly(1).of(userDao).delete(user);

				exactly(1).of(userDao).findOne(1L);
				will(returnValue(user));
			}
		});
		userService.delete(1l);
	}

	@Test
	public void testSave() {
		final UserSendto newEntry = new UserSendto();
		UserSendto.Department departmentSendto = new UserSendto.Department();
		departmentSendto.setId(department.getId());
		UserSendto.UserShared userSharedSendto = new UserSendto.UserShared();
		userSharedSendto.setId(userShared.getId());
		newEntry.setDepartment(departmentSendto);
		newEntry.setUserShared(userSharedSendto);

		context.checking(new Expectations() {

			{
				exactly(1).of(departmentDao).findOne(newEntry.getDepartment().getId());
				will(returnValue(department));

				exactly(1).of(userSharedDao).findOne(newEntry.getUserShared().getId());
				will(returnValue(userShared));

				exactly(1).of(userDao).save(with(any(User.class)));
				will(returnValue(user));

			}
		});
		UserSendto ret = userService.save(newEntry);
		assertEquals(user.getId(), ret.getId());
		assertEquals(department.getId(), ret.getDepartment().getId());
		assertEquals(userShared.getId(), ret.getUserShared().getId());
	}

	@Test
	public void testUpdate() {
		final UserSendto newEntry = new UserSendto();

		context.checking(new Expectations() {

			{
				exactly(1).of(userDao).save(user);
				will(returnValue(user));

				exactly(1).of(userDao).findOne(1l);
				will(returnValue(user));
			}
		});
		UserSendto ret = userService.update(1l, newEntry);
		assertEquals(1l, ret.getId().longValue());

	}

	@Test
	public void testFindAll() {
		final UserSpecification spec = new UserSpecification();
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id", "ASC");
		final List<User> users = new ArrayList<User>();
		users.add(user);
		final Page<User> page = new PageImpl<User>(users);
		context.checking(new Expectations() {

			{
				exactly(1).of(userDao).findAll(spec, pageable);
				will(returnValue(page));
			}
		});
		Page<UserSendto> rets = userService.findAll(spec, pageable);
		assertEquals(1, rets.getTotalElements());
		UserSendto ret = rets.iterator().next();
		assertEquals(user.getId(), ret.getId());
		// assertEquals(user.getLastLogin(), ret.getLastLogin());
	}
}

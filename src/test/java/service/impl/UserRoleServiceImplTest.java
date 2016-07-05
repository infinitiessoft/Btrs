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

import resources.specification.SimplePageRequest;
import resources.specification.UserRoleSpecification;
import sendto.RoleSendto;
import service.impl.UserRoleServiceImpl;
import dao.RoleDao;
import dao.UserDao;
import dao.UserRoleDao;
import entity.Role;
import entity.RoleEnum;
import entity.User;
import entity.UserRole;

public class UserRoleServiceImplTest extends ServiceTest {
	private UserDao userDao;
	private RoleDao roleDao;
	private UserRoleDao userroleDao;
	private UserRoleServiceImpl userroleService;

	private User user;
	private Role admin;
	private UserRole userrole;

	@Before
	public void setUp() throws Exception {
		userDao = context.mock(UserDao.class);
		roleDao = context.mock(RoleDao.class);
		userroleDao = context.mock(UserRoleDao.class);
		userroleService = new UserRoleServiceImpl(userroleDao, userDao, roleDao);

		user = new User();
		user.setId(1L);
		admin = new Role();
		admin.setId(1L);
		admin.setValue(RoleEnum.EMPLOYEE);

		userrole = new UserRole();
		userrole.setId(1L);
		userrole.setUser(user);
		userrole.setRole(admin);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindByUserIdAndRoleId() {
		context.checking(new Expectations() {

			{
				exactly(1).of(userroleDao).findByUserIdAndRoleId(user.getId(),
						admin.getId());
				will(returnValue(userrole));
			}
		});
		RoleSendto ret = userroleService.findByUserIdAndRoleId(user.getId(),
				admin.getId());
		assertEquals(admin.getId(), ret.getId());
	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {

			{
				exactly(1).of(userroleDao).findByUserIdAndRoleId(user.getId(),
						admin.getId());
				will(returnValue(userrole));

				exactly(1).of(userroleDao).delete(userrole);
			}
		});
		userroleService.revokeRoleFromUser(user.getId(), admin.getId());
	}

	@Test
	public void testSave() {
		context.checking(new Expectations() {

			{
				exactly(1).of(userDao).findOne(user.getId());
				will(returnValue(user));

				exactly(1).of(roleDao).findOne(admin.getId());
				will(returnValue(admin));

				exactly(1).of(userroleDao).save(with(any(UserRole.class)));
				will(returnValue(userrole));
			}
		});
		userroleService.grantRoleToUser(user.getId(), admin.getId());
	}

	@Test
	public void testFindAll() {
		final UserRoleSpecification spec = new UserRoleSpecification();
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id",
				"ASC");
		final List<UserRole> userroles = new ArrayList<UserRole>();
		userroles.add(userrole);
		final Page<UserRole> page = new PageImpl<UserRole>(userroles);

		context.checking(new Expectations() {

			{
				exactly(1).of(userroleDao).findAll(spec, pageable);
				will(returnValue(page));
			}
		});
		Page<RoleSendto> rets = userroleService.findAll(spec, pageable);
		assertEquals(1, rets.getTotalElements());
		RoleSendto ret = rets.iterator().next();
		assertEquals(admin.getId(), ret.getId());
	}
}
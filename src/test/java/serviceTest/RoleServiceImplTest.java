package serviceTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.api.Invocation;
import org.jmock.lib.action.CustomAction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.RoleDao;
import entity.Role;
import sendto.RoleSendto;
import serviceImpl.RoleServiceImpl;

public class RoleServiceImplTest extends ServiceTest {

	private RoleDao roleDao;
	private RoleServiceImpl roleService;

	private Role role;

	@Before
	public void setUp() throws Exception {
		roleDao = context.mock(RoleDao.class);
		roleService = new RoleServiceImpl(roleDao);
		role = new Role();
		role.setId(1L);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieve() {
		context.checking(new Expectations() {

			{
				exactly(1).of(roleDao).findOne(1L);
				will(returnValue(role));
			}
		});
		RoleSendto ret = roleService.retrieve(1);
		assertEquals(1l, ret.getId().longValue());
	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {

			{
				exactly(1).of(roleDao).delete(1L);
			}
		});
		roleService.delete(1l);
	}

	@Test
	public void testSave() {
		final RoleSendto newEntry = new RoleSendto();
		newEntry.setId(2l);
		context.checking(new Expectations() {

			{
				exactly(1).of(roleDao).save(with(any(Role.class)));
				will(new CustomAction("save role") {

					@Override
					public Object invoke(Invocation invocation) throws Throwable {
						Role e = (Role) invocation.getParameter(0);
						e.setId(2L);
						return e;
					}
				});
			}
		});
		RoleSendto ret = roleService.save(newEntry);
		assertEquals(2l, ret.getId().longValue());
	}

	@Test
	public void testUpdate() {
		final RoleSendto newEntry = new RoleSendto();
		newEntry.setId(2l);
		context.checking(new Expectations() {

			{
				exactly(1).of(roleDao).save(role);
				will(returnValue(role));

				exactly(1).of(roleDao).findOne(1l);
				will(returnValue(role));
			}
		});
		RoleSendto ret = roleService.update(1l);
		assertEquals(1l, ret.getId().longValue());
	}

	@Test
	public void testFindAll() {
		final List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		Collection<RoleSendto> rets = roleService.findAll();
		assertEquals(1, rets.toString());
		RoleSendto ret = rets.iterator().next();
		assertEquals(1l, ret.getId().longValue());
	}
}

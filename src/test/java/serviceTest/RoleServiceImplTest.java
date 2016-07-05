package serviceTest;

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

import resources.specification.RoleSpecification;
import resources.specification.SimplePageRequest;
import sendto.RoleSendto;
import service.impl.RoleServiceImpl;
import dao.RoleDao;
import entity.Role;
import entity.RoleEnum;

public class RoleServiceImplTest extends ServiceTest {

	private RoleDao roleDao;
	private RoleServiceImpl roleService;
	private Specification<Role> spec;
	private Role role;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		roleDao = context.mock(RoleDao.class);
		roleService = new RoleServiceImpl(roleDao);
		spec = context.mock(Specification.class);
		role = new Role();
		role.setId(1L);
		role.setValue(RoleEnum.EMPLOYEE);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieve() {
		context.checking(new Expectations() {

			{
				exactly(1).of(roleDao).findOne(spec);
				will(returnValue(role));
			}
		});
		RoleSendto ret = roleService.retrieve(spec);
		assertEquals(1l, ret.getId().longValue());
	}

	@Test
	public void testFindAll() {
		final RoleSpecification spec = new RoleSpecification();
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id",
				"ASC");
		final List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		final Page<Role> page = new PageImpl<Role>(roles);
		context.checking(new Expectations() {
			{
				exactly(1).of(roleDao).findAll(spec, pageable);
				will(returnValue(page));
			}
		});
		Page<RoleSendto> rets = roleService.findAll(spec, pageable);
		assertEquals(1, rets.getTotalElements());
		RoleSendto ret = rets.iterator().next();
		assertEquals(1l, ret.getId().longValue());
	}
}

package serviceTest;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;

import dao.UserSharedDao;
import entity.UserShared;
import service.impl.UserDetailServiceImpl;

public class UserDetailServiceImplTest extends ServiceTest {

	private UserSharedDao userDao;
	private UserDetailServiceImpl userDetailService;
	private UserShared user;

	@Before
	public void setUp() throws Exception {
		userDao = context.mock(UserSharedDao.class);
		userDetailService = new UserDetailServiceImpl(userDao);
		user = new UserShared();
		user.setId(1L);
		user.setUsername("demo");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoadUserByUsername() {
		context.checking(new Expectations() {

			{
				exactly(1).of(userDao).findByUsername(user.getUsername());
				will(returnValue(user));
			}
		});
		UserDetails ret = userDetailService.loadUserByUsername(user.getUsername());
		assertEquals(user.getUsername(), ret.getUsername());
	}

}

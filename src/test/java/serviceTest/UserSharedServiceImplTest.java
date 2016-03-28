package serviceTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.api.Invocation;
import org.jmock.lib.action.CustomAction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import dao.UserSharedDao;
import entity.UserShared;
import resources.specification.SimplePageRequest;
import resources.specification.UserSharedSpecification;
import sendto.UserSharedSendto;
import serviceImpl.UserSharedServiceImpl;

public class UserSharedServiceImplTest extends ServiceTest {

	private UserSharedDao userSharedDao;
	private UserSharedServiceImpl userSharedService;

	private UserShared userShared;

	@Before
	public void setUp() throws Exception {
		userSharedDao = context.mock(UserSharedDao.class);
		userSharedService = new UserSharedServiceImpl(userSharedDao);
		userShared = new UserShared();
		userShared.setId(1L);
		userShared.setUsername("demo");
		userShared.setJobTitle("admin");
		userShared.setGender("male");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieve() {
		context.checking(new Expectations() {

			{
				exactly(1).of(userSharedDao).findOne(1L);
				will(returnValue(userShared));
			}
		});
		UserSharedSendto ret = userSharedService.retrieve(1);
		assertEquals(userShared.getId(), ret.getId());
		assertEquals(userShared.getUsername(), ret.getUsername());
		assertEquals(userShared.getJobTitle(), ret.getJobTitle());
		assertEquals(userShared.getGender(), ret.getGender());
	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {

			{
				exactly(1).of(userSharedDao).delete(1L);
			}
		});
		userSharedService.delete(1l);
	}

	@Test
	public void testSave() {
		final UserSharedSendto newEntry = new UserSharedSendto();
		newEntry.setUsername("name");

		context.checking(new Expectations() {

			{
				exactly(1).of(userSharedDao).save(with(any(UserShared.class)));
				will(new CustomAction("save ExpenseCategory") {

					@Override
					public Object invoke(Invocation invocation) throws Throwable {
						UserShared e = (UserShared) invocation.getParameter(0);
						e.setId(1L);
						return e;
					}
				});
			}
		});
		UserSharedSendto ret = userSharedService.save(newEntry);
		assertEquals(userShared.getId(), ret.getId());
		assertEquals(newEntry.getUsername(), ret.getUsername());
		assertEquals(newEntry.getJobTitle(), ret.getJobTitle());
		assertEquals(newEntry.getGender(), ret.getGender());
	}

	@Test
	public void testUpdate() {
		final UserSharedSendto newEntry = new UserSharedSendto();
		newEntry.setUsername("demo");
		newEntry.setJobTitle("admin");
		context.checking(new Expectations() {

			{
				exactly(1).of(userSharedDao).save(userShared);
				will(returnValue(userShared));

				exactly(1).of(userSharedDao).findOne(1l);
				will(returnValue(userShared));
			}
		});
		UserSharedSendto ret = userSharedService.update(1l, newEntry);
		assertEquals(userShared.getId(), ret.getId());
		assertEquals(newEntry.getUsername(), ret.getUsername());
		assertEquals(newEntry.getJobTitle(), ret.getJobTitle());
		assertEquals(userShared.getGender(), ret.getGender());
	}

	@Test
	public void testFindAll() {
		final UserSharedSpecification spec = new UserSharedSpecification();
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id", "ASC");
		final List<UserShared> userShares = new ArrayList<UserShared>();
		userShares.add(userShared);
		final Page<UserShared> page = new PageImpl<UserShared>(userShares);
		context.checking(new Expectations() {

			{
				exactly(1).of(userSharedDao).findAll(spec, pageable);
				will(returnValue(page));
			}
		});
		Page<UserSharedSendto> rets = userSharedService.findAll(spec, pageable);
		assertEquals(1, rets.getTotalElements());
		UserSharedSendto ret = rets.iterator().next();
		assertEquals(userShared.getId(), ret.getId());
		assertEquals(userShared.getUsername(), ret.getUsername());
		assertEquals(userShared.getJobTitle(), ret.getJobTitle());
		assertEquals(userShared.getGender(), ret.getGender());
	}
}

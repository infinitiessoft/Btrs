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

import dao.ReportDao;
import dao.StatusChangesDao;
import dao.UserDao;
import entity.Report;
import entity.StatusChanges;
import entity.User;
import resources.specification.SimplePageRequest;
import resources.specification.StatusChangesSpecification;
import sendto.StatusChangesSendto;
import serviceImpl.StatusChangesServiceImpl;

public class StatusChangesServiceImplTest extends ServiceTest {

	private StatusChangesDao statusChangesDao;
	private ReportDao reportDao;
	private UserDao userDao;
	private StatusChangesServiceImpl statusChangesService;

	private StatusChanges statusChanges;
	private Report report;
	private User user;

	@Before
	public void setUp() throws Exception {
		statusChangesDao = context.mock(StatusChangesDao.class);
		reportDao = context.mock(ReportDao.class);
		userDao = context.mock(UserDao.class);
		statusChangesService = new StatusChangesServiceImpl(statusChangesDao, reportDao, userDao);
		statusChanges = new StatusChanges();
		statusChanges.setId(1L);
		statusChanges.setCreatedDate(new Date());
		statusChanges.setComment("OKK");

		report = new Report();
		report.setId(1L);
		statusChanges.setReport(report);

		user = new User();
		user.setId(1L);
		statusChanges.setUser(user);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieve() {
		context.checking(new Expectations() {

			{
				exactly(1).of(statusChangesDao).findOne(1L);
				will(returnValue(statusChanges));
			}
		});
		StatusChangesSendto ret = statusChangesService.retrieve(1);
		assertEquals(1l, ret.getId().longValue());
		assertEquals("OKK", ret.getComment());
		assertEquals(report.getId(), ret.getReport().getId());
		assertEquals(user.getId(), ret.getUser().getRevisor_id());
	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {

			{
				exactly(1).of(statusChangesDao).delete(statusChanges);

				exactly(1).of(statusChangesDao).findOne(1L);
				will(returnValue(statusChanges));

			}
		});
		statusChangesService.delete(1l);
	}

	@Test
	public void testSave() {
		final StatusChangesSendto newEntry = new StatusChangesSendto();
		StatusChangesSendto.Report reportSendto = new StatusChangesSendto.Report();
		reportSendto.setId(report.getId());
		newEntry.setReport(reportSendto);

		context.checking(new Expectations() {

			{

				exactly(1).of(reportDao).findOne(newEntry.getReport().getId());
				will(returnValue(report));

				exactly(1).of(statusChangesDao).save(with(any(StatusChanges.class)));
				will(returnValue(statusChanges));

			}
		});
		StatusChangesSendto ret = statusChangesService.save(newEntry);
		assertEquals(statusChanges.getId(), ret.getId());
		assertEquals(statusChanges.getCreatedDate(), ret.getCreatedDate());
		assertEquals(statusChanges.getComment(), ret.getComment());
		assertEquals(report.getId(), ret.getReport().getId());
	}

	@Test
	public void testUpdate() {
		final StatusChangesSendto newEntry = new StatusChangesSendto();
		newEntry.setComment("nooo");

		context.checking(new Expectations() {

			{
				exactly(1).of(statusChangesDao).save(statusChanges);
				will(returnValue(statusChanges));

				exactly(1).of(statusChangesDao).findOne(statusChanges.getId());
				will(returnValue(statusChanges));
			}
		});
		StatusChangesSendto ret = statusChangesService.update(1l, newEntry);
		assertEquals(1L, ret.getId().longValue());
		assertEquals(newEntry.getComment(), ret.getComment());
	}

	@Test
	public void testFindAll() {
		final StatusChangesSpecification spec = new StatusChangesSpecification();
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id", "ASC");
		final List<StatusChanges> status = new ArrayList<StatusChanges>();
		status.add(statusChanges);
		final Page<StatusChanges> page = new PageImpl<StatusChanges>(status);
		context.checking(new Expectations() {

			{
				exactly(1).of(statusChangesDao).findAll(spec, pageable);
				will(returnValue(page));
			}
		});
		Page<StatusChangesSendto> rets = statusChangesService.findAll(spec, pageable);
		assertEquals(1, rets.getTotalElements());
		StatusChangesSendto ret = rets.iterator().next();
		assertEquals(1l, ret.getId().longValue());
		assertEquals(statusChanges.getCreatedDate(), ret.getCreatedDate());
		assertEquals(statusChanges.getComment(), ret.getComment());
	}
}

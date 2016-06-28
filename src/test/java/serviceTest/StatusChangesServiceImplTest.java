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
import dao.StatusChangeDao;
import dao.UserDao;
import entity.Report;
import entity.StatusChange;
import entity.User;
import resources.specification.SimplePageRequest;
import resources.specification.StatusChangeSpecification;
import sendto.StatusChangeSendto;
import service.impl.StatusChangeServiceImpl;

public class StatusChangesServiceImplTest extends ServiceTest {

	private StatusChangeDao statusChangesDao;
	private ReportDao reportDao;
	private UserDao userDao;
	private StatusChangeServiceImpl statusChangesService;

	private StatusChange statusChanges;
	private Report report;
	private User user;

	@Before
	public void setUp() throws Exception {
		statusChangesDao = context.mock(StatusChangeDao.class);
		reportDao = context.mock(ReportDao.class);
		userDao = context.mock(UserDao.class);
		statusChangesService = new StatusChangeServiceImpl(statusChangesDao, reportDao, userDao);
		statusChanges = new StatusChange();
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
		StatusChangeSendto ret = statusChangesService.retrieve(1);
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
		final StatusChangeSendto newEntry = new StatusChangeSendto();
		StatusChangeSendto.Report reportSendto = new StatusChangeSendto.Report();
		reportSendto.setId(report.getId());
		newEntry.setReport(reportSendto);

		context.checking(new Expectations() {

			{

				exactly(1).of(reportDao).findOne(newEntry.getReport().getId());
				will(returnValue(report));

				exactly(1).of(statusChangesDao).save(with(any(StatusChange.class)));
				will(returnValue(statusChanges));

			}
		});
		StatusChangeSendto ret = statusChangesService.save(newEntry);
		assertEquals(statusChanges.getId(), ret.getId());
		assertEquals(statusChanges.getCreatedDate(), ret.getCreatedDate());
		assertEquals(statusChanges.getComment(), ret.getComment());
		assertEquals(report.getId(), ret.getReport().getId());
	}

	@Test
	public void testUpdate() {
		final StatusChangeSendto newEntry = new StatusChangeSendto();
		newEntry.setComment("nooo");

		context.checking(new Expectations() {

			{
				exactly(1).of(statusChangesDao).save(statusChanges);
				will(returnValue(statusChanges));

				exactly(1).of(statusChangesDao).findOne(statusChanges.getId());
				will(returnValue(statusChanges));
			}
		});
		StatusChangeSendto ret = statusChangesService.update(1l, newEntry);
		assertEquals(1L, ret.getId().longValue());
		assertEquals(newEntry.getComment(), ret.getComment());
	}

	@Test
	public void testFindAll() {
		final StatusChangeSpecification spec = new StatusChangeSpecification();
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id", "ASC");
		final List<StatusChange> status = new ArrayList<StatusChange>();
		status.add(statusChanges);
		final Page<StatusChange> page = new PageImpl<StatusChange>(status);
		context.checking(new Expectations() {

			{
				exactly(1).of(statusChangesDao).findAll(spec, pageable);
				will(returnValue(page));
			}
		});
		Page<StatusChangeSendto> rets = statusChangesService.findAll(spec, pageable);
		assertEquals(1, rets.getTotalElements());
		StatusChangeSendto ret = rets.iterator().next();
		assertEquals(1l, ret.getId().longValue());
		assertEquals(statusChanges.getCreatedDate(), ret.getCreatedDate());
		assertEquals(statusChanges.getComment(), ret.getComment());
	}
}

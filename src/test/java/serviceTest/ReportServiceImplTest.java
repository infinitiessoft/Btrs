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

import dao.ReportDao;
import dao.UserDao;
import entity.Report;
import entity.User;
import resources.specification.ReportSpecification;
import resources.specification.SimplePageRequest;
import sendto.ReportSendto;
import serviceImpl.ReportServiceImpl;

public class ReportServiceImplTest extends ServiceTest {

	private ReportDao reportDao;
	private ReportServiceImpl reportService;
	private Report report;

	private UserDao userDao;
	private User user;

	@Before
	public void setUp() throws Exception {
		reportDao = context.mock(ReportDao.class);
		userDao = context.mock(UserDao.class);
		reportService = new ReportServiceImpl(reportDao, userDao);
		report = new Report();
		report.setId(1L);
		report.setReason("demo");
		report.setComment("good");

		user = new User();
		user.setId(2l);
		report.setOwner(user);

		user = new User();
		user.setId(2l);
		report.setReviewer(user);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieve() {
		context.checking(new Expectations() {

			{
				exactly(1).of(reportDao).findOne(1L);
				will(returnValue(report));
			}
		});
		ReportSendto ret = reportService.retrieve(1);
		assertEquals(1l, ret.getId().longValue());
		assertEquals("demo", ret.getReason());
		assertEquals("good", ret.getComment());
		assertEquals(user.getId(), ret.getUserOwner().getId());
		assertEquals(user.getId(), ret.getUserReviewer().getId());

	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {

			{
				exactly(1).of(reportDao).delete(report);

				exactly(1).of(reportDao).findOne(1L);
				will(returnValue(report));
			}
		});
		reportService.delete(1l);
	}

	@Test
	public void testSave() {
		final ReportSendto newEntry = new ReportSendto();

		ReportSendto.User userSendto = new ReportSendto.User();
		userSendto.setId(1L);
		newEntry.setUserOwner(userSendto);

		ReportSendto.User userSend = new ReportSendto.User();
		userSendto.setId(2L);
		newEntry.setUserReviewer(userSend);

		context.checking(new Expectations() {

			{
				exactly(1).of(userDao).findOne(newEntry.getUserOwner().getId());
				will(returnValue(user));

				exactly(1).of(userDao).findOne(newEntry.getUserReviewer().getId());
				will(returnValue(user));

				exactly(1).of(reportDao).save(with(any(Report.class)));
				will(returnValue(report));

			}

		});

		ReportSendto ret = reportService.save(newEntry);

		assertEquals(report.getId(), ret.getId());
		assertEquals(report.getCreatedDate(), ret.getCreatedDate());
		assertEquals(report.getComment(), ret.getComment());
	}

	@Test
	public void testUpdate() {
		final ReportSendto newEntry = new ReportSendto();
		newEntry.setReason("demo");
		newEntry.setComment("good");
		context.checking(new Expectations() {

			{
				exactly(1).of(reportDao).save(report);
				will(returnValue(report));

				exactly(1).of(reportDao).findOne(1l);
				will(returnValue(report));
			}
		});
		ReportSendto ret = reportService.update(1l, newEntry);
		assertEquals(1l, ret.getId().longValue());
		assertEquals(newEntry.getReason(), ret.getReason());
		assertEquals(newEntry.getComment(), ret.getComment());
	}

	@Test
	public void testFindAll() {
		final ReportSpecification spec = new ReportSpecification();
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id", "ASC");
		final List<Report> expenseCategories = new ArrayList<Report>();
		expenseCategories.add(report);
		final Page<Report> page = new PageImpl<Report>(expenseCategories);
		context.checking(new Expectations() {

			{
				exactly(1).of(reportDao).findAll(spec, pageable);
				will(returnValue(page));
			}
		});
		Page<ReportSendto> rets = reportService.findAll(spec, pageable);
		assertEquals(1, rets.getTotalElements());
		ReportSendto ret = rets.iterator().next();
		assertEquals(1l, ret.getId().longValue());
		assertEquals(report.getReason(), ret.getReason());
		assertEquals(report.getComment(), ret.getComment());
	}
}

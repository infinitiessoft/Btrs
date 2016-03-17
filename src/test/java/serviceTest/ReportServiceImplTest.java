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

import dao.ReportDao;
import entity.Report;
import resources.specification.ReportSpecification;
import resources.specification.SimplePageRequest;
import sendto.ReportSendto;
import serviceImpl.ReportServiceImpl;

public class ReportServiceImplTest extends ServiceTest {

	private ReportDao reportDao;
	private ReportServiceImpl reportService;

	private Report report;

	@Before
	public void setUp() throws Exception {
		reportDao = context.mock(ReportDao.class);
		reportService = new ReportServiceImpl(reportDao);
		report = new Report();
		report.setId(1L);
		report.setReason("demo");
		report.setComment("good");
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
	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {

			{
				exactly(1).of(reportDao).delete(1L);
			}
		});
		reportService.delete(1l);
	}

	@Test
	public void testSave() {
		final ReportSendto newEntry = new ReportSendto();
		newEntry.setReason("trip");
		context.checking(new Expectations() {

			{
				exactly(1).of(reportDao).save(with(any(Report.class)));
				will(new CustomAction("save Report") {

					@Override
					public Object invoke(Invocation invocation) throws Throwable {
						Report e = (Report) invocation.getParameter(0);
						e.setId(2L);
						return e;
					}
				});
			}
		});
		ReportSendto ret = reportService.save(newEntry);
		assertEquals(2l, ret.getId().longValue());
		assertEquals(newEntry.getReason(), ret.getReason());
		assertEquals(newEntry.getComment(), ret.getComment());
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

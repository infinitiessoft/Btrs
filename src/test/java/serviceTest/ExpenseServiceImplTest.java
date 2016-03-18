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

import dao.ExpenseDao;
import dao.ExpenseTypeDao;
import dao.ReportDao;
import entity.Expense;
import entity.ExpenseType;
import entity.Report;
import resources.specification.ExpenseSpecification;
import resources.specification.SimplePageRequest;
import sendto.ExpenseSendto;
import serviceImpl.ExpenseServiceImpl;

public class ExpenseServiceImplTest extends ServiceTest {

	private ExpenseDao expenseDao;
	private ExpenseTypeDao expenseTypeDao;
	private ReportDao reportDao;
	private ExpenseServiceImpl expenseService;

	private Report report;
	private ExpenseType expenseType;
	private Expense expense;

	@Before
	public void setUp() throws Exception {
		expenseDao = context.mock(ExpenseDao.class);
		reportDao = context.mock(ReportDao.class);
		expenseTypeDao = context.mock(ExpenseTypeDao.class);
		expenseService = new ExpenseServiceImpl(expenseDao, expenseTypeDao, reportDao);
		expense = new Expense();
		expense.setId(1L);
		expense.setTaxAmount(0);
		expense.setTotalAmount(1234);
		report = new Report();
		report.setId(1L);
		expense.setReport(report);
		expenseType = new ExpenseType();
		expenseType.setId(2L);
		expense.setExpenseType(expenseType);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieve() {
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseDao).findOne(1L);
				will(returnValue(expense));
			}
		});
		ExpenseSendto ret = expenseService.retrieve(1);
		assertEquals(1l, ret.getId().longValue());
		assertEquals(0, ret.getTaxAmount().intValue());
		assertEquals(1234, ret.getTotalAmount().intValue());
		assertEquals(report.getId(), ret.getReport().getId());
		// assertEquals(expenseType.getId(), ret.getExpenseType().getId());
	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseDao).delete(expense);

				exactly(1).of(expenseDao).findOne(1L);
				will(returnValue(expense));
			}
		});
		expenseService.delete(1l);
	}

	@Test
	public void testSave() {
		final ExpenseSendto newEntry = new ExpenseSendto();
		newEntry.setTaxAmount(12);
		ExpenseSendto.Report rpt = new ExpenseSendto.Report();
		rpt.setId(1L);
		newEntry.setReport(rpt);
		ExpenseSendto.ExpenseType expType = new ExpenseSendto.ExpenseType();
		expenseType.setId(1L);
		newEntry.setExpenseType(expType);
		context.checking(new Expectations() {

			{
				exactly(1).of(reportDao).findOne(1L);
				will(returnValue(report));

				exactly(1).of(expenseTypeDao).findOne(1L);
				will(returnValue(expenseType));

				exactly(1).of(expenseDao).save(with(any(Expense.class)));
				will(new CustomAction("save Expense") {

					@Override
					public Object invoke(Invocation invocation) throws Throwable {
						Expense e = (Expense) invocation.getParameter(0);
						e.setId(2L);
						return e;
					}
				});
			}
		});
		ExpenseSendto ret = expenseService.save(newEntry);
		assertEquals(2l, ret.getId().longValue());
		assertEquals(newEntry.getTaxAmount(), ret.getTaxAmount());
		assertEquals(newEntry.getTotalAmount(), ret.getTotalAmount());
	}

	@Test
	public void testUpdate() {
		final ExpenseSendto newEntry = new ExpenseSendto();
		newEntry.setTaxAmount(0);
		newEntry.setTotalAmount(1234);
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseDao).save(expense);
				will(returnValue(expense));

				exactly(1).of(expenseDao).findOne(expense.getId());
				will(returnValue(expense));
			}
		});
		ExpenseSendto ret = expenseService.update(1l, newEntry);
		assertEquals(1l, ret.getId().longValue());
		assertEquals(newEntry.getTaxAmount(), ret.getTaxAmount());
		assertEquals(newEntry.getTotalAmount(), ret.getTotalAmount());
	}

	@Test
	public void testFindAll() {
		final ExpenseSpecification spec = new ExpenseSpecification();
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id", "ASC");
		final List<Expense> expenses = new ArrayList<Expense>();
		expenses.add(expense);
		final Page<Expense> page = new PageImpl<Expense>(expenses);
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseDao).findAll(spec, pageable);
				will(returnValue(page));
			}
		});
		Page<ExpenseSendto> rets = expenseService.findAll(spec, pageable);
		assertEquals(1, rets.getTotalElements());
		ExpenseSendto ret = rets.iterator().next();
		assertEquals(1l, ret.getId().longValue());
		assertEquals(expense.getTaxAmount(), ret.getTaxAmount());
		assertEquals(expense.getTotalAmount(), ret.getTotalAmount());
	}
}
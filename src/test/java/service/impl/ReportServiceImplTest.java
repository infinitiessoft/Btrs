package service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.api.Invocation;
import org.jmock.lib.action.CustomAction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import resources.specification.AttendRecordSpecification;
import resources.specification.EmployeeSpecification;
import resources.specification.ReportSpecification;
import resources.specification.SimplePageRequest;
import resources.specification.UserRoleSpecification;
import sendto.ReportSendto;
import sendto.ReportSummarySendto;
import sendto.StatusChangeSendto;
import service.MailService;
import service.impl.ReportServiceImpl;
import util.MailWritter;
import attendance.dao.AttendRecordDao;
import attendance.dao.EmployeeDao;
import attendance.entity.AttendRecord;
import attendance.entity.Employee;
import dao.ExpenseDao;
import dao.ExpenseTypeDao;
import dao.ParameterValueDao;
import dao.ReportDao;
import dao.StatusChangeDao;
import dao.UserDao;
import dao.UserRoleDao;
import entity.Expense;
import entity.ExpenseCategory;
import entity.ExpenseType;
import entity.ExpenseTypePara;
import entity.ParameterValue;
import entity.Report;
import entity.Role;
import entity.StatusChange;
import entity.StatusEnum;
import entity.TypeParameter;
import entity.User;
import entity.UserRole;

public class ReportServiceImplTest extends ServiceTest {

	private ReportServiceImpl reportService;
	private Report report;

	private ReportDao reportDao;
	private AttendRecordDao recordDao;
	private ExpenseDao expenseDao;
	private ExpenseTypeDao expenseTypeDao;
	private ParameterValueDao parameterValueDao;
	private StatusChangeDao statusChangeDao;
	private UserDao userDao;
	private UserRoleDao userRoleDao;
	private EmployeeDao employeeDao;
	private MailService mailService;
	private MailWritter writter;
	private ReportSpecification spec;

	private User owner, reviewer;
	private Employee employee, ownerE, reviewerE;
	private AttendRecord record;
	private UserRole userRole;
	private Role role;
	private ExpenseType other;
	private ParameterValue parameterValue;
	private TypeParameter typeParameter;
	private ExpenseTypePara expenseTypePara;
	private Expense exp, exp2;
	private ExpenseCategory category;
	private StatusChange statusChange;

	@Before
	public void setUp() throws Exception {
		spec = new ReportSpecification();
		spec.setApplicantName("applicantName");
		reportDao = context.mock(ReportDao.class);
		recordDao = context.mock(AttendRecordDao.class);
		expenseDao = context.mock(ExpenseDao.class);
		expenseTypeDao = context.mock(ExpenseTypeDao.class);
		parameterValueDao = context.mock(ParameterValueDao.class);
		statusChangeDao = context.mock(StatusChangeDao.class);
		userRoleDao = context.mock(UserRoleDao.class);
		employeeDao = context.mock(EmployeeDao.class);
		mailService = context.mock(MailService.class);
		writter = context.mock(MailWritter.class);
		userDao = context.mock(UserDao.class);
		reportService = new ReportServiceImpl(reportDao, recordDao, expenseDao,
				expenseTypeDao, parameterValueDao, statusChangeDao, userDao,
				userRoleDao, employeeDao, mailService, writter);
		report = new Report();
		report.setId(1L);
		report.setReason("demo");
		report.setComment("good");
		report.setCurrentStatus(StatusEnum.SUBMITTED);
		report.setAttendanceRecordId(1l);

		owner = new User();
		owner.setId(2l);
		owner.setUserSharedId(2l);
		report.setOwner(owner);

		reviewer = new User();
		reviewer.setId(3l);
		reviewer.setUserSharedId(3l);
		report.setReviewer(reviewer);

		employee = new Employee();
		employee.setId(1L);
		employee.setName("name");
		employee.setUsername("username");
		employee.setPassword("password");

		ownerE = new Employee();
		ownerE.setId(2l);
		ownerE.setName("ownerE");
		ownerE.setEmail("email");

		reviewerE = new Employee();
		reviewerE.setId(3l);
		reviewerE.setName("reviewerE");
		reviewerE.setEmail("email");

		record = new AttendRecord();
		role = new Role();
		userRole = new UserRole();
		userRole.setUser(reviewer);
		userRole.setRole(role);

		other = new ExpenseType();
		other.setId(1l);
		other.setTaxPercent(5d);
		expenseTypePara = new ExpenseTypePara();
		expenseTypePara.setExpenseType(other);
		expenseTypePara.setDataType("text");
		typeParameter = new TypeParameter();
		typeParameter.setId(1l);
		parameterValue = new ParameterValue();
		parameterValue.setId(1l);
		parameterValue.setValue("comment");
		typeParameter.getParameterValues().add(parameterValue);
		expenseTypePara.setTypeParameter(typeParameter);
		other.getExpenseTypePara().add(expenseTypePara);

		exp = new Expense();
		exp.setExpenseType(other);
		exp.setId(1l);

		exp2 = new Expense();
		exp2.setExpenseType(other);
		exp2.setId(2l);

		category = new ExpenseCategory();
		category.setId(1l);
		category.setName_key("demo");
		other.setExpenseCategory(category);

		report.getExpenses().add(exp);
		report.getExpenses().add(exp2);

		statusChange = new StatusChange();
		statusChange.setComment("comment");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieve() {
		context.checking(new Expectations() {

			{
				exactly(1).of(reportDao).findOne(spec);
				will(returnValue(report));

				exactly(1).of(employeeDao).findOne(
						with(any(EmployeeSpecification.class)));
				will(returnValue(employee));

				exactly(1).of(employeeDao).findOne(owner.getUserSharedId());
				will(returnValue(ownerE));

				exactly(1).of(employeeDao).findOne(reviewer.getUserSharedId());
				will(returnValue(reviewerE));
			}
		});
		ReportSendto ret = reportService.retrieve(spec);
		assertEquals(1l, ret.getId().longValue());
		assertEquals("demo", ret.getReason());
		assertEquals("good", ret.getComment());
		assertEquals(owner.getId(), ret.getOwner().getId());
		assertEquals(reviewer.getId(), ret.getReviewer().getId());

	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {

			{
				exactly(1).of(reportDao).delete(report);

				exactly(1).of(reportDao).findOne(spec);
				will(returnValue(report));

				exactly(1).of(employeeDao).findOne(
						with(any(EmployeeSpecification.class)));
				will(returnValue(employee));

			}
		});
		reportService.delete(spec);
	}

	@Test
	public void testSave() {
		final ReportSendto newEntry = new ReportSendto();
		newEntry.setAttendanceRecordId(1l);
		ReportSendto.User userSendto = new ReportSendto.User();
		userSendto.setId(1L);
		newEntry.setOwner(userSendto);

		ReportSendto.User userSend = new ReportSendto.User();
		userSendto.setId(2L);
		newEntry.setReviewer(userSend);

		ReportSendto.Expense expense = new ReportSendto.Expense();
		expense.setTotalAmount(100);
		expense.setTaxAmount(50);

		final ReportSendto.Expense.ExpenseType expenseType = new ReportSendto.Expense.ExpenseType();
		expenseType.setId(1l);
		expense.setExpenseType(expenseType);

		final ReportSendto.Expense.ParameterValue.TypeParameter typePara = new ReportSendto.Expense.ParameterValue.TypeParameter();
		typePara.setId(1l);

		ReportSendto.Expense.ParameterValue paraValue = new ReportSendto.Expense.ParameterValue();
		paraValue.setValue("demo");
		paraValue.setTypeParameter(typePara);
		expense.getParameterValues().add(paraValue);
		newEntry.getExpenses().add(expense);

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.MILLISECOND, -1);
		final Date lastMonthEnd = cal.getTime();

		final String subject = "subject";
		final String body = "body";

		final List<UserRole> userRoles = new ArrayList<UserRole>();
		userRoles.add(userRole);

		context.checking(new Expectations() {

			{
				exactly(1).of(userDao).findOne(newEntry.getOwner().getId());
				will(returnValue(owner));

				exactly(1).of(userDao).findOne(newEntry.getReviewer().getId());
				will(returnValue(owner));

				exactly(1).of(reportDao).save(with(any(Report.class)));
				will(new CustomAction("save report") {

					@Override
					public Object invoke(Invocation invocation)
							throws Throwable {
						return invocation.getParameter(0);
					}

				});

				exactly(2).of(employeeDao).findOne(owner.getUserSharedId());
				will(returnValue(ownerE));

				exactly(1).of(recordDao).findOne(
						with(any(AttendRecordSpecification.class)));
				will(returnValue(record));

				exactly(1).of(reportDao)
						.findTopByCreatedDateBeforeOrderByIdDesc(lastMonthEnd);
				will(returnValue(report));

				exactly(1).of(writter).buildSubmittedSubject(
						with(any(Report.class)));
				will(returnValue(subject));

				exactly(1).of(writter).buildSubmittedBody(
						with(any(Report.class)));
				will(returnValue(body));

				exactly(1).of(userRoleDao).findAll(
						with(any(UserRoleSpecification.class)));
				will(returnValue(userRoles));

				exactly(2).of(employeeDao).findOne(reviewer.getUserSharedId());
				will(returnValue(reviewerE));

				exactly(1).of(mailService).sendMail(reviewerE.getEmail(),
						subject, body);

				exactly(1).of(expenseTypeDao).findOne(expenseType.getId());
				will(returnValue(other));

				exactly(1).of(parameterValueDao).save(
						with(any(ParameterValue.class)));
				will(new CustomAction("save parameterValue") {

					@Override
					public Object invoke(Invocation invocation)
							throws Throwable {
						return invocation.getParameter(0);
					}

				});

				exactly(1).of(expenseDao).save(with(any(Expense.class)));
				will(new CustomAction("save expense") {

					@Override
					public Object invoke(Invocation invocation)
							throws Throwable {
						return invocation.getParameter(0);
					}

				});
			}

		});

		ReportSendto ret = reportService.save(newEntry);
		assertNotNull(ret.getCreatedDate());
		assertEquals(newEntry.getComment(), ret.getComment());
		for (sendto.ReportSendto.Expense e : ret.getExpenses()) {
			assertNotNull(e.getTaxAmount());
		}
	}

	@Test
	public void testUpdate() {
		final long currentUserid = owner.getId();
		final ReportSendto newEntry = new ReportSendto();
		ReportSendto.Expense newExpense = new ReportSendto.Expense();
		newExpense.setTotalAmount(100);
		newExpense.setTaxAmount(50);

		final ReportSendto.Expense.ExpenseType expenseType = new ReportSendto.Expense.ExpenseType();
		expenseType.setId(1l);
		newExpense.setExpenseType(expenseType);

		final ReportSendto.Expense.ParameterValue.TypeParameter typePara = new ReportSendto.Expense.ParameterValue.TypeParameter();
		typePara.setId(1l);

		ReportSendto.Expense.ParameterValue paraValue = new ReportSendto.Expense.ParameterValue();
		paraValue.setValue("demo");
		paraValue.setTypeParameter(typePara);
		newExpense.getParameterValues().add(paraValue);
		newEntry.getExpenses().add(newExpense);

		ReportSendto.Expense updateExpense = new ReportSendto.Expense();
		updateExpense.setId(exp.getId());
		updateExpense.setTotalAmount(100);
		updateExpense.setTaxAmount(50);
		updateExpense.setExpenseType(expenseType);
		updateExpense.getParameterValues().add(paraValue);
		newEntry.getExpenses().add(updateExpense);

		newEntry.setReason("demo");
		newEntry.setComment("good");
		context.checking(new Expectations() {

			{
				exactly(1).of(reportDao).save(report);
				will(new CustomAction("save report") {

					@Override
					public Object invoke(Invocation invocation)
							throws Throwable {
						return invocation.getParameter(0);
					}

				});

				exactly(1).of(reportDao).findOne(spec);
				will(returnValue(report));

				exactly(1).of(employeeDao).findOne(
						with(any(EmployeeSpecification.class)));
				will(returnValue(employee));

				exactly(1).of(employeeDao).findOne(owner.getUserSharedId());
				will(returnValue(ownerE));

				exactly(1).of(employeeDao).findOne(reviewer.getUserSharedId());
				will(returnValue(reviewerE));

				exactly(1).of(userDao).findByUserSharedId(employee.getId());
				will(returnValue(owner));

				exactly(1).of(userDao).findOne(currentUserid);
				will(returnValue(owner));

				exactly(1).of(expenseDao).delete(exp2.getId());

				exactly(1).of(expenseDao).save(exp);
				will(returnValue(exp));

				exactly(1).of(expenseDao).findOne(exp.getId());
				will(returnValue(exp));

				exactly(1).of(expenseTypeDao).findOne(other.getId());
				will(returnValue(other));

				exactly(1).of(parameterValueDao).save(
						with(any(ParameterValue.class)));
				will(returnValue(parameterValue));

				exactly(1).of(expenseDao).save(with(any(Expense.class)));
				will(returnValue(exp));
			}
		});
		ReportSendto ret = reportService.update(spec, newEntry, currentUserid);
		assertEquals(StatusEnum.SUBMITTED.name(), ret.getCurrentStatus());
		assertEquals(newEntry.getReason(), ret.getReason());
		assertEquals(newEntry.getComment(), ret.getComment());
	}

	@Test
	public void testFindAll() {
		final ReportSpecification spec = new ReportSpecification();
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id",
				"ASC");
		final List<Report> expenseCategories = new ArrayList<Report>();
		expenseCategories.add(report);
		final Page<Report> page = new PageImpl<Report>(expenseCategories);
		context.checking(new Expectations() {

			{
				exactly(1).of(reportDao).findAll(spec, pageable);
				will(returnValue(page));

				exactly(1).of(employeeDao).findOne(owner.getId());
				will(returnValue(ownerE));
			}
		});
		Page<ReportSummarySendto> rets = reportService.findAll(spec, pageable);
		assertEquals(1, rets.getTotalElements());
		ReportSummarySendto ret = rets.iterator().next();
		assertEquals(1l, ret.getId().longValue());
	}

	@Test
	public void testApprove() {
		final String subject = "subject";
		final String body = "body";
		final Long reportId = report.getId();
		final Long currentUserId = owner.getId();
		context.checking(new Expectations() {

			{
				exactly(1).of(reportDao).findOne(reportId);
				will(returnValue(report));

				exactly(1).of(userDao).findOne(currentUserId);
				will(returnValue(reviewer));

				exactly(1).of(statusChangeDao).save(statusChange);
				will(returnValue(statusChange));

				exactly(1).of(reportDao).save(report);
				will(returnValue(report));

				exactly(1).of(writter).buildReviewedSubject(
						with(any(Report.class)));
				will(returnValue(subject));

				exactly(1).of(writter).buildReviewedBody(
						with(any(Report.class)));
				will(returnValue(body));

				exactly(2).of(employeeDao).findOne(owner.getId());
				will(returnValue(ownerE));

				exactly(3).of(employeeDao).findOne(reviewer.getId());
				will(returnValue(reviewerE));

				exactly(1).of(mailService).sendMail(ownerE.getEmail(), subject,
						body);
			}
		});

		StatusChangeSendto statusChangeSendto = new StatusChangeSendto();
		statusChangeSendto.setComment("comment");
		ReportSendto ret = reportService.approve(reportId, statusChangeSendto,
				currentUserId);
		assertEquals(1l, ret.getId().longValue());
		assertEquals(StatusEnum.APPROVED.name(), ret.getCurrentStatus());
	}

	@Test
	public void testReject() {
		final String subject = "subject";
		final String body = "body";
		final Long reportId = report.getId();
		final Long currentUserId = owner.getId();
		context.checking(new Expectations() {

			{
				exactly(1).of(reportDao).findOne(reportId);
				will(returnValue(report));

				exactly(1).of(userDao).findOne(currentUserId);
				will(returnValue(reviewer));

				exactly(1).of(statusChangeDao).save(statusChange);
				will(returnValue(statusChange));

				exactly(1).of(reportDao).save(report);
				will(returnValue(report));

				exactly(1).of(writter).buildReviewedSubject(
						with(any(Report.class)));
				will(returnValue(subject));

				exactly(1).of(writter).buildReviewedBody(
						with(any(Report.class)));
				will(returnValue(body));

				exactly(2).of(employeeDao).findOne(owner.getId());
				will(returnValue(ownerE));

				exactly(3).of(employeeDao).findOne(reviewer.getId());
				will(returnValue(reviewerE));

				exactly(1).of(mailService).sendMail(ownerE.getEmail(), subject,
						body);
			}
		});

		StatusChangeSendto statusChangeSendto = new StatusChangeSendto();
		statusChangeSendto.setComment("comment");
		ReportSendto ret = reportService.reject(reportId, statusChangeSendto,
				currentUserId);
		assertEquals(1l, ret.getId().longValue());
		assertEquals(StatusEnum.REJECTED.name(), ret.getCurrentStatus());
	}
}

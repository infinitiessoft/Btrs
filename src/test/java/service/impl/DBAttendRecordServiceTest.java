package service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.api.Invocation;
import org.jmock.lib.action.CustomAction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import resources.specification.AttendRecordSpecification;
import resources.specification.ReportSpecification;
import resources.specification.SimplePageRequest;
import sendto.AttendRecordSendto;
import attendance.dao.AttendRecordDao;
import attendance.entity.AttendRecord;
import attendance.entity.AttendRecordType;
import attendance.entity.Employee;
import dao.ReportDao;
import dao.UserDao;
import entity.Report;
import entity.StatusEnum;
import entity.User;

public class DBAttendRecordServiceTest extends ServiceTest {

	private DBAttendRecordService service;
	private UserDao userDao;
	private AttendRecordDao attendRecordDao;
	private ReportDao reportDao;
	private AttendRecord attendRecord;
	private AttendRecordType type;
	private Employee employee;
	private User user;

	private Report report;

	@Before
	public void setUp() throws Exception {
		userDao = context.mock(UserDao.class);
		attendRecordDao = context.mock(AttendRecordDao.class);
		reportDao = context.mock(ReportDao.class);
		service = new DBAttendRecordService(attendRecordDao, reportDao, userDao);

		attendRecord = new AttendRecord();
		attendRecord.setId(1l);
		attendRecord.setBookDate(new Date());
		attendRecord.setDuration(1d);
		attendRecord.setStartDate(new Date());
		attendRecord.setEndDate(new Date());
		employee = new Employee();
		employee.setId(1L);
		employee.setName("name");
		employee.setUsername("username");
		employee.setPassword("password");
		attendRecord.setEmployee(employee);
		attendRecord.setStatus("permit");

		type = new AttendRecordType();
		type.setId(1L);
		attendRecord.setType(type);

		user = new User();
		user.setId(2l);
		user.setUserSharedId(2l);

		report = new Report();
		report.setId(1L);
		report.setReason("demo");
		report.setComment("good");
		report.setCurrentStatus(StatusEnum.SUBMITTED);
		report.setAttendanceRecordId(1l);

	}

	@Test
	public void testRetrieve() {
		final Long applicantId = 1L;
		context.checking(new Expectations() {

			{

				exactly(1).of(userDao).findOne(applicantId);
				will(returnValue(user));

				exactly(1).of(attendRecordDao).findOne(
						with(any(AttendRecordSpecification.class)));
				will(new CustomAction("find attendRecord") {

					@Override
					public Object invoke(Invocation invocation)
							throws Throwable {
						AttendRecordSpecification spec = (AttendRecordSpecification) invocation
								.getParameter(0);
						assertEquals(user.getUserSharedId(),
								spec.getApplicantId());
						return attendRecord;
					}

				});

			}
		});

		AttendRecordSpecification spec = new AttendRecordSpecification();
		spec.setApplicantId(applicantId);
		AttendRecordSendto ret = service.retrieve(spec);
		assertEquals(attendRecord.getId(), ret.getId());
	}

	@Test
	public void testFindAll() {
		final Long applicantId = 1L;
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id",
				"ASC");
		final List<AttendRecord> attendRecords = new ArrayList<AttendRecord>();
		attendRecords.add(attendRecord);
		final Page<AttendRecord> page = new PageImpl<AttendRecord>(
				attendRecords);
		context.checking(new Expectations() {

			{

				exactly(1).of(userDao).findOne(applicantId);
				will(returnValue(user));

				exactly(1).of(attendRecordDao).findAll(
						with(any(AttendRecordSpecification.class)),
						with(any(Pageable.class)));
				will(new CustomAction("find attendRecord") {

					@Override
					public Object invoke(Invocation invocation)
							throws Throwable {
						AttendRecordSpecification spec = (AttendRecordSpecification) invocation
								.getParameter(0);
						assertEquals(user.getUserSharedId(),
								spec.getApplicantId());
						return page;
					}

				});

			}
		});

		AttendRecordSpecification spec = new AttendRecordSpecification();
		spec.setApplicantId(applicantId);
		Page<AttendRecordSendto> rets = service.findAll(spec, pageable);
		assertEquals(1, rets.getTotalElements());
		AttendRecordSendto ret = rets.iterator().next();
		assertEquals(attendRecord.getId().longValue(), ret.getId().longValue());
	}

	@Test
	public void testFindAllAvailableRecords() {
		final Long applicantId = 1L;
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id",
				"ASC");
		final List<AttendRecord> attendRecords = new ArrayList<AttendRecord>();
		attendRecords.add(attendRecord);
		final Page<AttendRecord> page = new PageImpl<AttendRecord>(
				attendRecords);

		final List<Report> reports = new ArrayList<Report>();
		reports.add(report);
		context.checking(new Expectations() {

			{

				exactly(1).of(userDao).findOne(applicantId);
				will(returnValue(user));

				exactly(1).of(reportDao).findAll(
						with(any(ReportSpecification.class)));
				will(new CustomAction("find reports") {

					@Override
					public Object invoke(Invocation invocation)
							throws Throwable {
						ReportSpecification spec = (ReportSpecification) invocation
								.getParameter(0);
						assertEquals(applicantId, spec.getApplicantId());
						return reports;
					}

				});

				exactly(1).of(attendRecordDao).findAll(
						with(any(AttendRecordSpecification.class)),
						with(any(Pageable.class)));
				will(new CustomAction("find attendRecord") {

					@Override
					public Object invoke(Invocation invocation)
							throws Throwable {
						AttendRecordSpecification spec = (AttendRecordSpecification) invocation
								.getParameter(0);
						assertEquals(1, spec.getExclusion().size());
						assertEquals(report.getId(), spec.getExclusion()
								.iterator().next());
						assertEquals(user.getUserSharedId(),
								spec.getApplicantId());
						return page;
					}

				});

			}
		});

		AttendRecordSpecification spec = new AttendRecordSpecification();
		spec.setApplicantId(applicantId);
		Page<AttendRecordSendto> rets = service.findAllAvailableRecords(spec,
				pageable);
		assertEquals(1, rets.getTotalElements());
		AttendRecordSendto ret = rets.iterator().next();
		assertEquals(attendRecord.getId().longValue(), ret.getId().longValue());
	}

}

package service.impl;

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
import org.springframework.data.jpa.domain.Specification;

import resources.specification.SimplePageRequest;
import resources.specification.StatusChangeSpecification;
import sendto.StatusChangeSendto;
import service.impl.StatusChangeServiceImpl;
import attendance.dao.EmployeeDao;
import attendance.entity.Employee;
import dao.StatusChangeDao;
import entity.Report;
import entity.StatusChange;
import entity.StatusEnum;
import entity.User;

public class StatusChangesServiceImplTest extends ServiceTest {

	private StatusChangeDao statusChangesDao;
	private EmployeeDao employeeDao;
	private StatusChangeServiceImpl statusChangesService;
	private Specification<StatusChange> spec;
	private StatusChange statusChanges;
	private Report report;
	private User user;
	private Employee employee;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		spec = context.mock(Specification.class);
		statusChangesDao = context.mock(StatusChangeDao.class);
		employeeDao = context.mock(EmployeeDao.class);
		statusChangesService = new StatusChangeServiceImpl(statusChangesDao,
				employeeDao);
		statusChanges = new StatusChange();
		statusChanges.setId(1L);
		statusChanges.setCreatedDate(new Date());
		statusChanges.setComment("OKK");

		report = new Report();
		report.setId(1L);
		statusChanges.setReport(report);

		user = new User();
		user.setId(1L);
		user.setUserSharedId(1L);
		statusChanges.setUser(user);

		employee = new Employee();
		employee.setId(1L);
		employee.setName("name");

		statusChanges.setValue(StatusEnum.APPROVED);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieve() {
		context.checking(new Expectations() {

			{
				exactly(1).of(statusChangesDao).findOne(spec);
				will(returnValue(statusChanges));

				exactly(1).of(employeeDao).findOne(user.getUserSharedId());
				will(returnValue(employee));
			}
		});
		StatusChangeSendto ret = statusChangesService.retrieve(spec);
		assertEquals(1l, ret.getId().longValue());
		assertEquals("OKK", ret.getComment());
		assertEquals(report.getId(), ret.getReport().getId());
		assertEquals(user.getId(), ret.getUser().getId());
	}

	@Test
	public void testFindAll() {
		final StatusChangeSpecification spec = new StatusChangeSpecification();
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id",
				"ASC");
		final List<StatusChange> status = new ArrayList<StatusChange>();
		status.add(statusChanges);
		final Page<StatusChange> page = new PageImpl<StatusChange>(status);
		context.checking(new Expectations() {

			{
				exactly(1).of(statusChangesDao).findAll(spec, pageable);
				will(returnValue(page));

				exactly(1).of(employeeDao).findOne(user.getUserSharedId());
				will(returnValue(employee));
			}
		});
		Page<StatusChangeSendto> rets = statusChangesService.findAll(spec,
				pageable);
		assertEquals(1, rets.getTotalElements());
		StatusChangeSendto ret = rets.iterator().next();
		assertEquals(1l, ret.getId().longValue());
		assertEquals(statusChanges.getCreatedDate(), ret.getCreatedDate());
		assertEquals(statusChanges.getComment(), ret.getComment());
	}
}

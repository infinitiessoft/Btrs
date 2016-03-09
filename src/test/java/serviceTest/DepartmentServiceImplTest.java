package serviceTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.api.Invocation;
import org.jmock.lib.action.CustomAction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DepartmentDao;
import entity.Department;
import sendto.DepartmentSendto;
import serviceImpl.DepartmentServiceImpl;

public class DepartmentServiceImplTest extends ServiceTest {

	private DepartmentDao departmentDao;
	private DepartmentServiceImpl departmentService;

	private Department department;

	@Before
	public void setUp() throws Exception {
		departmentDao = context.mock(DepartmentDao.class);
		departmentService = new DepartmentServiceImpl(departmentDao);
		department = new Department();
		department.setId(1L);
		department.setName("demo");
		department.setComment("Good");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieve() {
		context.checking(new Expectations() {

			{
				exactly(1).of(departmentDao).findOne(1L);
				will(returnValue(department));
			}
		});
		DepartmentSendto ret = departmentService.retrieve(1);
		assertEquals(1l, ret.getId().longValue());
		assertEquals("demo", ret.getName());
		assertEquals("Good", ret.getComment());

	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {

			{
				exactly(1).of(departmentDao).delete(1L);
			}
		});
		departmentService.delete(1l);
	}

	@Test
	public void testSave() {
		final DepartmentSendto newEntry = new DepartmentSendto();
		newEntry.setName("D_name");

		context.checking(new Expectations() {

			{
				exactly(1).of(departmentDao).save(with(any(Department.class)));
				will(new CustomAction("save department") {

					@Override
					public Object invoke(Invocation invocation) throws Throwable {
						Department e = (Department) invocation.getParameter(0);
						e.setId(2L);
						return e;
					}
				});
			}
		});
		DepartmentSendto ret = departmentService.save(newEntry);
		assertEquals(2l, ret.getId().longValue());
		assertEquals(newEntry.getName(), ret.getName());
		assertEquals(newEntry.getComment(), ret.getComment());
	}

	@Test
	public void testUpdate() {
		final DepartmentSendto newEntry = new DepartmentSendto();
		newEntry.setName("D_name");
		context.checking(new Expectations() {

			{
				exactly(1).of(departmentDao).save(department);
				will(returnValue(department));

				exactly(1).of(departmentDao).findOne(1l);
				will(returnValue(department));
			}
		});
		DepartmentSendto ret = departmentService.update(1l, newEntry);
		assertEquals(1l, ret.getId().longValue());
		assertEquals(newEntry.getName(), ret.getName());
		assertEquals(newEntry.getComment(), ret.getComment());
	}

	@Test
	public void testFindAll() {
		final List<Department> departments = new ArrayList<Department>();
		departments.add(department);

		Collection<DepartmentSendto> rets = departmentService.findAll();
		assertEquals(1, rets.getClass());
		DepartmentSendto ret = rets.iterator().next();
		assertEquals(1l, ret.getId().longValue());
		assertEquals(department.getName(), ret.getName());
	}
}

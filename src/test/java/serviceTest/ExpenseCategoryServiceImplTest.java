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

import dao.ExpenseCategoryDao;
import entity.ExpenseCategory;
import resources.specification.ExpenseCategorySpecification;
import resources.specification.SimplePageRequest;
import sendto.ExpenseCategorySendto;
import serviceImpl.ExpenseCategoryServiceImpl;

public class ExpenseCategoryServiceImplTest extends ServiceTest {

	private ExpenseCategoryDao expenseCategoryDao;
	private ExpenseCategoryServiceImpl expenseCategoryService;

	private ExpenseCategory expenseCategory;

	@Before
	public void setUp() throws Exception {
		expenseCategoryDao = context.mock(ExpenseCategoryDao.class);
		expenseCategoryService = new ExpenseCategoryServiceImpl(expenseCategoryDao);
		expenseCategory = new ExpenseCategory();
		expenseCategory.setId(1L);
		expenseCategory.setName_key("demo");
		expenseCategory.setCode("demo");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieve() {
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseCategoryDao).findOne(1L);
				will(returnValue(expenseCategory));
			}
		});
		ExpenseCategorySendto ret = expenseCategoryService.retrieve(1);
		assertEquals(1l, ret.getId());
		assertEquals("demo", ret.getName_key());
		assertEquals("demo", ret.getCode());
	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseCategoryDao).delete(1L);
			}
		});
		expenseCategoryService.delete(1l);
	}

	@Test
	public void testSave() {
		final ExpenseCategorySendto newEntry = new ExpenseCategorySendto();
		newEntry.setName_key("name");

		context.checking(new Expectations() {

			{
				exactly(1).of(expenseCategoryDao).save(with(any(ExpenseCategory.class)));
				will(new CustomAction("save ExpenseCategory") {

					@Override
					public Object invoke(Invocation invocation) throws Throwable {
						ExpenseCategory e = (ExpenseCategory) invocation.getParameter(0);
						e.setId(2L);
						return e;
					}
				});
			}
		});
		ExpenseCategorySendto ret = expenseCategoryService.save(newEntry);
		assertEquals(2l, ret.getId());
		assertEquals(newEntry.getName_key(), ret.getName_key());
		assertEquals(newEntry.getCode(), ret.getCode());
	}

	@Test
	public void testUpdate() {
		final ExpenseCategorySendto newEntry = new ExpenseCategorySendto();
		newEntry.setName_key("demo");
		newEntry.setCode("demo");
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseCategoryDao).save(expenseCategory);
				will(returnValue(expenseCategory));

				exactly(1).of(expenseCategoryDao).findOne(1l);
				will(returnValue(expenseCategory));
			}
		});
		ExpenseCategorySendto ret = expenseCategoryService.update(1l, newEntry);
		assertEquals(1l, ret.getId());
		assertEquals(newEntry.getName_key(), ret.getName_key());
		assertEquals(newEntry.getCode(), ret.getCode());
	}

	@Test
	public void testFindAll() {
		final ExpenseCategorySpecification spec = new ExpenseCategorySpecification();
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id", "ASC");
		final List<ExpenseCategory> expenseCategories = new ArrayList<ExpenseCategory>();
		expenseCategories.add(expenseCategory);
		final Page<ExpenseCategory> page = new PageImpl<ExpenseCategory>(expenseCategories);
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseCategoryDao).findAll(spec, pageable);
				will(returnValue(page));
			}
		});
		Page<ExpenseCategorySendto> rets = expenseCategoryService.findAll(spec, pageable);
		assertEquals(1, rets.getTotalElements());
		ExpenseCategorySendto ret = rets.iterator().next();
		assertEquals(1l, ret.getId());
		assertEquals(expenseCategory.getName_key(), ret.getName_key());
		assertEquals(expenseCategory.getCode(), ret.getCode());
	}
}

package service.impl;

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
import org.springframework.data.jpa.domain.Specification;

import resources.specification.SimplePageRequest;
import sendto.ExpenseTypeSendto;
import service.impl.ExpenseTypeServiceImpl;
import dao.ExpenseTypeDao;
import entity.ExpenseCategory;
import entity.ExpenseType;

public class ExpenseTypeServiceImplTest extends ServiceTest {

	private ExpenseTypeDao expenseTypeDao;
	private ExpenseTypeServiceImpl expenseTypeService;
	private Specification<ExpenseType> spec;
	private ExpenseType expenseType;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		expenseTypeDao = context.mock(ExpenseTypeDao.class);
		spec = context.mock(Specification.class);
		expenseTypeService = new ExpenseTypeServiceImpl(expenseTypeDao);
		expenseType = new ExpenseType();
		expenseType.setId(1L);
		expenseType.setTaxPercent(10.5);
		expenseType.setValue("demo");
		ExpenseCategory cate = new ExpenseCategory();
		cate.setId(1L);
		cate.setName_key("name_key");
		expenseType.setExpenseCategory(cate);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieve() {
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseTypeDao).findOne(spec);
				will(returnValue(expenseType));
			}
		});
		ExpenseTypeSendto ret = expenseTypeService.retrieve(spec);
		assertEquals(1l, ret.getId().longValue());
		assertEquals(expenseType.getTaxPercent().doubleValue(), ret
				.getTaxPercent().doubleValue(), 0.1);
		assertEquals(expenseType.getValue(), ret.getValue());
	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseTypeDao).delete(expenseType);

				exactly(1).of(expenseTypeDao).findOne(spec);
				will(returnValue(expenseType));

			}
		});
		expenseTypeService.delete(spec);
	}

	@Test
	public void testSave() {
		final ExpenseTypeSendto newEntry = new ExpenseTypeSendto();
		newEntry.setTaxPercent(15.0);
		newEntry.setValue("demo");
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseTypeDao).findOne(1L);
				will(returnValue(expenseType));

				exactly(1).of(expenseTypeDao)
						.save(with(any(ExpenseType.class)));
				will(new CustomAction("save ExpenseType") {

					@Override
					public Object invoke(Invocation invocation)
							throws Throwable {
						ExpenseType e = (ExpenseType) invocation
								.getParameter(0);
						e.setId(2L);
						ExpenseCategory expenseCategory = new ExpenseCategory();
						expenseCategory.setId(1L);
						e.setExpenseCategory(expenseCategory);
						return e;
					}
				});
			}
		});
		ExpenseTypeSendto ret = expenseTypeService.save(newEntry);
		assertEquals(2l, ret.getId().longValue());
		assertEquals(newEntry.getTaxPercent(), ret.getTaxPercent());
		assertEquals(newEntry.getValue(), ret.getValue());

	}

	@Test
	public void testUpdate() {
		final ExpenseTypeSendto newEntry = new ExpenseTypeSendto();
		newEntry.setTaxPercent(10.5);
		newEntry.setValue("demo");
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseTypeDao).save(expenseType);
				will(returnValue(expenseType));

				exactly(1).of(expenseTypeDao).findOne(spec);
				will(returnValue(expenseType));
			}
		});
		ExpenseTypeSendto ret = expenseTypeService.update(spec, newEntry);
		assertEquals(1l, ret.getId().longValue());
		assertEquals(newEntry.getTaxPercent(), ret.getTaxPercent());
		assertEquals(newEntry.getValue(), ret.getValue());

	}

	@Test
	public void testFindAll() {
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id",
				"ASC");
		final List<ExpenseType> expenseTypes = new ArrayList<ExpenseType>();
		expenseTypes.add(expenseType);
		final Page<ExpenseType> page = new PageImpl<ExpenseType>(expenseTypes);
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseTypeDao).findAll(spec, pageable);
				will(returnValue(page));
			}
		});
		Page<ExpenseTypeSendto> rets = expenseTypeService.findAll(spec,
				pageable);
		assertEquals(1, rets.getTotalElements());
		ExpenseTypeSendto ret = rets.iterator().next();
		assertEquals(1l, ret.getId().longValue());
		assertEquals(expenseType.getTaxPercent(), ret.getTaxPercent());
		assertEquals(expenseType.getValue(), ret.getValue());

	}
}

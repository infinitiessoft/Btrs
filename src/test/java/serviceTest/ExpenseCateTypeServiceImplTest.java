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

import dao.ExpenseCateTypeDao;
import dao.ExpenseCategoryDao;
import dao.ExpenseTypeDao;
import entity.ExpenseCateType;
import entity.ExpenseCategory;
import entity.ExpenseType;
import resources.specification.ExpenseCateTypeSpecification;
import resources.specification.SimplePageRequest;
import sendto.ExpenseTypeSendto;
import serviceImpl.ExpenseCateTypeServiceImpl;

public class ExpenseCateTypeServiceImplTest extends ServiceTest {

	private ExpenseCategoryDao expenseCategoryDao;
	private ExpenseTypeDao expenseTypeDao;
	private ExpenseCateTypeServiceImpl expenseCateTypeService;
	private ExpenseCateTypeDao expenseCateTypeDao;

	private ExpenseCategory expenseCategory;
	private ExpenseType expenseType;
	private ExpenseCateType expenseCateType;

	@Before
	public void setUp() throws Exception {
		expenseCategoryDao = context.mock(ExpenseCategoryDao.class);
		expenseTypeDao = context.mock(ExpenseTypeDao.class);
		expenseCateTypeDao = context.mock(ExpenseCateTypeDao.class);
		expenseCateTypeService = new ExpenseCateTypeServiceImpl(expenseCategoryDao, expenseTypeDao, expenseCateTypeDao);

		expenseCategory = new ExpenseCategory();
		expenseCategory.setId(1L);
		expenseType = new ExpenseType();
		expenseType.setId(1L);

		expenseCateType = new ExpenseCateType();
		expenseCateType.setId(1L);
		expenseCateType.setExpenseCategory(expenseCategory);
		expenseCateType.setExpenseType(expenseType);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testFindByExpenseCategoryIdAndExpenseTypeId() {

		context.checking(new Expectations() {

			{
				exactly(1).of(expenseCateTypeDao).findByExpenseCategoryIdAndExpenseTypeId(expenseCategory.getId(),
						expenseType.getId());
				will(returnValue(expenseCateType));
			}
		});
		ExpenseTypeSendto ret = expenseCateTypeService.findByExpenseCategoryIdAndExpenseTypeId(expenseCategory.getId(),
				expenseType.getId());
		assertEquals(expenseType.getId(), ret.getId());
	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseCateTypeDao).findByExpenseCategoryIdAndExpenseTypeId(expenseCategory.getId(),
						expenseType.getId());
				will(returnValue(expenseCateType));

				exactly(1).of(expenseCateTypeDao).delete(expenseCateType);
			}
		});
		expenseCateTypeService.revokeExpenseTypeFromExpenseCategory(expenseCategory.getId(), expenseType.getId());
	}

	@Test
	public void testSave() {
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseCategoryDao).findOne(expenseCategory.getId());
				will(returnValue(expenseCategory));

				exactly(1).of(expenseTypeDao).findOne(expenseType.getId());
				will(returnValue(expenseType));

				exactly(1).of(expenseCateTypeDao).save(with(any(ExpenseCateType.class)));
				will(returnValue(expenseCateType));
			}
		});
		expenseCateTypeService.grantExpenseTypeToExpenseCategory(expenseCategory.getId(), expenseType.getId());
	}

	@Test
	public void testFindAll() {
		final ExpenseCateTypeSpecification spec = new ExpenseCateTypeSpecification();
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id", "ASC");
		final List<ExpenseCateType> expenseCateTypes = new ArrayList<ExpenseCateType>();
		expenseCateTypes.add(expenseCateType);
		final Page<ExpenseCateType> page = new PageImpl<ExpenseCateType>(expenseCateTypes);

		context.checking(new Expectations() {

			{
				exactly(1).of(expenseCateTypeDao).findAll(spec, pageable);
				will(returnValue(page));
			}
		});
		Page<ExpenseTypeSendto> rets = expenseCateTypeService.findAll(spec, pageable);
		assertEquals(1, rets.getTotalElements());
		ExpenseTypeSendto ret = rets.iterator().next();
		assertEquals(expenseType.getId(), ret.getId());
	}
}
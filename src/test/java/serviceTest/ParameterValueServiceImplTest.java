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

import dao.ExpenseDao;
import dao.ParameterValueDao;
import dao.TypeParameterDao;
import entity.Expense;
import entity.ParameterValue;
import entity.TypeParameter;
import resources.specification.ParameterValueSpecification;
import resources.specification.SimplePageRequest;
import sendto.ParameterValueSendto;
import serviceImpl.ParameterValueServiceImpl;

public class ParameterValueServiceImplTest extends ServiceTest {

	private ParameterValueDao parameterValueDao;
	private TypeParameterDao typeParameterDao;
	private TypeParameter typeParameter;
	private ExpenseDao expenseDao;
	private Expense expense;
	private ParameterValueServiceImpl parameterValueService;

	private ParameterValue parameterValue;

	@Before
	public void setUp() throws Exception {
		parameterValueDao = context.mock(ParameterValueDao.class);
		typeParameterDao = context.mock(TypeParameterDao.class);
		expenseDao = context.mock(ExpenseDao.class);
		parameterValueService = new ParameterValueServiceImpl(parameterValueDao, typeParameterDao, expenseDao);
		parameterValue = new ParameterValue();
		parameterValue.setId(1l);
		parameterValue.setValue("demo");
		parameterValue.setTypeParameter(typeParameter);
		parameterValue.setExpense(expense);

		typeParameter = new TypeParameter();
		typeParameter.setId(1L);
		parameterValue.setTypeParameter(typeParameter);

		expense = new Expense();
		expense.setId(1L);
		parameterValue.setExpense(expense);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieve() {
		context.checking(new Expectations() {

			{
				exactly(1).of(parameterValueDao).findOne(1L);
				will(returnValue(parameterValue));
			}
		});
		ParameterValueSendto ret = parameterValueService.retrieve(1);
		assertEquals(parameterValue.getId(), ret.getId());
		assertEquals(parameterValue.getValue(), ret.getValue());
		assertEquals(expense.getId(), ret.getExpense().getId());
		assertEquals(typeParameter.getId(), ret.getTypeParameter().getId());
	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {

			{
				exactly(1).of(parameterValueDao).delete(1L);
			}
		});
		parameterValueService.delete(1l);
	}

	@Test
	public void testSave() {
		final ParameterValueSendto newEntry = new ParameterValueSendto();
		ParameterValueSendto.Expense expenseSendto = new ParameterValueSendto.Expense();
		expenseSendto.setId(expense.getId());
		ParameterValueSendto.TypeParameter typeParameterSendto = new ParameterValueSendto.TypeParameter();
		typeParameterSendto.setId(typeParameter.getId());
		newEntry.setExpense(expenseSendto);
		newEntry.setTypeParameter(typeParameterSendto);

		context.checking(new Expectations() {

			{
				exactly(1).of(typeParameterDao).findOne(newEntry.getTypeParameter().getId());
				will(returnValue(typeParameter));

				exactly(1).of(expenseDao).findOne(newEntry.getExpense().getId());
				will(returnValue(expense));

				exactly(1).of(parameterValueDao).save(with(any(ParameterValue.class)));
				will(returnValue(parameterValue));

			}
		});

		ParameterValueSendto ret = parameterValueService.save(newEntry);
		assertEquals(parameterValue.getId(), ret.getId());
		assertEquals(parameterValue.getValue(), ret.getValue());
		assertEquals(expense.getId(), ret.getExpense().getId());
		assertEquals(typeParameter.getId(), ret.getTypeParameter().getId());
	}

	@Test
	public void testUpdate() {
		final ParameterValueSendto newEntry = new ParameterValueSendto();
		ParameterValueSendto.Expense expenseSendto = new ParameterValueSendto.Expense();
		expenseSendto.setId(expense.getId());
		ParameterValueSendto.TypeParameter typeParameterSendto = new ParameterValueSendto.TypeParameter();
		typeParameterSendto.setId(typeParameter.getId());
		newEntry.setExpense(expenseSendto);
		newEntry.setTypeParameter(typeParameterSendto);

		context.checking(new Expectations() {

			{
				exactly(1).of(parameterValueDao).save(parameterValue);
				will(returnValue(parameterValue));

				exactly(1).of(parameterValueDao).findOne(parameterValue.getId());
				will(returnValue(parameterValue));
			}
		});
		ParameterValueSendto ret = parameterValueService.update(1l, newEntry);
		assertEquals(parameterValue.getId(), ret.getId());
		assertEquals(parameterValue.getValue(), ret.getValue());
		assertEquals(expense.getId(), ret.getExpense().getId());
		assertEquals(typeParameter.getId(), ret.getTypeParameter().getId());
	}

	@Test
	public void testFindAll() {
		final ParameterValueSpecification spec = new ParameterValueSpecification();
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id", "ASC");
		final List<ParameterValue> parameterValues = new ArrayList<ParameterValue>();
		parameterValues.add(parameterValue);
		final Page<ParameterValue> page = new PageImpl<ParameterValue>(parameterValues);
		context.checking(new Expectations() {

			{
				exactly(1).of(parameterValueDao).findAll(spec, pageable);
				will(returnValue(page));
			}
		});
		Page<ParameterValueSendto> rets = parameterValueService.findAll(spec, pageable);
		assertEquals(1, rets.getTotalElements());
		ParameterValueSendto ret = rets.iterator().next();
		assertEquals(parameterValue.getId(), ret.getId());
		assertEquals(parameterValue.getValue(), ret.getValue());
		assertEquals(expense.getId(), ret.getExpense().getId());
		assertEquals(typeParameter.getId(), ret.getTypeParameter().getId());

	}
}

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

import dao.ExpenseTypeDao;
import dao.ExpenseTypeParaDao;
import dao.ParameterValueDao;
import entity.ExpenseType;
import entity.ExpenseTypePara;
import entity.ParameterValue;
import resources.specification.ExpenseTypeParaSpecification;
import resources.specification.SimplePageRequest;
import sendto.ParameterValueSendto;
import serviceImpl.ExpenseTypeParaServiceImpl;

public class ExpenseTypeParaServiceImplTest extends ServiceTest {

	private ExpenseTypeDao expenseTypeDao;
	private ParameterValueDao parameterValueDao;
	private ExpenseTypeParaDao expenseTypeParaDao;
	private ExpenseTypeParaServiceImpl expenseTypeParaService;

	private ExpenseType expenseType;
	private ParameterValue parameterValue;
	private ExpenseTypePara expenseTypePara;

	@Before
	public void setUp() throws Exception {
		expenseTypeDao = context.mock(ExpenseTypeDao.class);
		parameterValueDao = context.mock(ParameterValueDao.class);
		expenseTypeParaDao = context.mock(ExpenseTypeParaDao.class);
		expenseTypeParaService = new ExpenseTypeParaServiceImpl(expenseTypeDao, parameterValueDao, expenseTypeParaDao);

		expenseType = new ExpenseType();
		expenseType.setId(1L);
		parameterValue = new ParameterValue();
		parameterValue.setId(1L);

		expenseTypePara = new ExpenseTypePara();
		expenseTypePara.setId(1L);
		expenseTypePara.setExpenseType(expenseType);
		expenseTypePara.setParameterValue(parameterValue);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindByExpenseTypeIdAndParameterValueId() {
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseTypeParaDao).findByExpenseTypeIdAndParameterValueId(expenseType.getId(),
						parameterValue.getId());
				will(returnValue(expenseTypePara));
			}
		});
		ParameterValueSendto ret = expenseTypeParaService.findByExpenseTypeIdAndParameterValueId(expenseType.getId(),
				parameterValue.getId());
		assertEquals(parameterValue.getId(), ret.getId());

	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseTypeParaDao).findByExpenseTypeIdAndParameterValueId(expenseType.getId(),
						parameterValue.getId());
				will(returnValue(expenseTypePara));

				exactly(1).of(expenseTypeParaDao).delete(expenseTypePara);
			}
		});
		expenseTypeParaService.revokeParameterValueFromExpenseType(expenseType.getId(), parameterValue.getId());
	}

	@Test
	public void testSave() {
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseTypeDao).findOne(expenseType.getId());
				will(returnValue(expenseType));

				exactly(1).of(parameterValueDao).findOne(parameterValue.getId());
				will(returnValue(parameterValue));

				exactly(1).of(expenseTypeParaDao).save(with(any(ExpenseTypePara.class)));
				will(returnValue(expenseTypePara));
			}
		});
		expenseTypeParaService.grantParameterValueToExpenseType(expenseType.getId(), parameterValue.getId());
	}

	@Test
	public void testFindAll() {
		final ExpenseTypeParaSpecification spec = new ExpenseTypeParaSpecification();
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id", "ASC");
		final List<ExpenseTypePara> expenseTypeParas = new ArrayList<ExpenseTypePara>();
		expenseTypeParas.add(expenseTypePara);
		final Page<ExpenseTypePara> page = new PageImpl<ExpenseTypePara>(expenseTypeParas);

		context.checking(new Expectations() {

			{
				exactly(1).of(expenseTypeParaDao).findAll(spec, pageable);
				will(returnValue(page));
			}
		});
		Page<ParameterValueSendto> rets = expenseTypeParaService.findAll(spec, pageable);
		assertEquals(1, rets.getTotalElements());
		ParameterValueSendto ret = rets.iterator().next();
		assertEquals(parameterValue.getId(), ret.getId());
	}
}

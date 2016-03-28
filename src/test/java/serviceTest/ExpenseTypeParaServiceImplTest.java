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
import dao.TypeParameterDao;
import entity.ExpenseType;
import entity.ExpenseTypePara;
import entity.TypeParameter;
import resources.specification.ExpenseTypeParaSpecification;
import resources.specification.SimplePageRequest;
import sendto.ExpenseTypeParaSendto;
import serviceImpl.ExpenseTypeParaServiceImpl;

public class ExpenseTypeParaServiceImplTest extends ServiceTest {

	private ExpenseTypeDao expenseTypeDao;
	private TypeParameterDao typeParameterDao;
	private ExpenseTypeParaDao expenseTypeParaDao;
	private ExpenseTypeParaServiceImpl expenseTypeParaService;

	private ExpenseType expenseType;
	private TypeParameter typeParameter;
	private ExpenseTypePara expenseTypePara;

	@Before
	public void setUp() throws Exception {
		expenseTypeDao = context.mock(ExpenseTypeDao.class);
		typeParameterDao = context.mock(TypeParameterDao.class);
		expenseTypeParaDao = context.mock(ExpenseTypeParaDao.class);
		expenseTypeParaService = new ExpenseTypeParaServiceImpl(expenseTypeDao, typeParameterDao, expenseTypeParaDao);

		typeParameter = new TypeParameter();
		typeParameter.setId(1L);
		expenseType = new ExpenseType();
		expenseType.setId(1L);

		expenseTypePara = new ExpenseTypePara();
		expenseTypePara.setId(1L);
		expenseTypePara.setTypeParameter(typeParameter);
		expenseTypePara.setExpenseType(expenseType);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testFindByExpenseTypeIdAndParameterValueId() {

		context.checking(new Expectations() {

			{
				exactly(1).of(expenseTypeParaDao).findByExpenseTypeIdAndTypeParameterId(expenseType.getId(),
						typeParameter.getId());
				will(returnValue(expenseTypePara));
			}
		});
		ExpenseTypeParaSendto ret = expenseTypeParaService.findByExpenseTypeIdAndParameterValueId(typeParameter.getId(),
				expenseType.getId());
		assertEquals(typeParameter.getId(), ret.getId());
	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseTypeParaDao).findByExpenseTypeIdAndTypeParameterId(expenseType.getId(),
						typeParameter.getId());
				will(returnValue(expenseTypePara));

				exactly(1).of(expenseTypeParaDao).delete(expenseTypePara);
			}
		});
		expenseTypeParaService.revokeTypeParameterFromExpenseType(typeParameter.getId(), expenseType.getId());
	}

	@Test
	public void testSave() {
		context.checking(new Expectations() {

			{
				exactly(1).of(expenseTypeDao).findOne(expenseType.getId());
				will(returnValue(expenseType));

				exactly(1).of(typeParameterDao).findOne(typeParameter.getId());
				will(returnValue(typeParameter));

				exactly(1).of(expenseTypeParaDao).save(with(any(ExpenseTypePara.class)));
				will(returnValue(expenseTypePara));
			}
		});
		expenseTypeParaService.grantTypeParameterToExpenseType(typeParameter.getId(), expenseType.getId());
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
		Page<ExpenseTypeParaSendto> rets = expenseTypeParaService.findAll(spec, pageable);
		assertEquals(1, rets.getTotalElements());
		ExpenseTypeParaSendto ret = rets.iterator().next();
		assertEquals(typeParameter.getId(), ret.getId());
	}
}
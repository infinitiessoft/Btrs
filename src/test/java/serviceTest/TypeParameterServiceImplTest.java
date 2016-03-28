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

import dao.TypeParameterDao;
import entity.TypeParameter;
import resources.specification.SimplePageRequest;
import resources.specification.TypeParameterSpecification;
import sendto.TypeParameterSendto;
import serviceImpl.TypeParameterServiceImpl;

public class TypeParameterServiceImplTest extends ServiceTest {

	private TypeParameterDao typeParameterDao;
	private TypeParameterServiceImpl typeParameterService;

	private TypeParameter typeParameter;

	@Before
	public void setUp() throws Exception {
		typeParameterDao = context.mock(TypeParameterDao.class);
		typeParameterService = new TypeParameterServiceImpl(typeParameterDao);
		typeParameter = new TypeParameter();
		typeParameter.setId(1L);
		typeParameter.setValue("demo");

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieve() {
		context.checking(new Expectations() {

			{
				exactly(1).of(typeParameterDao).findOne(1L);
				will(returnValue(typeParameter));
			}
		});
		TypeParameterSendto ret = typeParameterService.retrieve(1);
		assertEquals(1l, ret.getId().longValue());
		assertEquals("demo", ret.getValue());
	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {

			{
				exactly(1).of(typeParameterDao).delete(1L);
			}
		});
		typeParameterService.delete(1l);
	}

	@Test
	public void testSave() {
		final TypeParameterSendto newEntry = new TypeParameterSendto();
		newEntry.setValue("demo");

		context.checking(new Expectations() {

			{
				exactly(1).of(typeParameterDao).save(with(any(TypeParameter.class)));
				will(new CustomAction("save TypeParameter") {

					@Override
					public Object invoke(Invocation invocation) throws Throwable {
						TypeParameter e = (TypeParameter) invocation.getParameter(0);
						e.setId(2L);
						return e;
					}
				});
			}
		});
		TypeParameterSendto ret = typeParameterService.save(newEntry);
		assertEquals(2l, ret.getId().longValue());
		assertEquals(newEntry.getValue(), ret.getValue());

	}

	@Test
	public void testUpdate() {
		final TypeParameterSendto newEntry = new TypeParameterSendto();
		newEntry.setValue("demo");
		context.checking(new Expectations() {

			{
				exactly(1).of(typeParameterDao).save(typeParameter);
				will(returnValue(typeParameter));

				exactly(1).of(typeParameterDao).findOne(1l);
				will(returnValue(typeParameter));
			}
		});
		TypeParameterSendto ret = typeParameterService.update(1l, newEntry);
		assertEquals(1l, ret.getId().longValue());
		assertEquals(newEntry.getValue(), ret.getValue());
	}

	@Test
	public void testFindAll() {
		final TypeParameterSpecification spec = new TypeParameterSpecification();
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id", "ASC");
		final List<TypeParameter> typeParameters = new ArrayList<TypeParameter>();
		typeParameters.add(typeParameter);
		final Page<TypeParameter> page = new PageImpl<TypeParameter>(typeParameters);
		context.checking(new Expectations() {

			{
				exactly(1).of(typeParameterDao).findAll(spec, pageable);
				will(returnValue(page));
			}
		});
		Page<TypeParameterSendto> rets = typeParameterService.findAll(spec, pageable);
		assertEquals(1, rets.getTotalElements());
		TypeParameterSendto ret = rets.iterator().next();
		assertEquals(1l, ret.getId().longValue());
		assertEquals(typeParameter.getValue(), ret.getValue());
	}
}

package test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Test;

import assertion.AssertUtils;
import entity.PageModel;
import resources.ResourceTest;
import resources.Type.admin.ParameterValueResource;
import sendto.ParameterValueSendto;

public class ParameterValueResourceTest extends ResourceTest {

	@Test
	public void testGetParameterValue() {
		Response response = target("parameterValue").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ParameterValueSendto sendto = response.readEntity(ParameterValueSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals("Taipei", sendto.getValue());
	}

	@Test
	public void testDeleteParameterValue() {
		Response response = target("parameterValue").path("2").register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteParameterValueWithNotFoundException() {
		Response response = target("parameterValue").path("3").register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateParameterValue() {
		ParameterValueSendto admin = new ParameterValueSendto();
		admin.setValue("administrator");

		ParameterValueSendto.Expense expense = new ParameterValueSendto.Expense();
		expense.setId(1L);
		admin.setExpense(expense);

		ParameterValueSendto.TypeParameter type = new ParameterValueSendto.TypeParameter();
		type.setId(1L);
		admin.setTypeParameter(type);

		Response response = target("parameterValue").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ParameterValueSendto sendto = response.readEntity(ParameterValueSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getValue(), sendto.getValue());
		assertEquals(admin.getExpense().getId().longValue(), sendto.getExpense().getId().longValue());
		assertEquals(admin.getTypeParameter().getId().longValue(), sendto.getTypeParameter().getId().longValue());
	}

	@Test
	public void testUpdateParameterValueWithNotFoundException() {
		ParameterValueSendto admin = new ParameterValueSendto();
		admin.setValue("administrator");

		ParameterValueSendto.Expense expense = new ParameterValueSendto.Expense();
		expense.setId(1L);
		admin.setExpense(expense);

		ParameterValueSendto.TypeParameter type = new ParameterValueSendto.TypeParameter();
		type.setId(1L);
		admin.setTypeParameter(type);
		Response response = target("parameterValue").path("3").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveParameterValue() {
		ParameterValueSendto admin = new ParameterValueSendto();
		ParameterValueSendto.Expense expense = new ParameterValueSendto.Expense();
		expense.setId(2L);
		admin.setExpense(expense);

		ParameterValueSendto.TypeParameter type = new ParameterValueSendto.TypeParameter();
		type.setId(2L);
		admin.setTypeParameter(type);
		Response response = target("parameterValue").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ParameterValueSendto sendto = response.readEntity(ParameterValueSendto.class);
		assertEquals(3l, sendto.getId().longValue());
		assertEquals(admin.getExpense().getId().longValue(), sendto.getExpense().getId().longValue());
		assertEquals(admin.getTypeParameter().getId().longValue(), sendto.getTypeParameter().getId().longValue());
	}

	@Test
	public void testFindallParameterValue() {
		Response response = target("parameterValue").register(JacksonFeature.class).request().header("user", "demo")
				.get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PageModel<ParameterValueSendto> rets = response.readEntity(new GenericType<PageModel<ParameterValueSendto>>() {
		});
		assertEquals(2, rets.getTotalElements());

	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { ParameterValueResource.class };
	}

}

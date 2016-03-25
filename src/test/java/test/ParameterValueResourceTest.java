package test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Test;

import entity.PageModel;
import resources.ResourceTest;
import resources.Type.ParameterValueResource;
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
		assertEquals(1l, sendto.getExpense());
		assertEquals(1l, sendto.getTypeParameter());
	}

	@Test
	public void testGetParameterValueWithNotFoundException() {
		Response response = target("parameterValue").path("4").register(JacksonFeature.class).request()
				.header("user", "demo").get();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());

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
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
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
		assertEquals(admin.getExpense().getId(), sendto.getExpense().getId());
		assertEquals(admin.getTypeParameter().getId(), sendto.getTypeParameter().getId());
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
		Response response = target("parameterValue").path("4").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());

	}

	@Test
	public void testSaveParameterValue() {
		ParameterValueSendto admin = new ParameterValueSendto();
		admin.setValue("Taipei");
		ParameterValueSendto.Expense expense = new ParameterValueSendto.Expense();
		expense.setId(1L);
		admin.setExpense(expense);

		ParameterValueSendto.TypeParameter type = new ParameterValueSendto.TypeParameter();
		type.setId(1L);
		admin.setTypeParameter(type);
		Response response = target("parameterValue").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ParameterValueSendto sendto = response.readEntity(ParameterValueSendto.class);
		assertEquals(2l, sendto.getId().longValue());
		assertEquals(admin.getValue(), sendto.getValue());
		assertEquals(admin.getExpense().getId(), sendto.getExpense().getId());
		assertEquals(admin.getTypeParameter().getId(), sendto.getTypeParameter().getId());
	}

	@Test
	public void testSaveParameterValueWithDuplicateName() {
		ParameterValueSendto admin = new ParameterValueSendto();
		admin.setValue("Taipei");
		ParameterValueSendto.Expense expense = new ParameterValueSendto.Expense();
		expense.setId(1L);
		admin.setExpense(expense);

		ParameterValueSendto.TypeParameter type = new ParameterValueSendto.TypeParameter();
		type.setId(1L);
		admin.setTypeParameter(type);
		Response response = target("parameterValue").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());

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

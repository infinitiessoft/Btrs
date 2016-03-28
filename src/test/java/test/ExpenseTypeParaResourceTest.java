package test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Test;

import entity.PageModel;
import resources.ResourceTest;
import resources.Type.ParameterValueResource;
import sendto.RoleSendto;

public class ExpenseTypeParaResourceTest extends ResourceTest {
	@Test
	public void testFindAllParameterValue() {
		Response response = target("expenseType").path("2").path("parameterValue").register(JacksonFeature.class)
				.request().header("user", "user").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PageModel<RoleSendto> rets = response.readEntity(new GenericType<PageModel<RoleSendto>>() {
		});
		assertEquals(1, rets.getTotalElements());
	}

	@Test
	public void testFindParameterValue() {
		Response response = target("expenseType").path("2").path("parameterValue").path("2")
				.register(JacksonFeature.class).request().header("user", "user").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		RoleSendto transfer = response.readEntity(RoleSendto.class);
		assertEquals(2l, transfer.getId().longValue());
	}

	@Test
	public void testFindParameterValueWithNotFoundException() {
		Response response = target("expenseType").path("2").path("parameterValue").path("4")
				.register(JacksonFeature.class).request().header("user", "user").get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testAssignParameterValueToExpenseType() {
		Response response = target("expenseType").property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true)
				.path("2").path("parameterValue").path("1").register(JacksonFeature.class).request()
				.header("user", "user").put(null);
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testAssignParameterValueToExpenseTypeWithParameterValueNotFound() {
		Response response = target("expenseType").property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true)
				.path("2").path("parameterValue").path("4").register(JacksonFeature.class).request()
				.header("user", "user").put(null);
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testRevokeParameterValueToExpenseParameterValue() {
		Response response = target("expenseType").property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true)
				.path("2").path("parameterValue").path("2").register(JacksonFeature.class).request()
				.header("user", "user").delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testRevokeParameterValueToExpenseTypeWithParameterValueNotFound() {
		Response response = target("expenseType").property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true)
				.path("2").path("parameterValue").path("3").register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { ParameterValueResource.class };
	}
}

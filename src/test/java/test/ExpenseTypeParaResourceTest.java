package test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import entity.PageModel;
import resources.ResourceTest;
import resources.Type.ExpenseTypeResource;
import sendto.TypeParameterSendto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExpenseTypeParaResourceTest extends ResourceTest {
	@Test
	public void testFindAllTypeParameter() {
		Response response = target("expenseType").path("2").path("typeParameter").register(JacksonFeature.class)
				.request().header("user", "user").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PageModel<TypeParameterSendto> rets = response.readEntity(new GenericType<PageModel<TypeParameterSendto>>() {
		});
		assertEquals(1, rets.getTotalElements());
	}

	@Test
	public void testFindTypeParameter() {
		Response response = target("expenseType").path("2").path("typeParameter").path("2")
				.register(JacksonFeature.class).request().header("user", "user").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		TypeParameterSendto sendto = response.readEntity(TypeParameterSendto.class);
		assertEquals(2l, sendto.getId().longValue());
	}

	@Test
	public void testFindTypeParameterWithNotFoundException() {
		Response response = target("expenseType").path("2").path("typeParameter").path("4")
				.register(JacksonFeature.class).request().header("user", "user").get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testAssignTypeParameterToExpenseType() {
		Response response = target("expenseType").property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true)
				.path("2").path("typeParameter").path("1").register(JacksonFeature.class).request()
				.header("user", "user").put(null);
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testRevokeTypeParameterToExpenseType() {
		Response response = target("expenseType").property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true)
				.path("2").path("typeParameter").path("2").register(JacksonFeature.class).request()
				.header("user", "user").delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { ExpenseTypeResource.class };
	}
}

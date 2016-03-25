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
import resources.Type.ExpenseCategoryResource;
import sendto.RoleSendto;

public class ExpenseCateTypeResourceTest extends ResourceTest {

	@Test
	public void testFindAllExpenseType() {
		Response response = target("expenseCategory").path("2").path("expenseType").register(JacksonFeature.class)
				.request().header("user", "user").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PageModel<RoleSendto> rets = response.readEntity(new GenericType<PageModel<RoleSendto>>() {
		});
		assertEquals(1, rets.getTotalElements());
	}

	@Test
	public void testFindExpenseType() {
		Response response = target("expenseCategory").path("2").path("expenseType").path("2")
				.register(JacksonFeature.class).request().header("user", "user").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		RoleSendto transfer = response.readEntity(RoleSendto.class);
		assertEquals(2l, transfer.getId().longValue());
	}

	@Test
	public void testFindExpenseTypeWithNotFoundException() {
		Response response = target("expenseCategory").path("2").path("expenseType").path("4")
				.register(JacksonFeature.class).request().header("user", "user").get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testAssignExpenseTypeToExpenseCategory() {
		Response response = target("expenseCategory")
				.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true).path("2").path("expenseType")
				.path("1").register(JacksonFeature.class).request().header("user", "user").put(null);
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testAssignExpenseTypeToExpenseCategoryWithExpenseTypeNotFound() {
		Response response = target("expenseCategory")
				.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true).path("2").path("expenseType")
				.path("4").register(JacksonFeature.class).request().header("user", "user").put(null);
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testRevokeExpenseTypeToExpenseCategory() {
		Response response = target("expenseCategory")
				.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true).path("2").path("expenseType")
				.path("2").register(JacksonFeature.class).request().header("user", "user").delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testRevokeExpenseTypeToExpenseCategoryWithExpenseTypeNotFound() {
		Response response = target("expenseCategory")
				.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true).path("2").path("expenseType")
				.path("3").register(JacksonFeature.class).request().header("user", "user").delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { ExpenseCategoryResource.class };
	}

}

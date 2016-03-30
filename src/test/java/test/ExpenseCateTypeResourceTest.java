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
import resources.Type.ExpenseCategoryResource;
import sendto.ExpenseTypeSendto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExpenseCateTypeResourceTest extends ResourceTest {

	@Test
	public void testFindAllExpenseType() {
		Response response = target("expenseCategory").path("2").path("expenseType").register(JacksonFeature.class)
				.request().header("user", "user").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PageModel<ExpenseTypeSendto> rets = response.readEntity(new GenericType<PageModel<ExpenseTypeSendto>>() {
		});
		assertEquals(1, rets.getTotalElements());
	}

	@Test
	public void testFindExpenseType() {
		Response response = target("expenseCategory").path("2").path("expenseType").path("2")
				.register(JacksonFeature.class).request().header("user", "user").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseTypeSendto sendto = response.readEntity(ExpenseTypeSendto.class);
		assertEquals(2l, sendto.getId().longValue());
	}

	@Test
	public void test1FindExpenseTypeWithNotFoundException() {
		Response response = target("expenseCategory").path("2").path("expenseType").path("4")
				.register(JacksonFeature.class).request().header("user", "user").get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void test1AssignExpenseTypeToExpenseCategory() {
		Response response = target("expenseCategory")
				.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true).path("2").path("expenseType")
				.path("1").register(JacksonFeature.class).request().header("user", "user").put(null);
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testRevokeExpenseTypeToExpenseCategory() {
		Response response = target("expenseCategory")
				.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true).path("2").path("expenseType")
				.path("2").register(JacksonFeature.class).request().header("user", "user").delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { ExpenseCategoryResource.class };
	}

}

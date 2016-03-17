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
import resources.Type.ExpenseCategoryResource;
import sendto.ExpenseCategorySendto;

public class ExpenseCategoryResourceTest extends ResourceTest {

	@Test
	public void testGetExpenseCategory() {
		Response response = target("expenseCategory").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseCategorySendto sendto = response.readEntity(ExpenseCategorySendto.class);
		assertEquals(1l, sendto.getId());
		assertEquals("Trip", sendto.getName());
		assertEquals("123", sendto.getCode());
	}

	@Test
	public void testGetExpenseCategoryWithNotFoundException() {
		Response response = target("expenseCategory").path("4").register(JacksonFeature.class).request()
				.header("user", "demo").get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteExpenseCategory() {
		Response response = target("expenseCategory").register(JacksonFeature.class).request().header("user", "demo")
				.delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteExpenseCategoryWithNotFoundException() {
		Response response = target("expenseCategory").register(JacksonFeature.class).request().header("user", "demo")
				.delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateExpenseCategory() {
		ExpenseCategorySendto admin = new ExpenseCategorySendto();
		admin.setName("administrator");
		Response response = target("expenseCategory").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseCategorySendto sendto = response.readEntity(ExpenseCategorySendto.class);
		assertEquals(1l, sendto.getId());
		assertEquals(admin.getName(), sendto.getName());
		assertEquals(admin.getCode(), sendto.getCode());
	}

	@Test
	public void testUpdateExpenseCategorytWithNotFoundException() {
		ExpenseCategorySendto admin = new ExpenseCategorySendto();
		admin.setName("administrator");
		Response response = target("expenseCategory").path("4").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveExpenseCategory() {
		ExpenseCategorySendto admin = new ExpenseCategorySendto();
		admin.setName("administrator");
		Response response = target("expenseCategory").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseCategorySendto sendto = response.readEntity(ExpenseCategorySendto.class);
		assertEquals(5l, sendto.getId());
		assertEquals(admin.getName(), sendto.getName());
		assertEquals(admin.getCode(), sendto.getCode());
	}

	@Test
	public void testSaveExpenseCategoryWithDuplicateName() {
		ExpenseCategorySendto admin = new ExpenseCategorySendto();
		admin.setName("Sales");
		Response response = target("expenseCategory").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		AssertUtils.assertBadRequest(response);
	}

	@Test
	public void testFindallExpenseCategory() {
		Response response = target("expenseCategory").register(JacksonFeature.class).request().header("user", "demo")
				.get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PageModel<ExpenseCategorySendto> rets = response
				.readEntity(new GenericType<PageModel<ExpenseCategorySendto>>() {
				});
		assertEquals(3, rets.getTotalElements());

	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { ExpenseCategoryResource.class };
	}

}

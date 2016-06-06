package test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import resources.ResourceTest;
import resources.Type.admin.ExpenseCategoryResource;
import sendto.ExpenseCategorySendto;
import assertion.AssertUtils;
import entity.PageModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExpenseCategoryResourceTest extends ResourceTest {

	@Test
	public void testGetExpenseCategory() {
		Response response = target("expenseCategory").path("1")
				.register(JacksonFeature.class).request()
				.header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseCategorySendto sendto = response
				.readEntity(ExpenseCategorySendto.class);
		assertEquals(1l, sendto.getId().longValue());
	}

	@Test
	public void test1GetExpenseCategoryWithNotFoundException() {
		Response response = target("expenseCategory").path("3")
				.register(JacksonFeature.class).request()
				.header("user", "demo").get();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testZDeleteExpenseCategory() {
		Response response = target("expenseCategory").path("2")
				.register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void test1ZDeleteExpenseCategoryWithNotFoundException() {
		Response response = target("expenseCategory").path("3")
				.register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateExpenseCategory() {
		ExpenseCategorySendto admin = new ExpenseCategorySendto();
		admin.setName_key("administrator");
		admin.setCode("transportation");
		Response response = target("expenseCategory").path("1")
				.register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseCategorySendto sendto = response
				.readEntity(ExpenseCategorySendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getName_key(), sendto.getName_key());
		assertEquals(admin.getCode(), sendto.getCode());
	}

	@Test
	public void test1UpdateExpenseCategoryWithNotFoundException() {
		ExpenseCategorySendto admin = new ExpenseCategorySendto();
		admin.setName_key("administrator");
		admin.setCode("transportation");
		Response response = target("expenseCategory").path("3")
				.register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveExpenseCategory() {
		ExpenseCategorySendto admin = new ExpenseCategorySendto();
		admin.setName_key("category.transport");
		admin.setCode("transport");
		Response response = target("expenseCategory")
				.register(JacksonFeature.class).request()
				.header("user", "demo").post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseCategorySendto sendto = response
				.readEntity(ExpenseCategorySendto.class);
		assertEquals(3l, sendto.getId().longValue());
		assertEquals(admin.getName_key(), sendto.getName_key());
		assertEquals(admin.getCode(), sendto.getCode());
	}

	@Test
	public void testFindallExpenseCategory() {
		Response response = target("expenseCategory")
				.register(JacksonFeature.class).request()
				.header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PageModel<ExpenseCategorySendto> rets = response
				.readEntity(new GenericType<PageModel<ExpenseCategorySendto>>() {
				});
		assertEquals(2, rets.getTotalElements());

	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { ExpenseCategoryResource.class };
	}

}

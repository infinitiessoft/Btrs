package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Test;

import assertion.AssertUtils;
import resources.ExpenseCategoryResource;
import resources.ResourceTest;
import sendto.ExpenseCategorySendto;

public class ExpenseCategoryResourceTest extends ResourceTest {

	@Test
	public void testGetExpenseCategory() {
		Response response = target("expenseCategory").path("1").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseCategorySendto sendto = response.readEntity(ExpenseCategorySendto.class);
		assertEquals(1l, sendto.getId());
		assertEquals("Trip", sendto.getName());
		assertEquals("123", sendto.getCode());
	}

	@Test
	public void testGetExpenseCategoryWithNotFoundException() {
		Response response = target("expenseCategory").path("4").register(JacksonFeature.class).request().get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteExpenseCategory() {
		Response response = target("expenseCategory").register(JacksonFeature.class).request().delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteExpenseCategoryWithNotFoundException() {
		Response response = target("expenseCategory").register(JacksonFeature.class).request().delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateExpenseCategory() {
		ExpenseCategorySendto admin = new ExpenseCategorySendto();
		admin.setName("administrator");
		Response response = target("expenseCategory").path("1").register(JacksonFeature.class).request()
				.put(Entity.json(admin));
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
				.put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveExpenseCategory() {
		ExpenseCategorySendto admin = new ExpenseCategorySendto();
		admin.setName("administrator");
		Response response = target("expenseCategory").register(JacksonFeature.class).request().post(Entity.json(admin));
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
		Response response = target("expenseCategory").register(JacksonFeature.class).request().post(Entity.json(admin));
		AssertUtils.assertBadRequest(response);
	}

	@Test
	public void testFindallExpenseCategory() {
		Response response = target("expenseCategory").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		Collection<ExpenseCategorySendto> rets = response.readEntity(new GenericType<List<ExpenseCategorySendto>>() {
		});
		assertEquals(200, response.getStatus());
		for (ExpenseCategorySendto expCat : rets) {
			assertNotNull(expCat.getId());
			assertNotNull(expCat.getName());
			assertNotNull(expCat.getCode());
		}
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { ExpenseCategoryResource.class };
	}

}

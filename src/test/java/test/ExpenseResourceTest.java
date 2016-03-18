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
import resources.ResourceTest;
import resources.Type.ExpenseResource;
import sendto.ExpenseSendto;

public class ExpenseResourceTest extends ResourceTest {

	@Test
	public void testGetExpense() {
		Response response = target("expense").path("1").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseSendto sendto = response.readEntity(ExpenseSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals("Good", sendto.getComment());
		assertEquals("Demo", sendto.getReport());
		assertEquals("12%", sendto.getTaxAmount());
		assertEquals("1234", sendto.getTotalAmount());
	}

	@Test
	public void testGetExpenseWithNotFoundException() {
		Response response = target("expense").path("4").register(JacksonFeature.class).request().get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteExpense() {
		Response response = target("expense").register(JacksonFeature.class).request().delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteExpenseWithNotFoundException() {
		Response response = target("expense").register(JacksonFeature.class).request().delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateExpense() {
		ExpenseSendto admin = new ExpenseSendto();
		admin.setTotalAmount(4321);
		Response response = target("expense").path("1").register(JacksonFeature.class).request()
				.put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseSendto sendto = response.readEntity(ExpenseSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getComment(), sendto.getComment());
		assertEquals(admin.getReport(), sendto.getReport());
		assertEquals(admin.getTaxAmount(), sendto.getTaxAmount());
		assertEquals(admin.getTotalAmount(), sendto.getTotalAmount());
	}

	@Test
	public void testUpdateExpenseWithNotFoundException() {
		ExpenseSendto admin = new ExpenseSendto();
		admin.setTotalAmount(4321);
		Response response = target("expense").path("4").register(JacksonFeature.class).request()
				.put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveExpense() {
		ExpenseSendto admin = new ExpenseSendto();
		admin.setTotalAmount(1234);
		Response response = target("expense").register(JacksonFeature.class).request().post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseSendto sendto = response.readEntity(ExpenseSendto.class);
		assertEquals(5l, sendto.getId().longValue());
		assertEquals(admin.getReport(), sendto.getReport());
		assertEquals(admin.getComment(), sendto.getComment());
		assertEquals(admin.getTaxAmount(), sendto.getTaxAmount());
		assertEquals(admin.getTotalAmount(), sendto.getTotalAmount());
	}

	@Test
	public void testSaveExpenseWithDuplicateName() {
		ExpenseSendto admin = new ExpenseSendto();
		admin.setTotalAmount(1234);
		Response response = target("expense").register(JacksonFeature.class).request().post(Entity.json(admin));
		AssertUtils.assertBadRequest(response);
	}

	@Test
	public void testFindallExpense() {
		Response response = target("expense").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		Collection<ExpenseSendto> rets = response.readEntity(new GenericType<List<ExpenseSendto>>() {
		});
		assertEquals(200, response.getStatus());
		for (ExpenseSendto exp : rets) {
			assertNotNull(exp.getId());
			assertNotNull(exp.getReport());
			assertNotNull(exp.getComment());
			assertNotNull(exp.getTaxAmount());
			assertNotNull(exp.getTotalAmount());
		}
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { ExpenseResource.class };
	}

}

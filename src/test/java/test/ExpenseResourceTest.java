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
import resources.Type.admin.ExpenseResource;
import sendto.ExpenseSendto;
import assertion.AssertUtils;
import entity.PageModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExpenseResourceTest extends ResourceTest {

	@Test
	public void testGetExpense() {
		Response response = target("expense").path("1").register(JacksonFeature.class).request().header("user", "demo")
				.get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseSendto sendto = response.readEntity(ExpenseSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals("no", sendto.getComment());
		assertEquals(60, sendto.getTaxAmount().intValue());
		assertEquals(1257, sendto.getTotalAmount().intValue());
	}

	@Test
	public void testGetExpenseWithNotFoundException() {
		Response response = target("expense").path("3").register(JacksonFeature.class).request().get();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testZDeleteExpense() {
		Response response = target("expense").path("2").register(JacksonFeature.class).request().header("user", "demo")
				.delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void test1ZDeleteExpenseWithNotFoundException() {
		Response response = target("expense").path("3").register(JacksonFeature.class).request().header("user", "demo")
				.delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateExpense() {
		ExpenseSendto admin = new ExpenseSendto();
		admin.setTotalAmount(4321);

		ExpenseSendto.ExpenseType expenseType = new ExpenseSendto.ExpenseType();
		expenseType.setId(1L);
		admin.setExpenseType(expenseType);

		ExpenseSendto.Report rpt = new ExpenseSendto.Report();
		rpt.setId(1L);
		admin.setReport(rpt);

		Response response = target("expense").path("1").register(JacksonFeature.class).request().header("user", "demo")
				.put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseSendto sendto = response.readEntity(ExpenseSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getTotalAmount(), sendto.getTotalAmount());
		assertEquals(admin.getExpenseType().getId(), sendto.getExpenseType().getId());
		assertEquals(admin.getReport().getId().longValue(), sendto.getReport().getId().longValue());
	}

	@Test
	public void test1UpdateExpenseWithNotFoundException() {
		ExpenseSendto admin = new ExpenseSendto();
		admin.setTotalAmount(4321);
		Response response = target("expense").path("3").register(JacksonFeature.class).request().header("user", "demo")
				.put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveExpense() {
		ExpenseSendto admin = new ExpenseSendto();
		admin.setTotalAmount(1257);
		admin.setTaxAmount(60);
		admin.setComment("no");
		ExpenseSendto.ExpenseType expenseType = new ExpenseSendto.ExpenseType();
		expenseType.setId(1L);
		admin.setExpenseType(expenseType);

		ExpenseSendto.Report rpt = new ExpenseSendto.Report();
		rpt.setId(1L);
		admin.setReport(rpt);

		Response response = target("expense").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseSendto sendto = response.readEntity(ExpenseSendto.class);
		assertEquals(3l, sendto.getId().longValue());
		assertEquals(admin.getComment(), sendto.getComment());
		assertEquals(admin.getTaxAmount(), sendto.getTaxAmount());
		assertEquals(admin.getTotalAmount(), sendto.getTotalAmount());
		assertEquals(admin.getExpenseType().getId().longValue(), sendto.getExpenseType().getId().longValue());
		assertEquals(admin.getReport().getId().longValue(), sendto.getReport().getId().longValue());

	}

	@Test
	public void testFindallExpense() {
		Response response = target("expense").register(JacksonFeature.class).request().header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PageModel<ExpenseSendto> rets = response.readEntity(new GenericType<PageModel<ExpenseSendto>>() {
		});
		assertEquals(2, rets.getTotalElements());

	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { ExpenseResource.class };
	}

}

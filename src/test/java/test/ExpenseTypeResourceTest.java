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
import resources.version1.admin.ExpenseTypeResource;
import sendto.ExpenseTypeSendto;
import assertion.AssertUtils;
import entity.PageModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExpenseTypeResourceTest extends ResourceTest {

	@Test
	public void testGetExpenseType() {
		Response response = target("expenseType").path("1").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseTypeSendto sendto = response.readEntity(ExpenseTypeSendto.class);
		assertEquals(1l, sendto.getId().longValue());
	}

	@Test
	public void test1GetExpenseTypeWithNotFoundException() {
		Response response = target("expenseType").path("3").register(JacksonFeature.class).request()
				.header("user", "demo").get();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testZDeleteExpenseType() {
		Response response = target("expenseType").path("2").register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void test1ZDeleteExpenseTypeWithNotFoundException() {
		Response response = target("expenseType").path("3").register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateExpenseType() {
		ExpenseTypeSendto admin = new ExpenseTypeSendto();
		admin.setTaxPercent(5.00);
		admin.setValue("value");
		Response response = target("expenseType").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseTypeSendto sendto = response.readEntity(ExpenseTypeSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getValue(), sendto.getValue());
		assertEquals(admin.getTaxPercent(), sendto.getTaxPercent());
	}

	@Test
	public void test1UpdateExpenseTypeWithNotFoundException() {
		ExpenseTypeSendto admin = new ExpenseTypeSendto();
		admin.setTaxPercent(5.00);
		admin.setValue("value");
		Response response = target("expenseType").path("3").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveExpenseType() {
		ExpenseTypeSendto admin = new ExpenseTypeSendto();
		admin.setTaxPercent(60.00);
		admin.setValue("Transp");
		Response response = target("expenseType").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseTypeSendto sendto = response.readEntity(ExpenseTypeSendto.class);
		assertEquals(3l, sendto.getId().longValue());
		assertEquals(admin.getTaxPercent(), sendto.getTaxPercent());
		assertEquals(admin.getValue(), sendto.getValue());

	}

	@Test
	public void testFindallExpenseType() {
		Response response = target("expenseType").register(JacksonFeature.class).request().header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PageModel<ExpenseTypeSendto> rets = response.readEntity(new GenericType<PageModel<ExpenseTypeSendto>>() {
		});
		assertEquals(2, rets.getTotalElements());

	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { ExpenseTypeResource.class };
	}

}

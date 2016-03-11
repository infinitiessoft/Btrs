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
import resources.ExpenseTypeResource;
import resources.ResourceTest;
import sendto.ExpenseTypeSendto;

public class ExpenseTypeResourceTest extends ResourceTest {

	@Test
	public void testGetExpenseType() {
		Response response = target("expType").path("1").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseTypeSendto sendto = response.readEntity(ExpenseTypeSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals("5%", sendto.getTaxPercent());

	}

	@Test
	public void testGetExpenseTypeWithNotFoundException() {
		Response response = target("expType").path("4").register(JacksonFeature.class).request().get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteExpenseType() {
		Response response = target("expType").register(JacksonFeature.class).request().delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteExpenseTypeWithNotFoundException() {
		Response response = target("expType").register(JacksonFeature.class).request().delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateExpenseType() {
		ExpenseTypeSendto admin = new ExpenseTypeSendto();
		admin.setTaxPercent(5.00);
		Response response = target("expType").path("1").register(JacksonFeature.class).request()
				.put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseTypeSendto sendto = response.readEntity(ExpenseTypeSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getTaxPercent(), sendto.getTaxPercent());
	}

	@Test
	public void testUpdateExpenseTypeWithNotFoundException() {
		ExpenseTypeSendto admin = new ExpenseTypeSendto();
		admin.setTaxPercent(5.00);
		Response response = target("expType").path("4").register(JacksonFeature.class).request()
				.put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveExpenseType() {
		ExpenseTypeSendto admin = new ExpenseTypeSendto();
		admin.setTaxPercent(5.00);
		Response response = target("expType").register(JacksonFeature.class).request().post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseTypeSendto sendto = response.readEntity(ExpenseTypeSendto.class);
		assertEquals(5l, sendto.getId().longValue());
		assertEquals(admin.getTaxPercent(), sendto.getTaxPercent());

	}

	@Test
	public void testSaveExpenseTypeWithDuplicateName() {
		ExpenseTypeSendto admin = new ExpenseTypeSendto();
		admin.setTaxPercent(5.00);
		Response response = target("expType").register(JacksonFeature.class).request().post(Entity.json(admin));
		AssertUtils.assertBadRequest(response);
	}

	@Test
	public void testFindallExpenseType() {
		Response response = target("expType").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		Collection<ExpenseTypeSendto> rets = response.readEntity(new GenericType<List<ExpenseTypeSendto>>() {
		});
		assertEquals(200, response.getStatus());
		for (ExpenseTypeSendto expType : rets) {
			assertNotNull(expType.getId());
			assertNotNull(expType.getTaxPercent());
		}
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { ExpenseTypeResource.class };
	}

}

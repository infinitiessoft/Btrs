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
import resources.ExpenseTypeParaResource;
import resources.ResourceTest;
import sendto.ExpenseTypeParaSendto;

public class ExpenseTypeParaResourceTest extends ResourceTest {

	@Test
	public void testGetExpenseTypePara() {
		Response response = target("expTypePara").path("1").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseTypeParaSendto sendto = response.readEntity(ExpenseTypeParaSendto.class);
		assertEquals(1l, sendto.getId().longValue());
	}

	@Test
	public void testGetExpenseTypeParaWithNotFoundException() {
		Response response = target("expTypePara").path("3").register(JacksonFeature.class).request().get();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testDeleteExpenseTypePara() {
		Response response = target("expTypePara").path("1").register(JacksonFeature.class).request().delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteExpenseTypeParaWithNotFoundException() {
		Response response = target("expTypePara").path("3").register(JacksonFeature.class).request().delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveExpenseTypePara() {
		ExpenseTypeParaSendto expTypePara = new ExpenseTypeParaSendto();
		expTypePara.setId(1l);
		Response response = target("expTypePara").register(JacksonFeature.class).request()
				.post(Entity.json(expTypePara));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseTypeParaSendto sendto = response.readEntity(ExpenseTypeParaSendto.class);
		assertEquals(4L, sendto.getId().longValue());
		assertEquals(expTypePara.getExpenseType().getId(), sendto.getExpenseType().getId());
		assertEquals(expTypePara.getParameterValue().getId(), sendto.getParameterValue().getId());
		assertEquals(expTypePara.getId(), sendto.getId());
	}

	@Test
	public void testSaveExpenseTypeParaWithDuplicateTypeAndParameter() {
		ExpenseTypeParaSendto expTypePara = new ExpenseTypeParaSendto();
		expTypePara.setId(1l);
		Response response = target("expTypePara").register(JacksonFeature.class).request()
				.post(Entity.json(expTypePara));
		AssertUtils.assertBadRequest(response);
	}

	@Test
	public void testUpdateExpenseTypePara() {
		ExpenseTypeParaSendto expTypePara = new ExpenseTypeParaSendto();
		expTypePara.setId(1l);

		Response response = target("expTypePara").path("1").register(JacksonFeature.class).request()
				.put(Entity.json(expTypePara));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseTypeParaSendto sendto = response.readEntity(ExpenseTypeParaSendto.class);
		assertEquals(1L, sendto.getId().longValue());
	}

	@Test
	public void testUpdateExpenseTypeParaWithNotFoundException() {
		ExpenseTypeParaSendto expTypePara = new ExpenseTypeParaSendto();
		expTypePara.setId(1l);

		Response response = target("expTypePara").path("3").register(JacksonFeature.class).request()
				.put(Entity.json(expTypePara));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testFindAllExpenseTypePara() {
		Response response = target("expTypePara").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		Collection<ExpenseTypeParaSendto> rets = response.readEntity(new GenericType<List<ExpenseTypeParaSendto>>() {
		});
		assertEquals(200, response.getStatus());
		for (ExpenseTypeParaSendto expTypePara : rets) {
			assertNotNull(expTypePara.getId().toString());
		}
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { ExpenseTypeParaResource.class };
	}

}

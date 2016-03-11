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
import resources.ExpenseCateTypeResource;
import resources.ResourceTest;
import sendto.ExpenseCateTypeSendto;

public class ExpenseCateTypeResourceTest extends ResourceTest {

	@Test
	public void testGetExpenseCateType() {
		Response response = target("expCateType").path("1").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseCateTypeSendto sendto = response.readEntity(ExpenseCateTypeSendto.class);
		assertEquals(1l, sendto.getId().longValue());
	}

	@Test
	public void testGetExpenseCateTypeWithNotFoundException() {
		Response response = target("expCateType").path("3").register(JacksonFeature.class).request().get();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testDeleteExpenseCateType() {
		Response response = target("expCateType").path("1").register(JacksonFeature.class).request().delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteExpenseCateTypeWithNotFoundException() {
		Response response = target("expCateType").path("3").register(JacksonFeature.class).request().delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveExpenseCateType() {
		ExpenseCateTypeSendto expCateType = new ExpenseCateTypeSendto();
		expCateType.setId(1l);
		Response response = target("expCateType").register(JacksonFeature.class).request()
				.post(Entity.json(expCateType));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseCateTypeSendto sendto = response.readEntity(ExpenseCateTypeSendto.class);
		assertEquals(4L, sendto.getId().longValue());
		assertEquals(expCateType.getExpCate().getId(), sendto.getExpCate().getId());
		assertEquals(expCateType.getExpType().getId(), sendto.getExpType().getId());
		assertEquals(expCateType.getId(), sendto.getId());
	}

	@Test
	public void testSaveExpenseCateTypeWithDuplicateCategoryAndType() {
		ExpenseCateTypeSendto expCateType = new ExpenseCateTypeSendto();
		expCateType.setId(1l);
		Response response = target("expCateType").register(JacksonFeature.class).request()
				.post(Entity.json(expCateType));
		AssertUtils.assertBadRequest(response);
	}

	@Test
	public void testUpdateExpenseCateType() {
		ExpenseCateTypeSendto expCateType = new ExpenseCateTypeSendto();
		expCateType.setId(1l);

		Response response = target("expCateType").path("1").register(JacksonFeature.class).request()
				.put(Entity.json(expCateType));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ExpenseCateTypeSendto sendto = response.readEntity(ExpenseCateTypeSendto.class);
		assertEquals(1L, sendto.getId().longValue());
	}

	@Test
	public void testUpdateExpenseCateTypeWithNotFoundException() {
		ExpenseCateTypeSendto expCateType = new ExpenseCateTypeSendto();
		expCateType.setId(1l);

		Response response = target("expCateType").path("3").register(JacksonFeature.class).request()
				.put(Entity.json(expCateType));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testFindAllExpenseCateType() {
		Response response = target("expCateType").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		Collection<ExpenseCateTypeSendto> rets = response.readEntity(new GenericType<List<ExpenseCateTypeSendto>>() {
		});
		assertEquals(200, response.getStatus());
		for (ExpenseCateTypeSendto expCateType : rets) {
			assertNotNull(expCateType.getId().toString());
		}
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { ExpenseCateTypeResource.class };
	}

}

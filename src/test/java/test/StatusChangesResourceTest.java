package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Test;

import assertion.AssertUtils;
import resources.ResourceTest;
import resources.StatusChangesResource;
import sendto.StatusChangesSendto;

public class StatusChangesResourceTest extends ResourceTest {

	@Test
	public void testGetStatusChanges() {
		Response response = target("statusChanges").path("1").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		StatusChangesSendto sendto = response.readEntity(StatusChangesSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals("Good", sendto.getComment());
		assertEquals(new Date(), sendto.getCreatedDate());
	}

	@Test
	public void testGetStatusChangesWithNotFoundException() {
		Response response = target("statusChanges").path("4").register(JacksonFeature.class).request().get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeletePhoto() {
		Response response = target("photo").register(JacksonFeature.class).request().delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteStatusChangesWithNotFoundException() {
		Response response = target("statusChanges").register(JacksonFeature.class).request().delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateStatusChanges() {
		StatusChangesSendto admin = new StatusChangesSendto();
		admin.setCreatedDate(new Date());
		Response response = target("statusChanges").path("1").register(JacksonFeature.class).request()
				.put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		StatusChangesSendto sendto = response.readEntity(StatusChangesSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getComment(), sendto.getComment());
		assertEquals(admin.getCreatedDate(), sendto.getCreatedDate());
	}

	@Test
	public void testUpdateStatusChangesWithNotFoundException() {
		StatusChangesSendto admin = new StatusChangesSendto();
		admin.setCreatedDate(new Date());
		Response response = target("statusChanges").path("4").register(JacksonFeature.class).request()
				.put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveStatusChanges() {
		StatusChangesSendto admin = new StatusChangesSendto();
		admin.setCreatedDate(new Date());
		Response response = target("statusChanges").register(JacksonFeature.class).request().post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		StatusChangesSendto sendto = response.readEntity(StatusChangesSendto.class);
		assertEquals(5l, sendto.getId().longValue());
		assertEquals(admin.getComment(), sendto.getComment());
		assertEquals(admin.getCreatedDate(), sendto.getCreatedDate());
	}

	@Test
	public void testSaveStatusChangesWithDuplicateName() {
		StatusChangesSendto admin = new StatusChangesSendto();
		admin.setCreatedDate(new Date());
		Response response = target("statusChanges").register(JacksonFeature.class).request().post(Entity.json(admin));
		AssertUtils.assertBadRequest(response);
	}

	@Test
	public void testFindallStatusChanges() {
		Response response = target("statusChanges").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		Collection<StatusChangesSendto> rets = response.readEntity(new GenericType<List<StatusChangesSendto>>() {
		});
		assertEquals(200, response.getStatus());
		for (StatusChangesSendto status : rets) {
			assertNotNull(status.getId().longValue());
			assertNotNull(status.getComment());
			assertNotNull(status.getCreatedDate());
		}
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { StatusChangesResource.class };
	}

}

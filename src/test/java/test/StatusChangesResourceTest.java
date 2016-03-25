package test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Test;

import assertion.AssertUtils;
import entity.PageModel;
import resources.ResourceTest;
import resources.Type.StatusChangesResource;
import sendto.StatusChangesSendto;

public class StatusChangesResourceTest extends ResourceTest {

	@Test
	public void testGetStatusChanges() {
		Response response = target("statusChanges").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		StatusChangesSendto sendto = response.readEntity(StatusChangesSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals("no", sendto.getComment());
		assertEquals("approved", sendto.getValue());
	}

	@Test
	public void testGetStatusChangesWithNotFoundException() {
		Response response = target("statusChanges").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").get();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testDeleteStatusChanges() {
		Response response = target("statusChanges").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteStatusChangesWithNotFoundException() {
		Response response = target("statusChanges").path("3").register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void testUpdateStatusChanges() {
		StatusChangesSendto admin = new StatusChangesSendto();
		admin.setCreatedDate(new Date());
		admin.setComment("Good");

		StatusChangesSendto.Report report = new StatusChangesSendto.Report();
		report.setId(2L);
		admin.setReport(report);

		StatusChangesSendto.User user = new StatusChangesSendto.User();
		user.setRevisor_id(2L);
		admin.setUser(user);

		Response response = target("statusChanges").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		StatusChangesSendto sendto = response.readEntity(StatusChangesSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getComment(), sendto.getComment());
		assertEquals(admin.getCreatedDate(), sendto.getCreatedDate());
		assertEquals(admin.getReport().getId(), sendto.getReport().getId());
		assertEquals(admin.getUser().getRevisor_id(), sendto.getUser().getRevisor_id());
	}

	@Test
	public void testUpdateStatusChangesWithNotFoundException() {
		StatusChangesSendto admin = new StatusChangesSendto();
		admin.setCreatedDate(new Date());
		admin.setComment("Good");

		StatusChangesSendto.Report report = new StatusChangesSendto.Report();
		report.setId(2L);
		admin.setReport(report);

		StatusChangesSendto.User user = new StatusChangesSendto.User();
		user.setRevisor_id(2L);
		admin.setUser(user);
		Response response = target("statusChanges").path("4").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void testSaveStatusChanges() {
		StatusChangesSendto admin = new StatusChangesSendto();
		admin.setCreatedDate(new Date());
		admin.setComment("no");

		StatusChangesSendto.Report report = new StatusChangesSendto.Report();
		report.setId(2L);
		admin.setReport(report);

		StatusChangesSendto.User user = new StatusChangesSendto.User();
		user.setRevisor_id(2L);
		admin.setUser(user);
		Response response = target("statusChanges").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		StatusChangesSendto sendto = response.readEntity(StatusChangesSendto.class);
		assertEquals(5l, sendto.getId().longValue());
		assertEquals(admin.getComment(), sendto.getComment());
		assertEquals(admin.getCreatedDate(), sendto.getCreatedDate());
		assertEquals(admin.getReport().getId(), sendto.getReport().getId());
		assertEquals(admin.getUser().getRevisor_id(), sendto.getUser().getRevisor_id());
	}

	@Test
	public void testSaveStatusChangesWithDuplicateName() {
		StatusChangesSendto admin = new StatusChangesSendto();
		admin.setCreatedDate(new Date());
		admin.setComment("no");

		StatusChangesSendto.Report report = new StatusChangesSendto.Report();
		report.setId(2L);
		admin.setReport(report);

		StatusChangesSendto.User user = new StatusChangesSendto.User();
		user.setRevisor_id(2L);
		admin.setUser(user);
		Response response = target("statusChanges").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void testFindallStatusChanges() {
		Response response = target("statusChanges").register(JacksonFeature.class).request().header("user", "demo")
				.get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PageModel<StatusChangesSendto> rets = response.readEntity(new GenericType<PageModel<StatusChangesSendto>>() {
		});
		assertEquals(2, rets.getTotalElements());

	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { StatusChangesResource.class };
	}

}

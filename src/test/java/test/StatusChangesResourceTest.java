package test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import resources.ResourceTest;
import resources.Type.admin.StatusChangesResource;
import sendto.StatusChangesSendto;
import assertion.AssertUtils;
import entity.PageModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StatusChangesResourceTest extends ResourceTest {

	@Test
	public void testGetStatusChanges() {
		Response response = target("statusChanges").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		StatusChangesSendto sendto = response.readEntity(StatusChangesSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals("no", sendto.getComment());
	}

	@Test
	public void test1GetStatusChangesWithNotFoundException() {
		Response response = target("statusChanges").path("3").register(JacksonFeature.class).request()
				.header("user", "demo").get();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testZDeleteStatusChanges() {
		Response response = target("statusChanges").path("2").register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void test1ZDeleteStatusChangesWithNotFoundException() {
		Response response = target("statusChanges").path("3").register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateStatusChanges() {
		StatusChangesSendto admin = new StatusChangesSendto();
		admin.setComment("Good");

		StatusChangesSendto.Report report = new StatusChangesSendto.Report();
		report.setId(1L);
		admin.setReport(report);

		StatusChangesSendto.User user = new StatusChangesSendto.User();
		user.setRevisor_id(1L);
		admin.setUser(user);

		Response response = target("statusChanges").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		StatusChangesSendto sendto = response.readEntity(StatusChangesSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getComment(), sendto.getComment());
		assertEquals(admin.getReport().getId().longValue(), sendto.getReport().getId().longValue());
		assertEquals(admin.getUser().getRevisor_id().longValue(), sendto.getUser().getRevisor_id().longValue());
	}

	@Test
	public void test1UpdateStatusChangesWithNotFoundException() {
		StatusChangesSendto admin = new StatusChangesSendto();
		admin.setCreatedDate(new Date());
		admin.setComment("Good");

		StatusChangesSendto.Report report = new StatusChangesSendto.Report();
		report.setId(1L);
		admin.setReport(report);

		StatusChangesSendto.User user = new StatusChangesSendto.User();
		user.setRevisor_id(1L);
		admin.setUser(user);
		Response response = target("statusChanges").path("4").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveStatusChanges() {
		StatusChangesSendto admin = new StatusChangesSendto();
		admin.setCreatedDate(new Date());
		admin.setComment("no");
		admin.setValue("value");
		StatusChangesSendto.Report report = new StatusChangesSendto.Report();
		report.setId(1L);
		admin.setReport(report);

		StatusChangesSendto.User user = new StatusChangesSendto.User();
		user.setRevisor_id(1L);
		admin.setUser(user);
		Response response = target("statusChanges").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		StatusChangesSendto sendto = response.readEntity(StatusChangesSendto.class);
		assertEquals(3l, sendto.getId().longValue());
		assertEquals(admin.getComment(), sendto.getComment());
		assertEquals(admin.getCreatedDate(), sendto.getCreatedDate());
		assertEquals(admin.getReport().getId().longValue(), sendto.getReport().getId().longValue());
		assertEquals(admin.getUser().getRevisor_id().longValue(), sendto.getUser().getRevisor_id().longValue());
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

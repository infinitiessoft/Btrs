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
import resources.version1.admin.StatusChangeResource;
import sendto.StatusChangeSendto;
import assertion.AssertUtils;
import entity.PageModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StatusChangesResourceTest extends ResourceTest {

	@Test
	public void testGetStatusChanges() {
		Response response = target("statusChanges").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		StatusChangeSendto sendto = response.readEntity(StatusChangeSendto.class);
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
		StatusChangeSendto admin = new StatusChangeSendto();
		admin.setComment("Good");

		StatusChangeSendto.Report report = new StatusChangeSendto.Report();
		report.setId(1L);
		admin.setReport(report);

		StatusChangeSendto.User user = new StatusChangeSendto.User();
		user.setRevisor_id(1L);
		admin.setUser(user);

		Response response = target("statusChanges").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		StatusChangeSendto sendto = response.readEntity(StatusChangeSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getComment(), sendto.getComment());
		assertEquals(admin.getReport().getId().longValue(), sendto.getReport().getId().longValue());
		assertEquals(admin.getUser().getRevisor_id().longValue(), sendto.getUser().getRevisor_id().longValue());
	}

	@Test
	public void test1UpdateStatusChangesWithNotFoundException() {
		StatusChangeSendto admin = new StatusChangeSendto();
		admin.setCreatedDate(new Date());
		admin.setComment("Good");

		StatusChangeSendto.Report report = new StatusChangeSendto.Report();
		report.setId(1L);
		admin.setReport(report);

		StatusChangeSendto.User user = new StatusChangeSendto.User();
		user.setRevisor_id(1L);
		admin.setUser(user);
		Response response = target("statusChanges").path("4").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveStatusChanges() {
		StatusChangeSendto admin = new StatusChangeSendto();
		admin.setCreatedDate(new Date());
		admin.setComment("no");
		admin.setValue("value");
		StatusChangeSendto.Report report = new StatusChangeSendto.Report();
		report.setId(1L);
		admin.setReport(report);

		StatusChangeSendto.User user = new StatusChangeSendto.User();
		user.setRevisor_id(1L);
		admin.setUser(user);
		Response response = target("statusChanges").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		StatusChangeSendto sendto = response.readEntity(StatusChangeSendto.class);
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
		PageModel<StatusChangeSendto> rets = response.readEntity(new GenericType<PageModel<StatusChangeSendto>>() {
		});
		assertEquals(2, rets.getTotalElements());

	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { StatusChangeResource.class };
	}

}

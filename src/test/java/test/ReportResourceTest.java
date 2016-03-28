package test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Test;

import assertion.AssertUtils;
import entity.PageModel;
import resources.ResourceTest;
import resources.Type.ReportResource;
import sendto.ReportSendto;

public class ReportResourceTest extends ResourceTest {

	@Test
	public void testGetReport() {
		Response response = target("report").path("1").register(JacksonFeature.class).request().header("user", "demo")
				.get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ReportSendto sendto = response.readEntity(ReportSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(1l, sendto.getAttendanceRecordId().longValue());
		assertEquals(1l, sendto.getMaxIdLastMonth().longValue());
		assertEquals("no", sendto.getComment());
		assertEquals("pic", sendto.getReason());
		assertEquals("Kaoshiung<-->Taipei", sendto.getRoute());
		assertEquals("approved", sendto.getCurrentStatus());
	}

	@Test
	public void testGetReportWithNotFoundException() {
		Response response = target("report").path("3").register(JacksonFeature.class).request().header("user", "demo")
				.get();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testDeleteReport() {
		Response response = target("report").path("1").register(JacksonFeature.class).request().header("user", "demo")
				.delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteReportWithNotFoundException() {
		Response response = target("report").path("3").register(JacksonFeature.class).request().header("user", "demo")
				.delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateReport() {
		ReportSendto admin = new ReportSendto();
		admin.setReason("pic");
		admin.setAttendanceRecordId(2L);
		admin.setComment("Good");
		admin.setMaxIdLastMonth(3L);
		admin.setCurrentStatus("approved");

		Response response = target("report").path("1").register(JacksonFeature.class).request().header("user", "demo")
				.put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ReportSendto sendto = response.readEntity(ReportSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getAttendanceRecordId(), sendto.getAttendanceRecordId());
		assertEquals(admin.getComment(), sendto.getComment());
		assertEquals(admin.getMaxIdLastMonth(), sendto.getMaxIdLastMonth());
		assertEquals(admin.getReason(), sendto.getReason());
		assertEquals(admin.getCurrentStatus(), sendto.getCurrentStatus());
	}

	@Test
	public void testUpdateReportWithNotFoundException() {
		ReportSendto admin = new ReportSendto();

		admin.setAttendanceRecordId(2L);
		admin.setComment("Good");
		admin.setMaxIdLastMonth(2L);
		Response response = target("report").path("3").register(JacksonFeature.class).request().header("user", "demo")
				.put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveReport() {
		ReportSendto admin = new ReportSendto();
		admin.setAttendanceRecordId(2L);
		admin.setComment("Good");
		admin.setMaxIdLastMonth(2L);
		admin.setCurrentStatus("approved");
		Response response = target("report").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ReportSendto sendto = response.readEntity(ReportSendto.class);
		assertEquals(3l, sendto.getId().longValue());
		assertEquals(admin.getAttendanceRecordId(), sendto.getAttendanceRecordId());
		assertEquals(admin.getComment(), sendto.getComment());
		assertEquals(admin.getMaxIdLastMonth(), sendto.getMaxIdLastMonth());
		assertEquals(admin.getReason(), sendto.getReason());
		assertEquals(admin.getRoute(), sendto.getRoute());
		assertEquals(admin.getCurrentStatus(), sendto.getCurrentStatus());
	}

	@Test
	public void testFindallReport() {
		Response response = target("report").register(JacksonFeature.class).request().header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PageModel<ReportSendto> rets = response.readEntity(new GenericType<PageModel<ReportSendto>>() {
		});
		assertEquals(2, rets.getTotalElements());

	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { ReportResource.class };
	}

}

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
import resources.Type.ReportResource;
import sendto.ReportSendto;

public class ReportResourceTest extends ResourceTest {

	@Test
	public void testGetReport() {
		Response response = target("report").path("1").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ReportSendto sendto = response.readEntity(ReportSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(2l, sendto.getAttendanceRecordId().longValue());
		assertEquals("", sendto.getMaxIdLastMonth().longValue());
		assertEquals("Good", sendto.getComment());
		assertEquals(new Date(), sendto.getCreatedDate());
		assertEquals("Trip", sendto.getReason());
		assertEquals("Taipei", sendto.getRoute());
		assertEquals(new Date(), sendto.getStartDate());
		assertEquals(new Date(), sendto.getEndDate());
		assertEquals(new Date(), sendto.getLastUpdatedDate());
	}

	@Test
	public void testGetReportWithNotFoundException() {
		Response response = target("report").path("4").register(JacksonFeature.class).request().get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteReport() {
		Response response = target("report").register(JacksonFeature.class).request().delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteReportWithNotFoundException() {
		Response response = target("report").register(JacksonFeature.class).request().delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateReport() {
		ReportSendto admin = new ReportSendto();
		admin.setStartDate(new Date());
		Response response = target("report").path("1").register(JacksonFeature.class).request().put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ReportSendto sendto = response.readEntity(ReportSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getAttendanceRecordId(), sendto.getAttendanceRecordId());
		assertEquals(admin.getComment(), sendto.getComment());
		assertEquals(admin.getCreatedDate(), sendto.getCreatedDate());
		assertEquals(admin.getEndDate(), sendto.getEndDate());
		assertEquals(admin.getLastUpdatedDate(), sendto.getLastUpdatedDate());
		assertEquals(admin.getMaxIdLastMonth(), sendto.getMaxIdLastMonth());
		assertEquals(admin.getReason(), sendto.getReason());
		assertEquals(admin.getRoute(), sendto.getRoute());
		assertEquals(admin.getStartDate(), sendto.getStartDate());
	}

	@Test
	public void testUpdateReportWithNotFoundException() {
		ReportSendto admin = new ReportSendto();
		admin.setStartDate(new Date());
		Response response = target("report").path("4").register(JacksonFeature.class).request().put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveReport() {
		ReportSendto admin = new ReportSendto();
		admin.setStartDate(new Date());
		Response response = target("report").register(JacksonFeature.class).request().post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ReportSendto sendto = response.readEntity(ReportSendto.class);
		assertEquals(5l, sendto.getId().longValue());
		assertEquals(admin.getAttendanceRecordId(), sendto.getAttendanceRecordId());
		assertEquals(admin.getComment(), sendto.getComment());
		assertEquals(admin.getCreatedDate(), sendto.getCreatedDate());
		assertEquals(admin.getEndDate(), sendto.getEndDate());
		assertEquals(admin.getLastUpdatedDate(), sendto.getLastUpdatedDate());
		assertEquals(admin.getMaxIdLastMonth(), sendto.getMaxIdLastMonth());
		assertEquals(admin.getReason(), sendto.getReason());
		assertEquals(admin.getRoute(), sendto.getRoute());
		assertEquals(admin.getStartDate(), sendto.getStartDate());
	}

	@Test
	public void testSaveReportWithDuplicateName() {
		ReportSendto admin = new ReportSendto();
		admin.setStartDate(new Date());
		Response response = target("report").register(JacksonFeature.class).request().post(Entity.json(admin));
		AssertUtils.assertBadRequest(response);
	}

	@Test
	public void testFindallReport() {
		Response response = target("report").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		Collection<ReportSendto> rets = response.readEntity(new GenericType<List<ReportSendto>>() {
		});
		assertEquals(200, response.getStatus());
		for (ReportSendto report : rets) {
			assertNotNull(report.getId().toString());
			assertNotNull(report.getAttendanceRecordId());
			assertNotNull(report.getComment());
			assertNotNull(report.getCreatedDate());
			assertNotNull(report.getEndDate());
			assertNotNull(report.getLastUpdatedDate());
			assertNotNull(report.getMaxIdLastMonth());
			assertNotNull(report.getReason());
			assertNotNull(report.getRoute());
			assertNotNull(report.getStartDate());
		}
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { ReportResource.class };
	}

}

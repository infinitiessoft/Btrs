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
import resources.version1.admin.ReportResource;
import sendto.ReportSendto;
import entity.PageModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
		Response response = target("report").path("5").register(JacksonFeature.class).request().header("user", "demo")
				.get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testZDeleteReport() {
		Response response = target("report").path("1").register(JacksonFeature.class).request().header("user", "demo")
				.delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void test1ZDeleteReportWithNotFoundException() {
		Response response = target("report").path("5").register(JacksonFeature.class).request().header("user", "demo")
				.delete();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testUpdateReport() {
		ReportSendto admin = new ReportSendto();
		admin.setReason("pic");
		admin.setComment("no");
		admin.setAttendanceRecordId(1l);
		admin.setMaxIdLastMonth(1l);

		ReportSendto.User user = new ReportSendto.User();
		user.setId(2L);
		admin.setUserOwner(user);

		ReportSendto.User review = new ReportSendto.User();
		review.setId(2L);
		admin.setUserReviewer(review);

		Response response = target("report").path("1").register(JacksonFeature.class).request().header("user", "demo")
				.put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ReportSendto sendto = response.readEntity(ReportSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getAttendanceRecordId().longValue(), sendto.getAttendanceRecordId().longValue());
		assertEquals(admin.getComment(), sendto.getComment());
		assertEquals(admin.getMaxIdLastMonth(), sendto.getMaxIdLastMonth());
		assertEquals(admin.getUserOwner().getId(), sendto.getUserOwner().getId());
		assertEquals(admin.getUserReviewer().getId(), sendto.getUserReviewer().getId());
	}

	@Test
	public void test1UpdateReportWithNotFoundException() {
		ReportSendto admin = new ReportSendto();

		admin.setAttendanceRecordId(2L);
		admin.setComment("Good");
		admin.setMaxIdLastMonth(2L);
		Response response = target("report").path("5").register(JacksonFeature.class).request().header("user", "demo")
				.put(Entity.json(admin));
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

	}

	@Test
	public void testSaveReport() {
		ReportSendto admin = new ReportSendto();
		admin.setAttendanceRecordId(2L);
		admin.setComment("Good");
		admin.setMaxIdLastMonth(2L);
		admin.setCurrentStatus("approved");
		admin.setReason("btrs");
		admin.setRoute("kaohsiung");
		admin.setStartDate(new Date());
		admin.setCreatedDate(new Date());
		admin.setLastUpdatedDate(new Date());

		ReportSendto.User user = new ReportSendto.User();
		user.setId(1L);
		admin.setUserOwner(user);

		ReportSendto.User review = new ReportSendto.User();
		review.setId(2L);
		admin.setUserReviewer(review);

		Response response = target("report").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ReportSendto sendto = response.readEntity(ReportSendto.class);
		assertEquals(3l, sendto.getId().longValue());
		assertEquals(admin.getAttendanceRecordId(), sendto.getAttendanceRecordId());
		assertEquals(admin.getComment(), sendto.getComment());
		assertEquals(admin.getMaxIdLastMonth(), sendto.getMaxIdLastMonth());
		assertEquals(admin.getCurrentStatus(), sendto.getCurrentStatus());
		assertEquals(admin.getReason(), sendto.getReason());
		assertEquals(admin.getCurrentStatus(), sendto.getCurrentStatus());
		assertEquals(admin.getReason(), sendto.getReason());
		assertEquals(admin.getRoute(), sendto.getRoute());
		assertEquals(admin.getStartDate(), sendto.getStartDate());
		assertEquals(admin.getEndDate(), sendto.getEndDate());
		assertEquals(admin.getCreatedDate(), sendto.getCreatedDate());
		assertEquals(admin.getLastUpdatedDate(), sendto.getLastUpdatedDate());
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

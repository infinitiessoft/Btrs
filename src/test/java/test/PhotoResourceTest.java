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
import resources.Type.admin.PhotoResource;
import sendto.PhotoSendto;
import assertion.AssertUtils;
import entity.PageModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PhotoResourceTest extends ResourceTest {

	@Test
	public void testGetPhoto() {
		Response response = target("photo").path("1").register(JacksonFeature.class).request().header("user", "demo")
				.get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PhotoSendto sendto = response.readEntity(PhotoSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals("DSC_0130.jpg", sendto.getFileName());
		assertEquals("image/jpeg", sendto.getContentType());
		assertEquals(617386, sendto.getSize().intValue());
		assertEquals("DSC_0130.jpg", sendto.getTitle());
	}

	@Test
	public void test1GetPhotoWithNotFoundException() {
		Response response = target("photo").path("3").register(JacksonFeature.class).request().header("user", "demo")
				.get();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testZDeletePhoto() {
		Response response = target("photo").path("1").register(JacksonFeature.class).request().header("user", "demo")
				.delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void test1ZDeletePhotoWithNotFoundException() {
		Response response = target("photo").path("3").register(JacksonFeature.class).request().header("user", "demo")
				.delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdatePhoto() {
		PhotoSendto admin = new PhotoSendto();
		admin.setFileName("Expense");
		admin.setContentType("image/jpeg");
		admin.setSize(617386);
		admin.setUploadDate(new Date());
		admin.setTitle("DSC_0130.jpg");

		PhotoSendto.Report rpt = new PhotoSendto.Report();
		rpt.setId(2L);
		admin.setReport(rpt);

		Response response = target("photo").path("1").register(JacksonFeature.class).request().header("user", "demo")
				.put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PhotoSendto sendto = response.readEntity(PhotoSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getFileName(), sendto.getFileName());
		assertEquals(admin.getContentType(), sendto.getContentType());
		assertEquals(admin.getSize(), sendto.getSize());
		assertEquals(admin.getTitle(), sendto.getTitle());
		assertEquals(admin.getUploadDate(), sendto.getUploadDate());
		assertEquals(admin.getReport().getId(), sendto.getReport().getId());
	}

	@Test
	public void test1UpdatePhotoWithNotFoundException() {
		PhotoSendto admin = new PhotoSendto();
		admin.setFileName("Expense");
		admin.setContentType("image/jpeg");
		admin.setSize(617386);
		admin.setUploadDate(new Date());
		admin.setTitle("DSC_0130.jpg");

		PhotoSendto.Report rpt = new PhotoSendto.Report();
		rpt.setId(2L);
		admin.setReport(rpt);

		Response response = target("photo").path("3").register(JacksonFeature.class).request().header("user", "demo")
				.put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSavePhoto() {
		PhotoSendto admin = new PhotoSendto();
		admin.setFileName("DSC_0131.jpg");
		admin.setContentType("image/jpeg");
		admin.setSize(617336);
		admin.setUploadDate(new Date());
		admin.setTitle("DSC_0130.jpg");

		PhotoSendto.Report rpt = new PhotoSendto.Report();
		rpt.setId(1L);
		admin.setReport(rpt);

		Response response = target("photo").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PhotoSendto sendto = response.readEntity(PhotoSendto.class);
		assertEquals(3l, sendto.getId().longValue());
		assertEquals(admin.getFileName(), sendto.getFileName());
		assertEquals(admin.getContentType(), sendto.getContentType());
		assertEquals(admin.getReport().getId().longValue(), sendto.getReport().getId().longValue());
		assertEquals(admin.getSize(), sendto.getSize());
		assertEquals(admin.getTitle(), sendto.getTitle());
		assertEquals(admin.getUploadDate(), sendto.getUploadDate());
	}

	@Test
	public void testFindallPhoto() {
		Response response = target("photo").register(JacksonFeature.class).request().header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PageModel<PhotoSendto> rets = response.readEntity(new GenericType<PageModel<PhotoSendto>>() {
		});
		assertEquals(2, rets.getTotalElements());

	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { PhotoResource.class };
	}

}

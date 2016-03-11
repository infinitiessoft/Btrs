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
import resources.PhotoResource;
import resources.ResourceTest;
import sendto.PhotoSendto;

public class PhotoResourceTest extends ResourceTest {

	@Test
	public void testGetPhoto() {
		Response response = target("photo").path("1").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PhotoSendto sendto = response.readEntity(PhotoSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals("Trip", sendto.getFileName());
		assertEquals("Report", sendto.getContentType());
		assertEquals("Demo", sendto.getData());
		assertEquals("Good", sendto.getReport());
		assertEquals("large", sendto.getSize());
		assertEquals("Expense", sendto.getTitle());
		assertEquals("12-02-2016", sendto.getUploadDate());
	}

	@Test
	public void testGetPhotoWithNotFoundException() {
		Response response = target("photo").path("4").register(JacksonFeature.class).request().get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeletePhoto() {
		Response response = target("photo").register(JacksonFeature.class).request().delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeletePhotoWithNotFoundException() {
		Response response = target("photo").register(JacksonFeature.class).request().delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdatePhoto() {
		PhotoSendto admin = new PhotoSendto();
		admin.setFileName("Expense");
		Response response = target("photo").path("1").register(JacksonFeature.class).request().put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PhotoSendto sendto = response.readEntity(PhotoSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getFileName(), sendto.getFileName());
		assertEquals(admin.getContentType(), sendto.getContentType());
	}

	@Test
	public void testUpdatePhotoWithNotFoundException() {
		PhotoSendto admin = new PhotoSendto();
		admin.setFileName("Expense");
		Response response = target("photo").path("4").register(JacksonFeature.class).request().put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSavePhoto() {
		PhotoSendto admin = new PhotoSendto();
		admin.setFileName("Expense");
		Response response = target("photo").register(JacksonFeature.class).request().post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PhotoSendto sendto = response.readEntity(PhotoSendto.class);
		assertEquals(5l, sendto.getId().longValue());
		assertEquals(admin.getFileName(), sendto.getFileName());
		assertEquals(admin.getContentType(), sendto.getContentType());
		assertEquals(admin.getData(), sendto.getData());
		assertEquals(admin.getReport(), sendto.getReport());
		assertEquals(admin.getSize(), sendto.getSize());
		assertEquals(admin.getTitle(), sendto.getTitle());
		assertEquals(admin.getUploadDate(), sendto.getUploadDate());
	}

	@Test
	public void testSavePhotoWithDuplicateName() {
		PhotoSendto admin = new PhotoSendto();
		admin.setFileName("Trip");
		Response response = target("photo").register(JacksonFeature.class).request().post(Entity.json(admin));
		AssertUtils.assertBadRequest(response);
	}

	@Test
	public void testFindallPhoto() {
		Response response = target("photo").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		Collection<PhotoSendto> rets = response.readEntity(new GenericType<List<PhotoSendto>>() {
		});
		assertEquals(200, response.getStatus());
		for (PhotoSendto photo : rets) {
			assertNotNull(photo.getId().toString());
			assertNotNull(photo.getFileName());
			assertNotNull(photo.getContentType());
			assertNotNull(photo.getData());
			assertNotNull(photo.getReport());
			assertNotNull(photo.getSize());
			assertNotNull(photo.getTitle());
			assertNotNull(photo.getUploadDate());
		}
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { PhotoResource.class };
	}

}

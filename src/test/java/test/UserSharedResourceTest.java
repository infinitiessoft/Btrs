package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import resources.ResourceTest;
import resources.Type.admin.UserSharedResource;
import sendto.UserSharedSendto;
import assertion.AssertUtils;
import entity.PageModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserSharedResourceTest extends ResourceTest {

	@Test
	public void testGetUserShared() throws ParseException {
		Response response = target("userShared").path("1").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		UserSharedSendto sendto = response.readEntity(UserSharedSendto.class);
		assertEquals(1l, sendto.getId().longValue());

		SimpleDateFormat yyyyMMddhhmmssformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		yyyyMMddhhmmssformat.setTimeZone(TimeZone.getTimeZone("GMT -8:00"));
		Date createdDate = yyyyMMddhhmmssformat.parse("2010-12-15 00:00:00");
		Date birthDate = yyyyMMddhhmmssformat.parse("2010-12-15 00:00:00");

		assertEquals(createdDate, sendto.getCreatedDate());
		assertEquals("admin@inifinitiessoft.com", sendto.getEmail());
		assertEquals("Admin", sendto.getFirstName());
		assertEquals("Male", sendto.getGender());
		assertEquals("demo", sendto.getJobTitle());
		assertEquals("Admin", sendto.getLastName());
		assertEquals("administrator", sendto.getUsername());
		assertTrue(sendto.getEnabled());
		assertEquals("b3aca92c793ee0e9b1a9b0a5f5fc044e05140df3", sendto.getPassword());
		assertEquals(birthDate, sendto.getDateOfBirth());
	}

	@Test
	public void test1GetUserSharedWithNotFoundException() {
		Response response = target("userShared").path("3").register(JacksonFeature.class).request()
				.header("user", "demo").get();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testZDeleteUserShared() {
		Response response = target("userShared").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void test1ZDeleteUserSharedWithNotFoundException() {
		Response response = target("userShared").path("3").register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateUserShared() {
		UserSharedSendto admin = new UserSharedSendto();
		admin.setUsername("demo2");
		Response response = target("userShared").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		UserSharedSendto sendto = response.readEntity(UserSharedSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getUsername(), sendto.getUsername());
	}

	@Test
	public void test1UpdateUserSharedWithNotFoundException() {
		UserSharedSendto admin = new UserSharedSendto();
		admin.setUsername("admin");

		Response response = target("userShared").path("3").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveUserShared() {
		UserSharedSendto admin = new UserSharedSendto();
		admin.setUsername("admin");
		admin.setEmail("admin@gmail.com");
		admin.setJobTitle("admin");
		admin.setLastName("demo");
		Response response = target("userShared").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		UserSharedSendto sendto = response.readEntity(UserSharedSendto.class);
		assertEquals(3l, sendto.getId().longValue());
		assertEquals(admin.getEmail(), sendto.getEmail());
		assertEquals(admin.getJobTitle(), sendto.getJobTitle());
		assertEquals(admin.getLastName(), sendto.getLastName());
		assertEquals(admin.getUsername(), sendto.getUsername());
	}

	@Test
	public void testFindallUserShared() {
		Response response = target("userShared").register(JacksonFeature.class).request().header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PageModel<UserSharedSendto> rets = response.readEntity(new GenericType<PageModel<UserSharedSendto>>() {
		});
		assertEquals(2, rets.getTotalElements());

	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { UserSharedResource.class };
	}

}

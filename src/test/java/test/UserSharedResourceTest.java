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
import resources.Type.UserSharedResource;
import sendto.UserSharedSendto;

public class UserSharedResourceTest extends ResourceTest {

	@Test
	public void testGetUserShared() {
		Response response = target("userShared").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		UserSharedSendto sendto = response.readEntity(UserSharedSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals("admin@inifinitiessoft.com", sendto.getEmail());
		assertEquals("Admin", sendto.getFirstName());
		assertEquals("Male", sendto.getGender());
		assertEquals("demo", sendto.getJobTitle());
		assertEquals("Admin", sendto.getLastName());
		assertEquals("administrator", sendto.getUsername());
		assertEquals("TRUE", sendto.getEnabled());
	}

	@Test
	public void testGetUserSharedWithNotFoundException() {
		Response response = target("userShared").path("4").register(JacksonFeature.class).request()
				.header("user", "demo").get();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteUserShared() {
		Response response = target("userShared").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteUserSharedWithNotFoundException() {
		Response response = target("userShared").path("3").register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testUpdateUserShared() {
		UserSharedSendto admin = new UserSharedSendto();
		admin.setUsername("admin");
		admin.setCreatedDate(new Date());
		admin.setDateOfBirth(new Date());
		admin.setEmail("admin@gmai.com");
		admin.setFirstName("admin");
		admin.setJobTitle("admin");
		admin.setLastName("demo");
		admin.setPassword("admin");

		Response response = target("userShared").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		UserSharedSendto sendto = response.readEntity(UserSharedSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getCreatedDate(), sendto.getCreatedDate());
		assertEquals(admin.getDateOfBirth(), sendto.getDateOfBirth());
		assertEquals(admin.getEmail(), sendto.getEmail());
		assertEquals(admin.getFirstName(), sendto.getFirstName());
		assertEquals(admin.getGender(), sendto.getGender());
		assertEquals(admin.getJobTitle(), sendto.getJobTitle());
		assertEquals(admin.getLastName(), sendto.getLastName());
		assertEquals(admin.getPassword(), sendto.getPassword());
		assertEquals(admin.getUsername(), sendto.getUsername());
	}

	@Test
	public void testUpdateUserSharedWithNotFoundException() {
		UserSharedSendto admin = new UserSharedSendto();
		admin.setUsername("admin");
		admin.setEmail("admin@gmai.com");
		admin.setFirstName("admin");
		admin.setJobTitle("admin");
		admin.setLastName("demo");
		admin.setPassword("admin");
		Response response = target("userShared").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveUserShared() {
		UserSharedSendto admin = new UserSharedSendto();
		admin.setUsername("admin");
		admin.setEmail("admin@gmai.com");
		admin.setFirstName("admin");
		admin.setJobTitle("admin");
		admin.setLastName("demo");
		admin.setPassword("admin");
		Response response = target("userShared").path("3").register(JacksonFeature.class).request()
				.header("user", "demo").post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		UserSharedSendto sendto = response.readEntity(UserSharedSendto.class);
		assertEquals(2l, sendto.getId().longValue());
		assertEquals(admin.getCreatedDate(), sendto.getCreatedDate());
		assertEquals(admin.getDateOfBirth(), sendto.getDateOfBirth());
		assertEquals(admin.getEmail(), sendto.getEmail());
		assertEquals(admin.getFirstName(), sendto.getFirstName());
		assertEquals(admin.getGender(), sendto.getGender());
		assertEquals(admin.getJobTitle(), sendto.getJobTitle());
		assertEquals(admin.getLastName(), sendto.getLastName());
		assertEquals(admin.getPassword(), sendto.getPassword());
		assertEquals(admin.getUsername(), sendto.getUsername());
	}

	@Test
	public void testSaveUserSharedWithDuplicateName() {
		UserSharedSendto admin = new UserSharedSendto();
		admin.setUsername("admininstration");
		admin.setEmail("admin@gmai.com");
		admin.setFirstName("admin");
		admin.setJobTitle("admin");
		admin.setLastName("demo");
		admin.setPassword("admin");
		Response response = target("userShared").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
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

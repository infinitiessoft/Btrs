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
import resources.UserSharedResource;
import sendto.UserSharedSendto;

public class UserSharedResourceTest extends ResourceTest {

	@Test
	public void testGetUserShared() {
		Response response = target("userShared").path("1").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		UserSharedSendto sendto = response.readEntity(UserSharedSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(new Date(), sendto.getCreatedDate());
		assertEquals(02 - 15 - 2016, sendto.getDateOfBirth());
		assertEquals("info@gmail.com", sendto.getEmail());
		assertEquals("Gana", sendto.getFirstName());
		assertEquals("Female", sendto.getGender());
		assertEquals("Engineer", sendto.getJobTitle());
		assertEquals("Shree", sendto.getLastName());
		assertEquals("1234", sendto.getPassword());
		assertEquals("gana", sendto.getUsername());
	}

	@Test
	public void testGetUserSharedWithNotFoundException() {
		Response response = target("userShared").path("4").register(JacksonFeature.class).request().get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteUserShared() {
		Response response = target("userShared").register(JacksonFeature.class).request().delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteUserSharedWithNotFoundException() {
		Response response = target("userShared").register(JacksonFeature.class).request().delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateUserShared() {
		UserSharedSendto admin = new UserSharedSendto();
		admin.setUsername("admin");
		Response response = target("userShared").path("1").register(JacksonFeature.class).request()
				.put(Entity.json(admin));
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
		Response response = target("userShared").path("4").register(JacksonFeature.class).request()
				.put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveUserShared() {
		UserSharedSendto admin = new UserSharedSendto();
		admin.setUsername("admin");
		Response response = target("userShared").register(JacksonFeature.class).request().post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		UserSharedSendto sendto = response.readEntity(UserSharedSendto.class);
		assertEquals(5l, sendto.getId().longValue());
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
		admin.setUsername("admin");
		Response response = target("userShared").register(JacksonFeature.class).request().post(Entity.json(admin));
		AssertUtils.assertBadRequest(response);
	}

	@Test
	public void testFindallUserShared() {
		Response response = target("userShared").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		Collection<UserSharedSendto> rets = response.readEntity(new GenericType<List<UserSharedSendto>>() {
		});
		assertEquals(200, response.getStatus());
		for (UserSharedSendto userShared : rets) {
			assertNotNull(userShared.getId().toString());
			assertNotNull(userShared.getCreatedDate());
			assertNotNull(userShared.getDateOfBirth());
			assertNotNull(userShared.getEmail());
			assertNotNull(userShared.getFirstName());
			assertNotNull(userShared.getGender());
			assertNotNull(userShared.getJobTitle());
			assertNotNull(userShared.getLastName());
			assertNotNull(userShared.getPassword());
			assertNotNull(userShared.getUsername());
		}
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { UserSharedResource.class };
	}

}

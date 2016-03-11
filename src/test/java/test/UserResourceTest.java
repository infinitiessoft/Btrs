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
import resources.UserResource;
import sendto.UserSendto;

public class UserResourceTest extends ResourceTest {

	@Test
	public void testGetUser() {
		Response response = target("user").path("1").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		UserSendto sendto = response.readEntity(UserSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(new Date(), sendto.getLastLogin());
		assertEquals("123", sendto.getUserSharedId());
	}

	@Test
	public void testGetUserWithNotFoundException() {
		Response response = target("user").path("4").register(JacksonFeature.class).request().get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteUser() {
		Response response = target("user").register(JacksonFeature.class).request().delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteUserWithNotFoundException() {
		Response response = target("user").register(JacksonFeature.class).request().delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateUser() {
		UserSendto admin = new UserSendto();
		admin.setUserSharedId(2l);
		Response response = target("user").path("1").register(JacksonFeature.class).request().put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		UserSendto sendto = response.readEntity(UserSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getLastLogin(), sendto.getLastLogin());
		assertEquals(admin.getUserSharedId(), sendto.getUserSharedId());
	}

	@Test
	public void testUpdateUserWithNotFoundException() {
		UserSendto admin = new UserSendto();
		admin.setUserSharedId(2l);
		Response response = target("user").path("4").register(JacksonFeature.class).request().put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveUser() {
		UserSendto admin = new UserSendto();
		admin.setUserSharedId(2l);
		Response response = target("user").register(JacksonFeature.class).request().post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		UserSendto sendto = response.readEntity(UserSendto.class);
		assertEquals(5l, sendto.getId().longValue());
		assertEquals(admin.getLastLogin(), sendto.getLastLogin());
		assertEquals(admin.getUserSharedId(), sendto.getUserSharedId());
	}

	@Test
	public void testSaveUserWithDuplicateName() {
		UserSendto admin = new UserSendto();
		admin.setUserSharedId(2l);
		Response response = target("user").register(JacksonFeature.class).request().post(Entity.json(admin));
		AssertUtils.assertBadRequest(response);
	}

	@Test
	public void testFindallUser() {
		Response response = target("user").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		Collection<UserSendto> rets = response.readEntity(new GenericType<List<UserSendto>>() {
		});
		assertEquals(200, response.getStatus());
		for (UserSendto user : rets) {
			assertNotNull(user.getId().toString());
			assertNotNull(user.getLastLogin());
			assertNotNull(user.getUserSharedId());
		}
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { UserResource.class };
	}

}

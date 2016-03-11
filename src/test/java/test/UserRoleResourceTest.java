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
import resources.ResourceTest;
import resources.UserRoleResource;
import sendto.UserRoleSendto;

public class UserRoleResourceTest extends ResourceTest {

	@Test
	public void testGetUserRole() {
		Response response = target("userRole").path("1").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		UserRoleSendto sendto = response.readEntity(UserRoleSendto.class);
		assertEquals(1l, sendto.getId().longValue());
	}

	@Test
	public void testGetUserRoleWithNotFoundException() {
		Response response = target("userRole").path("4").register(JacksonFeature.class).request().get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteUserRole() {
		Response response = target("userRole").register(JacksonFeature.class).request().delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteUserRoleWithNotFoundException() {
		Response response = target("userRole").register(JacksonFeature.class).request().delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveUserRole() {
		UserRoleSendto userRole = new UserRoleSendto();
		userRole.setId(1l);
		Response response = target("userRole").register(JacksonFeature.class).request().post(Entity.json(userRole));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		UserRoleSendto sendto = response.readEntity(UserRoleSendto.class);
		assertEquals(4L, sendto.getId().longValue());
		assertEquals(userRole.getUser().getId(), sendto.getUser().getId());
		assertEquals(userRole.getRole().getId(), sendto.getRole().getId());
		assertEquals(userRole.getId(), sendto.getId());
	}

	@Test
	public void testSaveUserRoleWithDuplicateUserAndRole() {
		UserRoleSendto userRole = new UserRoleSendto();
		userRole.setId(1l);
		Response response = target("userRole").register(JacksonFeature.class).request().post(Entity.json(userRole));
		AssertUtils.assertBadRequest(response);
	}

	@Test
	public void testUpdateUserRole() {
		UserRoleSendto userRole = new UserRoleSendto();
		userRole.setId(1l);

		Response response = target("userRole").path("1").register(JacksonFeature.class).request()
				.put(Entity.json(userRole));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		UserRoleSendto sendto = response.readEntity(UserRoleSendto.class);
		assertEquals(1L, sendto.getId().longValue());
	}

	@Test
	public void testUpdateUserRoleWithNotFoundException() {
		UserRoleSendto userRole = new UserRoleSendto();
		userRole.setId(1l);

		Response response = target("userRole").path("3").register(JacksonFeature.class).request()
				.put(Entity.json(userRole));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testFindAllUserRole() {
		Response response = target("userRole").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		Collection<UserRoleSendto> rets = response.readEntity(new GenericType<List<UserRoleSendto>>() {
		});
		assertEquals(200, response.getStatus());
		for (UserRoleSendto userRole : rets) {
			assertNotNull(userRole.getId().toString());
		}
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { UserRoleResource.class };
	}

}

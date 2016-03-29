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
import resources.Type.UserResource;
import sendto.UserSendto;

public class UserResourceTest extends ResourceTest {

	@Test
	public void testGetUser() {
		Response response = target("user").path("1").register(JacksonFeature.class).request().header("user", "demo")
				.get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		UserSendto sendto = response.readEntity(UserSendto.class);
		assertEquals(1l, sendto.getId().longValue());
	}

	@Test
	public void testGetUserWithNotFoundException() {
		Response response = target("user").path("3").register(JacksonFeature.class).request().get();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testDeleteUser() {
		Response response = target("user").path("1").register(JacksonFeature.class).request().header("user", "demo")
				.delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteUserWithNotFoundException() {
		Response response = target("user").path("3").register(JacksonFeature.class).request().header("user", "demo")
				.delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateUser() {
		UserSendto admin = new UserSendto();
		UserSendto.Department dpt = new UserSendto.Department();
		dpt.setId(2L);
		admin.setDepartment(dpt);

		UserSendto.UserShared usr = new UserSendto.UserShared();
		usr.setId(2L);
		admin.setUserShared(usr);

		Response response = target("user").path("2").register(JacksonFeature.class).request().header("user", "demo")
				.put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		UserSendto sendto = response.readEntity(UserSendto.class);
		assertEquals(2l, sendto.getId().longValue());
		assertEquals(admin.getUserShared().getId().longValue(), sendto.getUserShared().getId().longValue());
		assertEquals(admin.getDepartment().getId().longValue(), sendto.getDepartment().getId().longValue());
	}

	@Test
	public void testSaveUser() {
		UserSendto admin = new UserSendto();

		UserSendto.Department dpt = new UserSendto.Department();
		dpt.setId(1L);
		admin.setDepartment(dpt);

		UserSendto.UserShared usr = new UserSendto.UserShared();
		usr.setId(1L);
		admin.setUserShared(usr);
		Response response = target("user").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		UserSendto sendto = response.readEntity(UserSendto.class);
		assertEquals(3l, sendto.getId().longValue());
		assertEquals(admin.getUserShared().getId(), sendto.getUserShared().getId());
		assertEquals(admin.getDepartment().getId(), sendto.getDepartment().getId());
	}

	@Test
	public void testFindallUser() {
		Response response = target("user").register(JacksonFeature.class).request().header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PageModel<UserSendto> rets = response.readEntity(new GenericType<PageModel<UserSendto>>() {
		});
		assertEquals(2, rets.getTotalElements());

	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { UserResource.class };
	}

}

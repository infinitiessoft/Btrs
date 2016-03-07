package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import assertion.AssertUtils;
import dao.UserRoleDao;
import resources.UserRoleResource;
import sendto.UserRoleSendto;
import service.UserRoleService;
import serviceImpl.UserRoleServiceImpl;

public class UserRoleResourceTest extends JerseyTest {

	@Override
	protected Application configure() {

		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);

		ResourceConfig config = new ResourceConfig(UserRoleResource.class);
		config.register(new InjectableProvider());
		config.register(JacksonFeature.class);

		return config;
	}

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

	protected Class<?>[] getResource() {
		return new Class<?>[] { UserRoleResource.class };
	}

	class InjectableProvider extends AbstractBinder {

		@Override
		protected void configure() {
			bind(UserRoleServiceImpl.class).to(UserRoleService.class).in(Singleton.class);
			bind(UserRoleSendto.class).to(UserRoleDao.class).in(Singleton.class);
		}

	}

}

package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.Date;
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
import dao.UserDao;
import resources.UserResource;
import sendto.UserSendto;
import service.UserService;
import serviceImpl.UserServiceImpl;

public class UserResourceTest extends JerseyTest {

	@Override
	protected Application configure() {

		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);

		ResourceConfig config = new ResourceConfig(UserResource.class);
		config.register(new InjectableProvider());
		config.register(JacksonFeature.class);

		return config;
	}

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

	protected Class<?>[] getResource() {
		return new Class<?>[] { UserResource.class };
	}

	class InjectableProvider extends AbstractBinder {

		@Override
		protected void configure() {
			bind(UserServiceImpl.class).to(UserService.class).in(Singleton.class);
			bind(UserSendto.class).to(UserDao.class).in(Singleton.class);
		}

	}

}

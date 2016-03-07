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
import dao.UserSharedDao;
import resources.UserSharedResource;
import sendto.UserSharedSendto;
import service.UserSharedService;
import serviceImpl.UserSharedServiceImpl;

public class UserSharedResourceTest extends JerseyTest {

	@Override
	protected Application configure() {

		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);

		ResourceConfig config = new ResourceConfig(UserSharedResource.class);
		config.register(new InjectableProvider());
		config.register(JacksonFeature.class);

		return config;
	}

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

	protected Class<?>[] getResource() {
		return new Class<?>[] { UserSharedResource.class };
	}

	class InjectableProvider extends AbstractBinder {

		@Override
		protected void configure() {
			bind(UserSharedServiceImpl.class).to(UserSharedService.class).in(Singleton.class);
			bind(UserSharedSendto.class).to(UserSharedDao.class).in(Singleton.class);
		}

	}

}

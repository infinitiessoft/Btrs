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
import dao.DepartmentDao;
import resources.DepartmentResource;
import sendto.DepartmentSendto;
import service.DepartmentService;
import serviceImpl.DepartmentServiceImpl;

public class DepartmentResourceTest extends JerseyTest {

	@Override
	protected Application configure() {

		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);

		ResourceConfig config = new ResourceConfig(DepartmentResource.class);
		config.register(new InjectableProvider());
		config.register(JacksonFeature.class);

		return config;
	}

	@Test
	public void testGetDepartment() {
		Response response = target("department").path("1").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		DepartmentSendto sendto = response.readEntity(DepartmentSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals("Sales", sendto.getName());
		assertEquals("Good", sendto.getComment());
	}

	@Test
	public void testGetDepartmentWithNotFoundException() {
		Response response = target("department").path("4").register(JacksonFeature.class).request().get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteDepartment() {
		Response response = target("department").register(JacksonFeature.class).request().delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteDepartmentWithNotFoundException() {
		Response response = target("department").register(JacksonFeature.class).request().delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateDepartment() {
		DepartmentSendto admin = new DepartmentSendto();
		admin.setName("administrator");
		Response response = target("department").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		DepartmentSendto sendto = response.readEntity(DepartmentSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getName(), sendto.getName());
		assertEquals(admin.getComment(), sendto.getComment());
	}

	@Test
	public void testUpdateDepartmentWithNotFoundException() {
		DepartmentSendto admin = new DepartmentSendto();
		admin.setName("administrator");
		Response response = target("department").path("4").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveDepartment() {
		DepartmentSendto admin = new DepartmentSendto();
		admin.setName("administrator");
		Response response = target("department").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		DepartmentSendto sendto = response.readEntity(DepartmentSendto.class);
		assertEquals(5l, sendto.getId().longValue());
		assertEquals(admin.getName(), sendto.getName());
		assertEquals(admin.getComment(), sendto.getComment());
	}

	@Test
	public void testSaveDepartmentWithDuplicateName() {
		DepartmentSendto admin = new DepartmentSendto();
		admin.setName("Sales");
		Response response = target("department").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		AssertUtils.assertBadRequest(response);
	}

	@Test
	public void testFindallDepartment() {
		Response response = target("department").register(JacksonFeature.class).request().header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		Collection<DepartmentSendto> rets = response.readEntity(new GenericType<List<DepartmentSendto>>() {
		});
		assertEquals(200, response.getStatus());
		for (DepartmentSendto department : rets) {
			assertNotNull(department.getId().toString());
			assertNotNull(department.getName());
			assertNotNull(department.getComment());
		}
	}

	protected Class<?>[] getResource() {
		return new Class<?>[] { DepartmentResource.class };
	}

	class InjectableProvider extends AbstractBinder {

		@Override
		protected void configure() {
			bind(DepartmentServiceImpl.class).to(DepartmentService.class).in(Singleton.class);
			bind(DepartmentSendto.class).to(DepartmentDao.class).in(Singleton.class);
		}

	}

}

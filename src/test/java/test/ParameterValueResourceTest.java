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
import dao.ParameterValueDao;
import resources.ParameterValueResource;
import sendto.ParameterValueSendto;
import service.ParameterValueService;
import serviceImpl.ParameterValueServiceImpl;

public class ParameterValueResourceTest extends JerseyTest {

	@Override
	protected Application configure() {

		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);

		ResourceConfig config = new ResourceConfig(ParameterValueResource.class);
		config.register(new InjectableProvider());
		config.register(JacksonFeature.class);

		return config;
	}

	@Test
	public void testGetParameterValue() {
		Response response = target("parameterValue").path("1").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ParameterValueSendto sendto = response.readEntity(ParameterValueSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals("Demo", sendto.getValue());
	}

	@Test
	public void testGetParameterValueWithNotFoundException() {
		Response response = target("parameterValue").path("4").register(JacksonFeature.class).request().get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteParameterValue() {
		Response response = target("parameterValue").register(JacksonFeature.class).request().delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteParameterValueWithNotFoundException() {
		Response response = target("parameterValue").register(JacksonFeature.class).request().delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateParameterValue() {
		ParameterValueSendto admin = new ParameterValueSendto();
		admin.setValue("administrator");
		Response response = target("parameterValue").path("1").register(JacksonFeature.class).request()
				.put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ParameterValueSendto sendto = response.readEntity(ParameterValueSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getValue(), sendto.getValue());
	}

	@Test
	public void testUpdateParameterValueWithNotFoundException() {
		ParameterValueSendto admin = new ParameterValueSendto();
		admin.setValue("administrator");
		Response response = target("parameterValue").path("4").register(JacksonFeature.class).request()
				.put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveParameterValue() {
		ParameterValueSendto admin = new ParameterValueSendto();
		admin.setValue("administrator");
		Response response = target("parameterValue").register(JacksonFeature.class).request().post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		ParameterValueSendto sendto = response.readEntity(ParameterValueSendto.class);
		assertEquals(5l, sendto.getId().longValue());
		assertEquals(admin.getValue(), sendto.getValue());
	}

	@Test
	public void testSaveParameterValueWithDuplicateName() {
		ParameterValueSendto admin = new ParameterValueSendto();
		admin.setValue("Demo");
		Response response = target("parameterValue").register(JacksonFeature.class).request().post(Entity.json(admin));
		AssertUtils.assertBadRequest(response);
	}

	@Test
	public void testFindallParameterValue() {
		Response response = target("parameterValue").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		Collection<ParameterValueSendto> rets = response.readEntity(new GenericType<List<ParameterValueSendto>>() {
		});
		assertEquals(200, response.getStatus());
		for (ParameterValueSendto parameterValue : rets) {
			assertNotNull(parameterValue.getId().toString());
			assertNotNull(parameterValue.getValue());
		}
	}

	protected Class<?>[] getResource() {
		return new Class<?>[] { ParameterValueResource.class };
	}

	class InjectableProvider extends AbstractBinder {

		@Override
		protected void configure() {
			bind(ParameterValueServiceImpl.class).to(ParameterValueService.class).in(Singleton.class);
			bind(ParameterValueSendto.class).to(ParameterValueDao.class).in(Singleton.class);
		}

	}

}

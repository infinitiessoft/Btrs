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
import resources.Type.ParameterValueResource;
import sendto.ParameterValueSendto;

public class ParameterValueResourceTest extends ResourceTest {

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

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { ParameterValueResource.class };
	}

}

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
import resources.TypeParameterResource;
import sendto.PhotoSendto;
import sendto.TypeParameterSendto;

public class TypeParameterResourceTest extends ResourceTest {

	@Test
	public void testGetTypeParameter() {
		Response response = target("typeParameter").path("1").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		TypeParameterSendto sendto = response.readEntity(TypeParameterSendto.class);
		assertEquals(1l, sendto.getId().longValue());
	}

	@Test
	public void testGetTypeParameterWithNotFoundException() {
		Response response = target("typeParameter").path("4").register(JacksonFeature.class).request().get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteTypeParameter() {
		Response response = target("typeParameter").register(JacksonFeature.class).request().delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteTypeParameterWithNotFoundException() {
		Response response = target("typeParameter").register(JacksonFeature.class).request().delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateTypeParameter() {
		TypeParameterSendto admin = new TypeParameterSendto();
		admin.setId(2l);
		Response response = target("typeParameter").path("1").register(JacksonFeature.class).request()
				.put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PhotoSendto sendto = response.readEntity(PhotoSendto.class);
		assertEquals(1l, sendto.getId().longValue());
	}

	@Test
	public void testUpdateTypeParameterWithNotFoundException() {
		TypeParameterSendto admin = new TypeParameterSendto();
		admin.setId(2l);
		Response response = target("typeParameter").path("4").register(JacksonFeature.class).request()
				.put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveTypeParameter() {
		TypeParameterSendto admin = new TypeParameterSendto();
		admin.setId(2l);
		Response response = target("typeParameter").register(JacksonFeature.class).request().post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		TypeParameterSendto sendto = response.readEntity(TypeParameterSendto.class);
		assertEquals(5l, sendto.getId().longValue());
	}

	@Test
	public void testSaveTypeParameterWithDuplicateName() {
		TypeParameterSendto admin = new TypeParameterSendto();
		admin.setId(2l);
		Response response = target("typeParameter").register(JacksonFeature.class).request().post(Entity.json(admin));
		AssertUtils.assertBadRequest(response);
	}

	@Test
	public void testFindallTypeParameter() {
		Response response = target("typeParameter").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		Collection<TypeParameterSendto> rets = response.readEntity(new GenericType<List<TypeParameterSendto>>() {
		});
		assertEquals(200, response.getStatus());
		for (TypeParameterSendto typeParameter : rets) {
			assertNotNull(typeParameter.getId().toString());
		}
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { TypeParameterResource.class };
	}

}

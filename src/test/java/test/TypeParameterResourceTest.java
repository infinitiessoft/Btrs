package test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import resources.ResourceTest;
import resources.version1.admin.TypeParameterResource;
import sendto.TypeParameterSendto;
import assertion.AssertUtils;
import entity.PageModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TypeParameterResourceTest extends ResourceTest {

	@Test
	public void testGetTypeParameter() {
		Response response = target("typeParameter").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		TypeParameterSendto sendto = response.readEntity(TypeParameterSendto.class);
		assertEquals(1l, sendto.getId().longValue());
	}

	@Test
	public void test1GetTypeParameterWithNotFoundException() {
		Response response = target("typeParameter").path("3").register(JacksonFeature.class).request()
				.header("user", "demo").get();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testZDeleteTypeParameter() {
		Response response = target("typeParameter").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void test1ZDeleteTypeParameterWithNotFoundException() {
		Response response = target("typeParameter").path("3").register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateTypeParameter() {
		TypeParameterSendto admin = new TypeParameterSendto();
		admin.setValue("PERSONS");
		Response response = target("typeParameter").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		TypeParameterSendto sendto = response.readEntity(TypeParameterSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getValue(), sendto.getValue());

	}

	@Test
	public void test1UpdateTypeParameterWithNotFoundException() {
		TypeParameterSendto admin = new TypeParameterSendto();
		admin.setValue("PERSONS");
		Response response = target("typeParameter").path("3").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveTypeParameter() {
		TypeParameterSendto admin = new TypeParameterSendto();
		admin.setValue("demo");
		Response response = target("typeParameter").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		TypeParameterSendto sendto = response.readEntity(TypeParameterSendto.class);
		assertEquals(3l, sendto.getId().longValue());
	}

	@Test
	public void testFindallTypeParameter() {
		Response response = target("typeParameter").register(JacksonFeature.class).request().header("user", "demo")
				.get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PageModel<TypeParameterSendto> rets = response.readEntity(new GenericType<PageModel<TypeParameterSendto>>() {
		});
		assertEquals(2, rets.getTotalElements());

	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { TypeParameterResource.class };
	}

}

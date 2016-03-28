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
import resources.Type.RoleResource;
import sendto.RoleSendto;

public class RoleResourceTest extends ResourceTest {

	@Test
	public void testGetRole() {
		Response response = target("role").path("1").register(JacksonFeature.class).request().header("user", "demo")
				.get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		RoleSendto sendto = response.readEntity(RoleSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals("Employee", sendto.getValue());

	}

	@Test
	public void testGetRoleWithNotFoundException() {
		Response response = target("role").path("3").register(JacksonFeature.class).request().header("user", "demo")
				.get();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteRole() {
		Response response = target("role").path("1").register(JacksonFeature.class).request().header("user", "demo")
				.delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteReportWithNotFoundException() {
		Response response = target("role").path("3").register(JacksonFeature.class).request().header("user", "demo")
				.delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateRole() {
		RoleSendto admin = new RoleSendto();
		admin.setValue("value");
		Response response = target("role").path("1").register(JacksonFeature.class).request().header("user", "demo")
				.put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		RoleSendto sendto = response.readEntity(RoleSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getValue(), sendto.getValue());

	}

	@Test
	public void testSaveRole() {
		RoleSendto admin = new RoleSendto();
		Response response = target("role").register(JacksonFeature.class).request().header("user", "demo")
				.post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		RoleSendto sendto = response.readEntity(RoleSendto.class);
		assertEquals(3l, sendto.getId().longValue());
	}

	@Test
	public void testFindallRole() {
		Response response = target("role").register(JacksonFeature.class).request().header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PageModel<RoleSendto> rets = response.readEntity(new GenericType<PageModel<RoleSendto>>() {
		});
		assertEquals(2, rets.getTotalElements());

	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { RoleResource.class };
	}
}
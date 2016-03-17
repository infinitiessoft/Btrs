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
import resources.Type.RoleResource;
import sendto.RoleSendto;

public class RoleResourceTest extends ResourceTest {

	@Test
	public void testGetRole() {
		Response response = target("role").path("1").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		RoleSendto sendto = response.readEntity(RoleSendto.class);
		assertEquals(1l, sendto.getId().longValue());
	}

	@Test
	public void testGetRoleWithNotFoundException() {
		Response response = target("role").path("4").register(JacksonFeature.class).request().get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteRole() {
		Response response = target("role").register(JacksonFeature.class).request().delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteReportWithNotFoundException() {
		Response response = target("role").register(JacksonFeature.class).request().delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateRole() {
		RoleSendto admin = new RoleSendto();
		admin.setId(2l);
		Response response = target("role").path("1").register(JacksonFeature.class).request().put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		RoleSendto sendto = response.readEntity(RoleSendto.class);
		assertEquals(1l, sendto.getId().longValue());
	}

	@Test
	public void testUpdateRoleWithNotFoundException() {
		RoleSendto admin = new RoleSendto();
		admin.setId(2l);
		Response response = target("role").path("4").register(JacksonFeature.class).request().put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveRole() {
		RoleSendto admin = new RoleSendto();
		admin.setId(2l);
		Response response = target("role").register(JacksonFeature.class).request().post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		RoleSendto sendto = response.readEntity(RoleSendto.class);
		assertEquals(5l, sendto.getId().longValue());
	}

	@Test
	public void testSaveRoleWithDuplicateName() {
		RoleSendto admin = new RoleSendto();
		admin.setId(2l);
		Response response = target("role").register(JacksonFeature.class).request().post(Entity.json(admin));
		AssertUtils.assertBadRequest(response);
	}

	@Test
	public void testFindallRole() {
		Response response = target("role").register(JacksonFeature.class).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		Collection<RoleSendto> rets = response.readEntity(new GenericType<List<RoleSendto>>() {
		});
		assertEquals(200, response.getStatus());
		for (RoleSendto role : rets) {
			assertNotNull(role.getId().toString());
		}
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { RoleResource.class };
	}
}
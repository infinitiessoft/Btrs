package test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import entity.PageModel;
import resources.ResourceTest;
import resources.Type.admin.UserResource;
import sendto.RoleSendto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRoleResourceTest extends ResourceTest {

	@Test
	public void testFindAllRole() {
		Response response = target("user").path("2").path("role").register(JacksonFeature.class).request()
				.header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PageModel<RoleSendto> rets = response.readEntity(new GenericType<PageModel<RoleSendto>>() {
		});
		assertEquals(1, rets.getTotalElements());
	}

	@Test
	public void testFindRole() {
		Response response = target("user").path("2").path("role").path("2").register(JacksonFeature.class).request()
				.header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		RoleSendto transfer = response.readEntity(RoleSendto.class);
		assertEquals(2l, transfer.getId().longValue());
	}

	@Test
	public void testFindRoleWithNotFoundException() {
		Response response = target("user").path("2").path("role").path("4").register(JacksonFeature.class).request()
				.header("user", "user").get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testAssignRoleToUser() {
		Response response = target("user").property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true)
				.path("2").path("role").path("1").register(JacksonFeature.class).request().header("user", "demo")
				.put(null);
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testRevokeRoleToUser() {
		Response response = target("user").property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true)
				.path("2").path("role").path("2").register(JacksonFeature.class).request().header("user", "demo")
				.delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { UserResource.class };
	}

}
package test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Test;

import entity.PrincipalImpl;
import resources.ResourceTest;
import resources.Type.admin.AuthResource;

public class AuthResourceTest extends ResourceTest {

	@Test
	public void testUser() {
		Response response = target("auth").register(JacksonFeature.class).request().header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PrincipalImpl ret = response.readEntity(PrincipalImpl.class);
		assertEquals("demo", ret.getName());
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { AuthResource.class };
	}

}
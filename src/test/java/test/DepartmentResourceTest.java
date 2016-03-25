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
import resources.Type.DepartmentResource;
import sendto.DepartmentSendto;

public class DepartmentResourceTest extends ResourceTest {

	@Test
	public void testGetDepartment() {
		Response response = target("/department").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		DepartmentSendto sendto = response.readEntity(DepartmentSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals("Sales", sendto.getName());
		assertEquals("Good", sendto.getComment());
	}

	@Test
	public void testGetDepartmentWithNotFoundException() {
		Response response = target("department").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").get();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testDeleteDepartment() {
		Response response = target("department").path("2").register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void testDeleteDepartmentWithNotFoundException() {
		Response response = target("department").path("2").register(JacksonFeature.class).request()
				.header("user", "demo").header("user", "demo").delete();
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
		Response response = target("department").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveDepartment() {
		DepartmentSendto admin = new DepartmentSendto();
		admin.setName("Sales");
		Response response = target("department").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").post(Entity.json(admin));
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
		Response response = target("department").path("1").register(JacksonFeature.class).request()
				.header("user", "demo").post(Entity.json(admin));
		AssertUtils.assertBadRequest(response);
	}

	@Test
	public void testFindallDepartment() {
		Response response = target("department").register(JacksonFeature.class).request().header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PageModel<DepartmentSendto> rets = response.readEntity(new GenericType<PageModel<DepartmentSendto>>() {
		});
		assertEquals(3, rets.getTotalElements());
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { DepartmentResource.class };
	}

}

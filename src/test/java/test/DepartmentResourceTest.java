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
import resources.Type.admin.DepartmentResource;
import sendto.DepartmentSendto;
import assertion.AssertUtils;
import entity.PageModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentResourceTest extends ResourceTest {

	@Test
	public void testGetDepartment() {
		Response response = target("/department").path("1")
				.register(JacksonFeature.class).request()
				.header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		DepartmentSendto sendto = response.readEntity(DepartmentSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals("Sales", sendto.getName());
		assertEquals("Good", sendto.getComment());
	}

	@Test
	public void test1GetDepartmentWithNotFoundException() {
		Response response = target("department").path("3")
				.register(JacksonFeature.class).request()
				.header("user", "demo").get();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testZDeleteDepartment() {
		Response response = target("department").path("1")
				.register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void test1ZDeleteDepartmentWithNotFoundException() {
		Response response = target("department").path("3")
				.register(JacksonFeature.class).request()
				.header("user", "demo").delete();
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testUpdateDepartment() {
		DepartmentSendto admin = new DepartmentSendto();
		admin.setName("administrator");
		Response response = target("department").path("1")
				.register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		DepartmentSendto sendto = response.readEntity(DepartmentSendto.class);
		assertEquals(1l, sendto.getId().longValue());
		assertEquals(admin.getName(), sendto.getName());
	}

	@Test
	public void test1UpdateDepartmentWithNotFoundException() {
		DepartmentSendto admin = new DepartmentSendto();
		admin.setName("administrator");
		Response response = target("department").path("3")
				.register(JacksonFeature.class).request()
				.header("user", "demo").put(Entity.json(admin));
		AssertUtils.assertNotFound(response);
	}

	@Test
	public void testSaveDepartment() {
		DepartmentSendto admin = new DepartmentSendto();
		admin.setName("Sales");
		admin.setComment("fair");
		Response response = target("department").register(JacksonFeature.class)
				.request().header("user", "demo").post(Entity.json(admin));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		DepartmentSendto sendto = response.readEntity(DepartmentSendto.class);
		assertEquals(3l, sendto.getId().longValue());
		assertEquals(admin.getName(), sendto.getName());
		assertEquals(admin.getComment(), sendto.getComment());
	}

	@Test
	public void testFindallDepartment() {
		Response response = target("department").register(JacksonFeature.class)
				.request().header("user", "demo").get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		PageModel<DepartmentSendto> rets = response
				.readEntity(new GenericType<PageModel<DepartmentSendto>>() {
				});
		assertEquals(2, rets.getTotalElements());
	}

	@Override
	protected Class<?>[] getResource() {
		return new Class<?>[] { DepartmentResource.class };
	}

}

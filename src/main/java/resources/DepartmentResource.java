package resources;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import sendto.DepartmentSendto;
import service.DepartmentService;

@Path(value = "/department")
public class DepartmentResource {

	@Autowired
	private DepartmentService departmentService;

	@GET
	@Path(value = "{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public DepartmentSendto getDepartment(@PathParam("id") long id) {
		return departmentService.retrieve(id);
	}

	@DELETE
	@Path(value = "{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteDepartment(@PathParam("id") long id) {
		departmentService.delete(id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path(value = "{id}")
	public DepartmentSendto updateDepartment(@PathParam("id") long id, DepartmentSendto department) {
		return departmentService.update(id, department);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public DepartmentSendto saveDepartment(DepartmentSendto department) {
		return departmentService.save(department);
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<DepartmentSendto> findallDepartment() {
		return departmentService.findAll();
	}

}

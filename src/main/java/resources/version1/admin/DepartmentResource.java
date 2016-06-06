package resources.version1.admin;

import javax.ws.rs.BeanParam;
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
import org.springframework.data.domain.Page;

import resources.specification.DepartmentSpecification;
import resources.specification.SimplePageRequest;
import sendto.DepartmentSendto;
import service.DepartmentService;

@Path(value = "/department")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartmentResource {

	@Autowired
	private DepartmentService departmentService;

	@GET
	@Path(value = "{id}")
	public DepartmentSendto getDepartment(@PathParam("id") long id) {
		return departmentService.retrieve(id);
	}

	@DELETE
	@Path(value = "{id}")
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
	public DepartmentSendto saveDepartment(DepartmentSendto department) {
		return departmentService.save(department);
	}

	@GET
	public Page<DepartmentSendto> findallDepartment(@BeanParam SimplePageRequest pageRequest,
			@BeanParam DepartmentSpecification spec) {
		return departmentService.findAll(spec, pageRequest);
	}

}

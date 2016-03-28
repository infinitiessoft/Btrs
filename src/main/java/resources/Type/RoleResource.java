package resources.Type;

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

import resources.specification.RoleSpecification;
import resources.specification.SimplePageRequest;
import sendto.RoleSendto;
import service.RoleService;

@Path(value = "/role")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoleResource {

	@Autowired
	private RoleService roleService;

	@GET
	@Path(value = "{id}")
	public RoleSendto getRole(@PathParam("id") long id) {
		return roleService.retrieve(id);
	}

	@DELETE
	@Path(value = "{id}")
	public Response deleteRole(@PathParam("id") long id) {
		roleService.delete(id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path(value = "{id}")
	public RoleSendto updateRole(@PathParam("id") long id, RoleSendto role) {
		return roleService.update(id, role);
	}

	@POST
	public RoleSendto saveRole(RoleSendto role) {
		return roleService.save(role);
	}

	@GET
	public Page<RoleSendto> findallRole(@BeanParam SimplePageRequest pageRequest, @BeanParam RoleSpecification spec) {
		return roleService.findAll(spec, pageRequest);
	}

}

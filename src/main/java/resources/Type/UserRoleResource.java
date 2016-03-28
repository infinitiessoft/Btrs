package resources.Type;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import resources.specification.SimplePageRequest;
import resources.specification.UserRoleSpecification;
import sendto.RoleSendto;
import service.UserRoleService;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path(value = "/userRole")
public class UserRoleResource {
	@Autowired
	private UserRoleService userRoleService;

	@GET
	public Page<RoleSendto> findAllRole(@PathParam("id") long id, @BeanParam UserRoleSpecification spec,
			@BeanParam SimplePageRequest pageRequest) {
		spec.setUserId(id);
		return userRoleService.findAll(spec, pageRequest);
	}

	@GET
	@Path(value = "{roleid}")
	public RoleSendto findRole(@PathParam("id") long id, @PathParam("roleid") long roleId) {
		return userRoleService.findByUserIdAndRoleId(id, roleId);
	}

	@PUT
	@Path(value = "{roleid}")
	public Response assignRoleToUser(@PathParam("id") long id, @PathParam("roleid") long roleId) {
		userRoleService.grantRoleToUser(id, roleId);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@DELETE
	@Path(value = "{roleid}")
	public Response revokeRoleToUser(@PathParam("id") long id, @PathParam("roleid") long roleId) {
		userRoleService.revokeRoleFromUser(id, roleId);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}
}
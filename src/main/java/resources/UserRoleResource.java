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

import sendto.UserRoleSendto;
import service.UserRoleService;

@Path(value = "/userRole")
public class UserRoleResource {
	@Autowired
	private UserRoleService userRoleService;

	@GET
	@Path(value = "{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserRoleSendto getUserRole(@PathParam("id") long id) {
		return userRoleService.retrieve(id);
	}

	@DELETE
	@Path(value = "{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteUserRole(@PathParam("id") long id) {
		userRoleService.delete(id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path(value = "{id}")
	public UserRoleSendto updateUserRole(@PathParam("id") long id, UserRoleSendto userRole) {
		return userRoleService.update(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public UserRoleSendto saveUserRole(UserRoleSendto userRole) {
		return userRoleService.save(userRole);
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<UserRoleSendto> findallUserRole() {
		return userRoleService.findAll();
	}

	@GET
	@Path(value = "{user_id}")
	public UserRoleSendto findUserRole(@PathParam("id") long id, @PathParam("user_id") long user_id) {
		return userRoleService.findByUserIdAndRoleId(id, user_id);
	}

	@PUT
	@Path(value = "{user_id}")
	public Response assignUserToRole(@PathParam("id") long id, @PathParam("user_id") long user_id) {
		userRoleService.grantUserToRole(id, user_id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@DELETE
	@Path(value = "{userid}")
	public Response revokeUserToRole(@PathParam("id") long id, @PathParam("userid") long user_id) {
		userRoleService.revokeUserFromRole(id, user_id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}
}

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
		return userRoleService.update(id, userRole);
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
	@Path(value = "{userid}")
	public UserRoleSendto findUserRole(@PathParam("id") long id, @PathParam("userid") long userId) {
		return userRoleService.findByUserIdAndRoleId(id, userId);
	}

	@PUT
	@Path(value = "{userid}")
	public Response assignUserToRole(@PathParam("id") long id, @PathParam("userid") long userId) {
		userRoleService.grantUserToRole(id, userId);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@DELETE
	@Path(value = "{userid}")
	public Response revokeUserToRole(@PathParam("id") long id, @PathParam("userid") long userId) {
		userRoleService.revokeUserFromRole(id, userId);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}
}

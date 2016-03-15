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

import sendto.UserSharedSendto;
import service.UserSharedService;

@Path(value = "/userShared")
public class UserSharedResource {

	@Autowired
	private UserSharedService userSharedService;

	@GET
	@Path(value = "{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserSharedSendto getUserShared(@PathParam("id") long id) {
		return userSharedService.retrieve(id);
	}

	@DELETE
	@Path(value = "{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteUserShared(@PathParam("id") long id) {
		userSharedService.delete(id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path(value = "{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public UserSharedSendto updateUserShared(@PathParam("id") long id, UserSharedSendto userShared) {
		return userSharedService.update(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public UserSharedSendto saveUserShared(UserSharedSendto userShared) {
		return userSharedService.save(userShared);
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<UserSharedSendto> findallUserShared() {
		return userSharedService.findAll();
	}

}

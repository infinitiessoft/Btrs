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

import resources.specification.SimplePageRequest;
import resources.specification.UserSharedSpecification;
import sendto.UserSharedSendto;
import service.UserSharedService;

@Path(value = "/userShared")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserSharedResource {

	@Autowired
	private UserSharedService userSharedService;

	@GET
	@Path(value = "{id}")
	public UserSharedSendto getUserShared(@PathParam("id") long id) {
		return userSharedService.retrieve(id);
	}

	@DELETE
	@Path(value = "{id}")
	public Response deleteUserShared(@PathParam("id") long id) {
		userSharedService.delete(id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path(value = "{id}")
	public UserSharedSendto updateUserShared(@PathParam("id") long id, UserSharedSendto userShared) {
		return userSharedService.update(id, userShared);
	}

	@POST
	public UserSharedSendto saveUserShared(UserSharedSendto userShared) {
		return userSharedService.save(userShared);
	}

	@GET
	public Page<UserSharedSendto> findallUserShared(@BeanParam SimplePageRequest pageRequest,
			@BeanParam UserSharedSpecification spec) {
		return userSharedService.findAll(spec, pageRequest);
	}

}

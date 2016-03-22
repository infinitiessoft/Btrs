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

import resources.specification.SimplePageRequest;
import resources.specification.UserSpecification;
import sendto.UserSendto;
import service.UserService;

@Path(value = "/user")
public class UserResource {

	@Autowired
	private UserService userService;

	@GET
	@Path(value = "{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserSendto getUser(@PathParam("id") long id) {
		return userService.retrieve(id);
	}

	@DELETE
	@Path(value = "{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("id") long id) {
		userService.delete(id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path(value = "{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public UserSendto updateUser(@PathParam("id") long id, UserSendto user) {
		return userService.update(id, user);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public UserSendto saveUser(UserSendto user) {
		return userService.save(user);
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Page<UserSendto> findallUser(@BeanParam SimplePageRequest pageRequest, @BeanParam UserSpecification spec) {
		return userService.findAll(spec, pageRequest);
	}

}

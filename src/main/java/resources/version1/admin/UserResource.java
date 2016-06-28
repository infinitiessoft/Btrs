package resources.version1.admin;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import resources.specification.SimplePageRequest;
import resources.specification.UserSpecification;
import sendto.UserSendto;
import service.UserService;

@Component
@Path(value = "/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

	@Autowired
	private UserService userService;

	@GET
	@Path(value = "{id}")
	public UserSendto getUser(@PathParam("id") long id) {
		return userService.retrieve(id);
	}

	@PUT
	@Path(value = "{id}")
	public UserSendto updateUser(@PathParam("id") long id, UserSendto user) {
		return userService.update(id, user);
	}

	@GET
	public Page<UserSendto> findallUser(
			@BeanParam SimplePageRequest pageRequest,
			@BeanParam UserSpecification spec) {
		return userService.findAll(spec, pageRequest);
	}

	@Path("{id}/role")
	public Class<UserRoleResource> getUserRolesResource() {
		return UserRoleResource.class;
	}

}

package resource.Type.member;

import java.util.ArrayList;

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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import entity.UserRole;
import resources.specification.SimplePageRequest;
import resources.specification.UserSpecification;
import sendto.UserSendto;
import service.UserService;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/user")
public class MemberResource {

	@Autowired
	private UserService userService;

	@GET
	@Path(value = "{id}")
	@PreAuthorize("isAuthenticated() and #id == principal.id")
	public UserSendto getUser(@PathParam("id") long id) {
		return userService.retrieve(id);
	}

	// **Method to update
	@PUT
	@Path(value = "{id}")
	@PreAuthorize("isAuthenticated() and #id == principal.id")
	public UserSendto updateUser(@PathParam("id") long id, UserSendto user) {
		if (user != null) {
			user.setDepartment(null);
			user.setDepartmentSet(false);
			user.setUserShared(null);
			user.setUserSharedSet(false);
			user.setUserRole(new ArrayList<UserRole>(0));
		}
		return userService.update(id, user);
	}

	// ** Method to find All the employees in the list
	@GET
	@PreAuthorize("isAuthenticated()")
	public Page<UserSendto> findAllUser(@BeanParam SimplePageRequest pageRequest, @BeanParam UserSpecification spec) {
		return userService.findAll(spec, pageRequest);
	}

	@Path("{id}/role")
	public Class<MemberRoleResource> getUserRoleResource() {
		return MemberRoleResource.class;
	}

	@Path("{id}/report")
	public Class<MemberReportResource> getUserReportResource() {
		return MemberReportResource.class;
	}

}
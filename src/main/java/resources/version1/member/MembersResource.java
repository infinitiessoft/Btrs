package resources.version1.member;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import sendto.UserSendto;
import service.UserService;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MembersResource {

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
			// user.setUserRole(new ArrayList<UserRole>(0));
		}
		return userService.update(id, user);
	}

	@Path("{id}/reports")
	public Class<MemberReportResource> getUserReportResource() {
		return MemberReportResource.class;
	}

	@Path("{id}/records")
	public Class<MemberAttendRecordResource> getUserAttendRecordResource() {
		return MemberAttendRecordResource.class;
	}

}

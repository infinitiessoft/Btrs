package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import resources.version1.admin.AdminResource;
import resources.version1.member.AuthResource;
import resources.version1.member.MemberExpenseTypeResource;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("v1.0")
public class Version1_1Resource {

	@Autowired
	private AdminResource adminResource;
	@Autowired
	private AuthResource authResource;
	@Autowired
	private resources.version1.member.MembersResource membersResource;
	@Autowired
	private MemberExpenseTypeResource memberExpenseTypeResource;

	@Path("admin")
	public AdminResource getAdminResource() {
		return adminResource;
	}

	@Path("auth")
	public AuthResource getAuthResource() {
		return authResource;
	}

	@Path("users")
	public resources.version1.member.MembersResource getUsersResource() {
		return membersResource;
	}

	@Path("expensetypes")
	public MemberExpenseTypeResource getExpenseTypeResource() {
		return memberExpenseTypeResource;
	}
}

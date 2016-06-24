package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import resources.version1.admin.AdminResource;
import resources.version1.member.AuthResource;
import resources.version1.member.MemberExpenseTypeResource;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("v1.0")
public class Version1_1Resource {

	@Path("admin")
	public Class<AdminResource> getAdminResource() {
		return AdminResource.class;
	}

	@Path("auth")
	public Class<AuthResource> getAuthResource() {
		return AuthResource.class;
	}

	@Path("users")
	public Class<resources.version1.member.MembersResource> getEmployeesResource() {
		return resources.version1.member.MembersResource.class;
	}

	@Path("departments")
	public Class<resources.version1.member.MemberDepartmentsResource> getDepartmentsResource() {
		return resources.version1.member.MemberDepartmentsResource.class;
	}

	@Path("expensetypes")
	public Class<MemberExpenseTypeResource> getExpenseTypeResource() {
		return MemberExpenseTypeResource.class;
	}
	//
	// @Path("recordtypes")
	// public Class<resources.version1.member.MemberAttendRecordTypesResource>
	// getAttendRecordTypesResource() {
	// return resources.version1.member.MemberAttendRecordTypesResource.class;
	// }
	//
	// @Path("general")
	// public Class<resources.version1.general.GeneralResource>
	// getGeneralResource() {
	// return resources.version1.general.GeneralResource.class;
	// }
}

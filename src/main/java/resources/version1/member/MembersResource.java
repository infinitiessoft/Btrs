package resources.version1.member;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import resources.specification.UserSpecification;
import sendto.UserSendto;
import service.UserService;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PreAuthorize("isAuthenticated()")
@Path("users")
public class MembersResource {

	@Autowired
	private UserService userService;
	@Autowired
	private MemberReportResource memberReportResource;
	@Autowired
	private MemberAttendRecordResource memberAttendRecordResource;
	@Autowired
	private MemberAuditResource memberAuditResource;
	@Autowired
	private MemberAuditAttendRecordResource memberAuditAttendRecordResource;

	@GET
	@Path(value = "{id}")
	@PreAuthorize("isAuthenticated() and #id == principal.id or hasAuthority('ADMIN') or hasAuthority('ACCOUNTANT')")
	public UserSendto getUser(@PathParam("id") long id) {
		UserSpecification spec = new UserSpecification();
		spec.setId(id);
		return userService.retrieve(spec);
	}

	@Path("{id}/reports")
	public MemberReportResource getUserReportResource() {
		return memberReportResource;
	}

	@Path("{id}/records")
	public MemberAttendRecordResource getUserAttendRecordResource() {
		return memberAttendRecordResource;
	}

	@Path("{id}/audits")
	public MemberAuditResource getUserAuditResource() {
		return memberAuditResource;
	}

	@Path("{id}/auditAttendRecords")
	public MemberAuditAttendRecordResource getAuditAttendRecordResource() {
		return memberAuditAttendRecordResource;
	}

}

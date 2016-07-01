package resources.version1.member;

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

import resources.specification.SimplePageRequest;
import resources.specification.UserSpecification;
import sendto.UserSendto;
import service.UserService;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PreAuthorize("isAuthenticated()")
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
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('ACCOUNTANT')")
	public Page<UserSendto> findallUser(
			@BeanParam SimplePageRequest pageRequest,
			@BeanParam UserSpecification spec) {
		return userService.findAll(spec, pageRequest);
	}

	@GET
	@Path(value = "{id}")
	@PreAuthorize("isAuthenticated() and #id == principal.id or hasAuthority('ADMIN') or hasAuthority('ACCOUNTANT')")
	public UserSendto getUser(@PathParam("id") long id) {
		UserSpecification spec = new UserSpecification();
		spec.setId(id);
		return userService.retrieve(spec);
	}

	// **Method to update
	@PUT
	@Path(value = "{id}")
	@PreAuthorize("isAuthenticated() and #id == principal.id or hasAuthority('ADMIN') or hasAuthority('ACCOUNTANT')")
	public UserSendto updateUser(@PathParam("id") long id, UserSendto user) {
		if (user != null) {
			user.setJobTitle(null);
			user.setJobTitleSet(false);
		}
		UserSpecification spec = new UserSpecification();
		spec.setId(id);
		return userService.update(spec, user);
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

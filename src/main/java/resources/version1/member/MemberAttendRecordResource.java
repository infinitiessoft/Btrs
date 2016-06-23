package resources.version1.member;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import resources.specification.AttendRecordSpecification;
import resources.specification.SimplePageRequest;
import sendto.AttendRecordSendto;
import service.AttendRecordService;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PreAuthorize("isAuthenticated()")
@Path("/records")
public class MemberAttendRecordResource {

	@Autowired
	private AttendRecordService attendRecordService;

	@GET
	@Path(value = "{recordid}")
	@Produces(MediaType.APPLICATION_JSON)
	public AttendRecordSendto getAttendRecord(@PathParam("recordid") long id) {
		return attendRecordService.retrieve(id);
	}

	@GET
	public Page<AttendRecordSendto> findallAttendRecord(
			@PathParam("id") long id, @BeanParam SimplePageRequest pageRequest,
			@BeanParam AttendRecordSpecification spec) {
		spec.setApplicantId(id);
		return attendRecordService.findAll(spec, pageRequest);
	}

	@GET
	@Path(value = "/available")
	public Page<AttendRecordSendto> findallAvailableAttendRecord(
			@PathParam("id") long id, @BeanParam SimplePageRequest pageRequest,
			@BeanParam AttendRecordSpecification spec) {
		spec.setApplicantId(12L);
		spec.setTypeName("official");
		spec.setStatus("permit");
		return attendRecordService.findAllAvailableRecords(spec, pageRequest);
	}
}

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
import service.ReportService;
import exceptions.AttendRecordNotFoundException;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PreAuthorize("hasAuthority('ACCOUNTANT') or hasAuthority('ADMIN')")
@Path("/records")
public class MemberAuditAttendRecordResource {

	@Autowired
	private AttendRecordService attendRecordService;
	@Autowired
	private ReportService reportService;

	@GET
	public Page<AttendRecordSendto> findAll(
			@BeanParam SimplePageRequest pageRequest,
			@BeanParam AttendRecordSpecification spec) {
		return attendRecordService.findAll(spec, pageRequest);
	}

	@GET
	@Path(value = "{recordid}")
	public AttendRecordSendto getAttendRecord(
			@PathParam("recordid") long recordId) {
		AttendRecordSpecification spec = new AttendRecordSpecification();
		spec.setId(recordId);
		AttendRecordSendto ret = attendRecordService.retrieve(spec);
		if (ret == null) {
			throw new AttendRecordNotFoundException(recordId);
		}
		return ret;
	}

}

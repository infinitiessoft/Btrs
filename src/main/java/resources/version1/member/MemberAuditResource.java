package resources.version1.member;

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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import resources.specification.ReportSpecification;
import resources.specification.SimplePageRequest;
import sendto.ReportSendto;
import sendto.ReportSummarySendto;
import sendto.StatusChangeSendto;
import service.ReportService;
import exceptions.ReportNotFoundException;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PreAuthorize("isAuthenticated() and #id == principal.id and hasAuthority('ACCOUNTANT') or hasAuthority('ADMIN')")
public class MemberAuditResource {

	@Autowired
	private ReportService reportService;

	@Autowired
	private MemberAuditStatusChangeResource memberAuditStatusChangeResource;

	@GET
	@Path(value = "{reportid}")
	public ReportSendto getReport(@PathParam("id") long id,
			@PathParam("reportid") long reportId) {
		ReportSpecification spec = new ReportSpecification();
		spec.setId(reportId);
		ReportSendto ret = reportService.retrieve(spec);
		if (ret == null) {
			throw new ReportNotFoundException(reportId);
		}
		return ret;
	}

	@GET
	public Page<ReportSummarySendto> findAll(@PathParam("id") long id,
			@BeanParam SimplePageRequest pageRequest,
			@BeanParam ReportSpecification spec) {
		return reportService.findAll(spec, pageRequest);
	}

	@DELETE
	@Path(value = "{reportid}")
	public Response deleteReport(@PathParam("id") long id,
			@PathParam("reportid") long reportId) {
		ReportSpecification spec = new ReportSpecification();
		spec.setId(reportId);
		reportService.delete(spec);
		return Response.status(Status.OK).type(MediaType.APPLICATION_JSON)
				.build();
	}

	@PUT
	@Path(value = "{reportid}")
	public ReportSendto updateReport(@PathParam("id") long id,
			@PathParam("reportid") long reportId, ReportSendto report) {
		ReportSpecification spec = new ReportSpecification();
		spec.setId(reportId);
		report.setOwner(null);
		report.setOwnerSet(false);
		return reportService.update(spec, report, id);
	}

	@POST
	@Path(value = "{reportid}/approve")
	public ReportSendto approve(@PathParam("id") long id,
			@PathParam("reportid") long reportId,
			StatusChangeSendto statusChange) {
		return reportService.approve(reportId, statusChange, id);
	}

	@POST
	@Path(value = "{reportid}/reject")
	public ReportSendto reject(@PathParam("id") long id,
			@PathParam("reportid") long reportId,
			StatusChangeSendto statusChange) {
		return reportService.reject(reportId, statusChange, id);
	}

	@Path(value = "{reportid}/statusChanges")
	@PreAuthorize("hasAuthority('ACCOUNTANT') or hasAuthority('admin')")
	public MemberAuditStatusChangeResource getMemberStatusChangeResource() {
		return memberAuditStatusChangeResource;
	}

}

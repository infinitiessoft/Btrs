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
import sendto.ReportSendto.Expense;
import sendto.ReportSummarySendto;
import service.ReportService;
import exceptions.ReportNotFoundException;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PreAuthorize("isAuthenticated() and #id == principal.id or hasAuthority('ADMIN')")
public class MemberReportResource {

	@Autowired
	private ReportService reportService;

	@Autowired
	private MemberReportStatusChangeResource memberStatusChangeResource;

	@GET
	@Path(value = "{reportid}")
	public ReportSendto getReport(@PathParam("id") long id,
			@PathParam("reportid") long reportId) {
		ReportSpecification spec = new ReportSpecification();
		spec.setApplicantId(id);
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
		spec.setApplicantId(id);
		return reportService.findAll(spec, pageRequest);
	}

	@DELETE
	@Path(value = "{reportid}")
	public Response deleteReport(@PathParam("id") long id,
			@PathParam("reportid") long reportId) {
		ReportSpecification spec = new ReportSpecification();
		spec.setApplicantId(id);
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
		spec.setApplicantId(id);
		spec.setId(reportId);
		report.setOwner(null);
		report.setOwnerSet(false);
		if (report.getExpenses() != null) {
			for (Expense e : report.getExpenses()) {
				e.setTaxAmount(null);
				e.setTaxAmountSet(false);
			}
		}
		return reportService.update(spec, report, id);
	}

	// **Method to save or create
	@POST
	public ReportSendto saveReport(@PathParam("id") long id, ReportSendto report) {
		ReportSendto.User user = new ReportSendto.User();
		user.setId(id);
		report.setOwner(user);
		return reportService.save(report);
	}

	@Path(value = "{reportid}/statusChanges")
	public MemberReportStatusChangeResource getMemberStatusChangeResource() {
		return memberStatusChangeResource;
	}

}

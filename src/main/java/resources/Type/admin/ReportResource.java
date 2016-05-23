package resources.Type.admin;

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
import org.springframework.stereotype.Component;

import resources.specification.ReportSpecification;
import resources.specification.SimplePageRequest;
import sendto.ReportSendto;
import service.ReportService;

@Component
@Path(value = "/report")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReportResource {

	@Autowired
	private ReportService reportService;

	@GET
	@Path(value = "{id}")
	public ReportSendto getReport(@PathParam("id") long id) {
		return reportService.retrieve(id);
	}

	@DELETE
	@Path(value = "{id}")
	public Response deleteReport(@PathParam("id") long id) {
		reportService.delete(id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path(value = "{id}")
	public ReportSendto updateReport(@PathParam("id") long id, ReportSendto report) {
		return reportService.update(id, report);
	}

	@POST
	public ReportSendto saveReport(ReportSendto report) {

		// UserSendto user = userService.findByUserSharedId(id);
		// List<UserSendto.Report> incomingReports = user.getIncomingReport();
		// List<UserSendto.Report> outgoingReports = user.getOutgoingReport();

		return reportService.save(report);
	}

	@GET
	public Page<ReportSendto> findallReport(@BeanParam SimplePageRequest pageRequest,
			@BeanParam ReportSpecification spec) {
		return reportService.findAll(spec, pageRequest);
	}

}

package resources;

import java.util.Collection;

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

import sendto.ReportSendto;
import service.ReportService;

@Path(value = "/report")
public class ReportResource {

	@Autowired
	private ReportService reportService;

	@GET
	@Path(value = "{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ReportSendto getReport(@PathParam("id") long id) {
		return reportService.retrieve(id);
	}

	@DELETE
	@Path(value = "{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteReport(@PathParam("id") long id) {
		reportService.delete(id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path(value = "{id}")
	public ReportSendto updateReport(@PathParam("id") long id, ReportSendto report) {
		return reportService.update(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public ReportSendto saveReport(ReportSendto report) {
		return reportService.save(report);
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<ReportSendto> findallReport() {
		return reportService.findAll();
	}

}

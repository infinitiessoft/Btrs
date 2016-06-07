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

import resources.specification.ReportSpecification;
import resources.specification.SimplePageRequest;
import sendto.ReportSendto;
import service.ReportService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PreAuthorize("isAuthenticated()")
@Path("/reports")
public class MemberReportResource {

	@Autowired
	private ReportService reportService;

	@GET
	@Path(value = "{id}")
	public ReportSendto getReport(@PathParam("id") long id) {
		return reportService.retrieve(id);
	}

	@GET
	public Page<ReportSendto> findallReport(@BeanParam SimplePageRequest pageRequest,
			@BeanParam ReportSpecification spec) {
		return reportService.findAll(spec, pageRequest);
	}
}

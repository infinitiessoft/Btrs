package resources.version1.member;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import resources.specification.SimplePageRequest;
import resources.specification.StatusChangeSpecification;
import sendto.StatusChangeSendto;
import service.StatusChangeService;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PreAuthorize("isAuthenticated() and #id == principal.id or hasAuthority('ADMIN')")
public class MemberReportStatusChangeResource {

	private StatusChangeService statusChangeService;

	@GET
	@Path(value = "{statusChangeid}")
	@Produces(MediaType.APPLICATION_JSON)
	public StatusChangeSendto getStatusChange(@PathParam("id") long id,
			@PathParam("reportid") long reportId,
			@PathParam("statusChangeid") long statusChangeid) {
		StatusChangeSpecification spec = new StatusChangeSpecification();
		spec.setId(statusChangeid);
		spec.setReportId(reportId);
		spec.setReportOwnerId(id);
		return statusChangeService.retrieve(spec);
	}

	// ** Method to find All the departments in the list

	@GET
	public Page<StatusChangeSendto> findAll(@PathParam("id") long id,
			@PathParam("reportid") long reportId,
			@BeanParam SimplePageRequest pageRequest,
			@BeanParam StatusChangeSpecification spec) {
		spec.setReportId(reportId);
		spec.setReportOwnerId(id);
		return statusChangeService.findAll(spec, pageRequest);
	}

}

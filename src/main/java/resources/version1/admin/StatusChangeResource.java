package resources.version1.admin;

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

import resources.specification.SimplePageRequest;
import resources.specification.StatusChangeSpecification;
import sendto.StatusChangeSendto;
import service.StatusChangeService;

@Component
@Path(value = "/statusChanges")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StatusChangeResource {

	@Autowired
	private StatusChangeService statusChangesService;

	@GET
	@Path(value = "{id}")
	public StatusChangeSendto getStatusChanges(@PathParam("id") long id) {
		return statusChangesService.retrieve(id);
	}

	@DELETE
	@Path(value = "{id}")
	public Response deleteStatusChanges(@PathParam("id") long id) {
		statusChangesService.delete(id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path(value = "{id}")
	public StatusChangeSendto updateStatusChanges(@PathParam("id") long id, StatusChangeSendto statusChanges) {
		return statusChangesService.update(id, statusChanges);
	}

	@POST
	public StatusChangeSendto saveStatusChanges(StatusChangeSendto statusChanges) {
		return statusChangesService.save(statusChanges);
	}

	@GET
	public Page<StatusChangeSendto> findallStatusChanges(@BeanParam SimplePageRequest pageRequest,
			@BeanParam StatusChangeSpecification spec) {
		return statusChangesService.findAll(spec, pageRequest);
	}

}

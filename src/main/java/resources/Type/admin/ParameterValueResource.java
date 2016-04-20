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

import resources.specification.ParameterValueSpecification;
import resources.specification.SimplePageRequest;
import sendto.ParameterValueSendto;
import service.ParameterValueService;

@Path(value = "/parameterValue")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ParameterValueResource {

	@Autowired
	private ParameterValueService parameterValueService;

	@GET
	@Path(value = "{id}")
	public ParameterValueSendto getParameterValue(@PathParam("id") long id) {
		return parameterValueService.retrieve(id);
	}

	@DELETE
	@Path(value = "{id}")
	public Response deleteParameterValue(@PathParam("id") long id) {
		parameterValueService.delete(id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path(value = "{id}")
	public ParameterValueSendto updateParameterValue(@PathParam("id") long id, ParameterValueSendto parameterValue) {
		return parameterValueService.update(id, parameterValue);
	}

	@POST
	public ParameterValueSendto saveParameterValue(ParameterValueSendto parameterValue) {
		return parameterValueService.save(parameterValue);
	}

	@GET
	public Page<ParameterValueSendto> findallParameterValue(@BeanParam SimplePageRequest pageRequest,
			@BeanParam ParameterValueSpecification spec) {
		return parameterValueService.findAll(spec, pageRequest);
	}

}

package resources.Type;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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

import resources.specification.ExpenseTypeParaSpecification;
import resources.specification.SimplePageRequest;
import sendto.ParameterValueSendto;
import service.ExpenseTypeParaService;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path(value = "/expTypePara")
public class ExpenseTypeParaResource {

	@Autowired
	private ExpenseTypeParaService expTypeParaService;

	@GET
	public Page<ParameterValueSendto> findAllParameterValue(@PathParam("id") long id,
			@BeanParam ExpenseTypeParaSpecification spec, @BeanParam SimplePageRequest pageRequest) {
		spec.setExpenseTypeId(id);
		return expTypeParaService.findAll(spec, pageRequest);
	}

	@GET
	@Path(value = "{ parameterValueid}")
	public ParameterValueSendto findParameterValue(@PathParam("id") long id,
			@PathParam("parameterValueid") long parameterValueId) {
		return expTypeParaService.findByExpenseTypeIdAndParameterValueId(parameterValueId, id);
	}

	@PUT
	@Path(value = "{ parameterValueid}")
	public Response assignParameterValueToExpenseType(@PathParam("id") long id,
			@PathParam("parameterValueid") long parameterValueId) {
		expTypeParaService.grantParameterValueToExpenseType(parameterValueId, id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@DELETE
	@Path(value = "{ parameterValueid}")
	public Response revokeParameterValueToExpenseType(@PathParam("id") long id,
			@PathParam("parameterValueid") long parameterValueId) {
		expTypeParaService.revokeParameterValueFromExpenseType(parameterValueId, id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}
}

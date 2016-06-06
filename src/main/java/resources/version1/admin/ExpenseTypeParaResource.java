package resources.version1.admin;

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
import sendto.TypeParameterSendto;
import service.ExpenseTypeParaService;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ExpenseTypeParaResource {

	@Autowired
	private ExpenseTypeParaService expTypeParaService;

	@GET
	public Page<TypeParameterSendto> findAllExpenseType(@PathParam("id") long id,
			@BeanParam ExpenseTypeParaSpecification spec, @BeanParam SimplePageRequest pageRequest) {
		spec.setExpenseTypeId(id);
		return expTypeParaService.findAll(spec, pageRequest);
	}

	@GET
	@Path(value = "{typeParameterId}")
	public TypeParameterSendto findTypeParameter(@PathParam("id") long id,
			@PathParam("typeParameterId") long typeParameterId) {
		return expTypeParaService.findByExpenseTypeIdAndTypeParameterId(id, typeParameterId);
	}

	@PUT
	@Path(value = "{typeParameterId}")
	public Response assignTypeParameterToExpenseType(@PathParam("id") long id,
			@PathParam("typeParameterId") long typeParameterId) {
		expTypeParaService.grantTypeParameterToExpenseType(typeParameterId, id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@DELETE
	@Path(value = "{typeParameterId}")
	public Response revokeTypeParameterToExpenseType(@PathParam("id") long id,
			@PathParam("typeParameterId") long typeParameterId) {
		expTypeParaService.revokeTypeParameterFromExpenseType(typeParameterId, id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}
}

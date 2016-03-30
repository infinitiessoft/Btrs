package resources.Type;

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

import resources.specification.ExpenseTypeSpecification;
import resources.specification.SimplePageRequest;
import sendto.ExpenseTypeSendto;
import service.ExpenseTypeService;

@Path(value = "/expenseType")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ExpenseTypeResource {
	@Autowired
	private ExpenseTypeService expenseTypeService;

	@GET
	@Path(value = "{id}")
	public ExpenseTypeSendto getExpenseType(@PathParam("id") long id) {
		return expenseTypeService.retrieve(id);
	}

	@DELETE
	@Path(value = "{id}")
	public Response deleteExpenseType(@PathParam("id") long id) {
		expenseTypeService.delete(id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path(value = "{id}")
	public ExpenseTypeSendto updateExpenseType(@PathParam("id") long id, ExpenseTypeSendto expenseType) {
		return expenseTypeService.update(id, expenseType);
	}

	@POST
	public ExpenseTypeSendto saveExpenseType(ExpenseTypeSendto expenseType) {
		return expenseTypeService.save(expenseType);
	}

	@GET
	public Page<ExpenseTypeSendto> findallExpenseType(@BeanParam SimplePageRequest pageRequest,
			@BeanParam ExpenseTypeSpecification spec) {
		return expenseTypeService.findAll(spec, pageRequest);
	}

	@Path("{id}/typeParameter")
	public Class<ExpenseTypeParaResource> getExpenseTypeParaResource() {
		return ExpenseTypeParaResource.class;
	}

}

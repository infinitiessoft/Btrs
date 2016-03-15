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

import sendto.ExpenseCateTypeSendto;
import service.ExpenseCateTypeService;

@Path(value = "/expCateType")
public class ExpenseCateTypeResource {

	@Autowired
	private ExpenseCateTypeService expCateTypeService;

	@GET
	@Path(value = "{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ExpenseCateTypeSendto getExpenseCateType(@PathParam("id") long id) {
		return expCateTypeService.retrieve(id);
	}

	@DELETE
	@Path(value = "{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteExpenseCateType(@PathParam("id") long id) {
		expCateTypeService.delete(id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path(value = "{id}")
	public ExpenseCateTypeSendto updateExpenseCateType(@PathParam("id") long id,
			ExpenseCateTypeSendto expenseCateType) {
		return expCateTypeService.update(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public ExpenseCateTypeSendto saveExpenseCateType(ExpenseCateTypeSendto expenseCateType) {
		return expCateTypeService.save(expenseCateType);
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<ExpenseCateTypeSendto> findallExpenseCateType() {
		return expCateTypeService.findAll();
	}

	@GET
	@Path(value = "{category_id}")
	public ExpenseCateTypeSendto findExpenseCateType(@PathParam("id") long id,
			@PathParam("category_id") long category_id) {
		return expCateTypeService.findByExpenseCategoryIdAndExpenseTypeId(id, category_id);
	}

	@PUT
	@Path(value = "{category_id}")
	public Response assignCategoryToType(@PathParam("id") long id, @PathParam("category_id") long category_id) {
		expCateTypeService.grantExpenseCategoryToExpenseType(id, category_id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@DELETE
	@Path(value = "{category_id}")
	public Response revokeCategoryToType(@PathParam("id") long id, @PathParam("category_id") long category_id) {
		expCateTypeService.revokeExpenseCategoryFromExpenseType(id, category_id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}
}

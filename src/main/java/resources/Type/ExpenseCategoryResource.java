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

import sendto.ExpenseCategorySendto;
import service.ExpenseCategoryService;

@Path(value = "/expenseCategory")
public class ExpenseCategoryResource {

	@Autowired
	private ExpenseCategoryService expenseCategoryService;

	@GET
	@Path(value = "{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ExpenseCategorySendto getExpenseCategory(@PathParam("id") long id) {
		return expenseCategoryService.retrieve(id);
	}

	@DELETE
	@Path(value = "{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteExpenseCategory(@PathParam("id") long id) {
		expenseCategoryService.delete(id);
		return Response.status(Status.OK).entity("expenseCategory has been successfully deleted")
				.type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path(value = "{id}")
	public ExpenseCategorySendto updateExpenseCategory(@PathParam("id") long id,
			ExpenseCategorySendto expenseCategory) {
		return expenseCategoryService.update(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public ExpenseCategorySendto saveExpenseCategory(ExpenseCategorySendto expenseCategory) {
		return expenseCategoryService.save(expenseCategory);
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<ExpenseCategorySendto> findallExpenseCategory() {
		return expenseCategoryService.findAll();
	}

}

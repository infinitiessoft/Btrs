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

import resources.specification.ExpenseSpecification;
import resources.specification.SimplePageRequest;
import sendto.ExpenseSendto;
import service.ExpenseService;

@Path(value = "/expense")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExpenseResource {
	@Autowired
	private ExpenseService expenseService;

	@GET
	@Path(value = "{id}")

	public ExpenseSendto getExpense(@PathParam("id") long id) {
		return expenseService.retrieve(id);
	}

	@DELETE
	@Path(value = "{id}")
	public Response deleteExpense(@PathParam("id") long id) {
		expenseService.delete(id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path(value = "{id}")
	public ExpenseSendto updateExpense(@PathParam("id") long id, ExpenseSendto expense) {
		return expenseService.update(id, expense);
	}

	@POST
	public ExpenseSendto saveExpense(ExpenseSendto expense) {
		return expenseService.save(expense);
	}

	@GET
	public Page<ExpenseSendto> findallExpense(@BeanParam SimplePageRequest pageRequest,
			@BeanParam ExpenseSpecification spec) {
		return expenseService.findAll(spec, pageRequest);
	}

}

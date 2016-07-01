//package resources.version1.admin;
//
//import javax.ws.rs.BeanParam;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.Status;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Component;
//
//import resources.specification.ExpenseSpecification;
//import resources.specification.SimplePageRequest;
//import sendto.ExpenseSendto;
//import service.ExpenseService;
//
//@Component
//@Path(value = "/expense")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
//public class ExpenseResource {
//	@Autowired
//	private ExpenseService expenseService;
//
//	@GET
//	@Path(value = "{id}")
//	public ExpenseSendto getExpense(@PathParam("id") long id) {
//		ExpenseSpecification spec = new ExpenseSpecification();
//		spec.setId(id);
//		return expenseService.retrieve(spec);
//	}
//
//	@DELETE
//	@Path(value = "{id}")
//	public Response deleteExpense(@PathParam("id") long id) {
//		ExpenseSpecification spec = new ExpenseSpecification();
//		spec.setId(id);
//		expenseService.delete(spec);
//		return Response.status(Status.NO_CONTENT)
//				.type(MediaType.APPLICATION_JSON).build();
//	}
//
//	@PUT
//	@Path(value = "{id}")
//	public ExpenseSendto updateExpense(@PathParam("id") long id,
//			ExpenseSendto expense) {
//		ExpenseSpecification spec = new ExpenseSpecification();
//		spec.setId(id);
//		return expenseService.update(spec, expense);
//	}
//
//	@POST
//	public ExpenseSendto saveExpense(ExpenseSendto expense) {
//		return expenseService.save(expense);
//	}
//
//	@GET
//	public Page<ExpenseSendto> findallExpense(
//			@BeanParam SimplePageRequest pageRequest,
//			@BeanParam ExpenseSpecification spec) {
//		return expenseService.findAll(spec, pageRequest);
//	}
//
//}

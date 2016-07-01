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
//import resources.specification.ExpenseCategorySpecification;
//import resources.specification.SimplePageRequest;
//import sendto.ExpenseCategorySendto;
//import service.ExpenseCategoryService;
//
//@Component
//@Path(value = "/expenseCategory")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
//public class ExpenseCategoryResource {
//
//	@Autowired
//	private ExpenseCategoryService expenseCategoryService;
//
//	@GET
//	@Path(value = "{id}")
//	public ExpenseCategorySendto getExpenseCategory(@PathParam("id") long id) {
//		ExpenseCategorySpecification spec = new ExpenseCategorySpecification();
//		spec.setId(id);
//		return expenseCategoryService.retrieve(spec);
//	}
//
//	@DELETE
//	@Path(value = "{id}")
//	public Response deleteExpenseCategory(@PathParam("id") long id) {
//		ExpenseCategorySpecification spec = new ExpenseCategorySpecification();
//		spec.setId(id);
//		expenseCategoryService.delete(spec);
//		return Response.status(Status.NO_CONTENT)
//				.type(MediaType.APPLICATION_JSON).build();
//	}
//
//	@PUT
//	@Path(value = "{id}")
//	public ExpenseCategorySendto updateExpenseCategory(
//			@PathParam("id") long id, ExpenseCategorySendto expenseCategory) {
//		ExpenseCategorySpecification spec = new ExpenseCategorySpecification();
//		spec.setId(id);
//		return expenseCategoryService.update(spec, expenseCategory);
//	}
//
//	@POST
//	public ExpenseCategorySendto saveExpenseCategory(
//			ExpenseCategorySendto expenseCategory) {
//		return expenseCategoryService.save(expenseCategory);
//	}
//
//	@GET
//	public Page<ExpenseCategorySendto> findallExpenseCategory(
//			@BeanParam SimplePageRequest pageRequest,
//			@BeanParam ExpenseCategorySpecification spec) {
//		return expenseCategoryService.findAll(spec, pageRequest);
//	}
//
//}

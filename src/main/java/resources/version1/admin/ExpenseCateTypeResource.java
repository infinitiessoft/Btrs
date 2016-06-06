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

import resources.specification.ExpenseCateTypeSpecification;
import resources.specification.SimplePageRequest;
import sendto.ExpenseTypeSendto;
import service.ExpenseCateTypeService;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExpenseCateTypeResource {

	@Autowired
	private ExpenseCateTypeService expCateTypeService;

	@GET
	public Page<ExpenseTypeSendto> findAllExpenseType(@PathParam("id") long id,
			@BeanParam ExpenseCateTypeSpecification spec, @BeanParam SimplePageRequest pageRequest) {
		spec.setExpenseCategoryId(id);
		return expCateTypeService.findAll(spec, pageRequest);
	}

	@GET
	@Path(value = "{ expenseTypeid}")
	public ExpenseTypeSendto findExpenseType(@PathParam("id") long id, @PathParam("expenseTypeid") long expenseTypeId) {
		return expCateTypeService.findByExpenseCategoryIdAndExpenseTypeId(id, expenseTypeId);
	}

	@PUT
	@Path(value = "{ expenseTypeid}")
	public Response assignExpenseTypeToExpenseCategory(@PathParam("id") long id,
			@PathParam("expenseTypeid") long expenseTypeId) {
		expCateTypeService.grantExpenseTypeToExpenseCategory(id, expenseTypeId);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@DELETE
	@Path(value = "{ expenseTypeid}")
	public Response revokeExpenseTypeToExpenseCategory(@PathParam("id") long id,
			@PathParam("expenseTypeid") long expenseTypeId) {
		expCateTypeService.revokeExpenseTypeFromExpenseCategory(id, expenseTypeId);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}
}

package resources.version1.member;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import resources.specification.ExpenseTypeSpecification;
import resources.specification.SimplePageRequest;
import sendto.ExpenseTypeSendto;
import service.ExpenseTypeService;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MemberExpenseTypeResource {

	@Autowired
	private ExpenseTypeService expenseTypeService;

	@GET
	@Path(value = "{id}")
	public ExpenseTypeSendto getExpenseType(@PathParam("id") long id) {
		return expenseTypeService.retrieve(id);
	}

	@GET
	public Page<ExpenseTypeSendto> findAll(
			@BeanParam SimplePageRequest pageRequest,
			@BeanParam ExpenseTypeSpecification spec) {
		return expenseTypeService.findAll(spec, pageRequest);
	}
	
	

}

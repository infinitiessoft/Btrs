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

import sendto.ExpenseTypeParaSendto;
import service.ExpenseTypeParaService;

@Path(value = "/expTypePara")
public class ExpenseTypeParaResource {

	@Autowired
	private ExpenseTypeParaService expTypeParaService;

	@GET
	@Path(value = "{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ExpenseTypeParaSendto getExpenseTypePara(@PathParam("id") long id) {
		return expTypeParaService.retrieve(id);
	}

	@DELETE
	@Path(value = "{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteExpenseTypePara(@PathParam("id") long id) {
		expTypeParaService.delete(id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path(value = "{id}")
	public ExpenseTypeParaSendto updateExpenseTypePara(@PathParam("id") long id,
			ExpenseTypeParaSendto expenseTypePara) {
		return expTypeParaService.update(id, expenseTypePara);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public ExpenseTypeParaSendto saveExpenseTypePara(ExpenseTypeParaSendto expenseTypePara) {
		return expTypeParaService.save(expenseTypePara);
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<ExpenseTypeParaSendto> findallExpenseTypePara() {
		return expTypeParaService.findAll();
	}

	@GET
	@Path(value = "{typeid}")
	public ExpenseTypeParaSendto findExpenseTypePara(@PathParam("id") long id, @PathParam("typeid") long typeId) {
		return expTypeParaService.findByTypeIdAndParameterId(id, typeId);
	}

	@PUT
	@Path(value = "{typeid}")
	public Response assignTypeToParameter(@PathParam("id") long id, @PathParam("typeid") long typeId) {
		expTypeParaService.grantTypeToParameter(id, typeId);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@DELETE
	@Path(value = "{typeid}")
	public Response revokeTypeToParameter(@PathParam("id") long id, @PathParam("typeid") long typeId) {
		expTypeParaService.revokeTypeFromParameter(id, typeId);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}
}

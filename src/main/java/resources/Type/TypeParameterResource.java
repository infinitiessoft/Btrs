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

import sendto.TypeParameterSendto;
import service.TypeParameterService;

@Path(value = "/typeParameter")
public class TypeParameterResource {

	@Autowired
	private TypeParameterService typeParameterService;

	@GET
	@Path(value = "{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TypeParameterSendto getTypeParameter(@PathParam("id") long id) {
		return typeParameterService.retrieve(id);
	}

	@DELETE
	@Path(value = "{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteTypeParameter(@PathParam("id") long id) {
		typeParameterService.delete(id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path(value = "{id}")
	public TypeParameterSendto updateTypeParameter(@PathParam("id") long id, TypeParameterSendto type) {
		return typeParameterService.update(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public TypeParameterSendto saveTypeParameter(TypeParameterSendto type) {
		return typeParameterService.save(type);
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<TypeParameterSendto> findallTypeParameter() {
		return typeParameterService.findAll();
	}

}

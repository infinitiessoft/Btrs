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

import resources.specification.SimplePageRequest;
import resources.specification.TypeParameterSpecification;
import sendto.TypeParameterSendto;
import service.TypeParameterService;

@Path(value = "/typeParameter")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TypeParameterResource {

	@Autowired
	private TypeParameterService typeParameterService;

	@GET
	@Path(value = "{id}")
	public TypeParameterSendto getTypeParameter(@PathParam("id") long id) {
		return typeParameterService.retrieve(id);
	}

	@DELETE
	@Path(value = "{id}")
	public Response deleteTypeParameter(@PathParam("id") long id) {
		typeParameterService.delete(id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path(value = "{id}")
	public TypeParameterSendto updateTypeParameter(@PathParam("id") long id, TypeParameterSendto typeParameter) {
		return typeParameterService.update(id, typeParameter);
	}

	@POST
	public TypeParameterSendto saveTypeParameter(TypeParameterSendto type) {
		return typeParameterService.save(type);
	}

	@GET
	public Page<TypeParameterSendto> findallTypeParameter(@BeanParam SimplePageRequest pageRequest,
			@BeanParam TypeParameterSpecification spec) {
		return typeParameterService.findAll(spec, pageRequest);
	}

}

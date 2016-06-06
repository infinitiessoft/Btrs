package resources.version1.admin;

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

import resources.specification.PhotoSpecification;
import resources.specification.SimplePageRequest;
import sendto.PhotoSendto;
import service.PhotoService;

@Path(value = "/photo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PhotoResource {

	@Autowired
	private PhotoService photoService;

	@GET
	@Path(value = "{id}")
	public PhotoSendto getPhoto(@PathParam("id") long id) {
		return photoService.retrieve(id);
	}

	@DELETE
	@Path(value = "{id}")
	public Response deletePhoto(@PathParam("id") long id) {
		photoService.delete(id);
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path(value = "{id}")
	public PhotoSendto updatePhoto(@PathParam("id") long id, PhotoSendto photo) {
		return photoService.update(id, photo);
	}

	@POST
	public PhotoSendto savePhoto(PhotoSendto photo) {
		return photoService.save(photo);
	}

	@GET
	public Page<PhotoSendto> findallPhoto(@BeanParam SimplePageRequest pageRequest,
			@BeanParam PhotoSpecification spec) {
		return photoService.findAll(spec, pageRequest);
	}

}

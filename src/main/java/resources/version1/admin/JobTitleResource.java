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
import org.springframework.stereotype.Component;

import resources.specification.JobTitleSpecification;
import resources.specification.SimplePageRequest;
import sendto.JobTitleSendto;
import service.JobTitleService;

@Component
@Path(value = "/jobTitles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JobTitleResource {

	@Autowired
	private JobTitleService jobTitleService;

	@GET
	@Path(value = "{id}")
	public JobTitleSendto getJobTitle(@PathParam("id") long id) {
		JobTitleSpecification spec = new JobTitleSpecification();
		spec.setId(id);
		return jobTitleService.retrieve(spec);
	}

	@DELETE
	@Path(value = "{id}")
	public Response deleteJobTitle(@PathParam("id") long id) {
		JobTitleSpecification spec = new JobTitleSpecification();
		spec.setId(id);
		jobTitleService.delete(spec);
		return Response.status(Status.NO_CONTENT)
				.type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path(value = "{id}")
	public JobTitleSendto updateJobTitle(@PathParam("id") long id,
			JobTitleSendto jobTitle) {
		JobTitleSpecification spec = new JobTitleSpecification();
		spec.setId(id);
		return jobTitleService.update(spec, jobTitle);
	}

	@POST
	public JobTitleSendto saveJobTitle(JobTitleSendto jobTitle) {
		return jobTitleService.save(jobTitle);
	}

	@GET
	public Page<JobTitleSendto> findAll(
			@BeanParam SimplePageRequest pageRequest,
			@BeanParam JobTitleSpecification spec) {
		return jobTitleService.findAll(spec, pageRequest);
	}

}

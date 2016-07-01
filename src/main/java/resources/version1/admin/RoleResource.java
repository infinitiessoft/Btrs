//package resources.version1.admin;
//
//import javax.ws.rs.BeanParam;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Component;
//
//import resources.specification.RoleSpecification;
//import resources.specification.SimplePageRequest;
//import sendto.RoleSendto;
//import service.RoleService;
//
//@Component
//@Path(value = "/role")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//public class RoleResource {
//
//	@Autowired
//	private RoleService roleService;
//
//	@GET
//	@Path(value = "{id}")
//	public RoleSendto getRole(@PathParam("id") long id) {
//		RoleSpecification spec = new RoleSpecification();
//		spec.setId(id);
//		return roleService.retrieve(spec);
//	}
//
//	@GET
//	public Page<RoleSendto> findallRole(
//			@BeanParam SimplePageRequest pageRequest,
//			@BeanParam RoleSpecification spec) {
//		return roleService.findAll(spec, pageRequest);
//	}
//
//}

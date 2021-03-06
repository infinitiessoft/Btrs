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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import resources.specification.SimplePageRequest;
import resources.specification.UserRoleSpecification;
import sendto.RoleSendto;
import service.UserRoleService;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PreAuthorize("isAuthenticated() and #id == principal.id or hasAuthority('ADMIN')")
public class MemberRoleResource {

	@Autowired
	private UserRoleService userRoleService;

	@GET
	public Page<RoleSendto> findAllRole(@PathParam("id") long id, @BeanParam UserRoleSpecification spec,
			@BeanParam SimplePageRequest pageRequest) {
		spec.setUserId(id);
		return userRoleService.findAll(spec, pageRequest);
	}

	@GET
	@Path(value = "{roleid}")
	public RoleSendto findRole(@PathParam("id") long id, @PathParam("roleid") long roleId) {
		return userRoleService.findByUserIdAndRoleId(id, roleId);
	}

}
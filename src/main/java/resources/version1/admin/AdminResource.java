package resources.version1.admin;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PreAuthorize("hasAuthority('admin')")
public class AdminResource {

	@Path("report")
	public Class<ReportResource> getReportResource() {
		return ReportResource.class;
	}

	@Path("expenseCategory")
	public Class<ExpenseCategoryResource> getExpenseCategoryResource() {
		return ExpenseCategoryResource.class;
	}

	@Path("department")
	public Class<DepartmentResource> getDepartmentResource() {
		return DepartmentResource.class;
	}

	@Path("expenseType")
	public Class<ExpenseTypeResource> getExpenseTypeResource() {
		return ExpenseTypeResource.class;
	}

	@Path("expense")
	public Class<ExpenseResource> getExpenseResource() {
		return ExpenseResource.class;
	}

	@Path("statusChanges")
	public Class<StatusChangesResource> getStatusChangesResource() {
		return StatusChangesResource.class;
	}

	@Path("parameterValue")
	public Class<ParameterValueResource> getParameterValueResource() {
		return ParameterValueResource.class;
	}

	@Path("role")
	public Class<RoleResource> getRoleResource() {
		return RoleResource.class;
	}

	@Path("typeParameter")
	public Class<TypeParameterResource> getTypeParameterResource() {
		return TypeParameterResource.class;
	}

	@Path("user")
	public Class<UserResource> getUserResource() {
		return UserResource.class;
	}

	@Path("userShared")
	public Class<UserSharedResource> getUserSharedResource() {
		return UserSharedResource.class;
	}

}

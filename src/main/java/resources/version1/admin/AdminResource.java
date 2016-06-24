package resources.version1.admin;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PreAuthorize("hasAuthority('admin')")
public class AdminResource {

	@Autowired
	private ReportResource reportResource;
	@Autowired
	private ExpenseCategoryResource expenseCategoryResource;
	@Autowired
	private DepartmentResource departmentResource;
	@Autowired
	private ExpenseTypeResource expenseTypeResource;
	@Autowired
	private ExpenseResource expenseResource;
	@Autowired
	private StatusChangeResource statusChangesResource;
	@Autowired
	private ParameterValueResource parameterValueResource;
	@Autowired
	private RoleResource roleResource;
	@Autowired
	private TypeParameterResource typeParameterResource;
	@Autowired
	private UserResource userResource;
	@Autowired
	private UserSharedResource userSharedResource;

	@Path("reports")
	public ReportResource getReportResource() {
		return reportResource;
	}

	@Path("expenseCategorys")
	public ExpenseCategoryResource getExpenseCategoryResource() {
		return expenseCategoryResource;
	}

	@Path("departments")
	public DepartmentResource getDepartmentResource() {
		return departmentResource;
	}

	@Path("expenseTypes")
	public ExpenseTypeResource getExpenseTypeResource() {
		return expenseTypeResource;
	}

	@Path("expenses")
	public ExpenseResource getExpenseResource() {
		return expenseResource;
	}

	@Path("statusChanges")
	public StatusChangeResource getStatusChangesResource() {
		return statusChangesResource;
	}

	@Path("parameterValues")
	public ParameterValueResource getParameterValueResource() {
		return parameterValueResource;
	}

	@Path("roles")
	public RoleResource getRoleResource() {
		return roleResource;
	}

	@Path("typeParameters")
	public TypeParameterResource getTypeParameterResource() {
		return typeParameterResource;
	}

	@Path("users")
	public UserResource getUserResource() {
		return userResource;
	}

	@Path("userShareds")
	public UserSharedResource getUserSharedResource() {
		return userSharedResource;
	}

}

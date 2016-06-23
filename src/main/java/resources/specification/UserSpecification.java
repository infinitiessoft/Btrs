package resources.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

import org.springframework.data.jpa.domain.Specification;

import com.google.common.base.Strings;

import entity.Department;
import entity.User;
import entity.UserShared;

public class UserSpecification implements Specification<User> {

	@QueryParam("userSharedId")
	private Long userSharedId;
	@QueryParam("departmentId")
	private Long departmentId;
	
	private String username;

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (userSharedId != null) {
			predicates.add(cb.equal(root.<UserShared> get("userShared")
					.<Long> get("id"), userSharedId));
		}
		if (departmentId != null) {
			predicates.add(cb.equal(root.<Department> get("department")
					.<Long> get("id"), departmentId));
		}
		
		if(!Strings.isNullOrEmpty(username)){
			predicates.add(cb.equal(root.<UserShared> get("userShared")
					.<String> get("username"), username));
		}
		if (predicates.isEmpty()) {
			return null;
		}
		return cb.and(predicates.toArray(new Predicate[0]));
	}

	public Long getUserSharedId() {
		return userSharedId;
	}

	public void setUserSharedId(Long userSharedId) {
		this.userSharedId = userSharedId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

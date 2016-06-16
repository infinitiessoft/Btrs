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

import entity.User;
import entity.UserShared;

public class UserSharedSpecification implements Specification<UserShared> {

	@QueryParam("username")
	private String username;
	@QueryParam("jobTitle")
	private String jobTitle;
	@QueryParam("gender")
	private String gender;
	@QueryParam("first_name")
	private String first_name;
	@QueryParam("last_name")
	private String last_name;
	@QueryParam("userId")
	private Long userId;

	@Override
	public Predicate toPredicate(Root<UserShared> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (userId != null) {
			predicates.add(cb.equal(root.<User> get("user").<Long> get("id"),
					userId));
		}
		if (!Strings.isNullOrEmpty(username)) {
			predicates.add(cb.like(root.<String> get("username"), "%"
					+ username + "%"));
		}
		if (!Strings.isNullOrEmpty(jobTitle)) {
			predicates.add(cb.like(root.<String> get("jobTitle"), "%"
					+ jobTitle + "%"));
		}
		if (!Strings.isNullOrEmpty(gender)) {
			predicates.add(cb.like(root.<String> get("gender"), "%" + gender
					+ "%"));
		}

		if (!Strings.isNullOrEmpty(first_name)) {
			predicates.add(cb.like(root.<String> get("first_name"), "%"
					+ first_name + "%"));
		}

		if (!Strings.isNullOrEmpty(last_name)) {
			predicates.add(cb.like(root.<String> get("last_name"), "%"
					+ last_name + "%"));
		}

		if (predicates.isEmpty()) {
			return null;
		}
		return cb.and(predicates.toArray(new Predicate[0]));
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}

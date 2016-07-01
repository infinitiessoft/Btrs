package resources.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

import org.springframework.data.jpa.domain.Specification;

import entity.User;

public class UserSpecification implements Specification<User> {

	private Long id;
	@QueryParam("userSharedId")
	private Long userSharedId;

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (userSharedId != null) {
			predicates.add(cb.equal(root.<Long> get("userSharedId"),
					userSharedId));
		}

		if (id != null) {
			predicates.add(cb.equal(root.<Long> get("id"), id));
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

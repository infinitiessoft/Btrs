package resources.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import entity.StatusChanges;

public class StatusChangesSpecification implements Specification<StatusChanges> {

	@Override
	public Predicate toPredicate(Root<StatusChanges> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		return null;
	}

}

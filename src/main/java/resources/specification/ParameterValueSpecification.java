package resources.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import entity.ParameterValue;

public class ParameterValueSpecification implements Specification<ParameterValue> {

	@Override
	public Predicate toPredicate(Root<ParameterValue> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		return null;
	}

}

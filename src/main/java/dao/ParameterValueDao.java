package dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import entity.ParameterValue;

public interface ParameterValueDao
		extends PagingAndSortingRepository<ParameterValue, Long>, JpaSpecificationExecutor<ParameterValue> {

}

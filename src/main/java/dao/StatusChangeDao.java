package dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import entity.StatusChange;

public interface StatusChangeDao
		extends PagingAndSortingRepository<StatusChange, Long>, JpaSpecificationExecutor<StatusChange> {

}

package dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import entity.StatusChanges;

public interface StatusChangesDao
		extends PagingAndSortingRepository<StatusChanges, Long>, JpaSpecificationExecutor<StatusChanges> {

}

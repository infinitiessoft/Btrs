package dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import entity.UserShared;

public interface UserSharedDao
		extends PagingAndSortingRepository<UserShared, Long>, JpaSpecificationExecutor<UserShared> {

	UserShared findByUsername(String username);

}

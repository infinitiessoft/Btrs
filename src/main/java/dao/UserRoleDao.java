package dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import entity.UserRole;

public interface UserRoleDao extends PagingAndSortingRepository<UserRole, Long>, JpaSpecificationExecutor<UserRole> {

	UserRole findByUserIdAndRoleId(long user_id, long role_id);

}

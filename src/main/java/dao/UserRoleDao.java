package dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import entity.UserRole;
import sendto.UserRoleSendto;

public interface UserRoleDao extends JpaSpecificationExecutor<UserRole> {

	UserRole findOne(long id);

	void delete(long id);

	UserRole save(UserRole uRole);

	Collection<UserRole> findAll();

	UserRole save(UserRoleSendto userRole);

	void delete(UserRole userRole);

	UserRole findByUserIdAndRoleId(long userId, long roleId);

}

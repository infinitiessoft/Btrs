package dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import entity.Role;
import sendto.RoleSendto;

public interface RoleDao extends JpaSpecificationExecutor<Role> {

	Role findOne(long id);

	void delete(long id);

	Role save(Role role);

	Collection<Role> findAll();

	Role save(RoleSendto role);

}

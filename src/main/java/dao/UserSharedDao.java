package dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import entity.UserShared;
import sendto.UserSharedSendto;

public interface UserSharedDao extends JpaSpecificationExecutor<UserShared> {

	UserShared findOne(long id);

	void delete(long id);

	UserShared save(UserShared user);

	Collection<UserShared> findAll();

	UserShared save(UserSharedSendto userShared);

	UserShared findByUsername(String username);

}

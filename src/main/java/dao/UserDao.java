package dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import entity.User;
import sendto.UserSendto;

public interface UserDao extends JpaSpecificationExecutor<User> {

	User findOne(long userId);

	void delete(long id);

	User save(User usr);

	Collection<User> findAll();

	User save(UserSendto user);

	User findByName(String name);

}

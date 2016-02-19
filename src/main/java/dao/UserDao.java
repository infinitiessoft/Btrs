package dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import entity.User;

public interface UserDao extends JpaSpecificationExecutor<User> {

}

package service;

import java.util.Collection;

import entity.User;

public interface UserService {

	public User retrieve(long id);

	public void delete(long id);

	public void save(User user);

	public Collection<User> findAll();

	public void update(long id, User user);

}

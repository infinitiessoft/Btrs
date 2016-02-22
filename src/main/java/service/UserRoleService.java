package service;

import java.util.Collection;

import entity.UserRole;

public interface UserRoleService {
	public UserRole retrieve(long id);

	public void delete(long id);

	public void save(UserRole userRole);

	public Collection<UserRole> findAll();

	public void update(long id, UserRole userRole);
}

package service;

import java.util.Collection;

import entity.Role;

public interface RoleService {

	public Role retrieve(long id);

	public void delete(long id);

	public void save(Role role);

	public Collection<Role> findAll();

	public void update(long id, Role role);
}

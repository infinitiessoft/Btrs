package service;

import java.util.Collection;

import sendto.UserRoleSendto;

public interface UserRoleService {

	public UserRoleSendto retrieve(long id);

	public void delete(long id);

	public UserRoleSendto save(UserRoleSendto userRole);

	public UserRoleSendto update(long id, UserRoleSendto userRole);

	public void revokeUserFromRole(long userId, long roleId);

	public Collection<UserRoleSendto> findAll();

	public UserRoleSendto findByUserIdAndRoleId(long userId, long roleId);

	public void grantUserToRole(long userId, long roleId);

}
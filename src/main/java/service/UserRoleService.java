package service;

import java.util.Collection;

import sendto.UserRoleSendto;

public interface UserRoleService {

	public UserRoleSendto retrieve(long id);

	public void delete(long id);

	public UserRoleSendto save(UserRoleSendto userRole);

	public UserRoleSendto update(long id);

	public void revokeUserFromRole(long user_id, long role_id);

	public Collection<UserRoleSendto> findAll();

	public UserRoleSendto findByUserIdAndRoleId(long user_id, long role_id);

	public void grantUserToRole(long user_id, long role_id);

}
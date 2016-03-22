package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import resources.specification.UserRoleSpecification;
import sendto.RoleSendto;

public interface UserRoleService {

	public void revokeRoleFromUser(long roleId, long userId);

	public Page<RoleSendto> findAll(UserRoleSpecification spec, Pageable pageable);

	public RoleSendto findByUserIdAndRoleId(long userId, long roleId);

	public void grantRoleToUser(long userId, long roleId);
}
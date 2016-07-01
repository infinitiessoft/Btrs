package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import resources.specification.UserRoleSpecification;
import sendto.RoleSendto;
import service.UserRoleService;
import dao.RoleDao;
import dao.UserDao;
import dao.UserRoleDao;
import entity.Role;
import entity.User;
import entity.UserRole;
import exceptions.RoleNotFoundException;
import exceptions.UserAssignmentNotFoundException;
import exceptions.UserNotFoundException;

public class UserRoleServiceImpl implements UserRoleService {

	private UserRoleDao userRoleDao;
	private UserDao userDao;
	private RoleDao roleDao;

	public UserRoleServiceImpl(UserRoleDao userRoleDao, UserDao userDao,
			RoleDao roleDao) {
		this.userRoleDao = userRoleDao;
		this.userDao = userDao;
		this.roleDao = roleDao;
	}

	@Override
	public void revokeRoleFromUser(long userId, long roleId) {
		UserRole userRole = userRoleDao.findByUserIdAndRoleId(userId, roleId);
		if (userRole == null) {
			throw new UserAssignmentNotFoundException(roleId, userId);
		}
		userRoleDao.delete(userRole);
	}

	@Override
	public Page<RoleSendto> findAll(UserRoleSpecification spec,
			Pageable pageable) {
		List<RoleSendto> sendto = new ArrayList<RoleSendto>();
		Page<UserRole> userRoles = userRoleDao.findAll(spec, pageable);
		for (UserRole users : userRoles) {
			Role role = users.getRole();
			sendto.add(RoleServiceImpl.toRoleSendto(role));
		}
		Page<RoleSendto> rets = new PageImpl<RoleSendto>(sendto, pageable,
				userRoles.getTotalElements());
		return rets;
	}

	@Override
	public RoleSendto findByUserIdAndRoleId(long userId, long roleId) {
		UserRole userRole = userRoleDao.findByUserIdAndRoleId(userId, roleId);
		if (userRole == null) {
			throw new UserAssignmentNotFoundException(userId, roleId);
		}
		return RoleServiceImpl.toRoleSendto(userRole.getRole());
	}

	@Override
	public void grantRoleToUser(long userId, long roleId) {
		User user = userDao.findOne(userId);
		if (user == null) {
			throw new UserNotFoundException(userId);
		}
		Role role = roleDao.findOne(roleId);
		if (role == null) {
			throw new RoleNotFoundException(roleId);
		}
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		userRoleDao.save(userRole);
	}

}

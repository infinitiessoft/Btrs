package serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.RoleDao;
import dao.UserDao;
import dao.UserRoleDao;
import entity.Role;
import entity.User;
import entity.UserRole;
import exceptions.RoleNotFoundException;
import exceptions.UserAssignmentNotFoundException;
import exceptions.UserNotFoundException;
import exceptions.UserRoleNotFoundException;
import sendto.UserRoleSendto;
import service.UserRoleService;

public class UserRoleServiceImpl implements UserRoleService {

	private UserRoleDao userRoleDao;
	private UserDao userDao;
	private RoleDao roleDao;

	public UserRoleServiceImpl(UserRoleDao userRoleDao, UserDao userDao, RoleDao roleDao) {
		this.userRoleDao = userRoleDao;
		this.userDao = userDao;
		this.roleDao = roleDao;
	}

	@Override
	public UserRoleSendto retrieve(long id) {
		UserRole userRole = userRoleDao.findOne(id);
		if (userRole == null) {
			throw new UserRoleNotFoundException(id);
		}
		return toUserRoleSendto(userRole);
	}

	private UserRoleSendto toUserRoleSendto(UserRole userRole) {
		UserRoleSendto ret = new UserRoleSendto();
		ret.setId(userRole.getId());
		return ret;
	}

	@Override
	public void delete(long id) {
		userRoleDao.delete(id);
	}

	@Override
	public UserRoleSendto save(UserRoleSendto userRole) {
		userRole.setId(null);
		UserRole uRole = new UserRole();
		uRole = userRoleDao.save(uRole);
		return toUserRoleSendto(uRole);

	}

	@Override
	public UserRoleSendto update(long id, UserRoleSendto userRole) {
		UserRole uRole = userRoleDao.findOne(id);
		if (uRole == null) {
			throw new UserRoleNotFoundException(id);
		}
		return toUserRoleSendto(userRoleDao.save(userRole));
	}

	@Override
	public void revokeUserFromRole(long userId, long roleId) {
		UserRole userRole = userRoleDao.findByUserIdAndRoleId(userId, roleId);
		if (userRole == null) {
			throw new UserAssignmentNotFoundException(userId, roleId);
		}
		userRoleDao.delete(userRole);

	}

	@Override
	public Collection<UserRoleSendto> findAll() {
		List<UserRoleSendto> sendto = new ArrayList<UserRoleSendto>();
		Collection<UserRole> userRole = userRoleDao.findAll();
		for (UserRole uRole : userRole) {
			sendto.add(toUserRoleSendto(uRole));
		}
		return sendto;
	}

	@Override
	public UserRoleSendto findByUserIdAndRoleId(long userId, long roleId) {
		UserRole userRole = userRoleDao.findByUserIdAndRoleId(userId, roleId);
		if (userRole == null) {
			throw new UserAssignmentNotFoundException(userId, roleId);
		}
		return UserServiceImpl.toUserSendto(userRole);
	}

	@Override
	public void grantUserToRole(long userId, long roleId) {
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

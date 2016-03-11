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
	public UserRoleSendto update(long id) {
		UserRole uRole = userRoleDao.findOne(id);
		if (uRole == null) {
			throw new UserRoleNotFoundException(id);
		}
		return toUserRoleSendto(userRoleDao.save(uRole));
	}

	@Override
	public void revokeUserFromRole(long user_id, long role_id) {
		UserRole userRole = userRoleDao.findByUserIdAndRoleId(user_id, role_id);
		if (userRole == null) {
			throw new UserAssignmentNotFoundException(user_id, role_id);
		}
		userRoleDao.delete(userRole);

	}

	@Override
	public Collection<UserRoleSendto> findAll() {
		List<UserRoleSendto> sendto = new ArrayList<UserRoleSendto>();
		for (UserRole uRole : userRoleDao.findAll()) {
			sendto.add(toUserRoleSendto(uRole));
		}
		return sendto;
	}

	@Override
	public UserRoleSendto findByUserIdAndRoleId(long user_id, long role_id) {
		UserRole userRole = userRoleDao.findByUserIdAndRoleId(user_id, role_id);
		if (userRole == null) {
			throw new UserAssignmentNotFoundException(user_id, role_id);
		}
		return UserServiceImpl.toUserSendto(userRole);
	}

	@Override
	public void grantUserToRole(long user_id, long role_id) {
		User user = userDao.findOne(user_id);
		if (user == null) {
			throw new UserNotFoundException(user_id);
		}
		Role role = roleDao.findOne(role_id);
		if (role == null) {
			throw new RoleNotFoundException(role_id);
		}
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		userRoleDao.save(userRole);

	}

}

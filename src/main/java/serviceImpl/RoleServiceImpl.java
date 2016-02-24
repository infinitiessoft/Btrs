package serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.RoleDao;
import entity.Role;
import exceptions.RoleNotFoundException;
import sendto.RoleSendto;
import service.RoleService;

public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao;

	public RoleServiceImpl(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public RoleSendto retrieve(long id) {
		Role role = roleDao.findOne(id);
		if (role == null) {
			throw new RoleNotFoundException(id);
		}
		return toRoleSendto(role);
	}

	private RoleSendto toRoleSendto(Role role) {

		return null;
	}

	@Override
	public void delete(long id) {
		roleDao.delete(id);

	}

	@Override
	public RoleSendto save(RoleSendto role) {
		role.setId(null);
		Role roles = new Role();
		roles = roleDao.save(roles);
		return toRoleSendto(roles);
	}

	@Override
	public Collection<RoleSendto> findAll() {
		List<RoleSendto> sendto = new ArrayList<RoleSendto>();
		Collection<Role> role = roleDao.findAll();
		for (Role roles : role) {
			sendto.add(toRoleSendto(roles));
		}
		return sendto;
	}

	@Override
	public RoleSendto update(long id, RoleSendto role) {
		Role roles = roleDao.findOne(id);
		if (role == null) {
			throw new RoleNotFoundException(id);
		}
		return toRoleSendto(roleDao.save(role));
	}
}

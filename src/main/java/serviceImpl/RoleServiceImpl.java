package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

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
		RoleSendto ret = new RoleSendto();
		ret.setId(role.getId());
		return ret;
	}

	@Override
	public void delete(long id) {
		roleDao.delete(id);

	}

	@Override
	public RoleSendto save(RoleSendto role) {
		role.setId(null);
		Role roles = new Role();
		setUpRole(role, roles);
		roles = roleDao.save(roles);
		return toRoleSendto(roles);
	}

	@Override
	public Page<RoleSendto> findAll(Specification<Role> spec, Pageable pageable) {
		List<RoleSendto> sendto = new ArrayList<RoleSendto>();
		Page<Role> roles = roleDao.findAll(spec, pageable);
		for (Role role : roles) {
			sendto.add(toRoleSendto(role));
		}
		Page<RoleSendto> rets = new PageImpl<RoleSendto>(sendto, pageable, roles.getTotalElements());
		return rets;
	}

	@Override
	public RoleSendto update(long id, RoleSendto updated) {
		Role roles = roleDao.findOne(id);
		if (roles == null) {
			throw new RoleNotFoundException(id);
		}
		setUpRole(updated, roles);
		return toRoleSendto(roleDao.save(roles));
	}

	private void setUpRole(RoleSendto updated, Role roles) {
		// TODO Auto-generated method stub

	}
}

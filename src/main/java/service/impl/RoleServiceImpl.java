package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import sendto.RoleSendto;
import service.RoleService;
import dao.RoleDao;
import entity.Role;
import entity.RoleEnum;
import exceptions.RoleNotFoundException;

public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao;

	public RoleServiceImpl(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Transactional("transactionManager")
	@Override
	public RoleSendto retrieve(long id) {
		Role role = roleDao.findOne(id);
		if (role == null) {
			throw new RoleNotFoundException(id);
		}
		return toRoleSendto(role);
	}

	public static RoleSendto toRoleSendto(Role role) {
		RoleSendto ret = new RoleSendto();
		ret.setId(role.getId());
		ret.setValue(role.getValue().name());
		return ret;
	}

	@Transactional("transactionManager")
	@Override
	public void delete(long id) {
		try {
			Role role = roleDao.findOne(id);
			if (role == null) {
				throw new RoleNotFoundException(id);
			}
			roleDao.delete(role);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			throw new RoleNotFoundException(id);
		}
	}

	@Transactional("transactionManager")
	@Override
	public RoleSendto save(RoleSendto role) {
		role.setId(null);
		Role newEntry = new Role();
		setUpRole(role, newEntry);
		return toRoleSendto(roleDao.save(newEntry));
	}

	@Transactional("transactionManager")
	@Override
	public Page<RoleSendto> findAll(Specification<Role> spec, Pageable pageable) {
		List<RoleSendto> sendto = new ArrayList<RoleSendto>();
		Page<Role> roles = roleDao.findAll(spec, pageable);
		for (Role role : roles) {
			sendto.add(toRoleSendto(role));
		}
		Page<RoleSendto> rets = new PageImpl<RoleSendto>(sendto, pageable,
				roles.getTotalElements());
		return rets;
	}

	@Transactional("transactionManager")
	@Override
	public RoleSendto update(long id, RoleSendto updated) {
		Role roles = roleDao.findOne(id);
		if (roles == null) {
			throw new RoleNotFoundException(id);
		}
		setUpRole(updated, roles);
		return toRoleSendto(roleDao.save(roles));
	}

	private void setUpRole(RoleSendto sendto, Role newEntry) {
		if (sendto.isValueSet()) {
			newEntry.setValue(RoleEnum.valueOf(sendto.getValue()));
		}
	}
}

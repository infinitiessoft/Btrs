package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import sendto.RoleSendto;
import entity.Role;

public interface RoleService {

	public RoleSendto retrieve(Specification<Role> spec);

	// public void delete(long id);

	// public RoleSendto save(RoleSendto role);

	public Page<RoleSendto> findAll(Specification<Role> spec, Pageable pageable);

	// public RoleSendto update(Specification<Role> spec, RoleSendto role);

}

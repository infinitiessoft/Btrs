package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import entity.Role;
import sendto.RoleSendto;

public interface RoleService {

	public RoleSendto retrieve(long id);

	public void delete(long id);

	public RoleSendto save(RoleSendto role);

	public Page<RoleSendto> findAll(Specification<Role> spec, Pageable pageable);

	public RoleSendto update(long id, RoleSendto role);

}

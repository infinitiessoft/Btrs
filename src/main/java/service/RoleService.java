package service;

import java.util.Collection;

import sendto.RoleSendto;

public interface RoleService {

	public RoleSendto retrieve(long id);

	public void delete(long id);

	public RoleSendto save(RoleSendto role);

	public Collection<RoleSendto> findAll();

	public RoleSendto update(long id);

}

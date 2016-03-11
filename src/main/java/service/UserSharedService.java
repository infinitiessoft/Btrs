package service;

import java.util.Collection;

import sendto.UserSharedSendto;

public interface UserSharedService {

	public UserSharedSendto retrieve(long id);

	public void delete(long id);

	public UserSharedSendto save(UserSharedSendto userShared);

	public Collection<UserSharedSendto> findAll();

	public UserSharedSendto update(long id);

	public UserSharedSendto findByUsername(String username);

}

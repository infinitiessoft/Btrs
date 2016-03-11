package service;

import java.util.Collection;

import sendto.UserSendto;

public interface UserService {

	public UserSendto retrieve(long id);

	public void delete(long id);

	public UserSendto save(UserSendto user);

	public Collection<UserSendto> findAll();

	public UserSendto update(long id);

}

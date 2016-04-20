package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import resources.specification.UserSpecification;
import sendto.UserSendto;

public interface UserService {

	public UserSendto retrieve(long id);

	public void delete(long id);

	public UserSendto save(UserSendto user);

	public Page<UserSendto> findAll(UserSpecification spec, Pageable pageable);

	public UserSendto update(long id, UserSendto user);

	public UserSendto findByUserSharedId(long id);

}

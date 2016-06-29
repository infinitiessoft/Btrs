package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import resources.specification.UserSpecification;
import sendto.UserSendto;
import entity.User;

public interface UserService {

	public UserSendto retrieve(long id);

	public Page<UserSendto> findAll(UserSpecification spec, Pageable pageable);

	public UserSendto update(long id, UserSendto user);

	public User findOrSave(long userSharedId);
}

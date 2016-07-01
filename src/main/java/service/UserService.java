package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import sendto.UserSendto;
import entity.User;

public interface UserService {

	public UserSendto retrieve(Specification<User> spec);

	public Page<UserSendto> findAll(Specification<User> spec, Pageable pageable);

	public UserSendto update(Specification<User> spec, UserSendto user);

	// public User findOrSave(long userSharedId);
}

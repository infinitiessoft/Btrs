package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import resources.specification.UserSharedSpecification;
import sendto.UserSharedSendto;

public interface UserSharedService {

	public UserSharedSendto retrieve(long id);

	public void delete(long id);

	public UserSharedSendto save(UserSharedSendto userShared);

	public Page<UserSharedSendto> findAll(UserSharedSpecification spec, Pageable pageable);

	public UserSharedSendto update(long id, UserSharedSendto userShared);

	public UserSharedSendto findByUsername(String username);

}

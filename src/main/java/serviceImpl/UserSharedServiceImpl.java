package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import dao.UserSharedDao;
import entity.UserShared;
import exceptions.UserSharedNotFoundException;
import resources.specification.UserSharedSpecification;
import sendto.UserSharedSendto;
import service.UserSharedService;

public class UserSharedServiceImpl implements UserSharedService {

	private UserSharedDao userSharedDao;

	public UserSharedServiceImpl(UserSharedDao userSharedDao) {
		this.userSharedDao = userSharedDao;
	}

	@Transactional
	@Override
	public UserSharedSendto retrieve(long id) {
		UserShared userShared = userSharedDao.findOne(id);
		if (userShared == null) {
			throw new UserSharedNotFoundException(id);
		}
		return toUserSharedSendto(userShared);

	}

	private UserSharedSendto toUserSharedSendto(UserShared userShared) {
		UserSharedSendto ret = new UserSharedSendto();
		ret.setId(userShared.getId());
		ret.setUsername(userShared.getUsername());
		ret.setPassword(userShared.getPassword());
		ret.setJobTitle(userShared.getJobTitle());
		ret.setEmail(userShared.getEmail());
		ret.setGender(userShared.getGender());
		ret.setFirstName(userShared.getFirstName());
		ret.setLastName(userShared.getLastName());
		ret.setDateOfBirth(userShared.getDateofBirth());
		return ret;
	}

	@Transactional
	@Override
	public void delete(long id) {
		try {
			userSharedDao.delete(id);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			throw new UserSharedNotFoundException(id);
		}
	}

	@Transactional
	@Override
	public UserSharedSendto save(UserSharedSendto userShared) {
		userShared.setId(null);
		UserShared newEntry = new UserShared();
		setUpUserShared(userShared, newEntry);
		newEntry = userSharedDao.save(newEntry);
		return toUserSharedSendto(newEntry);
	}

	private void setUpUserShared(UserSharedSendto sendto, UserShared newEntry) {
		if (sendto.isCreatedDateSet()) {
			newEntry.setCreatedDate(sendto.getCreatedDate());
		}
		if (sendto.isDateOfBirthSet()) {
			newEntry.setDateofBirth(sendto.getDateOfBirth());
		}
		if (sendto.isEmailSet()) {
			newEntry.setEmail(sendto.getEmail());
		}
		if (sendto.isFirstNameSet()) {
			newEntry.setGender(sendto.getGender());
		}
		if (sendto.isJobTitleSet()) {
			newEntry.setJobTitle(sendto.getJobTitle());
		}
		if (sendto.isLastNameSet()) {
			newEntry.setLastName(sendto.getLastName());
		}
		if (sendto.isPasswordSet()) {
			newEntry.setPassword(sendto.getPassword());
		}

		if (sendto.isUsernameSet()) {
			newEntry.setUsername(sendto.getUsername());
		}
	}

	@Transactional
	@Override
	public Page<UserSharedSendto> findAll(UserSharedSpecification spec, Pageable pageable) {
		List<UserSharedSendto> sendto = new ArrayList<UserSharedSendto>();
		Page<UserShared> users = userSharedDao.findAll(spec, pageable);
		for (UserShared userShared : users) {
			sendto.add(toUserSharedSendto(userShared));
		}
		Page<UserSharedSendto> rets = new PageImpl<UserSharedSendto>(sendto, pageable, users.getTotalElements());
		return rets;
	}

	@Transactional
	@Override
	public UserSharedSendto update(long id, UserSharedSendto updated) {
		UserShared user = userSharedDao.findOne(id);
		if (user == null) {
			throw new UserSharedNotFoundException(id);
		}
		setUpUserShared(updated, user);
		return toUserSharedSendto(userSharedDao.save(user));
	}

	@Transactional
	@Override
	public UserSharedSendto findByUsername(String username) {
		return toUserSharedSendto(userSharedDao.findByUsername(username));

	}

}

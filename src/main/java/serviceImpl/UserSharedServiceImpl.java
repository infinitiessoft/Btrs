package serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.UserSharedDao;
import entity.UserShared;
import exceptions.UserSharedNotFoundException;
import sendto.UserSharedSendto;
import service.UserSharedService;

public class UserSharedServiceImpl implements UserSharedService {

	private UserSharedDao userSharedDao;

	public UserSharedServiceImpl(UserSharedDao userSharedDao) {
		this.userSharedDao = userSharedDao;
	}

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

	@Override
	public void delete(long id) {
		userSharedDao.delete(id);
	}

	@Override
	public UserSharedSendto save(UserSharedSendto userShared) {
		userShared.setId(null);
		UserShared user = new UserShared();
		user = userSharedDao.save(user);
		return toUserSharedSendto(user);
	}

	@Override
	public Collection<UserSharedSendto> findAll() {
		List<UserSharedSendto> sendto = new ArrayList<UserSharedSendto>();
		for (UserShared userShared : userSharedDao.findAll()) {
			sendto.add(toUserSharedSendto(userShared));
		}
		return sendto;
	}

	@Override
	public UserSharedSendto update(long id) {
		UserShared user = userSharedDao.findOne(id);
		if (user == null) {
			throw new UserSharedNotFoundException(id);
		}
		return toUserSharedSendto(userSharedDao.save(user));
	}

	@Override
	public UserSharedSendto findByUsername(String username) {
		return toUserSharedSendto(userSharedDao.findByUsername(username));

	}

}

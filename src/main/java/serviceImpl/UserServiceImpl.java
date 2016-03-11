package serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.UserDao;
import entity.User;
import entity.UserRole;
import exceptions.UserNotFoundException;
import sendto.UserRoleSendto;
import sendto.UserSendto;
import service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserSendto retrieve(long id) {
		User user = userDao.findOne(id);
		if (user == null) {
			throw new UserNotFoundException(id);
		}
		return toUserSendto(user);
	}

	private UserSendto toUserSendto(User user) {
		UserSendto ret = new UserSendto();
		ret.setId(user.getId());
		ret.setLastLogin(user.getLastLogin());
		ret.setUserSharedId(user.getUserSharedId());
		return ret;
	}

	@Override
	public void delete(long id) {
		userDao.delete(id);

	}

	@Override
	public UserSendto save(UserSendto user) {
		user.setId(null);
		User usr = new User();
		usr = userDao.save(usr);
		return toUserSendto(usr);
	}

	@Override
	public Collection<UserSendto> findAll() {
		List<UserSendto> sendto = new ArrayList<UserSendto>();
		for (User usr : userDao.findAll()) {
			sendto.add(toUserSendto(usr));
		}
		return sendto;
	}

	@Override
	public UserSendto update(long id) {
		User usr = userDao.findOne(id);
		if (usr == null) {
			throw new UserNotFoundException(id);
		}
		return toUserSendto(userDao.save(usr));
	}

	public static UserRoleSendto toUserSendto(UserRole userRole) {
		// TODO Auto-generated method stub
		return null;
	}

}

package serviceImpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import security.UserDetail;
import dao.UserSharedDao;
import entity.UserShared;

public class UserDetailServiceImpl implements UserDetailsService {

	private UserSharedDao userDao;

	public UserDetailServiceImpl(UserSharedDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserShared user = userDao.findByUsername(username);
		
		if (null == user) {
			throw new UsernameNotFoundException("The employee with username "
					+ username + " was not found");
		}
		return new UserDetail(user);
	}

}

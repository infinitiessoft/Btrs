package serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import dao.DepartmentDao;
import dao.UserDao;
import dao.UserSharedDao;
import entity.User;
import exceptions.DepartmentNotFoundException;
import exceptions.UserNotFoundException;
import exceptions.UserSharedNotFoundException;
import resources.specification.UserSpecification;
import sendto.UserSendto;
import service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private DepartmentDao departmentDao;
	private UserSharedDao userSharedDao;

	public UserServiceImpl(UserDao userDao, DepartmentDao departmentDao, UserSharedDao userSharedDao) {
		this.userDao = userDao;
		this.departmentDao = departmentDao;
		this.userSharedDao = userSharedDao;
	}

	@Transactional
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
		ret.setLastLogin(new Date());

		UserSendto.Department dept = new UserSendto.Department();
		dept.setId(user.getDepartment().getId());
		ret.setDepartment(dept);

		UserSendto.UserShared userShrd = new UserSendto.UserShared();
		userShrd.setId(user.getUserShared().getId());
		ret.setUserShared(userShrd);
		return ret;
	}

	@Transactional
	@Override
	public void delete(long id) {
		try {
			User user = userDao.findOne(id);
			if (user == null) {
				throw new UserNotFoundException(id);
			}
			userDao.delete(user);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			throw new UserNotFoundException(id);
		}

	}

	@Transactional
	@Override
	public UserSendto save(UserSendto user) {
		user.setId(null);
		User newEntry = new User();
		setUpUser(user, newEntry);
		return toUserSendto(userDao.save(newEntry));
	}

	@Transactional
	@Override
	public Page<UserSendto> findAll(UserSpecification spec, Pageable pageable) {
		List<UserSendto> sendto = new ArrayList<UserSendto>();
		Page<User> users = userDao.findAll(spec, pageable);
		for (User usr : users) {
			sendto.add(toUserSendto(usr));
		}
		Page<UserSendto> rets = new PageImpl<UserSendto>(sendto, pageable, users.getTotalElements());
		return rets;
	}

	@Transactional
	@Override
	public UserSendto update(long id, UserSendto updated) {
		User usr = userDao.findOne(id);
		if (usr == null) {
			throw new UserNotFoundException(id);
		}
		setUpUser(updated, usr);
		return toUserSendto(userDao.save(usr));
	}

	private void setUpUser(UserSendto sendto, User newEntry) {
		if (sendto.isLastLoginSet()) {
			newEntry.setLastLogin(sendto.getLastLogin());
		}
		if (sendto.isDepartmentSet()) {
			if (sendto.getDepartment().isIdSet()) {
				entity.Department department = departmentDao.findOne(sendto.getDepartment().getId());
				if (department == null) {
					throw new DepartmentNotFoundException(sendto.getDepartment().getId());
				}
				newEntry.setDepartment(department);
			}
		}
		if (sendto.isUserSharedSet()) {
			if (sendto.getUserShared().isIdSet()) {
				entity.UserShared userShared = userSharedDao.findOne(sendto.getUserShared().getId());
				if (userShared == null) {
					throw new UserSharedNotFoundException(sendto.getUserShared().getId());
				}
				newEntry.setUserShared(userShared);
			}
		}
	}

	@Override
	public UserSendto findByUserSharedId(long id) {
		return toUserSendto(userDao.findByUserSharedId(id));
	}

}

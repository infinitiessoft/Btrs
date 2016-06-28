package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import resources.specification.UserSpecification;
import sendto.UserSendto;
import service.UserService;
import dao.DepartmentDao;
import dao.UserDao;
import dao.UserSharedDao;
import entity.Gender;
import entity.User;
import entity.UserShared;
import exceptions.DepartmentNotFoundException;
import exceptions.InvalidGenderException;
import exceptions.UserNotFoundException;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private DepartmentDao departmentDao;
	private UserSharedDao userSharedDao;
	private PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserDao userDao, DepartmentDao departmentDao,
			UserSharedDao userSharedDao, PasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.departmentDao = departmentDao;
		this.userSharedDao = userSharedDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional("transactionManager")
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

		UserSendto.JobTitle jobTitle = new UserSendto.JobTitle();
		jobTitle.setId(user.getJobTitle().getId());
		jobTitle.setName(user.getJobTitle().getName());
		ret.setJobTitle(jobTitle);

		UserSendto.Department dept = new UserSendto.Department();
		dept.setId(user.getDepartment().getId());
		dept.setName(user.getDepartment().getName());
		ret.setDepartment(dept);

		UserSendto.UserShared userShrd = new UserSendto.UserShared();
		userShrd.setId(user.getUserShared().getId());
		userShrd.setEmail(user.getUserShared().getEmail());
		userShrd.setCreatedDate(user.getUserShared().getCreatedDate());
		userShrd.setFirstName(user.getUserShared().getFirstName());
		userShrd.setGender(user.getUserShared().getGender());
		userShrd.setLastName(user.getUserShared().getLastName());
		userShrd.setUsername(user.getUserShared().getUsername());
		ret.setUserShared(userShrd);

		return ret;
	}

	@Transactional("transactionManager")
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

	@Transactional("transactionManager")
	@Override
	public UserSendto save(UserSendto user) {
		user.setId(null);
		User newEntry = new User();
		setUpUser(user, newEntry);
		return toUserSendto(userDao.save(newEntry));
	}

	@Transactional("transactionManager")
	@Override
	public Page<UserSendto> findAll(UserSpecification spec, Pageable pageable) {
		List<UserSendto> sendto = new ArrayList<UserSendto>();
		Page<User> users = userDao.findAll(spec, pageable);
		for (User usr : users) {
			sendto.add(toUserSendto(usr));
		}
		Page<UserSendto> rets = new PageImpl<UserSendto>(sendto, pageable,
				users.getTotalElements());
		return rets;
	}

	@Transactional("transactionManager")
	@Override
	public UserSendto update(long id, UserSendto updated) {
		User usr = userDao.findOne(id);
		if (usr == null) {
			throw new UserNotFoundException(id);
		}
		setUpUser(updated, usr);
		return toUserSendto(userDao.save(usr));
	}

	private void setUpUser(UserSendto userSendto, User newEntry) {
		if (userSendto.isUserSharedSet()) {
			sendto.UserSendto.UserShared userShared = userSendto
					.getUserShared();
			UserShared userSharedEntry = newEntry.getUserShared();
			if (userShared.isCreatedDateSet()) {
				userSharedEntry.setCreatedDate(userShared.getCreatedDate());
			}
			if (userShared.isEmailSet()) {
				userSharedEntry.setEmail(userShared.getEmail());
			}
			if (userShared.isFirstNameSet()) {
				userSharedEntry.setFirstName(userShared.getFirstName());
			}
			if (userShared.isLastNameSet()) {
				userSharedEntry.setLastName(userShared.getLastName());
			}
			if (userShared.isUsernameSet()) {
				userSharedEntry.setUsername(userShared.getUsername());
			}
			if (userShared.isGenderSet()) {
				try {
					Gender.valueOf(userShared.getGender());
				} catch (Exception e) {
					throw new InvalidGenderException(userShared.getGender());
				}
				userSharedEntry.setGender(userShared.getGender());
			}
			if (userShared.isPasswordSet()) {
				userSharedEntry.setPassword(passwordEncoder.encode(userShared
						.getPassword()));
			}
		}
		if (userSendto.isDepartmentSet()) {
			if (userSendto.getDepartment().isIdSet()) {
				entity.Department department = departmentDao.findOne(userSendto
						.getDepartment().getId());
				if (department == null) {
					throw new DepartmentNotFoundException(userSendto
							.getDepartment().getId());
				}
				newEntry.setDepartment(department);
			}
		}
	}

}
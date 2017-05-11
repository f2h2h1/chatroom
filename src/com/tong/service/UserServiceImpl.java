package com.tong.service;

import java.util.List;

import com.tong.dao.IUserDao;
import com.tong.dao.UserDaoImpl;
import com.tong.javabean.User;

public class UserServiceImpl implements IUserService {
	private IUserDao dao = new UserDaoImpl();

	@Override
	public User login(String email, String password) {
		return dao.selectUserByInfo(email, password);
	}

	@Override
	public int saveUser(User user) {
		return dao.addUser(user);
	}

	@Override
	public int modefyUser(User user) {
		return dao.updateUser(user);
	}

	@Override
	public int removeUser(String email) {
		return dao.deleteUser(email);
	}

	@Override
	public User findUserById(String email) {
		return dao.selectUserById(email);
	}

	@Override
	public List<User> findAllUser() {
		return dao.selectAllUser();
	}

	@Override
	public int register(String email, String username, String passworda, String passwordb) {
		int result;
		User user;
		if (passworda.equals(passwordb)) {
			user = findUserById(email);
			if (user == null) {
				user = new User();
				user.setEmail(email);
				user.setPassword(passworda);
				user.setUsername(username);
				user.setFriend(null);
				user.setGroup_c(null);
				if (saveUser(user) >= 1) {
					result = 1;
				} else {
					result = 2;
				}
			} else {
				result = 3;
			}
		} else {
			result = 4;
		}
		return result;
	}

}

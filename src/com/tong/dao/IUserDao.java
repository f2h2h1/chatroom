package com.tong.dao;

import java.util.List;

import com.tong.javabean.User;

public interface IUserDao {

	User selectUserByInfo(String logonName, String logonPwd);

	int addUser(User user);

	int updateUser(User user);

	int deleteUser(String email);

	User selectUserById(String email);

	List<User> selectAllUser();

}

package com.tong.service;

import java.util.List;

import com.tong.javabean.User;

public interface IUserService {
	// 用户注册
	int register(String email, String username, String passworda, String passwordb);
	// 用户登录(用过用户名和密码在数据库中搜索是否存在这样一个用户)
	User login(String email, String password);
	// 保存用户
	int saveUser(User user);
	// 修改用户
	int modefyUser(User user);
	// 删除用户(根据用户ID删除用户)
	int removeUser(String email);
	// 根据ID查询用户
	User findUserById(String email);
	// 查询所有用户
	List<User> findAllUser();
}

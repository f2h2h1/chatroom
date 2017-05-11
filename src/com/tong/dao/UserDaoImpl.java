package com.tong.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.tong.javabean.User;
import com.tong.utils.JNDIUtil;

public class UserDaoImpl implements IUserDao{
	private QueryRunner qr = new QueryRunner(JNDIUtil.getDataSource());
	@Override
	public User selectUserByInfo(String email, String password) {
		String sql = "select * from user_index where email = ? and password = ?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), email, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int addUser(User user) {
		String sql = "insert into user_index(email, password, username, friend, group_c) value(?,?,?,?,?)";
		try {
			return qr.update(sql, user.getEmail(), user.getPassword(), user.getUsername(), user.getFriend(), user.getGroup_c());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateUser(User user) {
		String sql = "update user_index set password = ?, username = ?, friend = ?, group_c = ? where email = ?";
		try {
			return qr.update(sql, user.getPassword(), user.getUsername(), user.getFriend(), user.getGroup_c(), user.getEmail());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteUser(String email) {
		String sql = "delete from user_index where email = ?";
		try {
			return qr.update(sql, 
					email);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User selectUserById(String email) {
		String sql = "select * from user_index where email = ?";
		try {
			return qr.query(sql, 
					new BeanHandler<User>(User.class), 
					email);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<User> selectAllUser() {
		String sql = "select * from user_index";
		try {
			return qr.query(sql, 
					new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}

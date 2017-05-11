package com.tong.service;

import java.util.List;

import com.tong.javabean.User;

public interface IUserService {
	// �û�ע��
	int register(String email, String username, String passworda, String passwordb);
	// �û���¼(�ù��û��������������ݿ��������Ƿ��������һ���û�)
	User login(String email, String password);
	// �����û�
	int saveUser(User user);
	// �޸��û�
	int modefyUser(User user);
	// ɾ���û�(�����û�IDɾ���û�)
	int removeUser(String email);
	// ����ID��ѯ�û�
	User findUserById(String email);
	// ��ѯ�����û�
	List<User> findAllUser();
}

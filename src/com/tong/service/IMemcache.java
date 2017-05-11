package com.tong.service;

import com.tong.javabean.User;

public interface IMemcache {
	void set(String key, Object value);
	Object get(String key);
	void del(String key);
	void set(String id, User user);
}

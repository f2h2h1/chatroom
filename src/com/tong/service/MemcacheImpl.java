package com.tong.service;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.tong.javabean.User;

import net.spy.memcached.MemcachedClient;

public class MemcacheImpl implements IMemcache {
	public MemcachedClient mcc;
	
	public MemcacheImpl() {
		try {
			mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void set(String key, Object value) {
		mcc.set(key, 0, value);
	}

	@Override
	public Object get(String key) {
		return mcc.get(key);
	}

	@Override
	public void del(String key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void set(String id, User user) {
		// TODO Auto-generated method stub
		
	}

}

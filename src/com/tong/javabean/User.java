package com.tong.javabean;


public class User {
	private String webSocketId;
	private String sessionId;
	private int id;
	private String email;
	private String password;
	private String username;
	private String friend;
	private String group_c;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFriend() {
		return friend;
	}
	public void setFriend(String friend) {
		this.friend = friend;
	}
	public String getGroup_c() {
		return group_c;
	}
	public void setGroup_c(String group_c) {
		this.group_c = group_c;
	}
	public String getWebSocketId() {
		return webSocketId;
	}
	public void setWebSocketId(String webSocketId) {
		this.webSocketId = webSocketId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	@Override
	public String toString() {
		return "User [webSocketId=" + webSocketId + ", sessionId=" + sessionId + ", id=" + id + ", email=" + email
				+ ", password=" + password + ", username=" + username + ", friend=" + friend + ", group_c=" + group_c
				+ "]";
	}
}

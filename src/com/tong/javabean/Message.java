package com.tong.javabean;

public class Message {
	private int type;
	private String fromId;
	private String toId;
	private String fromName;
	private String toName;
	private String text;
	private String date;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Message [type=" + type + ", fromId=" + fromId + ", toId=" + toId + ", fromName=" + fromName
				+ ", toName=" + toName + ", text=" + text + ", date=" + date + "]";
	}

}

package com.tong.javabean;

public class Onlinemap {
	private static String[][] onlineList;

	public Onlinemap(){
		if (Onlinemap.onlineList==null) {
			onlineList= new String[99][4];
		}
	}
	
	public static String[][] getOnlineList() {
		return onlineList;
	}

	public static void setOnlineList(String[][] onlineList) {
		Onlinemap.onlineList = onlineList;
	}
	public int searchWebsocketId(String websocketId) {
		for(int i = 0; i < onlineList.length; i++)
		{
			if (onlineList[i][0] != null && onlineList[i][3].equals(websocketId)) {
				return i;
			}
		}
		return 1;
	}
	public int searchUserId(String userId) {
		for(int i = 0; i < onlineList.length; i++)
		{
			if (onlineList[i][0] != null && onlineList[i][0].equals(userId)) {
				return i;
			}
		}
		return -1;
	}
}

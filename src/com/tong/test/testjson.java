package com.tong.test;

import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class testjson {
	public static void main(String[] args) {
		
		JSONObject studentJSONObject2 = new JSONObject();
		try {
//			String[] c={"0","大厅","daiting@java.com"};
//			studentJSONObject2.put("type", 0);
//			studentJSONObject2.put("userList", c);
			studentJSONObject2.put("type", 2);
			studentJSONObject2.put("userId", 1999);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println("发送消息"+studentJSONObject2.toString());
	}
	
}

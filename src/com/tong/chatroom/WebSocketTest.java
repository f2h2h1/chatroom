package com.tong.chatroom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.CopyOnWriteArraySet;

import java.util.Arrays;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.tong.javabean.Message;
import com.tong.javabean.Onlinemap;
import com.tong.javabean.User;

import net.spy.memcached.MemcachedClient;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 *                 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/websocket")
public class WebSocketTest {
	
	private static String[][] webSocketSeeionList;
	
	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;

	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	private static CopyOnWriteArraySet<WebSocketTest> webSocketSet = new CopyOnWriteArraySet<WebSocketTest>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	public static CopyOnWriteArraySet<WebSocketTest> getWebSocketSet() {
		return webSocketSet;
	}

	public Session getSession() {
		return session;
	}

	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param session
	 *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		webSocketSet.add(this); // 加入set中
		addOnlineCount(); // 在线数加1
		System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		System.out.println("客户端 " + session.toString() + "下线");
		
		Onlinemap onLineMap = new Onlinemap();
		String[][] d = Onlinemap.getOnlineList();
		int i;
		String userId;
		int flg;
		flg = onLineMap.searchWebsocketId(session.toString());
		if (flg != -1) {
			userId=d[flg][0];
			d[flg][0]=null;
			d[flg][1]=null;
			d[flg][2]=null;
			d[flg][3]=null;
			System.out.println(Arrays.toString(d[flg]));
			Onlinemap.setOnlineList(d);
			for (WebSocketTest item : webSocketSet) {
				if(!item.session.toString().equals(session.toString())){
					JSONObject studentJSONObject2 = new JSONObject();
					try {
						
						studentJSONObject2.put("type", 2);
						studentJSONObject2.put("userId", userId);

					} catch (JSONException e) {
						e.printStackTrace();
					}
					sending(studentJSONObject2.toString(), item);
				}
			}
		}
		webSocketSet.remove(this); // 从set中删除
		subOnlineCount(); // 在线数减1
		System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message
	 *            客户端发送过来的消息
	 * @param session
	 *            可选的参数
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("来自客户端 " + session.toString() + " 的消息:" + message);
		querenyonghudenglu(message, session);
	}

	private void querenyonghudenglu(String message, Session session) {
		JSONTokener jsonTokener = new JSONTokener(message);
		JSONObject studentJSONObject;
		int i;
		
		int type;
		
		String sessionId;
		String webSocketId;

		String fromId;
		String toId;
		String fromName;
		String toName;
		String text;
		String date;
		int userId;
		
		Message m;
		MemcachedClient mcc;
		
		//Onlinemap onLineMap = new Onlinemap();
		String[][] d = Onlinemap.getOnlineList();
		
		try {
			studentJSONObject = (JSONObject) jsonTokener.nextValue();
			type = studentJSONObject.getInt("type");
			System.out.println("来自客户端 " + session.toString() + " 的消息类型type:" + type);
			
			
			// 判断消息类型
			if (type == 0) {
				sessionId = studentJSONObject.getString("sessionId");
				userId = studentJSONObject.getInt("userId");

				for(i=0;i<d.length;i++)
				{
					if (d[i][0] != null && d[i][0].equals(String.valueOf(userId))) {
						d[i][3] = session.toString();
						break;
					}
				}
				Onlinemap.setOnlineList(d);
				System.out.println(Arrays.toString(d[i]));
				
				for (WebSocketTest item : webSocketSet) {
					
						System.out.println("item.session.toString():"+item.session.toString()+" session.toString():"+session.toString());
						if(!item.session.toString().equals(session.toString())){
							JSONObject studentJSONObject2 = new JSONObject();
							try {
								
								studentJSONObject2.put("type", 0);
								//studentJSONObject2.put("userList", c);
								studentJSONObject2.put("userList", d[i]);

							} catch (JSONException e) {
								e.printStackTrace();
							}
							sending(studentJSONObject2.toString(), item);
						}else{
							JSONObject studentJSONObject2 = new JSONObject();
							try {
								
								studentJSONObject2.put("type", 3);
								studentJSONObject2.put("userList", d);

							} catch (JSONException e) {
								e.printStackTrace();
							}

							sending(studentJSONObject2.toString(), item);
						}
				}
				
			} else if(type==1){
				toId = String.valueOf(studentJSONObject.getInt("toId"));
				System.out.println("toId: "+toId);
				
				if (!toId.equals("0")) {
					for(i=0;i<d.length;i++)
					{
						if (d[i][0] != null && d[i][0].equals(String.valueOf(toId))) {
							
							System.out.println(Arrays.toString(d[i]));
							privateSending(message, d[i][3]);
							break;
						}
					}
				} else {
					groupSending(message);
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发生错误时调用
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}

	/**
	 * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
		// this.session.getAsyncRemote().sendText(message);
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocketTest.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocketTest.onlineCount--;
	}
	
	public JSONObject getJsonFromString(String message) {
		JSONTokener jsonTokener = new JSONTokener(message);
		JSONObject studentJSONObject;
		studentJSONObject = (JSONObject) jsonTokener.nextValue();
		return studentJSONObject;
	}
	public void sending(String message, WebSocketTest item) {
		System.out.println("发送消息"+message+"  "+item.session.toString());
		try {
			item.sendMessage(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void groupSending(String message) {
		for (WebSocketTest item : webSocketSet) {
			sending(message, item);
		}
	}
	public void privateSending(String message, String sessionStr) {
		for (WebSocketTest item : webSocketSet) {
			if (item.session.toString().equals(sessionStr)) {
				sending(message, item);
				break;
			}
		}
	}
	
	
//	public String createJsonString(Message message) {
//		
//		JSONObject studentJSONObject2 = new JSONObject();
//		try {
//			studentJSONObject2.put("type", message.getType());
//			studentJSONObject2.put("from", message.getFrom());
//			studentJSONObject2.put("msg", message.getMsg());
//			studentJSONObject2.put("to", message.getTo());
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		
//		return studentJSONObject2.toString();
//	}
	
}
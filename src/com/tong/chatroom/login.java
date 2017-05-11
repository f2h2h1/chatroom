package com.tong.chatroom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.concurrent.Future;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.tong.javabean.Onlinemap;
import com.tong.javabean.User;
import com.tong.service.IMemcache;
import com.tong.service.IUserService;
import com.tong.service.MemcacheImpl;
import com.tong.service.UserServiceImpl;

import net.spy.memcached.MemcachedClient;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.getWriter().append("<script>alert('登录失败，该用户已在线');history.back();</script>").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(email);
		System.out.println(password);
		User user = new User();
		IUserService userService = new UserServiceImpl();
		user = userService.login(email, password);
		Onlinemap onLineMap = new Onlinemap();
		if (user != null) {
			String[][] c = Onlinemap.getOnlineList();
			String userIdStr = String.valueOf(user.getId());
			int i;
			int flg;
			// 判断用户是否已登录
			flg = onLineMap.searchUserId(userIdStr);
			if (flg != -1) {
				WebSocketTest websockettest = new WebSocketTest();
				for (WebSocketTest item : WebSocketTest.getWebSocketSet()) {
					if(item.getSession().toString().equals(c[flg][3])) {
						item.onClose();
					}
				}
				System.out.println("该用户已在线，将已在线的用户踢下线");
			}
			

			String[] a = {userIdStr, user.getUsername(), user.getEmail()};
			HttpSession session = request.getSession();
			session.setAttribute("data", user);
			
			for(i = 0; i < c.length; i++) {
				if (c[i][0] == null) {
					c[i][0] = a[0];
					c[i][1] = a[1];
					c[i][2] = a[2];
					break;
				}
			}
			Onlinemap.setOnlineList(c);
			
			System.out.println("登录成功");
			request.getRequestDispatcher("/test3.jsp").forward(request, response);

		} else {
			System.out.println("登录失败");
			response.sendRedirect("/TestLogin1/index.jsp");
		}
	}

}

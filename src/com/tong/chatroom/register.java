package com.tong.chatroom;

import java.io.IOException;
//import java.net.Authenticator.RequestorType;
//import java.util.Enumeration;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.tong.javabean.User;
import com.tong.service.IUserService;
import com.tong.service.UserServiceImpl;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
//		response.getWriter().append(request.getParameter("email")).append("\n");
//		response.getWriter().append(request.getParameter("username")).append("\n");
//		response.getWriter().append(request.getParameter("passworda")).append("\n");
//		response.getWriter().append(request.getParameter("passwordb")).append("\n");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String passworda = request.getParameter("password");
		String passwordb = request.getParameter("passwordb");
//		User user = new User();
		IUserService userService = new UserServiceImpl();
		int result = userService.register(email, username, passworda, passwordb);
		if (result == 1) {
			System.out.println("注册成功");
			request.getRequestDispatcher("/index.jsp").forward(request, response); 
			//response.sendRedirect("/TestLogin1/chat/Login/index.jsp");
		} else {
			System.out.println("注册失败");System.out.println(result);
			response.sendRedirect("/TestLogin1/register.jsp");
		}
		
		
		
		
//        request.setCharacterEncoding("UTF-8");
//
//        Enumeration<String> names = request.getParameterNames();
//        while (names.hasMoreElements()) {
//            String strings = (String) names.nextElement();
//            String[] parameterValues = request.getParameterValues(strings);
//            for (int i = 0;parameterValues!=null&&i < parameterValues.length; i++) {
//                System.out.println(strings+":"+parameterValues[i]+"\t");
//            }
//        }
        doGet(request, response);
	}

}

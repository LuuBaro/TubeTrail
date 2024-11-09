package com.asm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/account/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.invalidate(); // Hủy session
	        }

	        // Xóa cookie nếu cần
//	        Cookie[] cookies = request.getCookies();
//	        if (cookies != null) {
//	            for (Cookie cookie : cookies) {
//	                if ("username".equals(cookie.getName()) || "password".equals(cookie.getName())) {
//	                    cookie.setMaxAge(0);
//	                    response.addCookie(cookie);
//	                }
//	            }
//	        }

	        // Chuyển hướng về trang đăng nhập hoặc trang chính
	        response.sendRedirect(request.getContextPath() + "/main");
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

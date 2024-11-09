package com.asm.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asm.entity.User;

@WebFilter({"/ASM_1/yeuthich","/ASM_1/capnhat","/ASM_1/videodatil", "/admin/*"})
public class AuthFilter implements HttpFilter{

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		User user = (User) request.getSession().getAttribute("user");
		String error = "";
		if (user == null) {
			error = "401";
		}else if (!user.getAdmin() && uri.contains("admin")) {
			error = "404";
		}
		
		if (error.isEmpty()) {
			try {
				chain.doFilter(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}else {
			try {
				response.sendRedirect("/ASM_1/account/dangnhap?error=" + error);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

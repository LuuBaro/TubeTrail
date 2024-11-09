package com.asm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.asm.dao.UserDAO;
import com.asm.entity.User;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao = new UserDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
//
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String uri = req.getRequestURI();
//		if (uri.contains("dangnhap")) {
//			this.doSignIn(req, resp);
//		} else if (uri.contains("dangky")) {
//			this.doSignUp(req, resp);
//		} else if (uri.contains("capnhat")) {
//			this.doEditProfile(req, resp);
//		} else if (uri.contains("doimatkhau")) {
//			this.doChangePassword(req, resp);
//		}
//	}
//
//	private void doSignIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String method = req.getMethod();
//		if (method.equalsIgnoreCase("POST")) {
//
//			String id = req.getParameter("username");
//			String pw = req.getParameter("password");
//
//			if (id == null || id.isEmpty() || pw == null || pw.isEmpty()) {
//				req.setAttribute("message", "Tên đăng nhập và mật khẩu không được để trống!");
//			} else {
//				try {
//					req.setCharacterEncoding("UTF-8");
//					resp.setCharacterEncoding("UTF-8");
//					UserDAO dao = new UserDAO();
//					User user = dao.findById(id);
//					if (user == null || !user.getPassword().equals(pw)) {
//						req.setAttribute("message", "Sai tên đăng nhập hoặc mật khẩu!");
//					} else {
//						req.setAttribute("message", "Đăng nhập thành công!");
//						req.getSession().setAttribute("user", user);
//					}
//				} catch (Exception e) {
//					req.setAttribute("message", "Lỗi đăng nhập!");
//					e.printStackTrace();
//				}
//			}
//		}
//		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
//	}
//
//	private void doSignUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String method = req.getMethod();
//		if (method.equalsIgnoreCase("POST")) {
//			String id = req.getParameter("id");
//			String pw = req.getParameter("password");
//			String email = req.getParameter("email");
//			String fullname = req.getParameter("fullName");
//			if (id == null || id.isEmpty() || pw == null || pw.isEmpty() || email == null || email.isEmpty()
//					|| fullname == null || fullname.isEmpty()) {
//				req.setAttribute("message", "Tất cả các trường đều phải được điền!");
//			} else {
//				try {
//					req.setCharacterEncoding("UTF-8");
//					resp.setCharacterEncoding("UTF-8");
//
//					UserDAO dao = new UserDAO();
//					User existingUser = dao.findById(id);
//					if (existingUser != null) {
//						req.setAttribute("message", "Tên đăng nhập đã tồn tại!");
//					} else {
//						User entity = new User();
//						BeanUtils.populate(entity, req.getParameterMap());
//						dao.create(entity);
//						req.setAttribute("message", "Đăng ký thành công!");
//					}
//				} catch (Exception e) {
//					req.setAttribute("message", "Lỗi đăng ký!");
//					e.printStackTrace();
//				}
//			}
//		}
//		req.getRequestDispatcher("/views/dangky.jsp").forward(req, resp);
//	}
//	
//	
//
//    private void doEditProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User user = (User) req.getSession().getAttribute("user");
//
//        if (user == null) {
//            resp.sendRedirect(req.getContextPath() + "/dangnhap");
//            return;
//        }
//
//        String method = req.getMethod();
//        if (method.equalsIgnoreCase("POST")) {
//            try {
//                req.setCharacterEncoding("UTF-8");
//                resp.setCharacterEncoding("UTF-8");
//
//                BeanUtils.populate(user, req.getParameterMap());
//
//                UserDAO dao = new UserDAO();
//                dao.update(user);
//
//                req.getSession().setAttribute("user", user);
//                req.setAttribute("message", "Cập nhật tài khoản thành công!");
//            } catch (Exception e) {
//                req.setAttribute("message", "Lỗi cập nhật tài khoản!");
//                e.printStackTrace();
//            }
//        }
//
//        req.setAttribute("user", user);
//        req.getRequestDispatcher("/views/capnhattk.jsp").forward(req, resp);
//    }
//
//    
//    private void doChangePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User user = (User) req.getSession().getAttribute("user");
//
//        if (user == null) {
//            resp.sendRedirect(req.getContextPath() + "/dangnhap");
//            return;
//        }
//
//        String method = req.getMethod();
//        if (method.equalsIgnoreCase("POST")) {
//            String currentPassword = req.getParameter("currentPassword");
//            String newPassword = req.getParameter("newPassword");
//            String confirmPassword = req.getParameter("confirmPassword");
//
//            if (currentPassword == null || newPassword == null || confirmPassword == null ||
//                currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
//                req.setAttribute("message", "Tất cả các trường đều phải được điền!");
//            } else if (!user.getPassword().equals(currentPassword)) {
//                req.setAttribute("message", "Mật khẩu hiện tại không chính xác!");
//            } else if (!newPassword.equals(confirmPassword)) {
//                req.setAttribute("message", "Mật khẩu mới và xác nhận mật khẩu không khớp!");
//            } else {
//                try {
//                    req.setCharacterEncoding("UTF-8");
//                    resp.setCharacterEncoding("UTF-8");
//
//                    user.setPassword(newPassword);
//
//                    UserDAO dao = new UserDAO();
//                    dao.update(user);
//
//                    req.getSession().setAttribute("user", user);
//                    req.setAttribute("message", "Đổi mật khẩu thành công!");
//                } catch (Exception e) {
//                    req.setAttribute("message", "Lỗi đổi mật khẩu!");
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        req.getRequestDispatcher("/views/doimk.jsp").forward(req, resp);
//    }

}

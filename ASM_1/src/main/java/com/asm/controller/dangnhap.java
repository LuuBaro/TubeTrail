package com.asm.controller;

import java.io.IOException;

import java.util.Properties;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.beanutils.BeanUtils;

import com.asm.dao.UserDAO;
import com.asm.entity.User;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Servlet implementation class dangnhap
 */
@WebServlet({ "/account/dangnhap", "/account/dangky", "/account/capnhat", "/account/quenmatkhau" })
public class dangnhap extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EntityManagerFactory factory;

	private static final String FROM_EMAIL = "luubaoa6@gmail.com"; // Email gửi
	private static final String PASSWORD = "dmgi mwof ldmn fvqk"; // Mật khẩu của email gửi

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public dangnhap() {
		super();
		factory = Persistence.createEntityManagerFactory("ASM_1");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if (uri.contains("dangnhap")) {
			this.doSignIn(req, resp);
		} else if (uri.contains("dangky")) {
			this.doSignUp(req, resp);
		} else if (uri.contains("capnhat")) {
			this.doEditProfile(req, resp);
		} else if (uri.contains("quenmatkhau")) {
			this.doChangePassword(req, resp);
		}
	}

	private void doSignIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		if (method.equalsIgnoreCase("POST")) {
			String id = req.getParameter("id");
			String pw = req.getParameter("password");
			String rememberMe = req.getParameter("rememberMe");

			if (id == null || id.isEmpty() || pw == null || pw.isEmpty()) {
				req.setAttribute("message", "Tên đăng nhập và mật khẩu không được để trống!");
			} else {
				try {
					req.setCharacterEncoding("UTF-8");
					resp.setCharacterEncoding("UTF-8");
					UserDAO dao = new UserDAO();
					User user = dao.findById(id);
					if (user == null || !user.getPassword().equals(pw)) {
						req.setAttribute("message", "Sai tên đăng nhập hoặc mật khẩu!");
					} else {
						req.setAttribute("message", "Đăng nhập thành công!");
						req.getSession().setAttribute("user", user);

						// Xử lý cookie ghi nhớ tài khoản
						if ("on".equals(rememberMe)) {
							Cookie userCookie = new Cookie("username", id);
							userCookie.setMaxAge(30 * 24 * 60 * 60); // 30 days
							resp.addCookie(userCookie);
						} else {
							// Xóa cookie nếu không check Remember me
							Cookie userCookie = new Cookie("username", null);
							userCookie.setMaxAge(0);
							resp.addCookie(userCookie);
						}

						// Chuyển hướng tùy theo quyền hạn của người dùng
						String redirectAfterLogin = (String) req.getSession().getAttribute("redirectAfterLogin");
						if (redirectAfterLogin != null) {
							req.getSession().removeAttribute("redirectAfterLogin");
							resp.sendRedirect(req.getContextPath() + redirectAfterLogin);
						} else {
							req.getSession().setAttribute("loggedIn", true);
							resp.sendRedirect(req.getContextPath() + "/main");
						}
						return; // Dừng việc thực hiện tiếp theo của phương thức
					}
				} catch (Exception e) {
					req.setAttribute("message", "Lỗi đăng nhập!");
					e.printStackTrace();
				}
			}
		}

		// Đọc cookie để kiểm tra và tự động điền vào form nếu có
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("username".equals(cookie.getName())) {
					req.setAttribute("savedUsername", cookie.getValue());
				}
			}
		}

		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}

	private void doSignUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		req.setCharacterEncoding("UTF-8");
		if (method.equalsIgnoreCase("POST")) {
			String id = req.getParameter("id");
			String pw = req.getParameter("password");
			String email = req.getParameter("email");
			String fullname = req.getParameter("fullName");

			if (id == null || id.isEmpty() || pw == null || pw.isEmpty() || email == null || email.isEmpty()
					|| fullname == null || fullname.isEmpty()) {
				req.setAttribute("message", "Tất cả các trường đều phải được điền!");
			} else {
				try {
					req.setCharacterEncoding("UTF-8");
					resp.setCharacterEncoding("UTF-8");

					UserDAO dao = new UserDAO();
					User existingUser = dao.findById(id);
					if (existingUser != null) {
						req.setAttribute("message", "Tên đăng nhập đã tồn tại!");
					} else {
						User entity = new User();
						entity.setAdmin(false);
						BeanUtils.populate(entity, req.getParameterMap());
						dao.create(entity);
						req.getSession().setAttribute("user", entity); // Đăng nhập tự động sau khi đăng ký thành công
						req.setAttribute("message", "Đăng ký thành công!");
						req.getRequestDispatcher("/views/notification.jsp").forward(req, resp); // Chuyển tiếp đến trang
																								// thông báo
						return; // Dừng thực hiện các bước tiếp theo
					}
				} catch (Exception e) {
					req.setAttribute("message", "Lỗi đăng ký!");
					e.printStackTrace();
				}
			}
		}
		req.getRequestDispatcher("/views/dangky.jsp").forward(req, resp);
	}

	

	private void doEditProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		UserDAO userDAO = new UserDAO();

		if ("updateUser".equals(action)) {
			// Retrieve user data from request
			String userId = req.getParameter("userId");
			String oldPassword = req.getParameter("password-old");
			String newPassword = req.getParameter("password-new");
			String fullname = req.getParameter("fullname");
			String email = req.getParameter("email");

			// Check if userId is provided
			if (userId != null && !userId.isEmpty()) {
				// Find the existing user in the database
				User existingUser = userDAO.findById(userId);

				if (existingUser != null) {
					// Check if the old password is correct
					if (oldPassword == null || oldPassword.isEmpty()
							|| !existingUser.getPassword().equals(oldPassword)) {
						req.setAttribute("errorMessage", "Mật khẩu cũ không chính xác!");
						req.getRequestDispatcher("/views/capnhattk.jsp").forward(req, resp);
						return;
					}

					// Update user data
					existingUser.setFullName(fullname);
					existingUser.setEmail(email);

					// Update password if new password is provided
					if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals(oldPassword)) {
						existingUser.setPassword(newPassword); // Ensure password is hashed if necessary
					}

					// Update user in the database
					userDAO.update(existingUser);

					// Clear the session and redirect to login page or any other page
					req.getSession().invalidate(); // Invalidate the session
					resp.sendRedirect(req.getContextPath() + "/account/dangnhap?message=UpdateSuccess");
					return;
				} else {
					// Handle the case where the user is not found
					req.setAttribute("errorMessage", "Người dùng không tồn tại!");
					req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
				}
			} else {
				// Handle the case where userId is missing
				req.setAttribute("errorMessage", "ID người dùng không được cung cấp!");
				req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
			}
		}
	}
	
	
	private void sendPasswordEmail(String toEmail, String password) {
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.required", "true");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM_EMAIL));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject("Mật khẩu của bạn");
			message.setText("Mật khẩu của bạn là: " + password);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private void doChangePassword(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
	    String username = req.getParameter("username");
	    String email = req.getParameter("email");
	    UserDAO userDao = new UserDAO();
	    try {
	        User user = userDao.findById(username);
	        if (user != null && user.getEmail().equals(email)) {
	            sendPasswordEmail(email, user.getPassword());	
	            req.getSession().setAttribute("message", "Mật khẩu đã được gửi đến email của bạn.");
	            resp.sendRedirect(req.getContextPath() + "/views/quenmk.jsp");
	        } else {
	            req.getSession().setAttribute("message", "Tên đăng nhập hoặc email không đúng!");
	            resp.sendRedirect(req.getContextPath() + "/views/quenmk.jsp");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        req.getSession().setAttribute("message", "");
	        resp.sendRedirect(req.getContextPath() + "/views/quenmk.jsp");
	    }
	}


}

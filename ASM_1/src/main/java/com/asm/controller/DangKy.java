package com.asm.controller;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.asm.dao.UserDAO;
import com.asm.entity.User;
import com.asm.utils.JpaUtil;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

/**
 * Servlet implementation class DangKy
 */
@WebServlet("/dangky/register")
public class DangKy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DangKy() {
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
		request.getRequestDispatcher("/views/dangky_demo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		
		
		 String method = req.getMethod();
		    if (method.equalsIgnoreCase("POST")) {
		        String id = req.getParameter("id");
		        String pw = req.getParameter("password");
		        String email = req.getParameter("email");
		String fullname = req.getParameter("fullName");
		        if (id == null || id.isEmpty() || pw == null || pw.isEmpty() || email == null || email.isEmpty()|| fullname==null || fullname.isEmpty()) {
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
		                    BeanUtils.populate(entity, req.getParameterMap());
		                    dao.create(entity);
		                    req.setAttribute("message", "Đăng ký thành công!");
		                }
		            } catch (Exception e) {
		                req.setAttribute("message", "Lỗi đăng ký!");
		                e.printStackTrace();
		            }
		        }
		    }
		    req.getRequestDispatcher("/views/dangky_demo.jsp").forward(req, resp);
	
	}
		
		
		
		
		
		
		
		
		
		
		
//	    EntityManagerFactory factory = Persistence.createEntityManagerFactory("ASM_1");
//	    EntityManager entityManager = factory.createEntityManager();
//	    
//	    String id = request.getParameter("id");
//	    String email = request.getParameter("email");
//	    String fullName = request.getParameter("fullName");
//	    String password = request.getParameter("password");
//	 
//
//	    User newUser = new User();
//	    newUser.setId(id);
//	    newUser.setEmail(email);
//	    newUser.setFullName(fullName);
//	    newUser.setPassword(password);
//	
//
//	    EntityTransaction transaction = entityManager.getTransaction();
//	    try {
//	        transaction.begin();
//	        entityManager.persist(newUser);
//	        transaction.commit();
//	        request.setAttribute("successMessage", "Đăng ký thành công! Bạn có thể đăng nhập.");
//	        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
//	    } catch (Exception e) {
//	        if (transaction.isActive()) {
//	            transaction.rollback();
//	        }
//	        e.printStackTrace();
//	        request.setAttribute("errorMessage", "Đã xảy ra lỗi khi đăng ký: " + e.getMessage());
//	        request.getRequestDispatcher("/views/dangky.jsp").forward(request, response);
//	    } finally {
//	        entityManager.close();
//	    }
//	}
//
//	        
	        
	        
	        
//	        try {
//	            // Kiểm tra xem người dùng có tồn tại không
//	        	String jpql = "SELECT u FROM User u WHERE u.id = :username";
//
//	            TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
//	            query.setParameter("username", username);
//	            User existingUser = query.getResultStream().findFirst().orElse(null);
//
//	            if (existingUser != null) {
//	                request.setAttribute("errorMessage", "Tên tài khoản đã tồn tại.");
//	                request.getRequestDispatcher("/views/dangky.jsp").forward(request, response);
//	                return;
//	            }
//
//	            // Lưu người dùng mới vào cơ sở dữ liệu
//	            entityManager.getTransaction().begin();
//	            entityManager.persist(newUser);
//	            entityManager.getTransaction().commit();
//	            request.setAttribute("successMessage", "Đăng ký thành công! Bạn có thể đăng nhập.");
//	            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
//
//	        } catch (Exception e) {
//	            e.printStackTrace(); // In ra thông tin lỗi chi tiết
//	            if (entityManager.getTransaction().isActive()) {
//	                entityManager.getTransaction().rollback();
//	            }
//	            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi đăng ký: " + e.getMessage());
//	            request.getRequestDispatcher("/views/dangky.jsp").forward(request, response);
//	        } finally {
//	            entityManager.close();
//	        }
//
//	 }
//	 
	 @Override
	 protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	     request.setCharacterEncoding("UTF-8");
//	     String uriString = request.getRequestURI();
//
//	     // Khởi tạo UserDAO với EntityManager
//	     UserDAO userDao = new UserDAO();
//
//	     if (uriString.contains("/dangky")) {
//	         // Hiển thị trang đăng ký
//	         request.getRequestDispatcher("/views/dangky.jsp").forward(request, response);
//	     } else if (uriString.contains("/dangky/register")) { // Sửa thành /dangky/register
//	         // Xử lý dữ liệu đăng ký
//	         User user = new User();
//	         try {
//	             // Tạo đối tượng User từ tham số request
//	             BeanUtils.populate(user, request.getParameterMap());
//
//	             // Kiểm tra xem người dùng có tồn tại không
//	             if (userDao.findById(user.getId()) != null) {
//	                 request.setAttribute("errorMessage", "Tên tài khoản đã tồn tại.");
//	                 request.getRequestDispatcher("/views/dangky.jsp").forward(request, response);
//	                 return;
//	             }
//
//	             // Gọi phương thức create của UserDAO để lưu vào cơ sở dữ liệu
//	             userDao.create(user);
//
//	             // Cập nhật thông báo và chuyển hướng
//	             request.setAttribute("successMessage", "Thêm mới thành công. Bạn có thể đăng nhập.");
//	             request.getRequestDispatcher("/views/login.jsp").forward(request, response);
//	         } catch (Exception e) {
//	             e.printStackTrace();
//	             request.setAttribute("errorMessage", "Đã xảy ra lỗi khi đăng ký người dùng: " + e.getMessage());
//	             request.getRequestDispatcher("/views/dangky.jsp").forward(request, response);
//	         }
//	     }
		 
		 doSignUp(request, response);
	 }



//	

private void doSignUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getMethod();
    if (method.equalsIgnoreCase("POST")) {
        String id = req.getParameter("id");
        String pw = req.getParameter("password");
        String email = req.getParameter("email");
String fullname = req.getParameter("fullName");
        if (id == null || id.isEmpty() || pw == null || pw.isEmpty() || email == null || email.isEmpty()|| fullname==null || fullname.isEmpty()) {
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
                    BeanUtils.populate(entity, req.getParameterMap());
                    dao.create(entity);
                    req.setAttribute("message", "Đăng ký thành công!");
                }
            } catch (Exception e) {
                req.setAttribute("message", "Lỗi đăng ký!");
                e.printStackTrace();
            }
        }
    }
    req.getRequestDispatcher("/views/dangky.jsp").forward(req, resp);
}
	}


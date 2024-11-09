package com.asm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asm.dao.UserDAO;
import com.asm.entity.User;
import com.asm.entity.Video;

/**
 * Servlet implementation class NguoiSD
 */
@WebServlet("/admin/NguoiSD")
public class NguoiSD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       UserDAO userDAO = new UserDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NguoiSD() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        String action = request.getParameter("action");
        if ("deleteUser".equals(action) && userId != null && !userId.isEmpty()) {
            // Xóa người dùng trong cơ sở dữ liệu
            userDAO.delete(userId);
            
            // Chuyển hướng về danh sách người dùng
            response.sendRedirect(request.getContextPath() + "/admin/NguoiSD");
            return;
        }
        
        
        if (userId != null && !userId.isEmpty()) {
            // Retrieve the specific user by ID
            User user = userDAO.findById(userId);
            request.setAttribute("user", user); // Set the user attribute
        }
        
        List<User> users = userDAO.findAll(); // Fetch all users
        request.setAttribute("users", users); // Set the users attribute
        request.getRequestDispatcher("/views/nguoisudung.jsp").forward(request, response); // Forward to JSP
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("updateUser".equals(action)) {
            // Retrieve user data from request
            String userId = request.getParameter("userId");
            String password = request.getParameter("password");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");

            // Check if userId is provided
            if (userId != null && !userId.isEmpty()) {
                // Find the existing user in the database
                User existingUser = userDAO.findById(userId);

                if (existingUser != null) {
                    // Update user data
                    existingUser.setPassword(password);
                    existingUser.setFullName(fullname);
                    existingUser.setEmail(email);

                    // Update user in database
                    userDAO.update(existingUser);

                    // Redirect to the user list
                    response.sendRedirect(request.getContextPath() + "/admin/NguoiSD");
                } else {
                    // Handle the case where the user is not found
                    request.setAttribute("errorMessage", "User not found");
                    request.getRequestDispatcher("/views/error.jsp").forward(request, response);
                }
            } else {
                // Handle the case where userId is missing
                request.setAttribute("errorMessage", "User ID is missing");
                request.getRequestDispatcher("/views/error.jsp").forward(request, response);
            }
        }
    }


}

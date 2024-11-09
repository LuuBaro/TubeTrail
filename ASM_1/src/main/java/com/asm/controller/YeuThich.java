package com.asm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.asm.dao.FavoriteDAO;
import com.asm.dao.UserDAO;
import com.asm.dao.VideoDAO;
import com.asm.entity.Favorite;
import com.asm.entity.User;
import com.asm.entity.Video;
import com.asm.utils.JpaUtil;

import jakarta.persistence.TypedQuery;

/**
 * Servlet implementation class YeuThich
 */@WebServlet("/yeuthich")
 public class YeuThich extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private UserDAO userDao = new UserDAO();
	    private VideoDAO videoDao = new VideoDAO();
	    private FavoriteDAO favoriteDao = new FavoriteDAO();

	    public YeuThich() {
	        super();
	    }

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        User user = (User) session.getAttribute("user");

	        if (user != null) {
	            List<Favorite> favorites = favoriteDao.findByUserId(user.getId());
	            List<Video> likedVideos = new ArrayList<>();

	            for (Favorite favorite : favorites) {
	                likedVideos.add(favorite.getVideo());
	            }

	            request.setAttribute("videos", likedVideos);
	        }

	        request.getRequestDispatcher("/views/yeuthich.jsp").forward(request, response);
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        String userIdStr = request.getParameter("userId");
	        String videoIdStr = request.getParameter("videoId");

	        // Kiểm tra thông tin đầu vào
	        System.out.println("userIdStr: " + userIdStr);
	        System.out.println("videoIdStr: " + videoIdStr);

	        if (userIdStr == null || videoIdStr == null) {
	            System.out.println("Thông tin không hợp lệ.");
	            session.setAttribute("message", "Thông tin không hợp lệ.");
	            response.sendRedirect("main");
	            return;
	        }

	        try {
	            // Không chuyển đổi thành Long nếu ID là chuỗi
	            User user = userDao.findById(userIdStr); // Cần điều chỉnh phương thức findById để chấp nhận String
	            Video video = videoDao.findById(videoIdStr); // Cần điều chỉnh phương thức findById để chấp nhận String

	            System.out.println("User: " + user);
	            System.out.println("Video: " + video);

	            if (user == null || video == null) {
	                System.out.println("Người dùng hoặc video không tồn tại.");
	                session.setAttribute("message", "Người dùng hoặc video không tồn tại.");
	                response.sendRedirect("main");
	                return;
	            }

	            TypedQuery<Favorite> query = JpaUtil.getEntityManager().createQuery(
	                    "SELECT f FROM Favorite f WHERE f.user.id = :userId AND f.video.id = :videoId", Favorite.class);
	            query.setParameter("userId", userIdStr); // Cập nhật theo kiểu dữ liệu ID
	            query.setParameter("videoId", videoIdStr); // Cập nhật theo kiểu dữ liệu ID
	            List<Favorite> resultList = query.getResultList();

	            System.out.println("Result List Size: " + resultList.size());

	            if (resultList.isEmpty()) {
	                Favorite favorite = new Favorite();
	                favorite.setUser(user);
	                favorite.setVideo(video);
	                favorite.setLikeDate(new Date());

	                System.out.println("Creating Favorite: " + favorite);
	                favoriteDao.create(favorite);

	                session.setAttribute("message", "Video đã được thích.");
	                System.out.println("Video đã được thích.");
	            } else {
	                session.setAttribute("message", "Bạn đã thích video này rồi.");
	                System.out.println("Bạn đã thích video này rồi.");
	            }

	            response.sendRedirect("main");
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
	            session.setAttribute("message", "Đã xảy ra lỗi.");
	            response.sendRedirect("main");
	        }
	    }



	}

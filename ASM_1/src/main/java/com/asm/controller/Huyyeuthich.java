package com.asm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asm.dao.FavoriteDAO;
import com.asm.dao.UserDAO;
import com.asm.dao.VideoDAO;
import com.asm.entity.Favorite;
import com.asm.entity.User;
import com.asm.entity.Video;

@WebServlet("/huyyeuthich")
public class Huyyeuthich extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String videoId = request.getParameter("videoId");

        try {
            // Tạo các DAO
            FavoriteDAO favoriteDAO = new FavoriteDAO();
            UserDAO userDAO = new UserDAO();
            VideoDAO videoDAO = new VideoDAO();

            // Tìm kiếm đối tượng User và Video từ cơ sở dữ liệu
            User user = userDAO.findById(userId);
            Video video = videoDAO.findById(videoId);

            if (user == null || video == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User hoặc Video không tồn tại");
                return;
            }

            // Tạo đối tượng Favorite với User và Video tìm được
            Favorite favorite = new Favorite();
            favorite.setUser(user);
            favorite.setVideo(video);

            // Xử lý hủy yêu thích
            boolean success = favoriteDAO.remove(favorite);
            if (success) {
                // Redirect về trang trước đó
                response.sendRedirect(request.getHeader("Referer"));
            } else {
                // Xử lý lỗi
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Không thể hủy yêu thích");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
        }
    }
}

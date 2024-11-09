package com.asm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asm.dao.FavoriteDAO;
import com.asm.dao.ShareDAO;
import com.asm.dao.UserDAO;
import com.asm.dao.VideoDAO;
import com.asm.entity.Favorite;
import com.asm.entity.Share;
import com.asm.entity.User;
import com.asm.entity.Video;

/**
 * Servlet implementation class BaoCao
 */
@WebServlet("/admin/baocao")
public class BaoCao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VideoDAO videoDao;
	private FavoriteDAO favoriteDao;
	private ShareDAO shareDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BaoCao() {
		super();
		videoDao = new VideoDAO();
		favoriteDao = new FavoriteDAO();
		shareDao = new ShareDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");

	    // Lấy thông tin tổng hợp từ FavoriteDao
	    List<Object[]> summary = favoriteDao.findFavoriteSummary();
	    request.setAttribute("summary", summary);

	    // Lấy danh sách video
	    List<Video> videos = videoDao.findAll();
	    request.setAttribute("videos", videos);

	    // Xử lý thông tin videoId
	    String videoId = request.getParameter("videoId");
	    List<Favorite> favorites = new ArrayList<>();
	    if (videoId != null && !videoId.isEmpty()) {
	        favorites = favoriteDao.findByVideoId(videoId);
	    }
	    request.setAttribute("favorites", favorites);

	    // Xử lý thông tin shareVideoId
	    String shareVideoId = request.getParameter("shareVideoId");
	    List<Share> shares = new ArrayList<>();
	    if (shareVideoId != null && !shareVideoId.isEmpty()) {
	        shares = shareDao.findByVideoId(shareVideoId);
	    }
	    request.setAttribute("shares", shares);

	    // Chuyển tiếp tới JSP
	    request.getRequestDispatcher("/views/baocao.jsp").forward(request, response);
	}

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        VideoDAO videoDAO = new VideoDAO();
//        UserDAO userDAO = new UserDAO();
//        FavoriteDAO favoriteDAO = new FavoriteDAO();
//        ShareDAO shareDAO = new ShareDAO();
//
//        // Lấy số trang từ tham số 'page', nếu không có thì sử dụng trang 1 làm mặc định
//        int pageNumber = 1;
//        String pageParam = request.getParameter("page");
//        if (pageParam != null && !pageParam.isEmpty()) {
//            try {
//                pageNumber = Integer.parseInt(pageParam);
//            } catch (NumberFormatException e) {
//                pageNumber = 1; // Nếu không thể phân tích số, mặc định là trang 1
//            }
//        }
//
//        int pageSize = 10; // số video hiển thị trên mỗi trang
//
//        // Lấy dữ liệu video với số lượt yêu thích
//        List<Object[]> videoData = videoDAO.getVideoWithFavoriteCount();
//        request.setAttribute("videoData", videoData);
//
//        // Lấy danh sách video để chọn trong JSP
//        List<Video> videoList = videoDAO.getAllVideos();
//        request.setAttribute("videoList", videoList);
//
//        // Lấy tổng số video để tính số trang
//        long totalVideos = videoDAO.getTotalVideoCount();
//        int totalPages = (int) Math.ceil((double) totalVideos / pageSize);
//        request.setAttribute("totalPages", totalPages);
//
//        // Lấy dữ liệu người dùng yêu thích, có thể cần phải lọc theo videoId nếu có
//        String videoId = request.getParameter("videoId");
//        String shareVideoId = request.getParameter("shareVideoId");
//
//      
//
//        List<Favorite> favoriteUsersData = favoriteDAO.getFavoritesByVideoId(videoId);
//        request.setAttribute("favorites", favoriteUsersData);
//            
//     // Trong phương thức doGet
//        System.out.println("Video ID: " + videoId);
//        System.out.println("Share Video ID: " + shareVideoId);
//
//        List<Share> shares = new ArrayList<>();
//        if (shareVideoId != null && !shareVideoId.isEmpty()) {
//            shares = shareDAO.findByVideoId(shareVideoId);
//        }
//
//        System.out.println("Shares size: " + shares.size());
//        for (Share share : shares) {
//            System.out.println("Share User ID: " + share.getUser().getId());
//            System.out.println("Share User Fullname: " + share.getUser().getFullName());
//            System.out.println("Share User Email: " + share.getUser().getEmail());
//            System.out.println("Share Date: " + share.getShareDate());
//        }
//
//        request.setAttribute("shares", shares);
//
//       
//        request.setAttribute("favoriteUsersData", favoriteUsersData);
//        
//        request.setAttribute("currentPage", pageNumber);
//        request.setAttribute("pageSize", pageSize);
//
//        request.getRequestDispatcher("/views/baocao.jsp").forward(request, response);
//    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

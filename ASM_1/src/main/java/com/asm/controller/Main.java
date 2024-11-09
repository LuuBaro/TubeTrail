package com.asm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asm.dao.VideoDAO;
import com.asm.entity.Video;

/**
 * Servlet implementation class Main
 */
@WebServlet("/main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VideoDAO videoDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main() {
		super();
		videoDAO = new VideoDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Số lượng video trên mỗi trang
		int pageSize = 9;

		// Lấy số trang từ yêu cầu, nếu không có thì mặc định là trang 1
		int page;
		try {
			page = Integer.parseInt(request.getParameter("page"));
			if (page <= 0)
				page = 1;
		} catch (NumberFormatException e) {
			page = 1;
		}
		VideoDAO videoService = new VideoDAO();
		// Lấy tổng số video từ dịch vụ hoặc DAO (thay thế bằng phương thức thực tế của
		// bạn)
		int totalVideos = videoService.getTotalVideoCount(); // Ví dụ từ DAO
		int totalPages = (int) Math.ceil((double) totalVideos / pageSize);

		// Tính toán chỉ số bắt đầu và kết thúc của video trên trang hiện tại
		int start = (page - 1) * pageSize;
		int end = Math.min(start + pageSize, totalVideos);

		// Lấy danh sách video của trang hiện tại từ DAO (thay thế bằng phương thức thực
		// tế của bạn)
		List<Video> videos = videoService.getVideosByPage(start, end); // Ví dụ từ DAO

		// Gửi dữ liệu đến JSP
		request.setAttribute("videos", videos);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);
		request.getRequestDispatcher("/views/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

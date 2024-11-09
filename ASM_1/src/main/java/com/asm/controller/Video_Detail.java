package com.asm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asm.dao.VideoDAO;
import com.asm.entity.Video;

/**
 * Servlet implementation class Video_Detail
 */
@WebServlet("/videodetail")
public class Video_Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VideoDAO videoDAO = new VideoDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Video_Detail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    
	    
	   
	    VideoDAO videoDAO = new VideoDAO();
	    List<Video> videos = videoDAO.findAll();
	    Collections.shuffle(videos);
	    request.setAttribute("videos", videos);
	    String videoId = request.getParameter("id");
	    if (videoId != null && !videoId.isEmpty()) {
	        // Lấy video từ cơ sở dữ liệu
	        Video video = videoDAO.findById(videoId);
	        if (video != null) {
	            // Tăng số lượt xem của video
	            increaseViewCount(request, response, video);

	            // Lấy danh sách video đã xem để hiển thị trên JSP
	            Set<String> viewedVideosSet = getViewedVideosSet(request);
	            List<Video> viewedVideos = new ArrayList<>();
	            for (String id : viewedVideosSet) {
	                Video viewedVideo = videoDAO.findById(id);
	                if (viewedVideo != null) {
	                    viewedVideos.add(viewedVideo);
	                }
	            }

	            // Cài đặt video và danh sách video đã xem vào request attribute
	            request.setAttribute("video", video);
	            request.setAttribute("viewedVideos", viewedVideos);

	            // Forward đến JSP
	            request.getRequestDispatcher("/views/video_detail.jsp").forward(request, response);
	        } else {
	            response.sendError(HttpServletResponse.SC_NOT_FOUND);
	        }
	    } else {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	    }
	}


	private Set<String> getViewedVideosSet(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		Set<String> viewedVideosSet = new HashSet<>();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("viewedVideos")) {
					String decodedValue = new String(Base64.getDecoder().decode(cookie.getValue()));
					viewedVideosSet.addAll(Arrays.asList(decodedValue.split(",")));
				}
			}
		}
		return viewedVideosSet;
	}

	private void increaseViewCount(HttpServletRequest request, HttpServletResponse response, Video video)
			throws IOException {
		Set<String> viewedVideosSet = getViewedVideosSet(request);
		boolean hasViewed = viewedVideosSet.contains(video.getId());

		if (!hasViewed) {
			// Tăng số lượt xem của video trong cơ sở dữ liệu
			videoDAO.incrementViewCount(video.getId());

			// Thêm video vào set
			viewedVideosSet.add(video.getId());

			// Tạo cookie với tất cả các video ID đã xem
			String viewedVideos = viewedVideosSet.stream().collect(Collectors.joining(","));

			// Mã hóa giá trị cookie
			String encodedValue = Base64.getEncoder().encodeToString(viewedVideos.getBytes("UTF-8"));

			// Thêm video vào cookie
			Cookie viewedVideosCookie = new Cookie("viewedVideos", encodedValue);
			viewedVideosCookie.setMaxAge(60 * 60 * 24 * 30); // 30 ngày
			response.addCookie(viewedVideosCookie);
		}
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

}

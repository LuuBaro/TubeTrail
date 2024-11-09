package com.asm.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.asm.dao.VideoDAO;
import com.asm.entity.Video;

@WebServlet("/admin/all")
@MultipartConfig
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VideoDAO videoDAO;

	public Admin() {
		super();
		videoDAO = new VideoDAO(); // Initialize VideoDAO
	}

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int pageSize = 10; // Số lượng video mỗi trang
        int pageNumber = 1; // Trang hiện tại

        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                pageNumber = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                pageNumber = 1;
            }
        }

        if ("edit".equals(action)) {
            String id = request.getParameter("id");
            Video video = videoDAO.findById(id);
            if (video != null) {
                request.setAttribute("video", video);
                request.getRequestDispatcher("/views/edit-video.jsp").forward(request, response);
            } else {
                response.sendRedirect(
                        request.getContextPath() + "/views/admin.jsp?error=Video%20not%20found&page=" + pageNumber);
            }
        } else if ("delete".equals(action)) {
            deleteVideo(request, response); // Xử lý xóa video
            List<Video> videos = videoDAO.findAll();
            int totalVideos = videos.size();
            int start = (pageNumber - 1) * pageSize;
            int end = Math.min(start + pageSize, totalVideos);
            List<Video> paginatedVideos = videos.subList(start, end);

            request.setAttribute("videos", paginatedVideos);
            request.setAttribute("totalPages", (int) Math.ceil((double) totalVideos / pageSize));
            request.setAttribute("currentPage", pageNumber);
            request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
        } else {
            // Lấy danh sách video với phân trang
            List<Video> allVideos = videoDAO.findAll();
            int totalVideos = allVideos.size();
            int start = (pageNumber - 1) * pageSize;
            int end = Math.min(start + pageSize, totalVideos);

            List<Video> videos = allVideos.subList(start, end);

            request.setAttribute("videos", videos);
            request.setAttribute("totalPages", (int) Math.ceil((double) totalVideos / pageSize));
            request.setAttribute("currentPage", pageNumber);
            request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if ("add".equals(action)) {
            addVideo(request, response);
        } else if ("edit".equals(action)) {
            editVideo(request, response);
        } else if ("delete".equals(action)) {
            deleteVideo(request, response);
        }
        // Sau khi xử lý hành động, chuyển hướng về trang admin với phân trang hiện tại
        String pageParam = request.getParameter("page");
        if (pageParam == null || pageParam.isEmpty()) {
            pageParam = "1";
        }
        response.sendRedirect("admin?page=" + pageParam);
    }

    private void addVideo(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String title = request.getParameter("title");
        Integer views = Integer.parseInt(request.getParameter("views"));
        boolean active = request.getParameter("active") != null;
        String description = request.getParameter("description");

        // Xử lý ảnh
        Part filePart = request.getPart("poster");
        String poster = null;
        if (filePart != null && filePart.getSize() > 0) {
            // Get the absolute path for uploads
            String uploadDir = getServletContext().getRealPath("/uploads");

            // Create the uploads directory if it does not exist
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs(); // Create directory and any necessary parent directories
            }

            // Generate unique file name
            String fileName = UUID.randomUUID().toString() + "_" + filePart.getSubmittedFileName();
            String uploadPath = uploadDir + File.separator + fileName;

            // Write the file to the upload path
            filePart.write(uploadPath);

            // Set the poster path
            poster = "uploads/" + fileName;
        }

        Video video = new Video(id, title, views, active, description, poster);
        videoDAO.create(video);

        // Set a success message and forward to admin.jsp
        request.setAttribute("message", "Video đã được thêm thành công.");
        List<Video> videos = videoDAO.findAll();
        request.setAttribute("videos", videos);
        request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
    }

    private void editVideo(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        response.setCharacterEncoding("UTF-8");
        // Lấy thông tin video từ request
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        Integer views = Integer.parseInt(request.getParameter("views"));
        boolean active = request.getParameter("active") != null;
        String description = request.getParameter("description");

        // Xử lý ảnh poster nếu có
        Part filePart = request.getPart("poster");
        String poster = null;

        // Kiểm tra nếu có file poster mới
        if (filePart != null && filePart.getSize() > 0) {
            // Lưu ảnh poster mới và lấy đường dẫn
            poster = saveFile(filePart);
        } else {
            // Nếu không có ảnh mới, giữ nguyên ảnh cũ
            Video video = videoDAO.findById(id);
            if (video != null) {
                poster = video.getPoster(); // Giữ lại đường dẫn cũ của poster
            }
        }

        // Cập nhật video trong cơ sở dữ liệu
        Video video = new Video(id, title, views, active, description, poster);
        videoDAO.update(video);

        // Chuyển đến trang danh sách với thông báo thành công
        request.setAttribute("message", "Video đã được cập nhật thành công.");
        List<Video> videos = videoDAO.findAll();
        request.setAttribute("videos", videos);

        // Chuyển tiếp đến trang admin.jsp để hiển thị danh sách video đã cập nhật
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin.jsp");
        dispatcher.forward(request, response);
    }

    private String saveFile(Part filePart) throws IOException {
        // Lấy tên file từ phần của request
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        // Xác định đường dẫn thư mục tải lên
        String uploadDir = getServletContext().getRealPath("/uploads");
        File uploadDirFile = new File(uploadDir);

        // Tạo thư mục nếu không tồn tại
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        // Tạo file mới trong thư mục tải lên
        File file = new File(uploadDir, fileName);

        // Ghi dữ liệu vào file
        try (InputStream input = filePart.getInputStream(); FileOutputStream output = new FileOutputStream(file)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }

        // Trả về đường dẫn file đã lưu
        return "/uploads/" + fileName;
    }

    private void deleteVideo(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String id = request.getParameter("id");

        if (id != null && !id.isEmpty()) {
            try {
                // Tìm video theo ID
                Video video = videoDAO.findById(id);
                if (video != null) {
                    // Xóa video khỏi cơ sở dữ liệu
                    videoDAO.remove(video);
                    request.getSession().setAttribute("message", "Xóa video thành công!");
                } else {
                    request.getSession().setAttribute("error", "Video không tồn tại.");
                }
            } catch (Exception e) {
                e.printStackTrace(); // Xem xét sử dụng logging thay vì printStackTrace
                request.getSession().setAttribute("error", "Lỗi khi xóa video: " + e.getMessage());
            }
        } else {
            request.getSession().setAttribute("error", "ID video không hợp lệ.");
        }

        // Chuyển hướng về trang admin sau khi xóa
        response.sendRedirect("all");
    }

}

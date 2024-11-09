package com.asm.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import com.asm.dao.ShareDAO;
import com.asm.entity.Share;
import com.asm.entity.User;
import com.asm.entity.Video;

/**
 * Servlet implementation class ShareServlet
 */

@WebServlet("/share")
public class ShareServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String FROM_EMAIL = "luubaoa6@gmail.com"; // Email gửi
    private static final String PASSWORD = "dmgi mwof ldmn fvqk"; // Mật khẩu của email gửi
    private ShareDAO shareDAO = new ShareDAO();
    private EntityManagerFactory factory;

    public ShareServlet() {
        super();
        factory = Persistence.createEntityManagerFactory("ASM_1");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long videoId = Long.parseLong(request.getParameter("videoId"));
        List<Share> shares = shareDAO.getSharesForVideo(videoId);
        request.setAttribute("shares", shares);
        request.getRequestDispatcher("/path/to/your/view.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String videoId = request.getParameter("videoId");
        String email = request.getParameter("email");

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.getSession().setAttribute("message", "Bạn phải đăng nhập để chia sẻ video.");
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
            return;
        }

        EntityManager em = factory.createEntityManager();
        Video video = em.find(Video.class, videoId);
        em.close();

        if (video != null) {
            sendVideoEmail(email, video);

            ShareDAO shareDAO = new ShareDAO();
            Share share = new Share();
            share.setUser(user);
            share.setVideo(video);
            share.setEmails(email);
            share.setShareDate(new Date());
            shareDAO.create(share);

            request.getSession().setAttribute("message", "Video đã được gửi đến email của bạn.");
        } else {
            request.getSession().setAttribute("message", "Video không tồn tại.");
        }

        response.sendRedirect(request.getContextPath() + "/views/home.jsp");
    }
    
    private void sendVideoEmail(String toEmail, Video video) {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });

        try {
        	String videoUrl = "http://localhost:8080/ASM_1/videodetail?id=" + video.getId();
        	String emailContent = String.format(
        	    "Chào bạn,\n\nTôi muốn chia sẻ video này với bạn:\n\n" +
        	    "Tiêu đề: %s\n" +
        	    "Lượt xem: %d\n" +
        	    "Xem tại đây: %s",
        	    video.getTitle(), video.getViews(), videoUrl
        	);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Check out this video!");
            message.setText(emailContent);
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}




<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>
body {
	background-color: #000;
}

.navbar-brand img {
	margin-right: 10px;
}

footer {
	background-color: #121212;
	color: #aaa;
}

footer a {
	color: #aaa;
	text-decoration: none;
}

footer a:hover {
	color: #fff;
}

footer h5 {
	color: #fff;
}

footer p {
	font-size: 0.875rem;
	margin-bottom: 0.5rem;
}

.list-inline-item img {
	filter: invert(1);
}

.custom-img {
	width: 100%;
	height: 200px; /* Chiều cao cố định */
	background-color: #000;
	object-fit: contain; /* Giúp hình ảnh không bị biến dạng */
}

.bg-custom {
	background-color: #000;
	color: white;
}
</style>
</head>
<body>

	<%@ include file="menu.jsp"%>

<div class="container mt-4">
    <div class="row">
        <c:forEach var="video" items="${videos}">
            <div class="col-md-4 mb-4">
                <div class="card bg-custom">
                    <a href="./views/video_detail.jsp?videoId=${video.id}" class="text-decoration-none text-light">
                        <img src="${video.poster}" class="card-img-top custom-img" alt="${video.title}">
                        <div class="card-body text-white">
                            <h5 class="card-title">${video.title}</h5>
                            <p class="card-text">${video.views} lượt xem ・ <span>1 tháng trước</span></p>
                        </div>
                    </a>
                    <div class="card-footer d-flex justify-content-between">
                        <c:choose>
                            <c:when test="${video.isLikedByUser(user)}">
                                <!-- Unlike Button with Filled Icon -->
                                <form action="/ASM_1/huyyeuthich" method="post" class="d-inline">
                                    <input type="hidden" name="userId" value="${user.id}">
                                    <input type="hidden" name="videoId" value="${video.id}">
                                    <button type="submit" class="btn btn-danger btn-sm">
                                       <i class="bi bi-hand-thumbs-up-fill"></i> Unlike
                                    </button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <!-- Like Button with Outline Icon -->
                                <form action="/ASM_1/huyyeuthich" method="post" class="d-inline">
                                    <input type="hidden" name="userId" value="${user.id}">
                                    <input type="hidden" name="videoId" value="${video.id}">
                                    <button type="submit" class="btn btn-light btn-sm">
                                        <i class="bi bi-hand-thumbs-up-fill"></i> Unlike
                                    </button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                        <button class="btn btn-light btn-sm" data-bs-toggle="modal" data-bs-target="#shareModal">
                            <i class="bi bi-share"></i> Share
                        </button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>


	<!-- Footer -->
	<%@ include file="footer.jsp"%>



	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
		integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
		crossorigin="anonymous"></script>
</body>
</html>
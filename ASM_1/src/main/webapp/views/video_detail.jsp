<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>
body {
	background-color: black;
	color: white;
}

.video-thumbnail {
	width: 100px;
	height: 60px;
	object-fit: cover; /* Đảm bảo hình ảnh không bị biến dạng */
	margin-right: 10px;
}

.video-info {
	flex: 1;
}

.video-info h6 {
	color: #fff;
	font-weight: bold;
	margin: 0;
}

.video-info p {
	color: #ccc;
	margin: 0;
}

.sidebar-title {
	color: #fff;
	margin-bottom: 15px;
}

.video-image {
	width: 100%;
	height: 510px;
	border-radius: 8px;
}

.video-sidebar {
	max-height: calc(100vh - 60px);
	overflow-y: auto;
}

.video-sidebar .card {
	margin-bottom: 10px;
}

.video-title {
	font-size: 1.5rem;
	font-weight: bold;
	margin: 10px 0;
}

.video-details {
	margin-bottom: 15px;
}

.sidebar-title {
	font-weight: bold;
	margin-bottom: 20px;
}

.video-card img {
	width: 100px;
	height: 60px;
	object-fit: cover;
	border-radius: 4px;
}

.video-card {
	display: flex;
	margin-bottom: 15px;
}

.video-card a {
	text-decoration: none; /* Bỏ gạch chân liên kết */
	color: inherit; /* Kế thừa màu chữ từ phần tử cha */
}

.video-info h6 {
	color: #fff; /* Màu trắng cho tiêu đề */
	font-weight: bold; /* Chữ đậm */
}

.video-card .video-info {
	margin-left: 15px;
	flex-grow: 1;
}

.video-card .video-info h6 {
	margin: 0;
	font-size: 14px;
	font-weight: bold;
}

.video-card .video-info p {
	margin: 0;
	font-size: 12px;
	color: #666;
}

.comments-section {
	width: 100%;
	margin: 20px auto;
	padding: 20px;
	border-radius: 10px;
}

.add-comment {
	display: flex;
	align-items: center;
	margin-bottom: 20px;
}

.add-comment .user-avatar {
	width: 40px;
	height: 40px;
	border-radius: 50%;
	margin-right: 10px;
}

.add-comment .comment-input {
	width: 100%;
	padding: 10px;
	background-color: black;
	border-top: none;
	border-right: none;
	border-left: none;
	border-bottom: 1px solid white;
	border-radius: 5px;
	color: #FFF;
}

.comment {
	display: flex;
	margin-bottom: 20px;
}

.comment .user-avatar {
	width: 40px;
	height: 40px;
	border-radius: 50%;
	margin-right: 10px;
}

.comment .comment-content {
	background-color: black;
	padding: 10px;
	border-radius: 10px;
	width: 100%;
}

.comment .username {
	font-weight: bold;
	display: block;
}

.comment .timestamp {
	color: #AAA;
	font-size: 0.9em;
	margin-left: 10px;
}

.comment .comment-actions {
	margin-top: 10px;
	font-size: 0.9em;
}

.comment .comment-actions span {
	margin-right: 20px;
	cursor: pointer;
}

.comment .comment-actions .translate {
	color: #00A;
}

.comment .comment-actions .likes {
	color: #F00;
}

.comment .comment-actions .reply {
	color: #00A;
}
</style>

</head>
<body>

	<nav class="container navbar navbar-expand-lg navbar-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> <img
				src="https://images.fptplay.net/media/photo/OTT/2024/02/26/1708914022_P5N.png"
				width="100" height="auto" class="d-inline-block align-text-top">
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/ASM_1/main">Trang chủ</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/ASM_1/yeuthich">Yêu thích</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Giới
							thiệu</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Kho phim</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Liên hệ</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdownMore"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">Tài
							khoản </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdownMore">
							<li><a class="dropdown-item" href="/ASM_1/capnhat">Cập
									nhật tài khoản</a></li>

						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdownAdmin"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">Admin</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdownAdmin">
							<li><a class="dropdown-item" href="/ASM_1/admin/all">Quản
									lý video</a></li>
							<li><a class="dropdown-item" href="/ASM_1//admin/NguoiSD">Quản
									lý người sử dụng</a></li>
							<li><a class="dropdown-item" href="/ASM_1/admin/baocao">Báo
									cáo</a></li>
						</ul></li>
				</ul>
				<form class="d-flex">
					<!-- Nếu người dùng đã đăng nhập -->
					<c:if test="${sessionScope.loggedIn}">
						<a href="/ASM_1/account/logout" class="btn btn-outline-light me-2"
							type="button">Đăng xuất</a>
					</c:if>
					<!-- Nếu người dùng chưa đăng nhập -->
					<c:if test="${!sessionScope.loggedIn}">
						<a href="/ASM_1/account/dangnhap"
							class="btn btn-outline-light me-2" type="button">Đăng nhập</a>
					</c:if>
				</form>

			</div>
		</div>
	</nav>
	<div class="container text-white">
		<div class="row">
			<!-- Video Image Section -->
			<div class="col-lg-9">
				<img src="${video.poster}" alt="Video Image" class="video-image">
				<div class="video-details">
					<h1 class="video-title">${video.title}</h1>
					<div class="d-flex align-items-center">
						<img
							src="/ASM_1/images/bao001.jpg"
							class="rounded-circle me-3" alt="Channel Logo" width="50" height="50">
						<div>
							<p class="mb-0">
								<strong>Lưu Bảo</strong>
							</p>
							<p class="mb-0">5 triệu lượt đăng ký</p>
						</div>
						<button class="btn btn-danger ms-3">Subscribe</button>
					</div>
					<div class="mt-3">
						<form action="yeuthich" method="post" class="d-inline">
							<input type="hidden" name="userId" value="${user.id}">
							<!-- Thay thế bằng ID người dùng thực tế -->
							<input type="hidden" name="videoId" value="${video.id}">
							<button type="submit" class="btn btn-light btn-sm">
								<i class="bi bi-hand-thumbs-up"></i> Like
							</button>
						</form>
						<button class="btn btn-light btn-sm" data-bs-toggle="modal"
							data-bs-target="#shareModalV"
							onclick="document.getElementById('videoIdInput').value = '${video.id}';">
							<i class="bi bi-share"></i> Share
						</button>
					</div>
				</div>

				<div class="comments-section">
					<div class="add-comment">
						<img
							src="https://wac-cdn.atlassian.com/dam/jcr:ba03a215-2f45-40f5-8540-b2015223c918/Max-R_Headshot%20(1).jpg?cdnVersion=1937"
							alt="User Avatar" class="user-avatar"> <input type="text"
							placeholder="Add a comment..." class="comment-input">
					</div>
					<!-- Comments List -->
					<div class="comment" id="comment-1">
						<img
							src="https://media.licdn.com/dms/image/C4E03AQEEZUPHzQoE0A/profile-displayphoto-shrink_400_400/0/1623677348445?e=2147483647&v=beta&t=4yBsLbVOvjpli7F64hdqdgYCNg6KkkCwqV8WIHW-YZA"
							alt="User Avatar" class="user-avatar">
						<div class="comment-content">
							<span class="username">tothanhtai7636</span> <span
								class="timestamp">2 months ago</span>
							<p>
								<a href="#41:25">41:25</a> hay wa
							</p>
							<div class="comment-actions">
								<span class="translate">Translate to Vietnamese</span> <span
									class="likes">3</span> <span class="reply">Reply</span>
							</div>
						</div>
					</div>
					<!-- Repeat for other comments -->
					<div class="comment" id="comment-2">
						<img
							src="https://media.licdn.com/dms/image/C4D03AQHC1glHjAZ9Iw/profile-displayphoto-shrink_400_400/0/1637324198798?e=2147483647&v=beta&t=49tAie_afLooCij1oDBrworKSs7Q8GREkB_k4CzCaCo"
							alt="User Avatar" class="user-avatar">
						<div class="comment-content">
							<span class="username">nhutnguyenthanh1535</span> <span
								class="timestamp">2 months ago</span>
							<p>Chill quá</p>
							<div class="comment-actions">
								<span class="likes">1</span> <span class="reply">Reply</span>
							</div>
						</div>
					</div>
					<div class="comment" id="comment-3">
						<img
							src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR0Ao3ZRqYtphydnZ4yG51_fRIJm7PrvnfQauf7cBD2GQfZbDzSZ0WaSTAlZVkDsvEpZGA&usqp=CAU"
							alt="User Avatar" class="user-avatar">
						<div class="comment-content">
							<span class="username">linhnguyen-xu5mt</span> <span
								class="timestamp">4 months ago</span>
							<p>Đỉnh quá</p>
							<div class="comment-actions">
								<span class="reply">Reply</span>
							</div>
						</div>
					</div>
					<div class="comment" id="comment-4">
						<img
							src="https://media.licdn.com/dms/image/D4E03AQFIiuARUCqIHQ/profile-displayphoto-shrink_400_400/0/1714735263220?e=2147483647&v=beta&t=Y2yhuBfvsW3OXFR2ugr0kw2VdJHkKjAnXY2Mfa7yctU"
							alt="User Avatar" class="user-avatar">
						<div class="comment-content">
							<span class="username">thenguyen4288</span> <span
								class="timestamp">2 months ago</span>
							<p>nhạc to và tròn, rất hay</p>
							<div class="comment-actions">
								<span class="likes">3</span> <span class="reply">Reply</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Video Sidebar Section -->
			<!-- Video Sidebar Section -->
			<div class="col-lg-3 video-sidebar">
				<h5 class="sidebar-title">Đề xuất</h5>
				<c:forEach var="video" items="${videos}">
					<div class="video-card">
						<a
							href="${pageContext.request.contextPath}/videodetail?id=${video.id}"
							class="d-flex"
							onclick="updateMainVideo('${video.poster}', '${video.title}', '${video.views}', '${pageContext.request.contextPath}/videodetail?id=${video.id}')">
							<img src="${video.poster}" alt="Video Thumbnail"
							style="width: 100px; height: 60px;">
							<div class="video-info">
								<h6>${video.title}</h6>
								<p>${video.views}lượt xem ・ 2 Tháng trước</p>
							</div>
						</a>
					</div>
				</c:forEach>
			</div>

		</div>
	</div>


	<!-- Modal for sharing video -->
	<div class="modal fade" id="shareModal_${video.id}" tabindex="-1"
		aria-labelledby="shareModalLabel_${video.id}" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="shareModalLabel_${video.id}">Share
						Video</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="shareForm_${video.id}" action="share" method="post">
						<input type="hidden" name="videoId" id="videoIdInput_${video.id}">
						<div class="mb-3">
							<label for="emailInput_${video.id}" class="form-label">Enter
								email address</label> <input type="email" class="form-control"
								id="emailInput_${video.id}" name="email" required>
						</div>
						<button type="submit" class="btn btn-primary">Send</button>
					</form>
				</div>
			</div>
		</div>
	</div>


	<%@ include file="footer.jsp"%>

<script>
function shuffleVideos() {
    var container = document.querySelector('.video-sidebar');
    var videos = Array.from(container.querySelectorAll('.video-card'));
    var shuffledVideos = videos.sort(() => Math.random() - 0.5);
    
    // Xóa các video hiện tại
    container.innerHTML = '';
    
    // Thêm các video đã sắp xếp ngẫu nhiên vào lại container
    shuffledVideos.forEach(video => container.appendChild(video));
}

// Gán sự kiện nhấp chuột cho các video
document.addEventListener('DOMContentLoaded', function() {
    var videoLinks = document.querySelectorAll('.video-sidebar .video-card a');
    videoLinks.forEach(link => {
        link.addEventListener('click', function(event) {
            shuffleVideos();
        });
    });
});
</script>


</body>
</html>
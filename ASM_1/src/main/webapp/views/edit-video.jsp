<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa Video</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<style>
body {
	background-color: black;
	color: white;
}

.video-image {
	width: 100%;
	height: auto;
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
</style>
</head>

<body>
	<%@ include file="menu.jsp"%>

	<div class="container mt-5">
		<h2 class="mt-4">Sửa Video</h2>
		<form action="/ASM_1/admin/all" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="action" value="edit"> <input
				type="hidden" name="id" value="${video.id}">
			<div class="row">
				<div class="col-md-12 col-lg-4">
					<div
						class="input-img mb-3 p-5 border border-secondary h-100 d-flex justify-content-center align-items-center flex-column">
						<label for="poster" class="fw-bold text-uppercase">Ảnh xem
							trước:</label> <input type="file" id="poster" class="mb-3" name="poster" />
						<img id="img_preview" src="${video.poster}" alt="Preview"
							style="max-width: 100%;" />
					</div>
				</div>
				<div class="col-md-12 col-lg-8">
					<div class="input-title mb-3">
						<label for="title" class="fw-bold text-uppercase">Tên
							video:</label> <input type="text" id="title" class="form-control"
							name="title" value="${video.title}" required />
					</div>
					<div class="input-views mb-3">
						<label for="views" class="fw-bold text-uppercase">Lượt
							xem:</label> <input type="number" id="views" class="form-control"
							name="views" value="${video.views}" required />
					</div>
					<div class="input-status mb-3">
						<label class="fw-bold text-uppercase">Trạng thái:</label>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="active"
								name="active" ${video.active ? 'checked' : ''}> <label
								class="form-check-label" for="active">Đang hoạt động</label>
						</div>
					</div>
					<div class="input-description mb-3">
						<label for="description" class="fw-bold text-uppercase">Ghi
							chú:</label>
						<textarea id="description" class="form-control" name="description">${video.description}</textarea>
					</div>
					<div class="button-group">
						<button type="submit" class="btn btn-primary" name="action"
							value="edit">Cập nhật</button>
						<button type="reset" class="btn btn-secondary">Làm mới</button>
					</div>
				</div>
			</div>
		</form>

		<!-- Hiển thị thông báo thành công -->
		<c:if test="${not empty message}">
			<div class="alert alert-success mt-3">
				<c:out value="${message}" />
			</div>
		</c:if>

		<!-- Hiển thị thông báo lỗi -->
		<c:if test="${not empty error}">
			<div class="alert alert-danger mt-3">
				<c:out value="${error}" />
			</div>
		</c:if>
	</div>

	<script>
		document
				.getElementById('poster')
				.addEventListener(
						'change',
						function(event) {
							const file = event.target.files[0];
							const reader = new FileReader();
							reader.onload = function(e) {
								document.getElementById('img_preview').src = e.target.result;
							}
							reader.readAsDataURL(file);
						});
	</script>


</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>




<div class="container mt-5">
	<div class="row">
		<c:forEach var="video" items="${videos}">
			<div class="col-md-4 mb-4">
				<div class="card bg-custom">
					<a href="${pageContext.request.contextPath}/videodetail?id=${video.id}"
						class="text-decoration-none text-light"> <img
						src="${video.poster}" class="card-img-top custom-img"
						alt="${video.title}">
						<div class="card-body text-white">
							<h5 class="card-title">${video.title}</h5>
							<p class="card-text">${video.views}
								lượt xem ・ <span>1 tháng trước</span>
							</p>
						</div>
					</a>


					<div class="card-footer d-flex justify-content-between">
						<div class="cus">
							<form action="yeuthich" method="post" class="d-inline">
								<input type="hidden" name="userId" value="${user.id}">
								<!-- Thay thế bằng ID người dùng thực tế -->
								<input type="hidden" name="videoId" value="${video.id}">
								<button type="submit" class="btn btn-light btn-sm">
									<i class="bi bi-hand-thumbs-up"></i> Like
								</button>
							</form>
						</div>
						<button class="btn btn-light btn-sm" data-bs-toggle="modal"
							data-bs-target="#shareModal"
							onclick="document.getElementById('videoIdInput').value = '${video.id}';">
							<i class="bi bi-share"></i> Share
						</button>

					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>


<!-- Modal for sharing video -->
<div class="modal fade" id="shareModal" tabindex="-1"
	aria-labelledby="shareModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="shareModalLabel">Share Video</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form id="shareForm" action="share" method="post">
					<input type="hidden" name="videoId" id="videoIdInput">
					<div class="mb-3">
						<label for="emailInput" class="form-label">Enter email
							address</label> <input type="email" class="form-control" id="emailInput"
							name="email" required>
					</div>
					<button type="submit" class="btn btn-primary">Send</button>
				</form>
			</div>
		</div>
	</div>
</div>

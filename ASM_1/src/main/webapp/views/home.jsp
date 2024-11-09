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
	height: 200px;
	/* Chiều cao cố định */
	background-color: #000;
	object-fit: cover;
	/* Giúp hình ảnh không bị biến dạng */
}

.bg-custom {
	background-color: #000;
	color: white;
	border: 1px solid #f0f0f0;
}
.btn-like {
    background-color: #007bff; /* Màu nền cho nút Like */
    color: #fff;
}

.btn-like:disabled {
    background-color: #0056b3; /* Màu nền khi nút đã được chọn */
}

.btn-unlike {
    background-color: #dc3545; /* Màu nền cho nút Unlike */
    color: #fff;
}

.btn-unlike:disabled {
    background-color: #c82333; /* Màu nền khi nút đã được chọn */
}

</style>	
</head>
<body>



	<%@ include file="menu.jsp"%>
	<%@ include file="carousel.jsp"%>
	<!-- Thông báo nếu có -->
<c:if test="${not empty message}">
    <div class="alert alert-info alert-dismissible fade show" role="alert">
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>
	<%@ include file="video.jsp"%>
	
	
	
	
	  <!-- Phân trang -->
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                        <a class="page-link" href="?page=${i}">${i}</a>
                    </li>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage + 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>






    <!-- Modal share (nếu cần) -->
    <div class="modal fade" id="shareModal" tabindex="-1" aria-labelledby="shareModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="shareModalLabel">Share</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- Nội dung chia sẻ -->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
	


	<div class="container text-white">
		<hr>
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
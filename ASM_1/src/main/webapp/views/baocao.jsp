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

.nav-tabs .nav-link.active {
	background-color: #f5f5f5;
	border-color: #dee2e6 #dee2e6 #fff;
}

.table thead th {
	color: #dc3545;
	font-weight: bold;
}
</style>
</head>
<body>

	<%@ include file="menu.jsp"%>

	
<div class="container mt-4">
    <!-- Tabs -->
    <div class="tabs-container">
        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item" role="presentation">
                <button class="nav-link active" id="favorites-tab"
                    data-bs-toggle="tab" data-bs-target="#favorites" type="button"
                    role="tab" aria-controls="favorites" aria-selected="true">
                    Video</button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" id="favorite-users-tab"
                    data-bs-toggle="tab" data-bs-target="#favorite-users"
                    type="button" role="tab" aria-controls="favorite-users"
                    aria-selected="false">Người dùng yêu thích</button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" id="shared-users-tab" data-bs-toggle="tab"
                    data-bs-target="#shared-users" type="button" role="tab"
                    aria-controls="shared-users" aria-selected="false">Người
                    dùng chia sẻ</button>
            </li>
        </ul>
    </div>

    <!-- Tab content -->
    <div class="tab-content" id="myTabContent">
        <!-- Video tab -->
        <div class="tab-pane fade show active" id="favorites" role="tabpanel"
            aria-labelledby="favorites-tab">
            <table class="table table-bordered custom-table">
                <thead>
                    <tr>
                        <th>Tiêu đề Video</th>
                        <th>Số lượng Yêu thích</th>
                        <th>Ngày mới nhất</th>
                        <th>Ngày cũ nhất</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${summary}">
                        <tr>
                            <td><c:out value="${row[0]}" /></td>
                            <td><c:out value="${row[1]}" /></td>
                            <td><c:choose>
                                    <c:when test="${row[2] != null}">
                                        <fmt:formatDate value="${row[2]}" pattern="dd/MM/yyyy" />
                                    </c:when>
                                    <c:otherwise>N/A</c:otherwise>
                                </c:choose></td>
                            <td><c:choose>
                                    <c:when test="${row[3] != null}">
                                        <fmt:formatDate value="${row[3]}" pattern="dd/MM/yyyy" />
                                    </c:when>
                                    <c:otherwise>N/A</c:otherwise>
                                </c:choose></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Người dùng yêu thích tab -->
        <div class="tab-pane fade" id="favorite-users" role="tabpanel"
            aria-labelledby="favorite-users-tab">
            <form action="/ASM_1/admin/baocao" method="get">
                <div class="mb-3">
                    <label for="videoTitle" class="form-label">Tiêu đề Video:</label>
                    <select class="form-select" id="videoTitle" name="videoId">
                        <option value="" selected>Chọn video</option>
                        <c:forEach var="video" items="${videos}">
                            <option value="${video.id}"
                                <c:if test="${param.videoId == video.id}">selected</c:if>>${video.title}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Lọc</button>
            </form>

            <table class="table table-bordered custom-table mt-4">
                <thead>
                    <tr>
                        <th>ID người dùng</th>
                        <th>Họ và tên</th>
                        <th>Email</th>
                        <th>Ngày yêu thích</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="favorite" items="${favorites}">
                        <tr>
                            <td>${favorite.user.id}</td>
                            <td>${favorite.user.fullname}</td>
                            <td>${favorite.user.email}</td>
                            <td><fmt:formatDate value="${favorite.likeDate}"
                                    pattern="dd/MM/yyyy" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Người dùng chia sẻ tab -->
        <div class="tab-pane fade" id="shared-users" role="tabpanel"
            aria-labelledby="shared-users-tab">
            <form action="/admin/baocao" method="get">
                <div class="mb-3">
                    <label for="shareVideoTitle" class="form-label">Tiêu đề Video:</label>
                    <select class="form-select" id="shareVideoTitle" name="shareVideoId">
                        <option value="" selected>Chọn video</option>
                        <c:forEach var="video" items="${videos}">
                            <option value="${video.id}"
                                <c:if test="${param.shareVideoId == video.id}">selected</c:if>>${video.title}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Lọc</button>
            </form>

            <!-- Bảng người dùng chia sẻ -->
            <table class="table table-bordered custom-table mt-4">
                <thead>
                    <tr>
                        <th>ID người dùng</th>
                        <th>Họ và tên</th>
                        <th>Email</th>
                        <th>Ngày chia sẻ</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="share" items="${shares}">
                        <tr>
                            <td>${share.user.id}</td>
                            <td>${share.user.fullname}</td>
                            <td>${share.user.email}</td>
                            <td><fmt:formatDate value="${share.shareDate}"
                                    pattern="dd/MM/yyyy" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

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
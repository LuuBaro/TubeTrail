<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Quản Lý Video</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
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
        <!-- Thêm video -->
        <h2 class="mt-4">Thêm Video Mới</h2>
        <form action="/ASM_1/admin/all" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="add">
            <div class="row">
                <div class="col-md-12 col-lg-4">
                    <div class="input-img mb-3 p-5 border border-secondary h-100 d-flex justify-content-center align-items-center flex-column">
                        <label for="poster" class="fw-bold text-uppercase">Ảnh xem trước:</label>
                        <input type="file" id="poster" class="mb-3" name="poster" required />
                        <img id="img_preview" src="#" alt="Preview" style="max-width: 100%;" />
                    </div>
                </div>
                <div class="col-md-12 col-lg-8">
                    <div class="input-id mb-3">
                        <label for="id" class="fw-bold text-uppercase">ID Video:</label>
                        <input type="text" id="id" class="form-control" name="id" required>
                    </div>
                    <div class="input-title mb-3">
                        <label for="title" class="fw-bold text-uppercase">Tên video:</label>
                        <input type="text" id="title" class="form-control" name="title" required>
                    </div>
                    <div class="input-views mb-3">
                        <label for="views" class="fw-bold text-uppercase">Lượt xem:</label>
                        <input type="number" id="views" class="form-control" name="views" required>
                    </div>
                    <div class="input-status mb-3">
                        <label class="fw-bold text-uppercase">Trạng thái:</label>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="active" name="active">
                            <label class="form-check-label" for="active">Đang hoạt động</label>
                        </div>
                    </div>
                    <div class="input-description mb-3">
                        <label for="description" class="fw-bold text-uppercase">Ghi chú:</label>
                        <textarea id="description" class="form-control" name="description"></textarea>
                    </div>
                    <div class="button-group">
                        <button type="submit" class="btn btn-primary" name="action" value="add">Thêm</button>
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

        <!-- Danh sách video -->
        <h2 class="mt-5">Danh Sách Video</h2>
        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên video</th>
                    <th>Ảnh</th>
                    <th>Lượt xem</th>
                    <th>Trạng thái</th>
                    <th>Mô tả</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="video" items="${videos}">
                    <tr>
                        <td><c:out value="${video.id}" /></td>
                        <td><c:out value="${video.title}" /></td>
                        <td>
                            <img src="<c:out value="${video.poster}"/>" alt="Poster" style="max-width: 100px;">
                        </td>
                        <td><c:out value="${video.views}" /></td>
                        <td><c:out value="${video.active ? 'Đang hoạt động' : 'Không hoạt động'}" /></td>
                        <td><c:out value="${video.description}" /></td>
                        <td>
                            <a href="/ASM_1/admin/all?action=edit&id=<c:out value="${video.id}"/>" class="btn btn-primary">Sửa</a>
                            <a href="/ASM_1/admin/all?action=delete&id=<c:out value="${video.id}"/>" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa video này?');">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Phân trang -->
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <c:if test="${currentPage > 1}">
                    <li class="page-item"><a class="page-link" href="/ASM_1/admin/all?page=${currentPage - 1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a></li>
                </c:if>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="page-item ${currentPage == i ? 'active' : ''}"><a class="page-link" href="/ASM_1/admin/all?page=${i}">${i}</a></li>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <li class="page-item"><a class="page-link" href="/ASM_1/admin/all?page=${currentPage + 1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a></li>
                </c:if>
            </ul>
        </nav>
    </div>

    <script>
        document.getElementById('poster').addEventListener('change', function(event) {
            const file = event.target.files[0];
            const reader = new FileReader();
            reader.onload = function(e) {
                document.getElementById('img_preview').src = e.target.result;
            }
            reader.readAsDataURL(file);
        });
    </script>
     <script>
        function previewFile() {
            const file = document.querySelector('#poster').files[0];
            const reader = new FileReader();
            reader.onloadend = () => {
                document.querySelector('#posterPreview').src = reader.result;
            }
            if (file) {
                reader.readAsDataURL(file);
            }
        }
    </script>
</body>
</html>

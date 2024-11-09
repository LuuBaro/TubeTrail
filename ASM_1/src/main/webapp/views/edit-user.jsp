<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
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

<%@ include file="menu.jsp" %>

   



<div class="container w-50">
    <form action="/ASM_1/admin?action=updateUser" method="post">
        <input type="hidden" id="userId" name="userId" value="${user.id}">
        
        <div class="input-price mb-3">
            <label for="username" class="fw-bold text-uppercase">Tên đăng nhập:</label>
            <input type="text" id="username" name="username" class="form-control" value="${user.username}">
        </div>
        <div class="input-name mb-3">
            <label for="password" class="fw-bold text-uppercase">Mật khẩu:</label>
            <input type="password" id="password" name="password" class="form-control" value="${user.password}">
        </div>
        <div class="input-name mb-3">
            <label for="fullname" class="fw-bold text-uppercase">Họ và tên:</label>
            <input type="text" id="fullname" name="fullname" class="form-control" value="${user.fullName}">
        </div>
        <div class="input-name mb-3">
            <label for="email" class="fw-bold text-uppercase">Email:</label>
            <input type="email" id="email" name="email" class="form-control" value="${user.email}">
        </div>

        <div class="button-group">
            <button type="submit" class="btn btn-primary mx-1">Cập nhật</button>
        </div>
    </form>
</div>






   
     <div class="container mt-4">
        <h3>Danh sách người dùng:</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Tên đăng nhập</th>
                    <th>Mật khẩu</th>
                    <th>Họ và tên</th>
                    <th>Email</th>
                    <th>Chức vụ</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td><c:out value="${user.id}" /></td>
                        <td><c:out value="${user.password}" /></td>
                        <td><c:out value="${user.fullName}" /></td>
                        <td><c:out value="${user.email}" /></td>
                        <td><c:out value="${user.admin}" /></td>
                        <td>
                            <a href="/ASM_1/admin?action=editUser&id=<c:out value="${user.id}"/>"
                               class="btn btn-primary btn-sm">Sửa</a>
                            <a href="/ASM_1/admin?action=deleteUser&id=<c:out value="${user.id}"/>"
                               class="btn btn-danger btn-sm"
                               onclick="return confirm('Bạn có chắc chắn muốn xóa người dùng này?');">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

     <%@ include file="footer.jsp" %>

    <!-- Bootstrap JavaScript Libraries -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
</body>
</html>
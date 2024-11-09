<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách người dùng</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
    <style>
        body {
            background-color: black;
            color: white;
        }

        /* Các CSS khác */
    </style>
</head>
<body>

<%@ include file="menu.jsp" %>

<div class="container w-50 mt-4">
    <form action="${pageContext.request.contextPath}/admin/NguoiSD" method="post">
        <input type="hidden" id="userId" name="userId" value="${user.id}">
        
        <div class="mb-3">
            <label for="username" class="form-label fw-bold text-uppercase">Tên đăng nhập:</label>
            <input type="text" id="username" name="username" class="form-control" value="${user.id}" disabled>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label fw-bold text-uppercase">Mật khẩu:</label>
            <input type="password" id="password" name="password" class="form-control" value="${user.password}">
        </div>
        <div class="mb-3">
            <label for="fullname" class="form-label fw-bold text-uppercase">Họ và tên:</label>
            <input type="text" id="fullname" name="fullname" class="form-control" value="${user.fullName}">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label fw-bold text-uppercase">Email:</label>
            <input type="email" id="email" name="email" class="form-control" value="${user.email}">
        </div>

        <button type="submit" class="btn btn-primary" name="action" value="updateUser">Cập nhật</button>
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
                        <a href="${pageContext.request.contextPath}/admin/NguoiSD?id=<c:out value='${user.id}'/>"
                           class="btn btn-primary btn-sm">Sửa</a>
                       <a href="${pageContext.request.contextPath}/admin/NguoiSD?action=deleteUser&id=<c:out value='${user.id}'/>"
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

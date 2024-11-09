<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu</title>
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
body {
    background-color: #333;
    color: #fff;
}

.login-container {
    max-width: 400px;
    margin: 100px auto;
    padding: 20px;
    background-color: #1f1f1f;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.login-container h2 {
    margin-bottom: 20px;
}

.form-control {
    background-color: #333;
    color: #fff;
    border: none;
    border-bottom: 1px solid #555;
}

.form-control:focus {
    background-color: #333;
    color: #fff;
    border-color: #ff6600;
    box-shadow: none;
}

.btn-primary {
    background-color: #ff6600;
    border: none;
}

.btn-primary:hover {
    background-color: #e65c00;
}

.btn-secondary {
    background-color: #6c757d;
    border: none;
}

.btn-secondary:hover {
    background-color: #5a6268;
}

.hidden {
    display: none;
}
</style>
</head>
<body>
    <div class="login-container">
        <h2>Quên mật khẩu</h2>
        <form id="forgot-password-form" action="/ASM_1/account/quenmatkhau" method="post">
            <div class="form-group mb-3">
                <label for="username">Tên đăng nhập</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="Tên tài khoản" required>
            </div>
            <div class="form-group mb-3">
                <label for="email">Nhập Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
            </div>
            <button type="submit" class="btn btn-primary btn-block" id="submit-btn">Gửi yêu cầu</button>
        </form>

        <c:if test="${not empty sessionScope.message}">
            <c:choose>
                <c:when test="${sessionScope.message eq 'Mật khẩu đã được gửi đến email của bạn.'}">
                    <div class="alert alert-info mt-3">
                        ${sessionScope.message}
                    </div>
                    <a href="/ASM_1/login" class="btn btn-primary btn-block mt-3">Đăng nhập</a>
                    <script>
                        document.getElementById('submit-btn').classList.add('hidden');
                    </script>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-danger mt-3">
                        <c:out value="${sessionScope.message}" />
                    </div>
                </c:otherwise>
            </c:choose>
            <c:set var="sessionScope.message" value="" />
        </c:if>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

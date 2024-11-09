<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
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

.social-btn {
    display: flex;
    justify-content: space-around;
    margin-top: 15px;
}

.social-btn a {
    display: inline-block;
    width: 48px;
    height: 48px;
    line-height: 48px;
    text-align: center;
    color: #fff;
    border-radius: 50%;
    background-color: #3b5998; /* Facebook color */
}

.social-btn a.google {
    background-color: #db4437; /* Google color */
}

.social-btn a.apple {
    background-color: #000; /* Apple color */
}

.register-link {
    color: #ff6600;
}

.form-group-inline {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.form-group-inline .form-check {
    margin-bottom: 0;
}

.form-group-inline .form-check-input {
    margin-right: 10px;
}
</style>
</head>
<body>
    <div class="login-container">
        <h2>Đăng nhập</h2>
        <form action="/ASM_1/account/dangnhap" method="post">
            <!-- Cập nhật URL của servlet đăng nhập -->
            <div class="form-group">
                <label for="username">Tài khoản</label>
                <input type="text" class="form-control" id="username" name="id" placeholder="Tài khoản" value="<%= request.getAttribute("savedUsername") != null ? request.getAttribute("savedUsername") : "" %>" required>
                <!-- Đặt tên tương ứng với tham số của servlet -->
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu</label>
                <div class="input-group">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Mật khẩu" required>
                    <!-- Đặt tên tương ứng với tham số của servlet -->
                    <div class="input-group-append">
                        <span class="input-group-text"><i class="fas fa-eye-slash"></i></span>
                    </div>
                </div>
            </div>
            <div class="form-group form-group-inline">
                <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="rememberMe" name="rememberMe">
                    <label class="form-check-label" for="rememberMe">Ghi nhớ tài khoản</label>
                </div>
                <a href="/ASM_1/account/quenmatkhau" class="text-light">Quên mật khẩu?</a>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Đăng nhập</button>
        </form>
        <div class="text-center my-3">Hoặc đăng nhập bằng</div>
        <div class="social-btn">
            <a href="#" class="facebook"><i class="fab fa-facebook-f"></i></a>
            <a href="#" class="google"><i class="fab fa-google"></i></a>
            <a href="#" class="apple"><i class="fab fa-apple"></i></a>
        </div>
        <div class="text-center mt-3">
            <span>Chưa có tài khoản?</span>
            <a href="/ASM_1/account/dangky" class="register-link">Đăng ký miễn phí</a>
        </div>
        <%-- Thêm đoạn mã này để hiển thị thông báo lỗi nếu có --%>
        <%
        if (request.getAttribute("message") != null) {
        %>
        <div class="alert alert-danger">
            <%=request.getAttribute("message")%>
        </div>
        <%
        }
        %>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

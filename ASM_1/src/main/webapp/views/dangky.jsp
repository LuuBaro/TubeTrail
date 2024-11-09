<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #333;
            color: #fff;
        }
        .register-container {
            max-width: 400px;
            margin: 100px auto;
            padding: 20px;
            background-color: #1f1f1f;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .register-container h2 {
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
        .alert {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="register-container">
    <h2>Đăng ký</h2>
   <form action="/ASM_1/account/dangky" method="post">

        <div class="form-group">
            <label for="register-account">Tài khoản</label>
            <input type="text" class="form-control" id="register-account" name="id" placeholder="Tài khoản" required>
        </div>
        <div class="form-group">
            <label for="register-password">Mật khẩu</label>
            <input type="password" class="form-control" id="register-password" name="password" placeholder="Mật khẩu" required>
        </div>
        <div class="form-group">
            <label for="register-email">Email</label>
            <input type="email" class="form-control" id="register-email" name="email" placeholder="Email" required>
        </div>
        <div class="form-group">
            <label for="register-name">Họ và tên</label>
            <input type="text" class="form-control" id="register-name" name="fullName" placeholder="Họ và tên" required>
        </div>
        <button type="submit" class="btn btn-primary btn-block">Đăng ký</button>
        
        <%-- Hiển thị thông báo lỗi nếu có --%>
        <% if (request.getAttribute("message") != null) { %>
            <div class="alert alert-danger">
                <%= request.getAttribute("message") %>
            </div>
        <% } %>
    </form>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

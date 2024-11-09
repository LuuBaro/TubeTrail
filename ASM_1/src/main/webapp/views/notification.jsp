<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thông báo</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #333;
            color: #fff;
        }
        .notification-container {
            max-width: 500px;
            margin: 100px auto;
            padding: 20px;
            background-color: #1f1f1f;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .alert-success {
            background-color: #d4edda;
            color: #155724;
            border-color: #c3e6cb;
        }
        .alert-success a {
            color: #155724;
        }
        .redirecting {
            margin-top: 20px;
            font-size: 16px;
            color: #6c757d;
        }
    </style>
    <script>
        // Chuyển hướng sau 3 giây
        setTimeout(function() {
            window.location.href = "<%= request.getContextPath() %>/main";
        }, 3000);
    </script>
</head>
<body>
    <div class="notification-container">
        <div class="alert alert-success">
            <%= request.getAttribute("message") %>
        </div>
        <div class="redirecting">
            Đang chuyển hướng đến trang chính...
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

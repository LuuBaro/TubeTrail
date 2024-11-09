<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #333;
            color: #fff;
        }
        .reset-password-container {
            max-width: 400px;
            margin: 100px auto;
            padding: 20px;
            background-color: #1f1f1f;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .reset-password-container h2 {
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
    </style>
</head>
<body>



 <div class="reset-password-container">
        <h2>Đặt lại mật khẩu</h2>
        <form id="reset-password-form">
            <div class="form-group">
                <label for="new-password">Mật khẩu mới</label>
                <input type="password" class="form-control" id="new-password" placeholder="Mật khẩu mới">
            </div>
            <div class="form-group">
                <label for="confirm-password">Xác nhận mật khẩu</label>
                <input type="password" class="form-control" id="confirm-password" placeholder="Xác nhận mật khẩu">
            </div>
            <button type="submit" class="btn btn-primary btn-block">Đặt lại mật khẩu</button>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
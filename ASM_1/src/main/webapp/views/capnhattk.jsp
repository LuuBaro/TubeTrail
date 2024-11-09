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
        .update-account-container {
            max-width: 400px;
            margin: 100px auto;
            padding: 20px;
            background-color: #1f1f1f;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .update-account-container h2 {
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
 <div class="update-account-container">
    <h2>Cập nhật tài khoản</h2>
    <form id="update-account-form" action="/ASM_1/account/capnhat" method="post">
        <input type="hidden" name="action" value="updateUser"> <!-- Thêm giá trị cho action -->
        <div class="form-group">
            <label for="update-username">Tên đăng nhập</label>
            <input type="text" class="form-control" id="update-username" name="userId" value="${user.id}" placeholder="Tên đăng nhập" required >
        </div>
        <div class="form-group">
            <label for="update-password-old">Mật khẩu cũ</label>
            <input type="password" class="form-control" id="update-password-old" name="password-old" placeholder="Nhập mật khẩu cũ" required>
        </div>
        <div class="form-group">
            <label for="update-password-new">Mật khẩu mới</label>
            <input type="password" class="form-control" id="update-password-new" name="password-new" placeholder="Nhập mật khẩu mới (nếu có)">
        </div>
        <div class="form-group">
            <label for="update-name">Họ và tên</label>
            <input type="text" class="form-control" id="update-name" name="fullname" value="${user.fullName}" placeholder="Họ và tên" required>
        </div>
        <div class="form-group">
            <label for="update-email">Email</label>
            <input type="email" class="form-control" id="update-email" name="email" value="${user.email}" placeholder="Địa chỉ email" required>
        </div>
        <button type="submit" class="btn btn-primary btn-block">Cập nhật</button>
        <!-- Hiển thị thông báo nếu có -->
        <c:if test="${not empty requestScope.errorMessage}">
            <div class="alert alert-danger mt-3">${requestScope.errorMessage}</div>
        </c:if>
        <c:if test="${not empty param.message}">
            <div class="alert alert-success mt-3">${param.message}</div>
        </c:if>
    </form>
</div>


    
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
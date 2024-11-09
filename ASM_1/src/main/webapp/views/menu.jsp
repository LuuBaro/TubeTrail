<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<nav class="container navbar navbar-expand-lg navbar-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="#"> <img
			src="https://images.fptplay.net/media/photo/OTT/2024/02/26/1708914022_P5N.png"
			width="100" height="auto" class="d-inline-block align-text-top">
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="/ASM_1/main">Trang chủ</a></li>
				<li class="nav-item"><a class="nav-link" href="/ASM_1/yeuthich">Yêu
						thích</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Giới
						thiệu</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Kho phim</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Liên hệ</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdownMore"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">Tài khoản
						</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdownMore">
						<li><a class="dropdown-item" href="/ASM_1/capnhat">Cập nhật tài khoản</a></li>
						
					</ul></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdownAdmin"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">Admin</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdownAdmin">
						<li><a class="dropdown-item" href="/ASM_1/admin/all">Quản lý
								video</a></li>
						<li><a class="dropdown-item" href="/ASM_1//admin/NguoiSD">Quản
								lý người sử dụng</a></li>
						<li><a class="dropdown-item" href="/ASM_1/admin/baocao">Báo cáo</a></li>
					</ul></li>
			</ul>
			<form class="d-flex">
    <!-- Nếu người dùng đã đăng nhập -->
    <c:if test="${sessionScope.loggedIn}">
        <a href="/ASM_1/account/logout" class="btn btn-outline-light me-2" type="button">Đăng xuất</a>
    </c:if>
    <!-- Nếu người dùng chưa đăng nhập -->
    <c:if test="${!sessionScope.loggedIn}">
        <a href="/ASM_1/account/dangnhap" class="btn btn-outline-light me-2" type="button">Đăng nhập</a>
    </c:if>
</form>

		</div>
	</div>
</nav>

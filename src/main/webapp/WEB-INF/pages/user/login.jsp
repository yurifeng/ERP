<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>ERP登陆页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${base }/assets/css/reset.css">
<link rel="stylesheet" href="${base }/assets/css/supersized.css">
<link rel="stylesheet" href="${base }/assets/css/style.css">
</head>
<body>
	<div class="page-container">
		<h1>登陆</h1>
		<form action="${base }/permission/user/login" method="post">

			<c:if test="${param.info == 1 }">
				<b style='color: red'>用户名或者密码有误!</b>
			</c:if>
			<c:if test="${param.info == 2 }">
				<b style='color: red'>验证码有误!</b>
			</c:if>
			<c:if test="${param.info == 3 }">
				<b style='color: red'>请先登陆!</b>
			</c:if>

			<input type="text" name="username" class="username"
				placeholder="Username" value="${username }"> <input
				type="password" name="password" class="password"
				placeholder="Password" value="${password }"> <br /> <br />
			<input type="checkbox" name="nologin" value="0">&nbsp;&nbsp;七天免登陆
			<button type="submit">Sign me in</button>
			<div class="error">
				<span>+</span>
			</div>
		</form>
		<div class="connect">
			<p>Or connect with:</p>
			<p>
				<a class="facebook" href=""></a> <a class="twitter" href=""></a>
			</p>
		</div>
	</div>
	<script src="${base }/assets/js/jquery-1.8.2.min.js"></script>
	<script src="${base }/assets/js/supersized.3.2.7.min.js"></script>
	<script src="${base }/assets/js/supersized-init.js"></script>
	<script src="${base }/assets/js/scripts.js"></script>
</body>
</html>
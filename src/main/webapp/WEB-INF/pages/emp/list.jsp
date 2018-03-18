<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>ERP员工列表页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${base}/assets/css/list.css" rel="stylesheet">
<!--引用本地Bootstrap-->
<link href="${base}/assets/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- 兼容性 -->
<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#example-navbar-collapse">
					<span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">ERP</a>
			</div>
			<div class="collapse navbar-collapse" id="example-navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="${base }/permission/user/preLogin">首页</a></li>
					<li class="active"><a href="#">员工列表</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<h2>
		欢迎,<a href="${base }/permission/user/logout">${user.username }</a>
	</h2>
	
	<!-- JS表格渲染数据 -->
	<table id="data" class="table table-hover">
	
	</table>

	<script src="${base }/assets/js/jquery-3.1.1.min.js"></script>
	<script src="${base }/assets/dist/js/bootstrap.min.js"></script>
	<script src="${base }/assets/js/scripts.js"></script>
	<script src="${base }/assets/My97DatePicker/WdatePicker.js"></script>

	<script>
		/* 页面加载完毕,等价于定义一个function在body中使用onLoad事件触发 */
		$(function() {
			$("#data").load(
					"${base}/permission/emp/getData?pageNow=1&pageSize=3");
		});

		function queryPage(pageNow, pageSize) {
			$("#data").load(
					"${base}/permission/emp/getData?pageNow=" + pageNow
							+ "&pageSize=" + pageSize);
		}
	</script>
</body>
</html>

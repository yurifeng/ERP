<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<thead>
	<tr>
		<th>编号</th>
		<th>头像</th>
		<th>姓名</th>
		<th>入职日期</th>
		<th>性别</th>
		<th>工资</th>
		<th>操作</th>
	</tr>
</thead>
<c:if test="${!empty page }" var="hasEmp">
	<tbody>
		<c:forEach items="${page.pageDatas }" var="e" varStatus="status">
			<tr>
				<th>${status.count }</th>
				<th><a
					href="${base }/permission/emp/download?path=${e.headImage}"> <img
						class="head" src="/pic/${e.headImage}">
				</a></th>
				<th>${e.name }</th>
				<th><fmt:formatDate value="${e.hiredate }" pattern="yyyy-MM-dd" /></th>
				<th><c:if test="${e.gender == 'MALE'}" var="isMale">
							男
						</c:if> <c:if test="${!isMale }">
							女
						</c:if></th>
				<th>${e.salary }</th>
				<th><input type="button" class="btn btn-info"
					data-toggle="modal" data-target="#myModal${e.id}" value="编辑">
					<!-- 模态框 --> <!-- 模态框（Modal） -->
					<div class="modal fade" id="myModal${e.id}" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">编辑员工</h4>
								</div>
								<form action="${base }/permission/emp/edit" method="post"
									enctype="multipart/form-data">
									<div class="modal-body">
										<input type="hidden" name="id" value="${e.id }" />
										<table class="table table-bordered">
											<tr>
												<th>头像</th>
												<th><img class="head" src="/pic/${e.headImage }" /> <input
													type="file" name="file"></th>
											</tr>
											<tr>
												<th>姓名</th>
												<th><input type="text" name="name" value="${e.name }"></th>
											</tr>
											<tr>
												<th>入职日期</th>
												<th><input type="text" class="Wdate" name="hiredate"
													value="<fmt:formatDate value='${e.hiredate }' pattern='yyyy-MM-dd'/>"
													onclick="WdatePicker()"></th>
											</tr>
											<tr>
												<th>性别</th>
												<th>男&nbsp;<input type="radio" name="gender"
													value="MALE"
													<c:if test="${e.gender == 'MALE' }">
																checked
															</c:if>>&nbsp;&nbsp;
													女&nbsp;<input type="radio" name="gender" value="FEMALE"
													<c:if test="${e.gender == 'FEMALE' }">
																checked
															</c:if>>
												</th>
											</tr>
											<tr>
												<th>工资</th>
												<th><input type="text" name="salary"
													value="${e.salary }"></th>
											</tr>
										</table>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">关闭</button>
										<button type="submit" class="btn btn-primary">提交更改</button>
									</div>
								</form>
							</div>
						</div>
					</div> <input type="button" class="btn btn-danger" value="删除"
					onclick="deleteEmp(${e.id})"></th>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<th colspan="7">
				<div style="margin: auto">
					<ul class="pagination">
						<li
							<c:if test="${!page.isHavePrePage }">
				class="disabled"
			</c:if>><a
							href="javascript:queryPage(1,${page.pageSize })">&lt;&lt;</a></li>
						<li
							<c:if test="${!page.isHavePrePage }">
				class="disabled"
			</c:if>><a
							<c:if test="${page.isHavePrePage }" var="notLast">
					href="javascript:queryPage(${page.pageNow-1 },${page.pageSize })"
				</c:if>
							<c:if test="${!notLast }">
					href="#"
				</c:if>>&lt;</a></li>


						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						

						<li
							<c:if test="${!page.isHaveNextPage }">
				class="disabled"
			</c:if>><a
							<c:if test="${page.isHaveNextPage }" var="notLast">
					href="javascript:queryPage(${page.pageNow+1 },${page.pageSize })"
				</c:if>
							<c:if test="${!notLast }">
					href="#"
				</c:if>>&gt;</a></li>
						<li
							<c:if test="${!page.isHaveNextPage }">
				class="disabled"
			</c:if>><a
							href="javascript:queryPage(${page.totalPages },${page.pageSize })">&gt;&gt;</a></li>
					</ul>
				</div>
			</th>
		</tr>
	</tfoot>
</c:if>
<c:if test="${!hasEmp }">
	<tbody>
		<tr>
			<th colspan="7">暂无员工信息...</th>
		</tr>
	</tbody>
</c:if>

<script>
function deleteEmp(id) {
	/*var r = confirm('确定要删除该员工吗?');
	if (r) {
		window.location = '/erp/permission/emp/delete?id=' + id;
	}*/

	//AJAX删除
	var r = confirm('确定要删除该员工吗?');
	if (r) {
		var xmlhttp;
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				//alert(xmlhttp.responseText);
				if (xmlhttp.responseText == '1') {
					//注意:此处千万不能使用location.reload()刷新页面,这样就失去了AJAX的意义
					//此处应该使用jQuery.load(加载一个jsp页面片段进行局部刷新)
					//如果不使用jQuery,则使用原生JS,就要自己手动通过dom操作去拼接

					$(function() {
						$("#data").load("/erp/permission/emp/getData?pageNow=${page.pageNow}&pageSize=${page.pageSize}");
					});
				}
			}
		};
		xmlhttp.open("get", "/erp/permission/emp/delete?id=" + id, true);
		xmlhttp.send();
	}
}
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function studentDelete(studentId){
		if(confirm("确定要删除这条记录吗?")){
			$.post("student!deleteStudent",{id:studentId},
				function(result){
					alert(result);
					var result=eval('('+result+')');
					if(result.success){
						alert("删除成功！");
						window.location.href="${pageContext.request.contextPath}/student!list";
					}else{
						alert("删除失败");
					}
				}
			);
		}
	}
</script>
</head>
<body>
<div class="data_list">
	<div class="data_info">
		<p>考生信息管理</p>
	</div>
	<div class="search_content">
		<form action="${pageContext.request.contextPath}/student!list" method="post">
			<table align="center">
				<tr>
					<td><label>准考证号：</label></td>
					<td><input type="text" id="s_id" name="s_student.id" value="${s_student.id }"/></td>
					<td>&nbsp;</td>
					<td><label>姓名：</label></td>
					<td><input type="text" id="s_name" name="s_student.name" value="${s_student.name }"/></td>
					<td>&nbsp;</td>
					<td><button class="btn btn-primary" style="margin-bottom: 8px" type="submit">查询</button></td>
				</tr>
			</table>
		</form>
		<button style="float: right;margin-bottom: 8px;" class="btn btn-mini btn-primary" type="button" onclick="javascript:window.location='student!preSave'">添加考生信息</button>
	</div>
	<div class="data_content">
		<table class="table table-bordered table-hover">
			<tr>
				<th>序号</th>
				<th>准考证号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>身份证</th>
				<th>密码</th>
				<th>专业</th>
				<th>操作</th>
			</tr>
			<c:forEach var="student" items="${studentList }" varStatus="status">
			<tr>
				<td>${status.index+1 }</td>
				<td>${student.id }</td>
				<td>${student.name }</td>
				<td>${student.sex }</td>
				<td>${student.cardNo }</td>
				<td>${student.password }</td>
				<td>${student.prefession }</td>
				<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='student!preSave?id=${student.id}'">修改</button>&nbsp;&nbsp;<button class="btn btn-mini btn-danger" type="button" onclick="studentDelete('${student.id}')">删除</button></td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div>
		<div class="pagination pagination-centered">
			<ul>
				${pageCode }
			</ul>
		</div>
	</div>
</div>
</body>
</html>
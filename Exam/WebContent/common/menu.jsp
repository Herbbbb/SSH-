<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function logout(){
		if(confirm("您确定要推出系统吗？")){
			window.location.href = "student!logout";
		}
	}
	
	function logout2(){
		if(confirm("您确定要退出系统吗？")){
			window.location.href="manager!logout";
		}
	}
</script>
<div class="navbar">
  <div class="navbar-inner">
    <a class="brand" href="main.jsp">首页</a>
    <ul class="nav">
      <!-- 判断登陆用的身份，如果flag为1则是管理员登陆，否则为学生登陆 -->
      <c:choose>
      	<c:when test="${currentUser.flag==1 }">
      		  <li><a href="${pageContext.request.contextPath}/student!list">考生信息管理</a></li>
		      <li><a href="${pageContext.request.contextPath}/exam!list">考生成绩查询</a></li>
		      <li><a href="${pageContext.request.contextPath}/paper!paperList">试卷管理</a></li>
		      <li><a href="${pageContext.request.contextPath}/question!list">题目管理</a></li>
		      <li><a href="javascript:logout2()">退出系统</a></li>
      	</c:when>
      	<c:otherwise>
      		<li><a href="${pageContext.request.contextPath}/paper!list">在线考试</a></li>
		      <li><a href="${pageContext.request.contextPath}/exam!getExams?s_exam.student.id=${currentUser.id}">成绩查询</a></li>
		      <li><a href="${pageContext.request.contextPath}/student!preUpdatePassword">修改密码</a></li>
		      <li><a href="javascript:logout()">退出系统</a></li>
      	</c:otherwise>
      </c:choose>
    </ul>
  </div>
</div>
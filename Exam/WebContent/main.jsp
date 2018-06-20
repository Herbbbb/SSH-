<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/style/exam.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>


</head>
<%
	String mainPage=(String)request.getAttribute("mainPage");
	if(mainPage==null || mainPage.equals("")){
		mainPage="common/default.jsp";
	}
%>
<body>
	<table width:"1000px" aligin="center">
		<tr>
			<td>
				<jsp:include page="common/head.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td>
				<jsp:include page="common/menu.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td>
				<jsp:include page="<%=mainPage %>"></jsp:include>
			</td>
		</tr>
		<tr>
			<td>
				<jsp:include page="common/foot.jsp"></jsp:include>
			</td>
		</tr>
	</table>
</body>
</html>
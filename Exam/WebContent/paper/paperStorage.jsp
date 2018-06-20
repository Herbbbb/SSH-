<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkForm(){
		var paperName=$("#paperName").val();
		if(paperName==null || paperName==""){
			$("#error").html("试卷名称不能为空！");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<div class="data_list">
	<div class="data_info">
		<p>${title }</p>
	</div>
	
	<div class="data_content">
		<form action="paper!savePaper" method="post" onsubmit="return checkForm()">
		<table width="40%" align="center">
			<tr>
				<td><label>试卷名称：</label></td>
				<td><input type="text" id="paperName" name="paper.paperName" value="${paper.paperName }"/></td>
			</tr>
			<tr>
				<td>
					<input type="hidden" id="joinDate" name="paper.joinDate" value="${paper.joinDate }"/>
					<input type="hidden" id="id" name="paperId" value="${paper.id }"/>
					<button class="btn btn-primary" type="submit">保存</button>
				</td>
				<td>
					<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
					&nbsp;&nbsp;<font id="error" color="red">${error }</font>
				</td>
			</tr>
		</table>
		</form>
	</div>
</div>
</body>
</html>
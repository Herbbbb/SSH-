<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkForm(){
		var name=$("#name").val();
		var sex=$("#sex").val();
		var cardNo=$("#cardNo").val();
		var prefession=$("#prefession").val();
		var password=$("#password").val();
		
		if(name==null || name==""){
			$("#error").html("姓名不能为空！");
			return false;
		}
		if(sex==null || sex==""){
			$("#error").html("请选择性别！");
			return false;
		}
		if(cardNo==null || cardNo==""){
			$("#error").html("身份证不能为空！");
			return false;
		}
		if(prefession==null || prefession==""){
			$("#error").html("专业不能为空！");
			return false;
		}
		if(password==null || password==""){
			$("#error").html("密码不能为空！");
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
		<form action="student!saveStudent" method="post" onsubmit="return checkForm()">
		<table width="80%" align="center">
			<tr>
				<td><label>姓名：</label></td>
				<td><input type="text" id="name" name="student.name" value="${student.name }"/></td>
				<td>&nbsp;</td>
				<td><label>性别：</label></td>
				<td>
					<select id="sex" name="student.sex">
						<option value="">请选择性别：</option>
						<option value="男" ${student.sex=='男'?'selected':'' }>男</option>
						<option value="女" ${student.sex=='女'?'selected':'' }>女</option>					</select>
				</td>
			</tr>
			<tr>
				<td><label>身份证：</label></td>
				<td colspan="4">
					<input type="text" id="cardNo" name="student.cardNo" value="${student.cardNo }" class="input-large"/> 
				</td>
			</tr>
			<tr>
				<td><label>专业：</label></td>
				<td><input type="text" id="prefession" name="student.prefession" value="${student.prefession }"/></td>
				<td></td>
				<td><label>密码：</label></td>
				<td><input type="text" id="password" name="student.password" value="${student.password }"/></td>
			</tr>
			<tr>
				<td>
					<input type="hidden" id="id" name="student.id" value="${student.id }"/><button class="btn btn-primary" type="submit">保存</button>
				</td>
				<td colspan="4">
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
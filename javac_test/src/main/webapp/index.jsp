<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
    
<%
String code = (String)session.getAttribute("code");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="Shift_JIS">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/CSS/style.css">
<title>Insert title here</title>
</head>
<body>
	<div class = "m">
	<h2>�v���O�������L�q���Ă��������B</h2>
	<form action="javacServlet">
		<textarea rows="40" cols="75" wrap="hard" name="code" id="code">
public class Main{
    public static void main(String[] args) {
        System.out.println(Integer.valueOf(args[0])+Integer.valueOf(args[1]));
    }
}
		</textarea>
		<p>���ԍ��w��</p>
		<select name="number">
			<option value="0">�I�����Ă�������</option>
			<option value="1">���ԍ�1</option>
			<option value="2">���ԍ�2</option>
		</select>
		<input type="submit" onclick="location.href='./exe/index2.jsp'"/>
	</form>
	</div>
	
	<h2>��蕶���X�g</h2>
		<ol>
			<li>HelloWorld���o�͂��Ă��������B</li>
			<li>�����Z�������ʂ�\������v���O�������쐬���Ȃ���</li>
		</ol>
</body>
</html>
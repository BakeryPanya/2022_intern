<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="Shift_JIS">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/CSS/style.css">
<title>Insert title here</title>
</head>
<body>
	<div class = "m">
	<h1>Helloworld���o�͂��Ȃ����B</h1>
	<form action="javacServlet">
		<textarea rows="40" cols="75" wrap="hard" name="code">
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
	<a href=./exe/index2.jsp>��ʑJ�ڂ͂������N���b�N�I</a>
	</div>
</body>
</html>
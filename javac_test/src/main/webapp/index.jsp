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
	<h1>Helloworldを出力しなさい。</h1>
	<form action="javacServlet">
		<textarea rows="40" cols="75" wrap="hard" name="code">
public class Main{
    public static void main(String[] args) {
        System.out.println(Integer.valueOf(args[0])+Integer.valueOf(args[1]));
    }
}
		</textarea>
		<p>問題番号指定</p>
		<select name="number">
			<option value="0">選択してください</option>
			<option value="1">問題番号1</option>
			<option value="2">問題番号2</option>
		</select>
		<input type="submit" onclick="location.href='./exe/index2.jsp'"/>
	</form>
	<a href=./exe/index2.jsp>画面遷移はここをクリック！</a>
	</div>
</body>
</html>
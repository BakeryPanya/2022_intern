<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="Shift_JIS">
<link>
<title>Insert title here</title>
</head>
<body>
	<h1>Helloworldを出力しなさい。</h1>
	<form action="javacServlet">
		<textarea rows="40" cols="75" wrap="hard" name="code">
public class Main{
    public static void main(String[] args) {
        System.out.println("Helloworld");
    }
}
		</textarea>
		<input type="submit"/>
	</form>
</body>
</html>
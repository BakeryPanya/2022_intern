<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<%
//リクエストスコープからのデータの取得

String testcas = (String)session.getAttribute("testcase");
String exe = (String)session.getAttribute("exe");
String test_num = (String)session.getAttribute("test_num");
String kekka = (String)session.getAttribute("kekka");
String kekka_error = (String)session.getAttribute("kekka_error");


%>

<!DOCTYPE html>
<html>
<head>
<meta charset="Shift_JIS">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/CSS/style.css">

<title>Insert title here</title>
</head>
<body>
	<div id="compile">
	
	</div>
	
	<div class = "testcase_1">
		<p id=kekka_1>テストケースの実行結果はありません</p>
		<p id=testcase_1>想定している結果はありません</p>
		<p id=exe_1>正誤判定の結果はありません</p>
	</div>
	
	<div class = "testcase_2">
		<p id=kekka_2>テストケースの実行結果はありません</p>
		<p id=testcase_2>想定している結果はありません</p>
		<p id=exe_2>正誤判定の結果はありません</p>
	</div>
	
	<div class = "testcase_3">
		<p id=kekka_3>テストケースの実行結果はありません</p>
		<p id=testcase_3>想定している結果はありません</p>
		<p id=exe_3>正誤判定の結果はありません</p>
	</div>
	
	<div class = "testcase_4">
		<p id=kekka_4>テストケースの実行結果はありません</p>
		<p id=testcase_4>想定している結果はありません</p>
		<p id=exe_4>正誤判定の結果はありません</p>
	</div>
	
	<div class = "testcase_5">
		<p id=kekka_5>テストケースの実行結果はありません</p>
		<p id=testcase_5>想定している結果はありません</p>
		<p id=exe_5>正誤判定の結果はありません</p>
	</div>

<script>
	let test_num = Number(<%= test_num %>);
	let test= String("<%= testcas %>");
	let exe = String("<%= exe %>");
	let kekka = String("<%= kekka %>");
	
	let s_testcase;
	let s_exe;
	let s_kekka;
	let s_kekka_error;
	alert(test);
	for(var i=1;i<=test_num;i++){
		let s_testcase = "testcase_"+i;
		let a = document.getElementById(s_testcase)
		a.innerHTML = test;
		let s_exe = "exe_" + i;
		let b = document.getElementById(s_exe)
		b.innerHTML = exe;
		let s_kekka = "kekka_" + i;
		let c = document.getElementById(s_kekka)
		c.innerHTML = kekka;
	  }
    
	
</script>
</body>
</html>
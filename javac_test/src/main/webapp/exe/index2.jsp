<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<%
//���N�G�X�g�X�R�[�v����̃f�[�^�̎擾

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
		<p id=kekka_1>�e�X�g�P�[�X�̎��s���ʂ͂���܂���</p>
		<p id=testcase_1>�z�肵�Ă��錋�ʂ͂���܂���</p>
		<p id=exe_1>���딻��̌��ʂ͂���܂���</p>
	</div>
	
	<div class = "testcase_2">
		<p id=kekka_2>�e�X�g�P�[�X�̎��s���ʂ͂���܂���</p>
		<p id=testcase_2>�z�肵�Ă��錋�ʂ͂���܂���</p>
		<p id=exe_2>���딻��̌��ʂ͂���܂���</p>
	</div>
	
	<div class = "testcase_3">
		<p id=kekka_3>�e�X�g�P�[�X�̎��s���ʂ͂���܂���</p>
		<p id=testcase_3>�z�肵�Ă��錋�ʂ͂���܂���</p>
		<p id=exe_3>���딻��̌��ʂ͂���܂���</p>
	</div>
	
	<div class = "testcase_4">
		<p id=kekka_4>�e�X�g�P�[�X�̎��s���ʂ͂���܂���</p>
		<p id=testcase_4>�z�肵�Ă��錋�ʂ͂���܂���</p>
		<p id=exe_4>���딻��̌��ʂ͂���܂���</p>
	</div>
	
	<div class = "testcase_5">
		<p id=kekka_5>�e�X�g�P�[�X�̎��s���ʂ͂���܂���</p>
		<p id=testcase_5>�z�肵�Ă��錋�ʂ͂���܂���</p>
		<p id=exe_5>���딻��̌��ʂ͂���܂���</p>
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
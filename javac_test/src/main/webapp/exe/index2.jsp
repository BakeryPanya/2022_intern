<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<%
//���N�G�X�g�X�R�[�v����̃f�[�^�̎擾

String testcas1 = (String)session.getAttribute("testcase0");//�o�͂̐��딻��̓��� test_num��
String testcas2 = (String)session.getAttribute("testcase1");
String testcas3 = (String)session.getAttribute("testcase2");
String testcas4 = (String)session.getAttribute("testcase3");
String testcas5 = (String)session.getAttribute("testcase4");
String exe1 = (String)session.getAttribute("exe0");//�o�͌��ʂ̐��딻��@test_num��
String exe2 = (String)session.getAttribute("exe1");
String exe3 = (String)session.getAttribute("exe2");
String exe4 = (String)session.getAttribute("exe3");
String exe5 = (String)session.getAttribute("exe4");
String test_num = (String)session.getAttribute("test_num");//��ł���
String kekka1 = (String)session.getAttribute("kekka0");//kekka������
String kekka2 = (String)session.getAttribute("kekka1");
String kekka3 = (String)session.getAttribute("kekka2");
String kekka4 = (String)session.getAttribute("kekka3");
String kekka5 = (String)session.getAttribute("kekka4");
String kekka_error1 = (String)session.getAttribute("kekka_error0");
String kekka_error2 = (String)session.getAttribute("kekka_error1");
String kekka_error3 = (String)session.getAttribute("kekka_error2");
String kekka_error4 = (String)session.getAttribute("kekka_error3");
String kekka_error5 = (String)session.getAttribute("kekka_error4");
String compile = (String)session.getAttribute("compile");
String compile_error = (String)session.getAttribute("compile_error");
String check = (String)session.getAttribute("check");
String code = (String)session.getAttribute("code");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="Shift_JIS">
<link rel="stylesheet" type="text/css" href="../CSS/style2.css">

<title>Insert title here</title>
</head>
<body>
	
	<h1>����</h1>
	
	<div class = "compile">
		<p>�R���p�C�����ʂ͂�����ł�</p>
		<p id="comp_error">�R���p�C���ɖ��͂���܂���</p>
		<p id="error">����͒�~���Ă��܂���</p>
	</div>
	
	<div class = "testcase_1">
		<h3>�e�X�g�P�[�X1</h3>
		<p>���s����</p>
		<p id="kekka_1">�e�X�g�P�[�X�̎��s���ʂ͂���܂���</p>
		<p>�z�茋�ʂ�</p>
		<p id="testcase_1">�z�肵�Ă��铚���͂���܂���</p>
		<p>���딻�茋��</p>
		<p id="exe_1">���딻��̌��ʂ͂���܂���</p>
	</div>
	
	<div class = "testcase_2">
		<h3>�e�X�g�P�[�X2</h3>
		<p>���s����</p>
		<p id="kekka_2">�e�X�g�P�[�X�̎��s���ʂ͂���܂���</p>
		<p>�z�茋��</p>
		<p id="testcase_2">�z�肵�Ă��铚���͂���܂���</p>
		<p>���딻�茋�ʂ͂�����ł�</p>
		<p id="exe_2">���딻��̌��ʂ͂���܂���</p>
	</div>
	
	<div class = "testcase_3">
		<h3>�e�X�g�P�[�X3</h3>
		<p>���s����</p>
		<p id="kekka_3">�e�X�g�P�[�X�̎��s���ʂ͂���܂���</p>
		<p>�z�茋��</p>
		<p id="testcase_3">�z�肵�Ă��铚���͂���܂���</p>
		<p>���딻�茋��</p>
		<p id="exe_3">���딻��̌��ʂ͂���܂���</p>
	</div>
	
	<div class = "testcase_4">
		<h3>�e�X�g�P�[�X4</h3>
		<p>���s����</p>
		<p id="kekka_4">�e�X�g�P�[�X�̎��s���ʂ͂���܂���</p>
		<p>�z�茋��</p>
		<p id="testcase_4">�z�肵�Ă��铚���͂���܂���</p>
		<p>���딻�茋��</p>
		<p id="exe_4">���딻��̌��ʂ͂���܂���</p>
	</div>
	
	<div class = "testcase_5">
		<h3>�e�X�g�P�[�X5</h3>
		<p>���s����</p>
		<p id="kekka_5">�e�X�g�P�[�X�̎��s���ʂ͂���܂���</p>
		<p>�z�茋��</p>
		<p id="testcase_5">�z�肵�Ă���e�X�g�P�[�X�͂���܂���</p>
		<p>���딻�茋��</p>
		<p id="exe_5">���딻��̌��ʂ͂���܂���</p>
	</div>
	
	<div class = "hantei">
		<h3 id = check>�e�X�g�P�[�X���ʕ\��</h3>
	</div>
	
	<div class="write">
		<button onclick="location.href='../index.jsp'">�v���O������ʂɖ߂�</button>
	</div>

<script>
	
	
	let check = String('<%= check %>');
	let test_num = Number(<%= test_num %>);
	let comp = String('<%= compile %>');
	let comp_error = String('<%= compile_error %>');
	let test = [String("<%= testcas1 %>"),String("<%= testcas2 %>"),String("<%= testcas3 %>"),String("<%= testcas4 %>"),String("<%= testcas5 %>")];
	let exe = [String("<%= exe1 %>") , String("<%= exe2 %>") , String("<%= exe3 %>"),String("<%= exe4 %>"),String("<%= exe5 %>")];
	let kekka = [String("<%= kekka1 %>"),String("<%= kekka2 %>"),String("<%= kekka3 %>"),String("<%= kekka4 %>"),String("<%= kekka5 %>")];
	let kekka_error = [String("<%= kekka_error1 %>"),String("<%= kekka_error2 %>"),String("<%= kekka_error3 %>"),String("<%= kekka_error4 %>"),String("<%= kekka_error5 %>")];
	
	if(comp_error !== "null"){
		
		let e = document.getElementById("comp_error");
		e.innerHTML = comp_error;
		let f = document.getElementById("error");
		f.innerHTML = "������~���Ă��܂��B���̎��s���ʂ͑O�̎��s���ʂł��B"
	}else{
		for(var i=1;i<=test_num;i++){
			
			let s_testcase = "testcase_"+i;
			let a = document.getElementById(s_testcase);
			a.innerHTML = test[i-1];
			let s_exe = "exe_" + i;
			let b = document.getElementById(s_exe);
			b.innerHTML = exe[i-1];
			let s_kekka = "kekka_" + i;
			let c = document.getElementById(s_kekka);
			c.innerHTML = kekka[i-1];
			let g = document.getElementById("error");
			g.innerHTML = "����͒�~���Ă��܂���";
		
	  }	
		let h = document.getElementById("check");
		h.innerHTML = check;
	}
	
	
</script>
</body>
</html>
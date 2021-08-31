<%@ page pageEncoding="Windows-31J"
	contentType="text/html;charset=Windows-31J" %>

<%--JSTL 1.1.2 core �^�O���C�u����--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ja">
<head>
<meta charset="SJIS">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>���[�U�[�o�^</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="�����ɃT�C�g���������܂�">
<meta name="keywords" content="�L�[���[�h1,�L�[���[�h2,�L�[���[�h3,�L�[���[�h4,�L�[���[�h5">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/fixmenu_pagetop.css">
<script type="text/javascript" src="js/openclose.js"></script>
<script type="text/javascript" src="js/fixmenu_pagetop.js"></script>
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

<style>
	header{
		height:0.1%;
	}
	input{
        background-color:rgb(216, 209, 178);
    }
    textarea{
        background-color:rgb(216, 209, 178);
    }
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
$(function(){
$('a[href^="#"]').click(function() {
var speed = 800;
var href= $(this).attr("href");
var target = $(href == "#" || href == "" ? 'html' : href);
var position = target.offset().top;
$('body,html').animate({scrollTop:position}, speed, 'swing');
return false;
});
});
</script>
<!--���͍��ڃ`�F�b�N�p�̃X�N���v�g-->

<script type="text/javascript">
	function nullcheck(){

			var flag = 0;

			var judge = false;

			// �ݒ�J�n�i�K�{�ɂ��鍀�ڂ�ݒ肵�Ă��������j

			if(document.form1.name.value == ""){ // �utitle�v�̓��͂��`�F�b�N

				flag = 1;

			}
			else if(document.form1.pass.value == ""){ // �u�R�����g�v�̓��͂��`�F�b�N

				flag = 1;
			}


			// �ݒ�I��
			if(flag){

			window.alert('�K�{���ڂɖ����͂�����܂���'); // ���͘R�ꂪ����Όx���_�C�A���O��\��

			return false; // ���M�𒆎~

		}
			else{

			judge = lengthcheck();

				if(judge == false){

					return false;
				}else{

				getRandom();

				return true; // ���M�����s

				}

		}

	}
	/*���͕��������蕶�����𒴂��Ă��Ȃ����̃`�F�b�N*/

	function lengthcheck(){�@//�������`�F�b�N�̊֐�
		var flag2 = 0;

		if(document.form1.name.value.length > 20){	//���O�̕������`�F�b�N

			flag2 = 1;

			window.alert("���O��20�����ȓ��œ��͂��Ă�������");

		}else if(document.form1.pass.value.length > 20){	//�^�C�g���̕������`�F�b�N

			flag2 = 1;

			window.alert("�p�X���[�h��20�����ȓ��œ��͂��Ă�������");

		}else if(document.form1.pass.value != document.form1.re_pass.value){	//���e���e�̕������`�F�b�N

			flag2 = 1;

			window.alert("�p�X���[�h���Ⴂ�܂��B")

		}

		if(flag2){

			return false; // ���M�𒆎~

		}
			else{

			return true; // ���M�����s

		}
	}
</script>

<!-- ���d���e�֎~�p�ɗ����𐶐�����X�N���v�g-->

<script type="text/javascript">
    function getRandom(){
        let num = Math.floor(Math.random()*10000)+1;
        document.getElementById('formnumber').value = num;
    }
</script>

</head>

<body>

<header >

<h1 id="logo"><a href="postthread"><img src="images/logo1.png" alt="���e���"></a></h1>
<!--PC�p�i801px�ȏ�[���j���j���[-->
<nav id="menubar">
<ul>
<li id="menu1"><a href="addthread"><img src="images/menu_about1.png" alt="�{�����"></a></li>
<li id="menu2"><a href="index"><img src="images/menu_about.png" alt="�g�b�v�ɖ߂�"></a></li>
<!--
<li id="menu3"><a href="#link"><img src="images/menu_link.png" alt="�����N"></a></li>
<li id="menu4"><a href="#"><img src="images/menu_instagram.png" alt="�C���X�^�O����"></a></li>
-->
</ul>
</nav>
</header>

<div class="contents bg3"><!--contents�u���b�N�J�n����������������������������������������������������������������������������������������������������-->

<section>

<h2>Login<span></span></h2>

<h3>���O�C��</h3>
	<form method='post' action='${requestScope.context}${requestScope.target}' name="form1" onSubmit="return nullcheck()">
	<input id="formnumber" type="hidden" name="checknumber" value="">
	���O:<br>
	<input type='text' name='name' placeholder="20�����ȓ��L��" pattern=".*\S+.*" required><br>
	�p�X���[�h:<br>
	<input type='text' name='pass' placeholder="20�����ȓ��L��" pattern=".*\S+.*" required><br>
	�p�X���[�h���ē��́F<br>
	<input name='re_pass' pattern=".*\S+.*" required></input><br>

	<c:out value='${nouser}'></c:out><br>

	<input type='submit' value='���O�C��'><br>

	<input type="button" onclick="location.href='register'"value="�V�K�o�^"><br>
    </form>

</section>

</body>

</html>
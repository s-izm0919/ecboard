<%@ page pageEncoding="Windows-31J"
	contentType="text/html;charset=Windows-31J" %>

<%--JSTL 1.1.2 core �^�O���C�u����--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ja">
<head>
<meta charset="SJIS">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>�I���f����</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="�����ɃT�C�g���������܂�">
<meta name="keywords" content="�L�[���[�h�P,�L�[���[�h�Q,�L�[���[�h�R,�L�[���[�h�S,�L�[���[�h�T">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/fixmenu_pagetop.css">
<script type="text/javascript" src="js/openclose.js"></script>
<script type="text/javascript" src="js/fixmenu_pagetop.js"></script>
<script src="js/main.js"></script>
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
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
</head>
<style>
  header{
    height: 100%;
  }
</style>
<body>
<div id="container">
<div id="mainimg">���C���C���[�W</div>  
        


<header>

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
<div class="contents bg1"><!--contents�u���b�N�J�n����������������������������������������������������������������������������������������������������-->

<section>
    
<h2>About Me<span></span></h2>

<div class="about_me">
���̌f���́A���Ȃ������ܕ����Ă���<br>

<span style="color:red;font-size:140%;">

�u�ǂ��炪�����������Ă��܂��B�v<br>

�u�����Ă��邯�ǌ��߂邱�Ƃ��ł��Ȃ��v<br>

</span>

�Ƃ������Y�݂��������Ă���܂��B<br><br>

�g�����͊ȒP�ł��B<br>

���Ȃ������܂ǂ���ɂ��悤���Y��ł���Q�̑I����<br>

���k���e�ƈꏏ�ɏ������ނ�����<br>

�����̐l���A���̔Y�݂ɑ΂��ăA�h�o�C�X�����Ă����ł��傤�B<br>

�����āA���Ȃ��ɂǂ���̑I��������΂������������Ă���܂��B<br>

<a href="postthread"><img src="images/post_button.gif"></a>

<br><br>

<a href="addthread"><img src="images/list_button.gif"></a>

</div>
	
</section>  


</div>
<!--/container-->

</body>
</html>

<%@ page pageEncoding="Windows-31J"
	contentType="text/html;charset=Windows-31J" %>

<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ja">
<head>
<meta charset="SJIS">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>ユーザー登録</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="ここにサイト説明を入れます">
<meta name="keywords" content="キーワード1,キーワード2,キーワード3,キーワード4,キーワード5">
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
<!--入力項目チェック用のスクリプト-->

<script type="text/javascript">
	function nullcheck(){

			var flag = 0;

			var judge = false;

			// 設定開始（必須にする項目を設定してください）

			if(document.form1.name.value == ""){ // 「title」の入力をチェック

				flag = 1;

			}
			else if(document.form1.pass.value == ""){ // 「コメント」の入力をチェック

				flag = 1;
			}


			// 設定終了
			if(flag){

			window.alert('必須項目に未入力がありました'); // 入力漏れがあれば警告ダイアログを表示

			return false; // 送信を中止

		}
			else{

			judge = lengthcheck();

				if(judge == false){

					return false;
				}else{

				getRandom();

				return true; // 送信を実行

				}

		}

	}
	/*入力文字が既定文字数を超えていないかのチェック*/

	function lengthcheck(){　//文字数チェックの関数
		var flag2 = 0;

		if(document.form1.name.value.length > 20){	//名前の文字数チェック

			flag2 = 1;

			window.alert("名前は20文字以内で入力してください");

		}else if(document.form1.pass.value.length > 20){	//タイトルの文字数チェック

			flag2 = 1;

			window.alert("パスワードは20文字以内で入力してください");

		}else if(document.form1.pass.value != document.form1.re_pass.value){	//投稿内容の文字数チェック

			flag2 = 1;

			window.alert("パスワードが違います。")

		}

		if(flag2){

			return false; // 送信を中止

		}
			else{

			return true; // 送信を実行

		}
	}
</script>

<!-- 多重投稿禁止用に乱数を生成するスクリプト-->

<script type="text/javascript">
    function getRandom(){
        let num = Math.floor(Math.random()*10000)+1;
        document.getElementById('formnumber').value = num;
    }
</script>

</head>

<body>

<header >

<h1 id="logo"><a href="postthread"><img src="images/logo1.png" alt="投稿画面"></a></h1>
<!--PC用（801px以上端末）メニュー-->
<nav id="menubar">
<ul>
<li id="menu1"><a href="addthread"><img src="images/menu_about1.png" alt="閲覧画面"></a></li>
<li id="menu2"><a href="index"><img src="images/menu_about.png" alt="トップに戻る"></a></li>
<!--
<li id="menu3"><a href="#link"><img src="images/menu_link.png" alt="リンク"></a></li>
<li id="menu4"><a href="#"><img src="images/menu_instagram.png" alt="インスタグラム"></a></li>
-->
</ul>
</nav>
</header>

<div class="contents bg3"><!--contentsブロック開始＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝-->

<section>

<h2>Login<span></span></h2>

<h3>ログイン</h3>
	<form method='post' action='${requestScope.context}${requestScope.target}' name="form1" onSubmit="return nullcheck()">
	<input id="formnumber" type="hidden" name="checknumber" value="">
	名前:<br>
	<input type='text' name='name' placeholder="20文字以内記入" pattern=".*\S+.*" required><br>
	パスワード:<br>
	<input type='text' name='pass' placeholder="20文字以内記入" pattern=".*\S+.*" required><br>
	パスワードを再入力：<br>
	<input name='re_pass' pattern=".*\S+.*" required></input><br>

	<c:out value='${nouser}'></c:out><br>

	<input type='submit' value='ログイン'><br>

	<input type="button" onclick="location.href='register'"value="新規登録"><br>
    </form>

</section>

</body>

</html>
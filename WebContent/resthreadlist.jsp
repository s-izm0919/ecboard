<%@ page pageEncoding="Windows-31J"
	contentType="text/html;charset=Windows-31J" %>

<%--JSTL 1.1.2 core �^�O���C�u����--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ja">
<head>
<meta charset="SJIS">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>���e�ɕԓ��ꗗ</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="�����ɃT�C�g���������܂�">
<meta name="keywords" content="�L�[���[�h�P,�L�[���[�h�Q,�L�[���[�h�R,�L�[���[�h�S,�L�[���[�h�T">
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
        background-color:blanchedalmond;
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

<!--���͂��ꂽ���ڂɕs�����Ȃ������`�F�b�N����X�N���v�g-->

<script type="text/javascript"> 
	function lengthcheck(){
		
		var flag = 0;
		
		if(document.form1.rename.value.length > 10){ // ���O�̕������̃`�F�b�N
		
			flag = 1;

            window.alert("���O��10�����ȓ��œ��͂��Ă�������");
		
		}
		else if(document.form1.recontent.value.length > 100){ // �ԓ����e�̕������̃`�F�b�N
		
			flag = 1;

            window.alert("�ԓ����e��100�����ȓ��œ��͂��Ă�������");
		}
		
		// �ݒ�I��
		if(flag){

		return false; // ���M�𒆎~
	
		}else{

         getRandom();
			
        return true; // ���M�����s

		}
	
	}

</script>

<!--���d���e�֎~�p�̗��������̃X�N���v�g-->

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
        
<div class="contents bg2">
 <section>
<h2>Thread Response</h2>     

<h3>���e���ꂽ�X���b�h</h3>
   <div class="Res_thread">
			<div class="th_title"><c:out value="${data.title}"/></div>
			<c:out value="${data.number}"/>&emsp;<c:out value="${data.name}"/>&emsp;<c:out value="${data.time}"/><br>
			<div class="th_content">
			<c:out value="${data.content}" escapeXml="false"/>
			</div>
			���e�҂̎���F<c:out value="${data.question}"/><br>
			�I���P�F<c:out value="${data.choice1}"/>&emsp;&emsp;�I���Q�F<c:out value="${data.choice2}"/><br>
	</div>

<h3>���݂̏W�v����</h3>
    ���e���ꂽ����F${data.question}<br>
	${data.choice1}�F${agreement}<br>
    ${data.choice2}�F${disagreement}<br>
	<div style="width: 300px;">
    <canvas id="chart" width="150" height="150"></canvas>
    </div>
    <br>

    <!--�W�v���ʂ��O���t�����邽�߂̃X�N���v�g-->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js" integrity="sha256-R4pqcOYV8lt7snxMQO/HSbVCFRPMdrhAFMH+vr9giYI=" crossorigin="anonymous"></script>
	<script>
        var ctx = $('#chart');
        var mychart = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: [
                    '${data.choice1}',
                    '${data.choice2}',
                ],
                datasets: [{
                    label: '�T���v���O���t',
                    data: [
                    	${agreement},
                        ${disagreement},
                    ],
                    backgroundColor: [
                        'rgba(241, 107, 141, 1)',
                        'rgba( 31, 167, 165, 1)',
                        'rgba(249, 199,  83, 1)',
                        'rgba(176, 110,  30, 1)',
                        'rgba(124,  91, 164, 1)'
                    ]
                }]
            },
            options: {
                legend: {
                    position: 'bottom',
                },
                scales: {
                    yAxes: [{
                        ticks: {
                            display: false,
                            beginAtZero: true,
                        },
                        gridLines: {
                            display: false
                        }
                    }]
                }
            }
        });
    </script>

    <h3>�X���b�h�ւ̕ԐM�𓊍e����</h3>
	<form method='post' action='resthread' name="form1" onSubmit="return lengthcheck()">

    <input id='formnumber' type='hidden' name='checknumber' value="">
    
    <input type='hidden' name='reid' value='${data.id}'>
	���O:<br>
	<input type='text' name='rename' placeholder="10�����ȓ��L��"><br>
	���e���e�F<br>
	<textarea name='recontent' placeholder="300�����ȓ��L��" cols="50" rows="5"></textarea><br>
	���Ȃ��̈ӌ��́H:<br>
	<input type='radio' name='revoting' value='1' checked="checked">${data.choice1}<br>
	<input type='radio' name='revoting' value='2' >${data.choice2}<br>
	<input type='submit' value='�o�^'>
	</form>

    <br>

    <h3>���e���ꂽ�ԐM�X���b�h</h3>

    <c:forEach var="resdata" items="${resdata}">
        <c:out value="${resdata.number}"/>&emsp;<c:out value="${resdata.name}"/>&emsp;<c:out value="${resdata.time}"/><br>
        <div class="th_content">
			<c:out value="${resdata.content}" escapeXml="false"/>
		</div>
        �ԓ��҂̈ӌ��F<c:out value='${resdata.voting == "1" ? data.choice1:data.choice2}'/><br>
    </c:forEach>

    <c:forEach var="page" items="${page}">
			<td>
			<a href="resthread?page=${page}&id=${id}">${page}</a>
			</td>
	</c:forEach>
    
</section>
        
</body>
</html>
        
        
        
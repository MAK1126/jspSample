<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인</title>
<link href="css/main.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script>
	function lotto() {
		Swal.fire('로그인 ', '로그인 후 이용해주세요', 'error')
	}
	function lottery() {
		Swal.fire('로그인 ', '로그인 후 이용해주세요', 'error')
	}
</script>
</head>
<body style="overflow-x: hidden">
	<header>
	<div class="header">
		<div>
			<a href="./main"><img src="imges/logo.jpg"></a>
		</div>
		<div class="header_join">
			<a href="./login"><input type="submit" value="로그인" class="login"></input></a>
			<a href="./reg"><input type="submit" value="회원가입" class="join"></input></a>
		</div>
	</div>
	</header>

	<nav></nav>

	<section>
	<div class="main_img">
		<form name="lottofrm">
			<div class="a">
				<img onclick="lotto()" src="imges/lotto.jpg">
			</div>
			<div class="aa" onclick="lotto()">구매하기</div>
		</form>
		<form name="lotteryfrm">
			<div class="b">
				<img onclick="lottery()" src="imges/lottery1.jpg">
			</div>
			<div class="bb" onclick="lottery()">구매하기</div>
		</form>
	</div>
	<!-- interview -->
	<div class="interview">
		<div class="Intermain">
			<img src="imges/interviewlogo.png">
		</div>
		<div class="Intersub">
			<ul>
				<li>스피또2000 [1등]</li>
				<li><img src="imges/interview1.jpg"></li>
				<li>스피또2000 1등 당첨!</li>
			</ul>
			<ul>
				<li>연금복권720+ [1등]</li>
				<li><img src="imges/interview2.jpg"></li>
				<li>복권은 '일주일의 행복'이다.</li>
			</ul>
			<ul>
				<li>스피또1000 [1등]</li>
				<li><img src="imges/interview3.jpg"></li>
				<li>복권이 일생일대의 기회를 준 것 같아요.</li>
			</ul>
		</div>

	</div>
	</section>

	<footer>
	<div class="footer">
		<img src="imges/logo2.png" class="f_img1">
		<div>
			<p>
				수탁사업자 (주)동행복권 주소 : 06719 서울 서초구 남부순환로 2423 한원빌딩 4층 고객문의 1588-6450
				FAX 02-6933-3063 <br> 대표자 : 조형섭, 홍덕기 사업자등록번호 866-87-00833
				통신판매사업자번호 : 제2018 - 서울서초 - 2075호 <br> <br> Copyright (c)
				2018 복권위원회&동행복권. All rights reserved <br> <br> 본 홈페이지는 게시된
				이메일 주소가 자동 수집되는 것을 거부하며, 이를 위반시 정보통신망법에 의해 처벌됨을 유념하여 주시길 바랍니다. <br>
				청소년은 복권을 구매하거나 당첨금을 수령할 수 없습니다. <br> <br> 본 사이트는 Internet
				Explorer 11, Edge, Chrome, Safari 브라우저에 최적화되어 있습니다. <br>
			</p>
		</div>
		<img src="imges/footlogo.png" class="f_img2">
	</div>
	</footer>
</body>
</html>
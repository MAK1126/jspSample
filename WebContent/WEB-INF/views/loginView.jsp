<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<link href="css/login.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>

	function check() {
		
		let id = $('input[name=id]').val();
		let pw = $('input[name=pw]').val();

		if (id == "") {
			Swal.fire('로그인 실패', '아이디를 입력해주세요', 'error')
			return;
		} else if (pw == "") {
			Swal.fire('로그인 실패', '비밀번호를 입력해주세요', 'error')
			return;
		} else {
			$.ajax({
				type : "post",
				dataType : "text",
				url : "/Team4/login",
				data : {
					"id" : id,
					"pw" : pw
				},
				success : function(data, status) {
					if (data == 1) {
						Swal.fire('로그인 성공', 'success!', 'success').then(
								function() {
									window.location.href = "/Team4/main2";
								})
					} else if (data == 2) {
						Swal.fire('로그인 실패', '비밀번호가 일치하지 않습니다', 'error')
					} else if (data == 3) {
						Swal.fire('로그인 실패', '존재하지 않는 아이디 입니다', 'error')
					}
				},
				error : function(err) {
					console.log(err);
				}
			})

		}
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
	<div class="wrap">
		<h1>
			LOGIN
			<hr>
		</h1>
		<form name="checkLogin" action="./login" method="post">
			<div class="logbox">
				<div id="idnpw">
					<input type="text" class="cid" name="id" placeholder="아이디" value=""></input>
					<input type="password" class="cpw" name="pw" placeholder="비밀번호" value=""></input>
				</div>
				<input type="button" id="logbtn" class="clog" value="로그인" onclick="check()"></input>
			</div>
		</form>
		<div class="updatebox">
			<a href="./reg"><button class="regbox">회원가입</button></a>
			<button class="searchbox">아이디/비밀번호찾기 </button>
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
				통신판매사업자번호 : 제2018 - 서울서초 - 2075호 <br>
				<br> Copyright (c) 2018 복권위원회&동행복권. All rights reserved <br>
				<br> 본 홈페이지는 게시된 이메일 주소가 자동 수집되는 것을 거부하며, 이를 위반시 정보통신망법에 의해
				처벌됨을 유념하여 주시길 바랍니다. <br> 청소년은 복권을 구매하거나 당첨금을 수령할 수 없습니다. <br>
				<br> 본 사이트는 Internet Explorer 11, Edge, Chrome, Safari 브라우저에
				최적화되어 있습니다. <br>
			</p>
		</div>
		<img src="imges/footlogo.png" class="f_img2">
	</div>
	</footer>
</body>

</html>
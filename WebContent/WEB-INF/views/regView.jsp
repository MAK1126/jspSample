<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/reg.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script>
	function dup(){
		
		let id = $('input[name=id]').val();
		
		if(id==""){
			Swal.fire('아이디 중복 체크', '아이디를 입력해주세요', 'error')
			return;
		}
		
		$.ajax({
				type : "post",
				dataType : "text",
				url : "/Team4/iddup",
				data : {
					"id" : id,
				},
				success : function(data, status) {
					if (data == 1) {
						Swal.fire('아이디 중복 체크', '사용가능한 아이디 입니다', 'success').then(function() {
						$(".infobox2").attr("disabled", false);
						$(".infobox").attr("readonly", true);
						})
					} else if (data == 2) {
						Swal.fire('아이디 중복 체크', '이미 존재하는 아이디 입니다', 'error')
					}
				},
				error : function(err) {
					console.log(err);
				}
			})
	}
	
	function memberJoin(){
		let id = $('input[name=id]').val();
		let pw = $('input[name=pw]').val();
		let name = $('input[name=name]').val();
		let address = $('input[name=address]').val();
		let tel = $('input[name=tel]').val();
		
		if(id==""||pw==""||name==""||address==""||tel==""){
			Swal.fire('회원 가입', '비어 있는 항목이 있는지 확인해주세요', 'error')
			
		}else if(!/^010-\d{4}-\d{4}$/.test(tel)){
			Swal.fire('회원 가입', '전화번호 형식을 확인해주세요', 'error')
			
		}else if(!id==""&&!pw==""&&!name==""&&!address==""&&!tel==""){
			Swal.fire('회원 가입', '회원 가입이 완료되었습니다.', 'success').then(function() {
				document.join.submit();;
				})
		}
		
	}
</script>
</head>
<body>
	<% String duplication = (String)request.getAttribute("duplication"); %>
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
	<form name="join" action="./reg" method="post">
		<div class="wrap">
		
			<table>
				<h3>
					회원가입
				</h3>
				<hr>
				<tr>
					<td><b>아이디</b></td><span>
					<td><input type="text" name="id" class="infobox"><input id="dupBtn" value="중복확인" type="button" onclick="dup()"> 띄어쓰기 없는 영문대소문자, 숫자포함 4~14자 사용가능 </td></input>
				</tr>
				<tr>
					<td><b>비밀번호</b></td>
					<td><input type="text" name="pw" class="infobox2" disabled>
						9~14자의 영문대소문자,숫자,특수문자 사용</td>
				</tr>
				<tr>
					<td><b>이름 </b></td>
					<td><input type="text" name="name" class="infobox2" disabled> 만
						19세 미만 청소년은 가입하실 수 없습니다.</td>
				</tr>
				<tr>
					<td><b>주소</b></td>
					<td><input type="text" name="address" class="infobox2" disabled></td>
				</tr>
				<tr>
					<td><b>전화번호</b></td>
					<td><input type="text" name="tel" class="infobox2" disabled> 010-XXXX-XXXX 형식으로 입력해주세요</td>
				</tr>
				<tr>
					<td colspan="2" class="subnreset">
						<hr>
						<input type="button" onclick="memberJoin()" class="submitbox" value="회원가입">
						<input type="reset" class="resetbox" value="취소">
					</td>
				</tr>
			</table>
		</div>
	</form>

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
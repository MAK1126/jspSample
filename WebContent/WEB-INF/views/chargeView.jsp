<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>충전</title>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="css/charge.css" rel="stylesheet">
<script>

    function chargePoint(){
    	let money = $("select[name=money]").val();
    	
    	if(money==""){
    		Swal.fire('충전 실패', '충전 금액을 선택해주세요', 'error')
    		return;
    	}
    	
		let money_ = money.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    		
    	Swal.fire({
    		title: '충전',
    		text: money_+'원을 충전할까요?',
    		icon: 'question',
    		showCancelButton: true,
    		confirmButtonText: '확인',
    		cancelButtonText: '취소'
    		}).then((result)=> {
    			if (result.isConfirmed) {
    				Swal.fire('충전 성공', money_+'포인트 충전 완료', 'success').then(function() {
    					document.charge.submit();
					})
				}
    		})
    }
</script>
</head>
<body style="overflow-x: hidden">
	<header>
	<div class="header">
		<div>
			<a href="./main2"><img src="imges/logo.jpg"></a>
		</div>
		<div class="header_join">
			<a href="./logout"><input type="submit" value="로그아웃" class="login"></input></a>
			<a href="./reg"><input type="submit" value="회원가입" class="join"></input></a>
		</div>
	</div>
	</header>

	<nav> </nav>

	<section>
	<div class="section">
		<div class="s_title">
			<p>충전하기</p>
			<div class="s_link">
				<a href="">홈</a> > <a href="">마이페이지</a> > <a href="">예치금</a> > <a
					href="">충전하기</a>
			</div>
		</div>
		<ul>
			<li>가상계좌 입금</li>
		</ul>
		<table>
			<tr>
				<td>입금내역</td>
				<td>복권 예치금</td>
			</tr>
			<tr>
				<td>금액</td>
				<td>
					<form name="charge" action="./charge" method="post">
						<select id="money" name='money'>
							<option value=''>--선택 --</option>
							<option value='5000'>5,000원</option>
							<option value='10000'>10,000원</option>
							<option value='20000'>20,000원</option>
							<option value='50000'>50,000원</option>
							<option value='100000'>100,000원</option>
							<option value='100000'>200,000원</option>
							<option value='300000'>300,000원</option>
							<option value='500000'>500,000원</option>
							<option value='700000'>700,000원</option>
							<option value='1000000'>1,000,000원</option>
						</select>
					</form>
				</td>
			</tr>
			<tr>
				<%String name = (String)request.getAttribute("name"); %>
				<td>계좌주명</td>
				<td><p>
						동행복권_<%=name %></p></td>
			</tr>
		</table>

		<div class="okay">
			<input type="button" onclick="chargePoint()" value="확인"></input>
		</div>

		<p class="section_p">
			- 예치금이란 인터넷 복권을 구입하실 수 있도록 미리 충전해 둔 복권 구매용 결제수단입니다. <br> - 가상계좌는
			휴대폰 점유인증 후, 케이뱅크의 입금용 010가상계좌가 제공됩니다.(케이뱅크 고객은 이체 수수료 무료) <br> -
			회원탈퇴 또는 휴면계정으로 전환시 입금이 제한됩니다. <br> - <span class="span1">예치금충전은
				먼저 입금액을 선택 후 입금하시고, 입금시 계좌번호와 입금액, 계좌주명을 꼭 확인하십시오.</span> <br> - 입금용
			계좌번호로 입금이 확인되면 예치금으로 바로 충전되며, 입금완료 후 예치금 확인까지는 최대 5분 정도 소요될 수 있습니다. <br>
			<span class="span2">※ 예치금 입금용 케이뱅크 가상계좌는 주식회사 동행복권에서 모계좌를 개설한
				것으로, 각 회원 소유의 금융계좌가 아닙니다.</span> <br>
		</p>

		<div class="s_last">
			<p>예치금충전 절차</p>
			<img src="imges/s_img.png">
		</div>
	</div>
	</section>

	<footer>
	<div class="footer">
		<img src="imges/logo2.png" class="f_img1">
		<p>
			수탁사업자 (주)동행복권 주소 : 06719 서울 서초구 남부순환로 2423 한원빌딩 4층 고객문의 1588-6450 FAX
			02-6933-3063 <br> 대표자 : 조형섭, 홍덕기 사업자등록번호 866-87-00833 통신판매사업자번호
			: 제2018 - 서울서초 - 2075호 <br> <br> Copyright (c) 2018
			복권위원회&동행복권. All rights reserved <br> <br> 본 홈페이지는 게시된 이메일
			주소가 자동 수집되는 것을 거부하며, 이를 위반시 정보통신망법에 의해 처벌됨을 유념하여 주시길 바랍니다. <br>
			청소년은 복권을 구매하거나 당첨금을 수령할 수 없습니다. <br> <br> 본 사이트는 Internet
			Explorer 11, Edge, Chrome, Safari 브라우저에 최적화되어 있습니다. <br>
		</p>
		<img src="imges/footlogo.png" class="f_img2">
	</div>
	</footer>

</body>
</html>
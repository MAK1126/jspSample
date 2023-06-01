<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.dto.lotto"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/buylistView.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
		
	let code="";
	
	const prizeText = {
			'1등': { message: '1등 입니다!', money: 1680000000 },
		  	'2등': { message: '2등 입니다!', money: 120000000 },
		  	'3등': { message: '3등 입니다!', money: 100000 },
		  	'4등': { message: '4등 입니다!', money: 50000 },
		 	'5등': { message: '5등 입니다!', money: 5000 },
		  	'꽝': { message: '꽝입니다..', money: null }
	};

	function showResultMessage(prize) {
		const prizeInfo = prizeText[prize];
		if (!prizeInfo) return;
		const title = prize === '꽝' ? '꽝' : '당첨';
		const icon = prize === '꽝' ? 'warning' : 'success';
		const msg = prize === '꽝' ? '<br>다음 기회에' : '<br> 확인 버튼을 누르면 당첨금이 지급됩니다';
		
		Swal.fire({
			title: title,
		    html: prizeInfo.message + msg,
		    icon: icon,
		}).then((result) => {
			if (result.isConfirmed) {
				if (prizeInfo.money === null) return;
				return $.ajax({
					url: "/Team4/prize",
					type: "post",
					dataType: "text",
					data: { money: prizeInfo.money, code: code },
				}).then(() => {
					window.location.reload();
				});
			}
			}).catch((error) => {
				console.error(error);
			});
	}
	
	function prizeCheck(obj){
		
		const week = obj.parentElement.firstElementChild.value;
		const buyNum = obj.parentElement.lastElementChild.value;
		const receipt = obj.parentElement.firstElementChild.nextElementSibling.value;
		let code_ = obj.parentElement.firstElementChild.nextElementSibling.nextElementSibling.value;
		code = code_;
		
		if(receipt==1){
			Swal.fire('당첨금 수령 완료', '이미 당첨금을 수령하셨습니다', 'warning');
			return;
		}
		
		$.ajax({
			type: 'post',
			dataType: 'text',
			url: '/Team4/buylist2',
			data: {
				week: week,
				buyNum: buyNum,
			},
		}).then((data) => {
			showResultMessage(data);
		}).catch((error) => {
			console.error(error);
		});

	}
</script>
</head>
<body>
	<% 	ArrayList<lotto> buyList = (ArrayList<lotto>)request.getAttribute("buyList");
	  	Collections.reverse(buyList); // 순서를 반대로 뒤집음, 최신 구매내역이 위로 갈 수 있게
		String type="";
	%>
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
	<nav></nav>
	<section>
	<div class="list_wrap">
	<div>
		<table class="table-style">
		<caption class="p_pay">복권 구매내역</caption>
		<thead>
			<tr>
				<th>복권명</th>
				<th>회차</th>
				<th>선택번호/복권번호</th>
				<th>구입매수</th>
				<th>당첨확인</th>
			</tr>
		</thead>
			<% for(lotto buy : buyList){
                	if(buy.getCode().charAt(0)=='A'){
                		type="연금복권";
                	}else if(buy.getCode().charAt(0)=='B'){
                		type="로또";
                	}
            %>
			<form name="buyList" action="./buylist2" method="post">
				<tbody>
				<tr>
					<td><%=type %></td>
					<td><%=buy.getWeek() %></td>
					<td><% String[] numbers = buy.getNumber().split(",");
						for (int i = 0; i < numbers.length; i++) {
						out.print(numbers[i] + " ");
						}%>
			        </td>
					<td>1</td>
					<td>
						<input type="hidden" name="week" value="<%=buy.getWeek()%>"></input>
						<input type='hidden' value="<%=buy.getReceipt()%>"></input>
						<input type='hidden' value="<%=buy.getCode()%>"></input>
						<button id="prizeBtn" type="button" onclick="prizeCheck(this)">당첨확인</button>
						<input type="hidden" name="buyNum" value="<%=buy.getNumber()%>"></input>
					</td>
				</tr>
				</tbody>
			</form>
			<%} %>
		</table>
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
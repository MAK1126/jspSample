<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.text.DecimalFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="css/lottery.css" rel="stylesheet">
<title>Insert title here</title>

<script>
	let cnt = 1;
	let count = 0;

	let jo = "";
	let one = "";
	let two = "";
	let three = "";
	let four = "";
	let five = "";
	let six = "";

	let buyNum = "";

	function checkJo(obj) {
		
		jo = obj.value;
		document.getElementById("jo").innerHTML = jo + "조";

	}

	function checkNum(obj) {

		if (cnt == 1) {
			document.getElementById("num1").innerHTML = obj.value;
			one = obj.value;
			cnt++;
		} else if (cnt == 2) {
			document.getElementById("num2").innerHTML = obj.value;
			two = obj.value;
			cnt++;
		} else if (cnt == 3) {
			document.getElementById("num3").innerHTML = obj.value;
			three = obj.value;
			cnt++;
		} else if (cnt == 4) {
			document.getElementById("num4").innerHTML = obj.value;
			four = obj.value;
			cnt++;
		} else if (cnt == 5) {
			document.getElementById("num5").innerHTML = obj.value;
			five = obj.value;
			cnt++;
		} else if (cnt == 6) {
			document.getElementById("num6").innerHTML = obj.value;
			six = obj.value;
			cnt = 1;
		}
	}

	function buy() {
		$('input[name=buyNum]').val(buyNum);
		let point = $('input[name=point]').val();
		
		if(count==0){
			swal.fire('구매 실패','1개 이상 선택해주세요','error');
			return;
		}
		
	    Swal.fire({
	    	title: '구매 확인',
	    	text: count+'매를 구매할까요?',
	    	icon: 'question',
	    	showCancelButton: true,
	    	confirmButtonText: '확인',
	    	cancelButtonText: '취소'
	    	}).then((result)=> {
	    		if (result.isConfirmed) {
	    			if(point<count*1000){
	    				Swal.fire('구매 실패','잔액 부족','error')
	    			}else{
		    			Swal.fire('구매 성공', count+'매 구매 완료', 'success').then(function() {
		    				document.lottery.submit();
						})
	    			}
				}
	    	})
	}

	function choice() {

		if (jo == "" || one == "" || two == "" || three == "" || four == ""
				|| five == "" || six == "")
			return;

		document.getElementById("jo").innerHTML = "";
		document.getElementById("num1").innerHTML = "";
		document.getElementById("num2").innerHTML = "";
		document.getElementById("num3").innerHTML = "";
		document.getElementById("num4").innerHTML = "";
		document.getElementById("num5").innerHTML = "";
		document.getElementById("num6").innerHTML = "";
		cnt = 1;

		if (count < 5) {
			let
			lottery = jo + "조 " + one + " " + two + " " + three + " " + four
					+ " " + five + " " + six;
			let
			Num = jo + "," + one + "," + two + "," + three + "," + four + ","
					+ five + "," + six;
			buyNum = buyNum + Num + '|';
			count++;
			$('#lottery_ul').append("<li>" + lottery + "</li>")
			document.getElementById("money").innerHTML = count + ",000원";
		}

		jo = "";
		one = "";
		two = "";
		three = "";
		four = "";
		five = "";
		six = "";

	}
</script>
</head>
<body style="overflow-x: hidden">
	<%
		int point_ = (int) request.getAttribute("point");
		String point = Integer.toString(point_);
		point = point.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
	%>

	<header>
	<div class="header">
		<div>
			<a href="./main2"><img src="imges/logo.jpg"></a>
		</div>
		<div class="header_join">
			<a href="./login"><input type="submit" value="로그인" class="login"></input></a>
			<a href="./reg"><input type="submit" value="회원가입" class="join"></input></a>
		</div>
	</div>
	</header>
	
	<nav> </nav>
	
	<section>
	<div class="lo_wrap">
		<div class="view">
			<div id="jo"></div>
			<div id="num1"></div>
			<div id="num2"></div>
			<div id="num3"></div>
			<div id="num4"></div>
			<div id="num5"></div>
			<div id="num6"></div>
			<button class="btn" onclick="choice()">선택<br>완료</button>
		</div>
		<div class="check">
			<div class="jo_check">
                <p>조선택</p>
				<div>
					<button onclick="checkJo(this)" value="1">1조</button>
					<button onclick="checkJo(this)" value="2">2조</button>
					<button onclick="checkJo(this)" value="3">3조</button>
				</div>
				<div>
					<button onclick="checkJo(this)" value="4">4조</button>
					<button onclick="checkJo(this)" value="5">5조</button>
				</div>
			</div>
			<div class="num_check">
                <p>6자리 번호 선택</p>
				<div>
					<button onclick="checkNum(this)" value="1">1</button>
					<button onclick="checkNum(this)" value="2">2</button>
					<button onclick="checkNum(this)" value="3">3</button>
				</div>
				<div>
					<button onclick="checkNum(this)" value="4">4</button>
					<button onclick="checkNum(this)" value="5">5</button>
					<button onclick="checkNum(this)" value="6">6</button>
				</div>
				<div>
					<button onclick="checkNum(this)" value="7">7</button>
					<button onclick="checkNum(this)" value="8">8</button>
					<button onclick="checkNum(this)" value="9">9</button>
				</div>
				<div class="num_check0">
					<button onclick="checkNum(this)" value="0">0</button>
				</div>
			</div>
			<div class="choice_check">
                <p>내가 선택한 번호</p>
				<ul id="lottery_ul">
				</ul>
			</div>
		</div>
		<div class="bottom">
			<div class="btm1">
				<a href="./buylist"><button type="button">구매내역보기</button></a>
			</div>
			<div class="btm2">
				<span>보유중인 예치금 </span><a href="./charge"><span><button> 충전</button></span></a>
				<h4><%=point %>원</h4>
				<input type="hidden" value="<%=point_ %>" name="point"></input>
			</div>
			<div class="btm3">
				<div>
					<h1>결제 예정 금액</h1>
					<span id="money"></span>
				</div>
				<form name="lottery" action="./lottery" method="post">
					<input type="hidden" name="buyNum" value=""></input>
					<input type="hidden" name="week" value="1주차"></input>
					<input type="button" value="구매하기" onclick="buy()"></input>
				</form>
			</div>
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
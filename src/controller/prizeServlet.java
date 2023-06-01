package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.lotteryBuyDAO;
import model.dao.lottoBuyDAO;
import model.dao.memberDAO;
import model.dto.lottery;
import model.dto.lotto;
import model.dto.member;
import model.service.lotteryBuyService;
import model.service.lottoBuyService;
import model.service.memberService;

@WebServlet("/prize")
public class prizeServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		String point_ = request.getParameter("money");
		String code = request.getParameter("code");
		int point = Integer.parseInt(point_);
		
		memberService memService = new memberService();
		memberDAO memDao = new memberDAO();
		memService.setDao(memDao);
		
		member mem = new member(id, point);
		memService.checkCharge(mem);
		
		if(code.charAt(0)=='A'){
			lotteryBuyService lotteryService = new lotteryBuyService();
			lotteryBuyDAO lotteryDao = new lotteryBuyDAO();
			lotteryService.setDao(lotteryDao);
			
			lottery lottery = new lottery(code,1);
			lotteryService.checkUpdate(lottery);
			
		}else if(code.charAt(0)=='B'){
			lottoBuyService lottoService = new lottoBuyService();
			lottoBuyDAO lottoDao = new lottoBuyDAO();
			lottoService.setDao(lottoDao);
			
			lotto lotto = new lotto(code,1);
			lottoService.checkUpdate(lotto);
		}

		
	}

}

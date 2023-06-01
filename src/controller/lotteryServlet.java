package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.lotteryBuyDAO;
import model.dao.memberDAO;
import model.dto.lottery;
import model.dto.member;
import model.service.lotteryBuyService;
import model.service.memberService;

@WebServlet("/lottery")
public class lotteryServlet extends HttpServlet {

	/* 연금복권 서블릿 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		memberService service = new memberService();
		memberDAO memDao = new memberDAO();
		service.setDao(memDao);
		member mem = new member(id);

		int point = service.checkPoint(mem);

		request.setAttribute("point", point);
		request.getRequestDispatcher("WEB-INF/views/lotteryView.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		String buyNum = request.getParameter("buyNum");
		String week = request.getParameter("week");
		int receipt = 0;
		
		lotteryBuyService lotteryService = new lotteryBuyService();
		lotteryBuyDAO lotteryDao = new lotteryBuyDAO();
		lotteryService.setDao(lotteryDao);
		
		memberService memService = new memberService();
		memberDAO memDao = new memberDAO();
		memService.setDao(memDao);
		
		int buyCnt = lotteryService.checkCount(buyNum);
		String[] buyNumArr = lotteryService.checkDivision(buyNum);

		int pay = buyCnt * 1000;

		member mem = new member(id, pay);

		int point = memService.checkPoint(mem);

		/*if (point < pay) {
			response.sendRedirect("./charge");
			return;
		}*/

		for (int i = 0; i < buyCnt; i++) {
			lottery lottery = new lottery(id, week, buyNumArr[i],receipt);
			lotteryService.checkInsert(lottery);
		}

		memService.checkdeductPoint(mem);

		response.sendRedirect("./lottery");

	}
}
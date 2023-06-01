package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.lotteryBuyDAO;
import model.dao.lotteryPrizeDAO;
import model.dto.lottery;
import model.service.lotteryBuyService;
import model.service.lotteryPrizeService;

@WebServlet("/buylist")
public class buylistServlet extends HttpServlet {

	/* 援щℓ�궡�뿭 �꽌釉붾┸ */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		if (id == null) {
			request.getRequestDispatcher("WEB-INF/views/mainView.jsp").forward(request, response);
			return;
		}

		lotteryBuyService service = new lotteryBuyService();
		lotteryBuyDAO lotteryBuyDao = new lotteryBuyDAO();
		service.setDao(lotteryBuyDao);
		lottery lottery = new lottery(id);

		ArrayList<lottery> buyList = service.checkInquiry(lottery);

		request.setAttribute("buyList", buyList);
		request.getRequestDispatcher("WEB-INF/views/buylistView.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String buyNum = request.getParameter("buyNum");
		String week = request.getParameter("week");

		lotteryPrizeService service = new lotteryPrizeService();
		lotteryPrizeDAO lotteryPrizeDao = new lotteryPrizeDAO();
		service.setDao(lotteryPrizeDao);

		String prize = service.checkPrize(buyNum, week);
		
		response.getWriter().print(prize);
		
	}
}

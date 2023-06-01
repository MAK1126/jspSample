package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.lottoBuyDAO;
import model.dao.lottoPrizeDAO;
import model.dto.lotto;
import model.service.lottoBuyService;
import model.service.lottoPrizeService;

@WebServlet("/buylist2")
public class buylistServlet2 extends HttpServlet {

	/* 구매내역 서블릿 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		if (id == null) {
			request.getRequestDispatcher("WEB-INF/views/mainView.jsp").forward(request, response);
			return;
		}

		lottoBuyService service = new lottoBuyService();
		lottoBuyDAO lottoBuyDao = new lottoBuyDAO();
		service.setDao(lottoBuyDao);
		lotto lotto = new lotto(id);

		ArrayList<lotto> buyList = service.checkInquiry(lotto);

		request.setAttribute("buyList", buyList);
		request.getRequestDispatcher("WEB-INF/views/buylistView2.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String buyNum = request.getParameter("buyNum");
		String week = request.getParameter("week");

		lottoPrizeService service = new lottoPrizeService();
		lottoPrizeDAO lottoPrizeDao = new lottoPrizeDAO();
		service.setDao(lottoPrizeDao);

		String prize = service.checkPrize(buyNum, week);
		response.getWriter().print(prize);
		
	}
}

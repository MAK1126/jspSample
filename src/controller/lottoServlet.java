package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.lottoBuyDAO;
import model.dao.memberDAO;
import model.dto.lotto;
import model.dto.member;
import model.service.lottoBuyService;
import model.service.memberService;

@WebServlet("/lotto")
public class lottoServlet extends HttpServlet {

	/* 로또 서블릿 */

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
		request.getRequestDispatcher("WEB-INF/views/lottoView.jsp").forward(
				request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		String buyNum = request.getParameter("buyNum");
		String week = request.getParameter("week");
		int receipt = 0;
		
		lottoBuyService lottoService = new lottoBuyService();
		lottoBuyDAO lottoDao = new lottoBuyDAO();
		lottoService.setDao(lottoDao);
				
		memberService memService = new memberService();
		memberDAO memDao = new memberDAO();
		memService.setDao(memDao);

		int buyCnt = lottoDao.buyCount(buyNum);
		String[] buyNumArr = lottoDao.division(buyNum);

		int pay = buyCnt * 1000;

		member mem = new member(id, pay);

		int point = memService.checkPoint(mem);

		/*if (point < pay) {
			response.sendRedirect("./charge");
			return;
		}*/

		for (int i = 0; i < buyCnt; i++) {
			lotto lotto = new lotto(id, week,buyNumArr[i],receipt);
			lottoService.checkInsert(lotto);
		}

		memService.checkdeductPoint(mem);
		
		response.sendRedirect("./lotto");
	}
}

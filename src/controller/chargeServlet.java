package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.memberDAO;
import model.dto.member;
import model.service.memberService;

@WebServlet("/charge")
public class chargeServlet extends HttpServlet {

	/* 충전 서블릿 */

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		memberService service = new memberService();
		memberDAO memDao = new memberDAO();
		service.setDao(memDao);
		member mem = new member(id);

		String name = service.checkName(mem);

		request.setAttribute("name", name);
		request.getRequestDispatcher("WEB-INF/views/chargeView.jsp").forward(
				request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		String point_ = request.getParameter("money");
		int point = Integer.parseInt(point_);

		memberService service = new memberService();
		
		memberDAO memDao = new memberDAO();
		service.setDao(memDao);
		
		member mem = new member(id, point);
		service.checkCharge(mem);
		
		response.sendRedirect("./main2");
		
	}
}
package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.memberDAO;
import model.dto.member;
import model.service.memberService;

@WebServlet("/reg")
public class regServlet extends HttpServlet {

	/* 회원가입 서블릿 */

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/views/regView.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		int point = 0;

		memberService service = new memberService();
		memberDAO memDao = new memberDAO();
		service.setDao(memDao);
		
		member mem = new member(id, pw, name, address, tel, point);		
		service.checkReg(mem);
	
		response.sendRedirect("./login");
	}
}

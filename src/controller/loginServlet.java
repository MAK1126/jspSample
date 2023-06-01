package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.memberDAO;
import model.service.memberService;

@WebServlet("/login")
public class loginServlet extends HttpServlet {

	/* 로그인 서블릿 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/views/loginView.jsp").forward(
				request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		memberService service = new memberService();
		memberDAO memDao = new memberDAO();
		service.setDao(memDao);

		int login = service.checkLogin(id, pw);			
		response.getWriter().print(login);
		
		if (login == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
		}
		
	}
}

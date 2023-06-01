package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.memberDAO;
import model.service.memberService;

@WebServlet("/iddup")
public class idDupServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String id = (String)request.getParameter("id");
		
		memberService service = new memberService();
		memberDAO memDao = new memberDAO();
		service.setDao(memDao);
		
		int duplication = service.checkisDuplicated(id);
		
		response.getWriter().print(duplication);
	}
}

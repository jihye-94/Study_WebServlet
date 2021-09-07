package com.bit;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontServletController
 */
@WebServlet(description = "설명을 작성하는 곳 Description", 
			urlPatterns = { "/action.do" }) //
public class FrontServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FrontServletController() {
        super();
       System.out.println("FrontServletController 최초 컴파일시 한번 호출 초기화");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//192.168.0.57:8090/WebServlet_1/action.do 하면 servlet 실행 
		System.out.println("GET");
		request.setCharacterEncoding("UTF-8");
		String cmd = request.getParameter("cmd"); 
		String msg="";
		if (cmd.equals("greeting")) {
			Message m = new Message();
			msg = m.getMessage(cmd);
			
		}

		request.setAttribute("msg", msg);
		
		RequestDispatcher dis = request.getRequestDispatcher("/greeting.jsp");
		dis.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("POST");
	}

}

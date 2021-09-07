package com.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class FrontBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FrontBoardController() {    	
        super();
        System.out.println("최초요청에 의해 실행");

    }

	private void doProcess(HttpServletRequest request, HttpServletResponse response, String method) throws ServletException, IOException {
		//GET, POST 어떤 요청에 와도 실행하는 함수 
		System.out.println("클라이언트 요청: " + method);
		

		//1. 한글처리 		//request.setCharacterEncoding("UTF-8");
		
    	//2. 요청받기(request)		 //String cmd = request.getParameter("cmd"); 
		
    	//3. 요청판단 (판단의 기준 : parameter 기준 >> command 방식)
    	//3.1 192.168.0.57:8090/WebServlet_1/board?cmd=login&id=kglim&pwd=1004 요청 서버로
		
    	//서버는 cmd변수가 가지는 값을 가지고 판단 : login >> 로그인 처리 요청 인지가능
    	//3.2 192.168.0.57:8090/WebServlet_1/board?cmd=list   게시판 열람 요청 인지가능
    	
    	//String command = request.getParameter("cmd");
    	//if(command.equals("login")){ 로그인 서비스 처리 ....}
    	//else if(command.equals("list")){게시판 목록보기 서비스}
    	
    	//command 방식 반대 >> URL주소 방식 (판단의 기준 : URL 기준 )
    	//192.168.0.57:8090/WebServlet_1/board/boardlist
    	//192.168.0.57:8090/WebServlet_1/board/boardwrite?title=방가&content=방가방가
    	//마지막 주소값을 추출 
    	//   /boardlist   >> 게시판 열람 요청 인지가능
    	//   /boardwrite?title=방가&content=방가방가  >> 게시판 글작성 요청 인지가능
    	
    	
    	//4. 결과 저장 (request,   session ,  application)
    	
    	//5. view 지정 
    	//view page : *.jsp (로 생성)
    	//webapp > board > boardlist.jsp (목적에 맞는 네이밍을 가진 jsp파일 생성)
    	//webapp > error > error404.jsp
    	
    		//위 방식은 보안상 문제 >> 실개발 > webapp > WEB-INF > views폴더 생성 > board or member or admin  폴더 관리
    	
    	//ex) 보안폴더 : WEB-INF > views > board > boardlist.jsp
    	
    	//6. 데이터전달 (view 에게 request객체 forward 해서 사용가능 ....)
		
		//1. 한글처리
		request.setCharacterEncoding("UTF-8");
		
		//2. 요청받기(request)
		String cmd = request.getParameter("cmd"); 
		System.out.println("cmd: " +cmd);
		
		//3. 요청판단하기ㄱ
		String viewpage = null; 
		
		//cmd에 null이 들어오면 error.jsp 서비스되고 
		//cmd에 boardlist 들어오면 list.jsp 서비스되고
		//cmd에 boardwrite 들어오면 write.jsp 서비스됐으면 좋겠음 
		
	
		if(cmd == null) {
			viewpage = "/error/error.jsp"; //view될 페이지로 error.jsp사용하겠다 선언 
		}else if(cmd.equals("boardlist")) {
			//실제 업무처리
			//DB연결 -> select날리고 -> 데이터 담고 -> 객체 생성 
			//BoardDao dao = new BoardDao();
			//List<Board> list = dao.selectBoardlist(); 
			//request.setAttribute("list",list); 
			//forward로 view단 전달 -> requestScope.list 통해 데이터 처리(출력)
			
			viewpage = "/board/boardlist.jsp"; 
		}else if(cmd.equals("boardwrite")) {
			viewpage = "/board/boardwrite.jsp"; 
		}else if(cmd.equals("login")){
			viewpage = "/WEB-INF/views/login/login.jsp"; 
		}else {
			viewpage = "/error/error.jsp";
		}
		
		//4. 결과 저장 (request,   session ,  application) (모든 페이지 결과저장하려면 session, 근디
		request.setAttribute("data", viewpage);
    	
    	//5. view 지정 (은 3번에서 해놨음)
		RequestDispatcher dis = request.getRequestDispatcher(viewpage); 
		
		//6. 데이터전달(forward->request 객체사용)
		dis.forward(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response, "GET");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response, "POST");
	}

}

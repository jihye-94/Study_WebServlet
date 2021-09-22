package com;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet; 지우세요!
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SimpleController
 */
//@WebServlet("/SimpleController") 지우세요!

/*
 1. @WebServlet 어노테이션 삭제한 이유 ??
  - web.xml을 통한 설정 작업을 해보려고,(어노테이션 사용하지 않은 과거 작성 코드가 존재하니)
  
  jsp 배울때는 웹 브라우저에서 192.168.0.57:8090/WebJSP/login.jsp 요청
  현재 작업에서는 192.168.0.57:8090/WebJSP/SimpleController.java 요청해야하는데 
  그런거는 요청할 수가 없다. 
  어떠한 요청에 의해서 SimpleController 클래스가 컴파일 되게 해야함.
  어떤 요청 주소를 별도로 생성하고 그 요청에 대해 SimpleController이 컴파일, 실행되게 
  -- web.xml 파일에서 (192.168.0.57:8090/WebServlet_1/simple이라고 입력하면 
	>>com.SimpleController 라는 자바파일을 실행하겠다.) 라는 설정을 했음 
		//	<servlet>
				<servlet-name>simplecontroller</servlet-name>
				<servlet-class>com.SimpleController</servlet-class>
			</servlet>
			<servlet-mapping>
				<servlet-name>simplecontroller</servlet-name>
				<url-pattern>/simple</url-pattern> //이 패턴 이름은 tomato, 햐햐 상관없음 
													//WebServlet("/tomato") 요기임
			</servlet-mapping> //
	1. 설정방법
 1-1. web.xml (다른 코드 해석 및 유지보수를 위해) 
 1-2. @WebServlet (권장방법) (요거는 톰캣에 내장되어있는 어노테이션) 

 [Servlet의 정의] : java로 만든 웹 전용 파일
 조건 : java 파일이 반드시 HttpServlet를 상속해야함(상속하지 않으면 그냥 java파일일 뿐) 
 		 HttpServlet를 상속 > 웹 요청처리 가능 > request, response 객체 사용 가능 
 서블릿은 url에서 요청 불가, 요청하기 위해 mapping 작업 필수, 해당 주소로 주소요청
 	- 방법 2가지 (web.xml, @WebServlet)  

--------------------------------------------------------------------------------------------

[ 동작방식 ] 
이벤트 기반으로 동작

servlet이 가지고 있는 [특정함수]는 [특정사건]이 발생하면(=이벤트) 자동 호출
 
 [특정함수] 
1. 이벤트 발생에 따라 자동호출 
 - protected void doGet
 - protected void doPost

Client 요청
192.168.0.57:8090/WebServlet_1/simple?id=jihye&pwd=1004 : Get방식, 자동 doGet 호출 
1. <form method="GET" 
2. <a href = "/simple?num=1000"></a>

192.168.0.57:8090/WebServlet_1/simple : Post방식, 자동 doPost 호출 
1. <form method="POST"

 
doGET, doPOST의 역할 : 데이터 받기
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
원래는 HttpServletRequest request = new HttpServletRequest();  

HttpServletRequest request (jsp에서 사용했던 그 request)

 */
public class SimpleController extends HttpServlet { 
	//HttpServlet를 상속받는 순간 doGet과 doPost를 상속받는 SimpleController
	//무조건 HttpServlet를 상속 받아야 servlet
	//직접 코드작성하지않음, 서블렛 생성하며 슈퍼클래스 받으니 기재된 코드들 
	private static final long serialVersionUID = 1L;
       

    public SimpleController() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("클라이언트 요청이 get 방식으로 들어왔을때 처리");
		//서블릣이 하는 역할
		//1. 한글처리 
		request.setCharacterEncoding("UTF-8");
		//2. 데이터 받기 (요청의도 파악을 위해)
		String type = request.getParameter("type");
		//3. 로직 처리 (요청에 따른 업무 수행 = service)
		Object resultObj = null; 
		if(type == null || type.equals("greeting")) {
			resultObj = "hello!"; 
			
		}else if(type.equals("date")) {
			resultObj = new Date(); 
		}else {
			resultObj = "invalid String Type"; 
		}
		//4. 요청이 완료에 따른 결과를 저장 
			//MVC패턴 방식의 특징 : 잘하는 것만 해라 (화면-JSP, 서블리에서 생성한 정보를 JSP에 전달
			//결과를 저장한다는 것
			//1. session 변수를 사용, request 변수 사용 (특정페이지(include, forward)) 
		request.setAttribute("result", resultObj);
		//5. 저장한 결과를 JSP전달(UI구성할 수 있도록)
			//결과를 forward(는 주소값 바뀌지않고 페이지 저장 가능)
			//요청한 주소는 변화가 없고 buffer의 내용만 변경해서 전달하는 방식
		RequestDispatcher dis = request.getRequestDispatcher("/simpleview.jsp");
		//view 페이지 정의 (=/simpview.jsp를 화면단으로 쓰겠다)
		
		//view 페이지에 출력할 데이터 전송 (forward방식으로)
		dis.forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("클라이언트 요청이 post 방식으로 들어왔을때 처리");
	}

}

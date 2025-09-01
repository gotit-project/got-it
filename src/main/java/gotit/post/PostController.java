package gotit.post;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@WebServlet("/post/post.do")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch(m) {
//				case "input" : input(request, response); break;
//				case "insert" : insert(request, response); break;
//				case "del" : del(request, response); break;
//				case "sel" : sel(request, response); break;
//				case "edit" : edit(request, response); break; 
//			    case "update" : update(request, response); break;
				default: list(request, response);
			}
		}else {
			list(request, response);
		}
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//서비스 계층의 싱글톤 가져오기. 비즈니스 로직을 담당하는 객체를 얻음
		PostService service = PostService.getInstance();
		
		//DB까지 내려가서 조회된 게시글 목록을 받음 
	    List<Post> list = service.listS();
	    
	    //조회한 목록을 request scope에 저장 
	    //키 "list"로 JSP에서 ${list} 또는 <c:forEach items="${list}">로 꺼내 씀 
	    request.setAttribute("list", list);
	    
	    //이동할 뷰 경로를 정함. 현재 서블릿 매핑 폴더 구조 기준으로 상대경로 
	    String view = "/WEB-INF/views/board/post-list.jsp";
	    //다른 리소스(JSP)로 제어를 넘겨주는 도우미 
	    RequestDispatcher rd = request.getRequestDispatcher(view);
	    //서버 내부 이동(forward). url은 바뀌지 않고 request객체가 JSP까지 전달됨 
	    rd.forward(request, response);
	}
	
//	private void input(HttpServletRequest request, HttpServletResponse response) 
//	        throws ServletException, IOException {
//	    response.sendRedirect("input.jsp");
//	}

//	private void insert(HttpServletRequest request, HttpServletResponse response) 
//	        throws ServletException, IOException {
//
//		PostService service = PostService.getInstance();
//		
//		String writer = request.getParameter("writer"); //jsp form의 name하고 동일하게
//		String email = request.getParameter("email");
//		String subject = request.getParameter("subject");
//		String content = request.getParameter("content");
//		String fname = request.getParameter("fname");
//		
//		//insert하면 새 객체 생성
//		//-1은 아직 DB가 발급하지 않은 PK를 임시로 채워둔 값(placeholder)
//		PostDTO dto = new PostDTO(-1, writer, email, subject, content, fname, null);
//		
//		
//		//실제 비즈니스 로직 실행. 검증->DAO호출->INSERT실행
//		boolean flag = service.insertS(dto);
//		//JSP에서 메시지를 표현하기 위해 요청 스코프에 결과를 심
//	    request.setAttribute("flag", flag);
//	    request.setAttribute("kind", "insert");
//	    
//	    String view = "msg.jsp";
//	    RequestDispatcher rd = request.getRequestDispatcher(view);
//	    rd.forward(request, response);
//	}
//		
//	private void del(HttpServletRequest request, HttpServletResponse response) 
//	        throws ServletException, IOException {
//		String seqStr = request.getParameter("seq");
//		long seq = Long.parseLong(seqStr);
//		
//		PostService service = PostService.getInstance();
//	    boolean flag = service.deleteS(seq);
//		
//	    request.setAttribute("flag", flag);
//	    request.setAttribute("kind", "del");
//	    
//	    String view = "msg.jsp";
//	    RequestDispatcher rd = request.getRequestDispatcher(view);
//	    rd.forward(request, response);
//	}
//	
//	private void sel(HttpServletRequest request, HttpServletResponse response) 
//	        throws ServletException, IOException {
//		
//		PostService service = PostService.getInstance();
//		
//		String seqStr = request.getParameter("seq");
//	
//		long seq = Long.parseLong(seqStr);	
//		
//	
//		PostDTO dto = service.selectS(seq);
//		
//		request.setAttribute("dto", dto);
//		
//		//넘어갈 페이지 
//	    RequestDispatcher rd = request.getRequestDispatcher("content.jsp");
//	    rd.forward(request, response);
//	}
//	
//	private void update(HttpServletRequest request, HttpServletResponse response) 
//	        throws ServletException, IOException {
//	    request.setCharacterEncoding("utf-8");
//
//	    long seq = Long.parseLong(request.getParameter("seq"));  
//	    String email = request.getParameter("email");
//	    String subject = request.getParameter("subject");
//	    String content = request.getParameter("content");
//		String fname = request.getParameter("fname");
//		
//
//	    PostDTO dto = new PostDTO(seq, null, email, subject, content, fname, null);
//	    PostService service = PostService.getInstance();
//
//	    boolean flag = service.updateS(dto); // DB 업데이트 수행
//
//	    request.setAttribute("flag", flag);
//	    request.setAttribute("kind", "update");
//
//	    RequestDispatcher rd = request.getRequestDispatcher("msg.jsp");
//	    rd.forward(request, response);
//	}
//	
//	private void edit(HttpServletRequest request, HttpServletResponse response) 
//	        throws ServletException, IOException {
//	    long seq = Long.parseLong(request.getParameter("seq"));
//	    PostService service = PostService.getInstance();
//	    PostDTO dto = service.selectS(seq); // DB에서 글 조회
//
//	    request.setAttribute("dto", dto);
//	    RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
//	    rd.forward(request, response);
//	}
	

}

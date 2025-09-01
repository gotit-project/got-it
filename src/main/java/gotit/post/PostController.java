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
				case "select" : select(request, response); break;
				case "write" : write(request, response); break;
				case "insert" : insert(request, response); break;
				case "delete" : delete(request, response); break;
				case "edit" : edit(request, response); break; 
//				case "input" : input(request, response); break;
//				
		
//				case "edit" : edit(request, response); break; 
//			    case "update" : update(request, response); break;
				default: list(request, response);
			}
		}else {
			list(request, response);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PostService service = PostService.getInstance();
	    List<Post> list = service.listS();
	    request.setAttribute("list", list);
	    
	    String view = "/WEB-INF/views/post/post-list.jsp";
	    RequestDispatcher rd = request.getRequestDispatcher(view);
	    rd.forward(request, response);
	}
	
	private void write(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String view = "/WEB-INF/views/post/post-write.jsp";
	    RequestDispatcher rd = request.getRequestDispatcher(view);
	    rd.forward(request, response);
	}
	
	private void insert(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {

		PostService service = PostService.getInstance();
		long board_id = 1L;
		long user_id = 1L;
		String title = request.getParameter("title"); 
		String content = request.getParameter("content");
		boolean deleted = false; 
		String tag = request.getParameter("tag"); 
		
		Post dto = new Post(-1, board_id, user_id, title, content, deleted, null, null, tag);
		
		boolean flag = service.insertS(dto);
	    request.setAttribute("flag", flag);
	    request.setAttribute("kind", "insert");
	    
	    String view = "/WEB-INF/views/post/msg.jsp";
	    RequestDispatcher rd = request.getRequestDispatcher(view);
	    rd.forward(request, response);
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {

		String postIdStr = request.getParameter("postId");

		long postId = Long.parseLong(postIdStr);	
		
		PostService service = PostService.getInstance();
	    boolean flag = service.deleteS(postId);
		
	    request.setAttribute("flag", flag);
	    request.setAttribute("kind", "del");
	    
	    String view = "/WEB-INF/views/post/msg.jsp";
	    RequestDispatcher rd = request.getRequestDispatcher(view);
	    rd.forward(request, response);
	}
	
	private void select(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		
		PostService service = PostService.getInstance();
		
		String postIdStr = request.getParameter("postId");
	
		long postId = Long.parseLong(postIdStr);	
		
		Post dto = service.selectS(postId);
		
		request.setAttribute("dto", dto);
		
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/post/post-view.jsp");
	    rd.forward(request, response);
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");


		
		long board_id = 1L;
		long user_id = 1L;
		boolean deleted = false; 
		String title = request.getParameter("title"); 
		String content = request.getParameter("content");
		String tag = request.getParameter("tag"); 

		Post dto = new Post(-1, board_id, user_id, title, content, deleted, null, null, tag);

	    PostService service = PostService.getInstance();
	    
	    
	    boolean flag = service.updateS(dto); // DB 업데이트 수행

	    request.setAttribute("flag", flag);
	    request.setAttribute("kind", "update");

	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/post/msg.js");
	    rd.forward(request, response);
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   

		String postIdStr = request.getParameter("postId");
		long postId = Long.parseLong(postIdStr);	
		
		PostService service = PostService.getInstance();
	    Post dto = service.selectS(postId);

	    request.setAttribute("dto", dto);
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/post/post-edit.jsp");
	    rd.forward(request, response);
	}

	

	
	
}

package gotit.comment;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import gotit.model.Comment;
import gotit.model.Post;
import gotit.post.PostService;

@WebServlet("/post/comment.do")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(">> CommentController service() 실행, mode=");

		String mode = request.getParameter("mode");
		if(mode!= null) {
			mode = mode.trim();
			switch(mode) {
				case "insert": insert(request, response); break;  
				default: list(request, response);
			}
		}else {
			list(request, response);
		}
	}
	
	
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String view = "/WEB-INF/views/post/post-view.jsp"; // JSP 경로
	    RequestDispatcher rd = request.getRequestDispatcher(view);
	    rd.forward(request, response); // forward 사용
	    
	}

	
	
	private void insert(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		
		CommentService service = CommentService.getInstance();
		long postId = Long.parseLong(request.getParameter("postId"));
		long userId = 1L;
		String content = request.getParameter("content");
		boolean is_answer = false;
		boolean accepted = false;
		boolean deleted = false; 
		
		Comment commentDto = new Comment(-1, postId, userId, content, is_answer, accepted, deleted, null, null);
		
		boolean flag = service.insertS(commentDto);
		
		request.setAttribute("postId", postId);
		request.setAttribute("flag", flag);
		request.setAttribute("kind", "comment-insert");
		
		String view = "/WEB-INF/views/post/msg.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
		
	
	}
	
	
	
	



	
}

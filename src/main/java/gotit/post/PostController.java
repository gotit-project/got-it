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

import gotit.model.Post;
import gotit.model.Comment;
import gotit.comment.CommentService;

@WebServlet("/post/post.do")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		if(mode != null) {
			mode = mode.trim();
			switch(mode) {
				case "select" : select(request, response); break;
				case "write" : write(request, response); break;
				case "insert" : insert(request, response); break;
				case "delete" : delete(request, response); break;
				case "edit" : edit(request, response); break; 
				default: list(request, response);
			}
		}else {
			list(request, response);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PostService service = PostService.getInstance();
	    List<Post> list = service.listS();
	    request.setAttribute("postList", list);
	    
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
		long boardId = 1L;
		long userId = 1L;
		String title = request.getParameter("title"); 
		String content = request.getParameter("content");
		boolean deleted = false; 
		String tag = request.getParameter("tag"); 
		long viewCounts = 0;
		
		Post postDto = new Post(-1, boardId, userId, title, content, deleted, null, null, tag, viewCounts);
		
		boolean flag = service.insertS(postDto);
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
		
		// 1. CommentService 객체 생성
		CommentService commentService = CommentService.getInstance(); 
		
		String postIdStr = request.getParameter("postId");
		long postId = Long.parseLong(postIdStr);	
		
		// 2. 게시글 데이터 가져오기
		service.addViewCountS(postId); // 조회수 메서드 호출
		Post postDto = service.selectS(postId);
		request.setAttribute("postDto", postDto);
		
		// 3. 해당 게시글의 댓글 목록 데이터 가져오기
	    List<Comment> commentList = commentService.selectListS(postId);
	    //게시글 번호(postId)를 인자로 받아 그 게시글에 달린 댓글 목록(List<Comment>)을 반환하는 역할
	    
	    // 4.request에 댓글 목록 담기
	    request.setAttribute("commentList", commentList);
	
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/post/post-view.jsp");
	    rd.forward(request, response);
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
		
		long boardId = 1L;
		long userId = 1L;
		boolean deleted = false; 
		String title = request.getParameter("title"); 
		String content = request.getParameter("content");
		String tag = request.getParameter("tag"); 
		long viewCounts = 0;

		Post postDto = new Post(-1, boardId, userId, title, content, deleted, null, null, tag, viewCounts);

	    PostService service = PostService.getInstance();
	    
	    
	    boolean flag = service.updateS(postDto); 

	    request.setAttribute("flag", flag);
	    request.setAttribute("kind", "update");

	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/post/msg.js");
	    rd.forward(request, response);
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
		String postIdStr = request.getParameter("postId");
		long postId = Long.parseLong(postIdStr);	
		
		PostService service = PostService.getInstance();
	    Post postDto = service.selectS(postId);

	    request.setAttribute("postDto", postDto);
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/post/post-edit.jsp");
	    rd.forward(request, response);
	}
	


	
}

package gotit.comment;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import gotit.model.Comment;

@WebServlet("/comment.do")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommentService service = CommentService.getInstance(); 

	/* ==========================
	 * URL 파라미터 mode 요청들을 분기함
	 * ========================== */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		if(mode!= null) {
			mode = mode.trim();
			switch(mode) {
				case "insert": insert(request, response); break;  
				case "select": select(request, response); break; 
				case "update": update(request, response); break; 
		    	case "delete": delete(request, response); break; 
		    	case "accept": accept(request, response); break; 
				default: list(request, response);
			}
		}else {
			list(request, response);
		}
	}
	
	/* ==========================
	 * 게시글 목록 보기
	 * ========================== */
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String view = "/WEB-INF/views/post/post-view.jsp"; 
	    RequestDispatcher rd = request.getRequestDispatcher(view);
	    rd.forward(request, response); 
	    
	}

	/* ==========================
	 * 댓글 조회
	 * ========================== */
	private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postIdStr = request.getParameter("postId");
		long postId = Long.parseLong(postIdStr);	
		
		Comment commentDto = service.selectS(postId);
		System.out.println(commentDto.getBadgeName());
		request.setAttribute("commentDto", commentDto);
		
		 RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/post/post-comment.jsp");
		 rd.forward(request, response);
	    
	}
	
	/* ==========================
	 * 댓글 등록
	 * ========================== */
	private void insert(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		long postId = Long.parseLong(request.getParameter("postId"));
		long userId = Long.parseLong(request.getParameter("userId"));
		String content = request.getParameter("content");
		boolean is_answer = false;
		boolean accepted = false;
		
		Comment commentDto = new Comment(-1, postId, userId, content, is_answer, accepted, null, null);
		
		boolean flag = service.insertS(commentDto);
		
		request.setAttribute("postId", postId);
		request.setAttribute("flag", flag);
		

	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();

	    if(flag) {
	        out.println("<script>location.href='post.do?mode=view&postId=" + postId + "';</script>");
	    } else {
	        out.println("<script>alert('댓글 등록에 실패했습니다.'); history.back();</script>");
	    }
	    out.flush();

	}
	
	/* ==========================
	 * 댓글 수정
	 * ========================== */
	private void update(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
	    
		long postId = Long.parseLong(request.getParameter("postId"));
	    long commentId = Long.parseLong(request.getParameter("commentId"));
	    String content = request.getParameter("content");

	    CommentService service = CommentService.getInstance();
	    boolean flag = service.updateS(commentId, content);

	    request.setAttribute("flag", flag);
	    response.setContentType("application/json;charset=UTF-8");
	    response.getWriter().write("{\"flag\": " + (flag ? "true" : "false") + "}");
	}
	
	/* ==========================
	 * 댓글 삭제
	 * ========================== */
	private void delete(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		long postId = Long.parseLong(request.getParameter("postId"));
	    long commentId = Long.parseLong(request.getParameter("commentId"));
	    boolean flag = false;

	    try {
	        flag = service.deleteS(commentId);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    response.setContentType("application/json;charset=UTF-8");
	    response.getWriter().write("{\"flag\":" + flag + "}");
	    response.getWriter().flush();
	}
	
	/* ==========================
	 * 댓글 채택
	 * ========================== */
	private void accept(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		long commentId = Long.parseLong(request.getParameter("commentId"));
		long postUserId = Long.parseLong(request.getParameter("postUserId")); 

		boolean flag = service.acceptS(commentId, postUserId);

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write("{\"flag\": " + (flag ? "true" : "false") + "}");

	}
}

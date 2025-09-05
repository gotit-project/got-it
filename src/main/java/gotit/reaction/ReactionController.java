package gotit.reaction;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import gotit.model.Reaction;
import gotit.reaction.ReactionService;


@WebServlet("/reaction.do")
public class ReactionController  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  ReactionService service = ReactionService.getInstance();
	  
 	/* ==========================
   	* URL 파라미터 mode 요청들을 분기함
   	* ========================== */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");
        if(mode != null) {
            mode = mode.trim();
            switch(mode) {
                case "like-action" : likeToggle(request, response); break;
                case "scrap-action" : scrapToggle(request, response); break;
              //  default: likeToggle(request, response); 
            }
        } else {
        	//likeToggle(request, response);
        }
	}
	
	/* ==========================
   	* 게시글 좋아요 토글 처리 
   	* ========================== */
	private void likeToggle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long postId = Long.parseLong(request.getParameter("postId"));
	    long userId = Long.parseLong(request.getParameter("userId"));

	    Reaction reactionDto = new Reaction(postId, userId);
	    boolean success = service.likeToggle(reactionDto);

	    response.setContentType("application/json; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.print("{\"success\": " + success + "}");
	    out.flush();
	}
//
//	/* ==========================
//   	* 사용자가 해당 게시글에 좋아요
//   	* 눌렀는지 확인 
//   	* ========================== */
//	  private void hasUserLiked (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	  long postId = Long.parseLong(request.getParameter("postId"));
//      long userId = Long.parseLong(request.getParameter("userId"));
//
//        Reaction reactionDto = new Reaction(postId, userId);
//        service.likeToggle(reactionDto);
//
//        response.setContentType("application/json; charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        out.print("{\"success\": true}");
//        out.flush();
//	}
	
	/* ==========================
   	* 게시글 스크랩 토글 처리 
   	* ========================== */
	private void scrapToggle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long postId = Long.parseLong(request.getParameter("postId"));
	    long userId = Long.parseLong(request.getParameter("userId"));

	    Reaction reactionDto = new Reaction(postId, userId);
	    boolean success = service.scrapToggle(reactionDto);

	    response.setContentType("application/json; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.print("{\"success\": " + success + "}");
	    out.flush();
	    
	}
        
	
	
}

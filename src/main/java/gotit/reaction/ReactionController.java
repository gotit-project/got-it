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
        
	private void likeToggle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    ReactionService service = ReactionService.getInstance();

	    long postId = Long.parseLong(request.getParameter("postId"));
	    long userId = Long.parseLong(request.getParameter("userId"));

	    Reaction reactionDto = new Reaction(postId, userId);
	    boolean success = service.likeToggle(reactionDto);

	    int likeCount = service.likeCountByPostId(postId);

	    response.setContentType("application/json; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.print("{\"success\": " + success + ", \"likeCount\": " + likeCount + "}");
	    out.flush();
	}


	private void scrapToggle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    ReactionService service = ReactionService.getInstance();

	    long postId = Long.parseLong(request.getParameter("postId"));
	    long userId = Long.parseLong(request.getParameter("userId"));

	    Reaction reactionDto = new Reaction(postId, userId);
	    boolean success = service.scrapToggle(reactionDto);

	    int scrapCount = service.scrapCountByPostId(postId);

	    response.setContentType("application/json; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.print("{\"success\": " + success + ", \"scrapCount\": " + scrapCount + "}");
	    out.flush();
	}
        
	
	
}

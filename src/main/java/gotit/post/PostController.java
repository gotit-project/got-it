package gotit.post;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import gotit.model.Post;
import gotit.model.Board;
import gotit.model.Comment;
import gotit.model.User;
import gotit.board.BoardService;
import gotit.comment.CommentService;
import gotit.reaction.ReactionDAO;
import gotit.reaction.ReactionService;

@WebServlet("/post.do")
public class PostController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    BoardService boardService = BoardService.getInstance();
    PostService postService = PostService.getInstance();
    CommentService commentService = CommentService.getInstance();
    
   	/* ==========================
   	 * URL 파라미터 mode 요청들을 분기함
   	 * ========================== */
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");
        if(mode != null) {
            mode = mode.trim();
            switch(mode) {
                case "view" : view(request, response); break;
                case "write" : write(request, response); break;
                case "insert" : insert(request, response); break;
                case "delete" : delete(request, response); break;
                case "edit" : edit(request, response); break;
                case "update": update(request, response); break;
                //default: list(request, response);
            }
        } else {
            //list(request, response);
        }
    }
    
    /* ==========================
   	 * 작성하기 버튼 누르면 게시글 작성 페이지로 이동
   	 * ========================== */
    private void write(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String strId = request.getParameter("id");
    	int boardId = Integer.parseInt(strId);
    	Board board = boardService.getBoard(boardId);
    	request.setAttribute("board", board);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/post/post-write.jsp");
        rd.forward(request, response);
    }
    /* ==========================
   	 * 게시글 작성 처리 
   	 * ========================== */
    private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int boardId = Integer.parseInt(request.getParameter("boardId"));
        long userId = Long.parseLong(request.getParameter("userId"));
        int categorieId = Integer.parseInt(request.getParameter("categoryId"));
        String postTag = request.getParameter("postTag");
        String title = request.getParameter("title");
        String rawContent = request.getParameter("rawContent");
        //String htmlContent = request.getParameter("htmlContent");
        String htmlContent = "test";

        Post post = new Post(-1L, boardId, userId, categorieId, postTag, title, rawContent, htmlContent, "ACTIVE");
        boolean flag = postService.insertS(post);
        
        request.setAttribute("post", post);
        request.setAttribute("flag", flag);
        request.setAttribute("kind", "insert");

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/post/msg.jsp");
        rd.forward(request, response);
    }

    /* ==========================
   	 * 게시글 클릭했을 때 해당 게시글 보여주기 
   	 * ========================== */
    private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long postId = Long.parseLong(request.getParameter("postId"));
        ReactionDAO reactionDAO = new ReactionDAO(); 
        //service.getViewCountS(postId); // 조회수 증가
        
        //스크랩 수 조회 
       // int scrapCount = reactionDAO.scrapCountByPostId(postId);
        
        Post post = postService.selectS(postId);
        request.setAttribute("post", post);
        
        
        // 로그인 유저 정보 가져오기
        User loginUser = (User) request.getSession().getAttribute("loginOkUser");
        boolean userLiked = false;
        boolean userScrapped = false;

        if (loginUser != null) {
            long loginUserId = loginUser.getUserId();
            ReactionService reactionService = ReactionService.getInstance();
            userLiked = reactionService.hasUserLiked(postId, loginUserId);
            userScrapped = reactionService.hasUserScrapped(postId, loginUserId);
        }

        request.setAttribute("userLiked", userLiked);
        request.setAttribute("userScrapped", userScrapped);

        

        List<Comment> commentList = commentService.selectListS(postId);
        request.setAttribute("commentList", commentList);
        //request.setAttribute("scrapCount", scrapCount); 

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/post/post-view.jsp");
        rd.forward(request, response);
    }
    
    /* ==========================
   	 * 게시글 상태 deleted 로 변경 
   	 * ========================== */
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long postId = Long.parseLong(request.getParameter("postId"));
        boolean flag = postService.deleteS(postId);

        request.setAttribute("flag", flag);
        request.setAttribute("kind", "delete");

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/post/msg.jsp");
        rd.forward(request, response);
    }

    /* ==========================
   	 * 게시글 편집 페이지 이동 
   	 * ========================== */
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long postId = Long.parseLong(request.getParameter("postId"));
        PostService service = PostService.getInstance();
        
        Post post = service.selectS(postId);
        request.setAttribute("post", post);
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/post/post-edit.jsp");
        rd.forward(request, response);
    }

    /* ==========================
   	 * 게시글 수정 처리 
   	 * ========================== */
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        long postId = Long.parseLong(request.getParameter("postId"));
        int boardId = Integer.parseInt(request.getParameter("boardId"));
        long userId =  Long.parseLong(request.getParameter("userId"));
        int categorieId = Integer.parseInt(request.getParameter("categorieId"));
        String postTag = request.getParameter("postTag");
        String title = request.getParameter("title");
        String rawContent = request.getParameter("rawContent");
        String htmlContent = request.getParameter("htmlContent");        
        
        Post post = new Post(postId, boardId, userId, categorieId, postTag, title, rawContent, htmlContent, "ACTIVE");
        PostService service = PostService.getInstance();
        boolean flag = service.updateS(post);

        request.setAttribute("flag", flag);
        request.setAttribute("kind", "update");

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/post/msg.jsp");
        rd.forward(request, response);
    }
}

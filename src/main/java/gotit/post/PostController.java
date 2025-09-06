package gotit.post;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import gotit.model.Post;
import gotit.model.Board;
import gotit.model.Comment;
import gotit.model.User;
import gotit.board.BoardService;
import gotit.comment.CommentService;
import gotit.reaction.ReactionService;

@WebServlet("/post.do")
public class PostController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    BoardService boardService = BoardService.getInstance();
    PostService postService = PostService.getInstance();
    CommentService commentService = CommentService.getInstance();
    ReactionService reactionService = ReactionService.getInstance();
    
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
   	 * 작성하기 화면 이동
   	 * 파라미터로 넘어온 boardId 받음
   	 * 해당 board 정보 조회해서 request
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
   	 * 작성 폼에서 넘어온 데이터를 받음
   	 * Pos 객체 생성후 Service -> DB 
   	 * ========================== */
    private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int boardId = Integer.parseInt(request.getParameter("boardId"));
        long userId = Long.parseLong(request.getParameter("userId"));
        int categorieId = Integer.parseInt(request.getParameter("categoryId"));
        String postTag = request.getParameter("postTag");
        String title = request.getParameter("title");
        String rawContent = request.getParameter("rawContent");
        String htmlContent = request.getParameter("htmlContent");
        

        Post post = new Post(-1L, boardId, userId, categorieId, postTag, title, rawContent, htmlContent, "ACTIVE");
        boolean flag = postService.insertS(post);
        
        request.setAttribute("post", post);
        request.setAttribute("flag", flag);
  
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        if(flag) {
            out.println("<script>alert('게시글이 작성되었습니다!'); location.href='post.do?mode=view&postId=" + post.getPostId() + "';</script>");
        } else {
            out.println("<script>alert('게시글 작성에 실패했습니다.'); history.back();</script>");
        }
        out.flush();
    }

    /* ==========================
   	 * 게시글 상세보기
   	 * postId로 게시글 조회하여 request 저장 
   	 * 로그인한 사용자 좋아요, 스크랩 확인
   	 * 댓글 목록 조회 후 뷰페이지 전달 
   	 * ========================== */
    private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long postId = Long.parseLong(request.getParameter("postId"));
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
   	 * 게시글 비활성화
   	 * postId로 게시글 조회 후 상태 변경
   	 * 성공 여부 따라 alert 
   	 * ========================== */
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	long postId = Long.parseLong(request.getParameter("postId"));
    	Post post = postService.selectS(postId); // postId로 DB에서 게시글 가져오기
    	boolean flag = postService.deleteS(postId);

        request.setAttribute("flag", flag);
        request.setAttribute("kind", "delete");

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        if(flag) {																
            out.println("<script>alert('게시글이 삭제되었습니다!'); location.href='board.do?mode=list&id=" + post.getBoardId() + "&categoryId=" + post.getCategoryId() + "&page=1';</script>");
        } else {
            out.println("<script>alert('게시글 삭제에 실패했습니다.'); history.back();</script>");
        }
        out.flush();
    }

    /* ==========================
   	 * 게시글 수정 페이지 이동
   	 * postId와 boardId를 받아 게시판, 게시글 조회
   	 * 뷰페이지에 전달
   	 * ========================== */
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long postId = Long.parseLong(request.getParameter("postId"));
        int boardId = Integer.parseInt(request.getParameter("boardId"));
        Post post = postService.selectS(postId);
        Board board = boardService.getBoard(boardId);
        
        request.setAttribute("post", post);
        request.setAttribute("board", board);
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/post/post-edit.jsp");
        rd.forward(request, response);
    }

    /* ==========================
   	 * 게시글 수정 처리 
   	 * 작성 폼에서 수정된 데이터 받아 Post 객체 생성
   	 * DB 업데이트 실행
   	 * 성공 여부 따라 alert 
   	 * ========================== */
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long postId = Long.parseLong(request.getParameter("postId"));
        int boardId = Integer.parseInt(request.getParameter("boardId"));
        long userId =  Long.parseLong(request.getParameter("userId"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String postTag = request.getParameter("postTag");
        String title = request.getParameter("title");
        String rawContent = request.getParameter("rawContent");
        String htmlContent = request.getParameter("htmlContent"); 
        
        Post post = new Post(postId, boardId, userId, categoryId, postTag, title, rawContent, htmlContent, "ACTIVE");
        boolean flag = postService.updateS(post);

        request.setAttribute("flag", flag);
        request.setAttribute("kind", "update");

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        if(flag) {
            out.println("<script>alert('게시글이 수정되었습니다!'); location.href='post.do?mode=view&postId=" + post.getPostId() + "&page=1';</script>");
        } else {
            out.println("<script>alert('게시글 수정에 실패했습니다.'); history.back();</script>");
        }
        out.flush();
    }
}

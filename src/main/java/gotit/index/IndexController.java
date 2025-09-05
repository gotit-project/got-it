//package gotit.index;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//import gotit.model.Board;
//import gotit.model.Post;
//
//
//import gotit.board.BoardService;
//import gotit.comment.CommentService;
//import gotit.post.PostService;
//import gotit.reaction.ReactionService;
//
//@WebServlet("/index.do")
//public class IndexController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//    BoardService boardService = BoardService.getInstance();
//    PostService postService = PostService.getInstance();
//    CommentService commentService = CommentService.getInstance();
//    ReactionService reactionService = ReactionService.getInstance();
//	
//    	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	        try {
//    	            // 1. 게시판 목록 가져오기
//    	            List<Board> boards = boardService.getBoard(boardId);
//    	            request.setAttribute("boards", boards);
//
//    	            // 2. 게시글 전체 가져오기 + 보드별로 Map에 넣기
//    	            Map<Integer, List<Post>> boardPostsMap = new HashMap<>();
//    	            String sql = "SELECT post_id, board_id, title, content, user_id, created_at FROM posts ORDER BY board_id, created_at DESC";
//
//    	            try (Connection con = ds.getConnection();
//    	                 PreparedStatement pstmt = con.prepareStatement(sql);
//    	                 ResultSet rs = pstmt.executeQuery()) {6
//
//    	                while (rs.next()) {
//    	                    long postId = rs.getLong("post_id");
//    	                    int boardId = rs.getInt("board_id");
//    	                    String title = rs.getString("title");
//    	                    String content = rs.getString("content");
//    	                    long userId = rs.getLong("user_id");
//    	                    Date createdAt = rs.getDate("created_at");
//
//    	                    Post post = new Post(postId, boardId, title, content, userId, createdAt);
//
//    	                    // boardId 기준으로 Map에 넣기
//    	                    boardPostsMap.computeIfAbsent(boardId, k -> new ArrayList<>()).add(post);
//    	                }
//    	            }
//
//    	            request.setAttribute("boardPostsMap", boardPostsMap);
//
//    	            // 3. JSP로 포워딩
//    	            request.getRequestDispatcher("/index.jsp").forward(request, response);
//
//    	        } catch (Exception e) {
//    	            e.printStackTrace();
//    	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 오류");
//    	        }
//    	    }
//
//    }
//

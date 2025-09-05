//package gotit.controller;
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
//import gotit.post.PostService;
//
///**
// * Servlet implementation class IndexController
// */
//@WebServlet("/index.do")
//public class IndexController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//     
//    private BoardService boardService = new BoardService();
//	private PostService postService = new PostService();
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Board별 게시글 가져오기
//        List<Board> boardList = boardService.selectAllBoards();
//
//        for (Board board : boardList) {
//            List<Post> posts = postService.selectPostsByBoard(board.getBoardId());
//            board.setPosts(posts); // Board에 게시글 세팅
//        }
//
//        request.setAttribute("boardList", boardList);
//        request.getRequestDispatcher("/index.jsp").forward(request, response);
//    }
//
//}

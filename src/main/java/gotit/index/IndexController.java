package gotit.index;

import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.*;

import gotit.board.BoardService;
import gotit.model.Board;
import gotit.model.Post;
import gotit.post.PostService;


@WebServlet("/index.do")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IndexService service = IndexService.getInstance();
    private BoardService boardService = BoardService.getInstance();
    private PostService postService = PostService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 //보드 수동 생성
        List<Board> boards = new ArrayList<>();
        boards.add(new Board(4, "공지사항"));
        boards.add(new Board(1, "Q&A"));
        boards.add(new Board(2, "지식나눔"));

        //각 보드별 게시글 Map
        Map<String, List<Post>> boardPostsMap = new HashMap<>();
        boardPostsMap.put("4", postService.getPostsByBoard(4));
        boardPostsMap.put("1", postService.getPostsByBoard(1));
        boardPostsMap.put("2", postService.getPostsByBoard(2));

        request.setAttribute("boards", boards);
        request.setAttribute("boardPostsMap", boardPostsMap);
        
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
        

    }
}


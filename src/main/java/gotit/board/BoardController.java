// gotit.board.BoardController.java
package gotit.board;

import gotit.model.Board;
import gotit.model.Page;
import gotit.model.Post;
import gotit.post.PostService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

import static gotit.common.util.SqlUtils.*;

import static gotit.core.ViewPaths.*; // 예: BOARD_LIST = "/WEB-INF/views/board/list.jsp"

public class BoardController {
    private static final BoardController INSTANCE = new BoardController();
    private final BoardService boardService = BoardService.getInstance();
    private final PostService postService = PostService.getInstance();

    private BoardController(){}

    public static BoardController getInstance(){ 
    	return INSTANCE; 
    }

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String mode = request.getParameter("mode");
        switch (mode) {
            case "list":  list(request, response);  break;
            default:      list(request, response);  break;
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<Post> list = null;
    	int curPage = 1;                      // 1-base
        int pageSize = 10;                    // 기본 페이지 크기
    	int boardId = Integer.parseInt(request.getParameter("id"));
        int catParam = Integer.parseInt(request.getParameter("categoryId"));
        String sortParam = request.getParameter("sort");
        String orderBy;
        // 기본값은 최신순
        if (sortParam == null || sortParam.isBlank()) {
            orderBy = POST_SELECT_CREATE_AT;
        } else {
            switch (sortParam) {
                case "new":      // 최신순
                    orderBy = POST_SELECT_CREATE_AT;
                    break;
                case "like":     // 좋아요순
                    orderBy = POST_SELECT_LIKE;
                    break;
                case "comment":  // 댓글순
                    orderBy = POST_SELECT_COMMENT;
                    break;
                case "view":     // 조회순(추가 예시)
                    orderBy = POST_SELECT_VIEW;
                    break;
                default:         // 안전장치 (예상 외 값이면 최신순으로 fallback)
                    orderBy = POST_SELECT_CREATE_AT;
                    break;
            }
        }
        System.out.println(orderBy);

        Board board = boardService.getBoard(boardId);
        if (board == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Board not found: ");
            return;
        }
        curPage = Integer.parseInt(request.getParameter("page"));
        int totalCount = 0;
        Page page = null;
        
        if (catParam == 0) {
        	totalCount = postService.countS(boardId);
        	page = new Page(curPage, pageSize, totalCount);
        	list = postService.listPageS(boardId , orderBy, page);
        } else {
        	totalCount = postService.countCatS(boardId, catParam);
        	page = new Page(curPage, pageSize, totalCount);
        	list = postService.listCatPageS(boardId ,catParam , orderBy, page);
        }

        // 4) 뷰로 전달
        request.setAttribute("board", board);
        request.setAttribute("postList", list);
        request.setAttribute("page", page);
        request.setAttribute("catParam", catParam);
        
        RequestDispatcher rd = request.getRequestDispatcher(BOARD_LIST);
        rd.forward(request, response);
    }
}
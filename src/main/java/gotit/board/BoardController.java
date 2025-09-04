// gotit.board.BoardController.java
package gotit.board;

import gotit.model.Board;
import gotit.model.Post;
import gotit.post.PostService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

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

        // 0) 보드 조회 (필수 파라미터 검증)
        final String boardName = request.getParameter("name");
        if (boardName == null || boardName.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Required param 'name' is missing");
            return;
        }
        final Board board = boardService.getBoard(boardName);
        if (board == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Board not found: " + boardName);
            return;
        }
        final int boardId = board.getBoardId();

        // 1) 페이징 파라미터 (기본값 + 안전 클램프)
        int curPage = 1;                      // 1-base
        int pageSize = 10;                    // 기본 페이지 크기

        try {
            String pageStr = request.getParameter("page");
            if (pageStr != null && !pageStr.isBlank()) {
                curPage = Integer.parseInt(pageStr.trim());
            }
        } catch (NumberFormatException ignore) {}
        if (curPage < 1) curPage = 1;

        try {
            String sizeStr = request.getParameter("size");
            if (sizeStr != null && !sizeStr.isBlank()) {
                pageSize = Integer.parseInt(sizeStr.trim());
            }
        } catch (NumberFormatException ignore) {}
        if (pageSize < 1) pageSize = 10;      // 0 금지
        // 필요하면 상한도 걸 수 있음: if (pageSize > 100) pageSize = 100;

        // 2) 총 건수 (목록과 동일 조건으로 카운트)
        final int totalCount = postService.countS(boardId); // status='ACTIVE' + board_id=? 가정
        final int totalPage  = Math.max(1, (int) Math.ceil(totalCount / (double) pageSize));
        
        // 현재 페이지 상한/하한 클램프
        if (curPage > totalPage) curPage = totalPage;
        if (curPage < 1) curPage = 1;

        // 3) 목록 조회 (offset 기반)
        int offset = (curPage - 1) * pageSize;
        if (offset < 0) offset = 0;           // 방어
        final List<Post> list = postService.listPageS(boardId, offset, pageSize);
        // ※ DAO에서는 LIMIT ? OFFSET ? 라면 바인딩 순서가 (pageSize, offset)인지 꼭 확인

        // 4) 뷰로 전달
        request.setAttribute("board", board);
        request.setAttribute("postList", list);
        request.setAttribute("curPage", curPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalCount", totalCount);

        System.out.println("postList size = " + list.size());
        for (Post p : list) {
            System.out.println("postId=" + p.getPostId() + ", title=" + p.getTitle());
        }
        // (선택) 디버깅 로그
        System.out.printf("[Board:list] boardId=%d, total=%d, pageSize=%d, totalPage=%d, cur=%d, offset=%d%n",
                boardId, totalCount, pageSize, totalPage, curPage, offset);

        RequestDispatcher rd = request.getRequestDispatcher(BOARD_LIST);
        rd.forward(request, response);
    }
}
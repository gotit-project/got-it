// gotit.board.BoardController.java
package gotit.board;

import gotit.model.Board;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

import static gotit.core.ViewPaths.*; // 예: BOARD_LIST = "/WEB-INF/views/board/list.jsp"

public class BoardController {
    private static final BoardController INSTANCE = new BoardController();
    private final BoardService service = BoardService.getInstance();

    private BoardController(){}

    public static BoardController getInstance(){ 
    	return INSTANCE; 
    }

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String mode = request.getParameter("mode");
        switch (mode) {
            case "list":  list(request, response);  break;
            case "view":  view(request, response);  break;
            default:      list(request, response);  break;
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = param(request, "type"); // QNA/KNOW/FREE/NOTICE or null
        List<Board> boards = service.getBoards(type);
        request.setAttribute("boards", boards);
        RequestDispatcher rd = request.getRequestDispatcher(BOARD_LIST);
        rd.forward(request, response);
    }

    private void view(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long boardId = parseLongOrZero(request.getParameter("id"));
        Board board = service.getBoard(boardId);
        if (board == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        request.setAttribute("board", board);
        RequestDispatcher rd = request.getRequestDispatcher(BOARD_DETAIL);
        rd.forward(request, response);
    }

    // utils
    private static String param(HttpServletRequest req, String name){
        String v = req.getParameter(name);
        return v == null ? null : v.trim();
    }
    private static long parseLongOrZero(String s){
        try { return Long.parseLong(s); } catch (Exception e) { return 0L; }
    }
}
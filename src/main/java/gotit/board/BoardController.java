// gotit.board.BoardController.java
package gotit.board;

import gotit.model.Board;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

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
            default:      list(request, response);  break;
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String boardName = request.getParameter("name"); // QNA/KNOW/FREE/NOTICE or null
        Board board = service.getBoard(boardName);
        System.out.println(boardName);
        
        request.setAttribute("board", board);
        
        System.out.println("boards attr = " + request.getAttribute("board"));
        RequestDispatcher rd = request.getRequestDispatcher(BOARD_LIST);
        rd.forward(request, response);
    }
}
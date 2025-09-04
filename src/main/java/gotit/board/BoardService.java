// gotit.board.BoardService.java
package gotit.board;

import gotit.model.Board;

import java.sql.SQLException;

public class BoardService {
    private static final BoardService INSTANCE = new BoardService();
    private final BoardDAO dao = BoardDAO.getInstance();

    private BoardService(){}

    public static BoardService getInstance(){ return INSTANCE; }

    public Board getBoard(int boardId) {
        try {
        	System.out.println(boardId);
            return dao.findByBoard(boardId);
        } catch (SQLException e) {
            return null;
        }
    }
}
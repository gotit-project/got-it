// gotit.board.BoardService.java
package gotit.board;

import gotit.model.Board;

import java.sql.SQLException;

public class BoardService {
    private static final BoardService INSTANCE = new BoardService();
    private final BoardDAO dao = BoardDAO.getInstance();

    private BoardService(){}

    public static BoardService getInstance(){ return INSTANCE; }

    public Board getBoard(String boardName) {
        try {
        	System.out.println(boardName);
            return dao.findByBoard(boardName);
        } catch (SQLException e) {
            return null;
        }
    }
    
    
}
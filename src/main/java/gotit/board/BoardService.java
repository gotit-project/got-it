// gotit.board.BoardService.java
package gotit.board;

import gotit.model.Board;

import java.sql.SQLException;
import java.util.List;

public class BoardService {
    private static final BoardService INSTANCE = new BoardService();
    private final BoardDAO dao = BoardDAO.getInstance();

    private BoardService(){}

    public static BoardService getInstance(){ return INSTANCE; }
    
    public List<Board> getBoardList() {
    	try {
            return dao.getBoards();
        } catch (Exception e) {
            return null;
        }
    }

    public Board getBoard(int boardId) {
        try {
            return dao.findByBoard(boardId);
        } catch (SQLException e) {
            return null;
        }
    }
}
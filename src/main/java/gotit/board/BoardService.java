// gotit.board.BoardService.java
package gotit.board;

import gotit.model.Board;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class BoardService {
    private static final BoardService INSTANCE = new BoardService();
    private final BoardDAO dao = BoardDAO.getInstance();

    private BoardService(){}

    public static BoardService getInstance(){ return INSTANCE; }

    public List<Board> getBoards(String typeCode) {
        try {
            if (typeCode == null || typeCode.isBlank()) {
                return dao.findAll();
            }
            return dao.findByTypeCode(typeCode);
        } catch (SQLException e) {
            // TODO: 로깅
            return Collections.emptyList();
        }
    }

    public Board getBoard(long boardId) {
        try {
            return dao.findById(boardId);
        } catch (SQLException e) {
            // TODO: 로깅
            return null;
        }
    }
}
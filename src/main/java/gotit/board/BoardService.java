package gotit.board;

import java.util.ArrayList;

import gotit.model.Board;

public class BoardService {
	private BoardDAO dao;
	
	private static final BoardService instance = new BoardService();

	private BoardService() {
		dao = new BoardDAO();
	}
	
	public static BoardService getInstance() {
		return instance;
	}
	
	public ArrayList<Board> list() {
		return dao.getList();
	}
}

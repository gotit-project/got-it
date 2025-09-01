package gotit.board;

public class BoardService {
	private BoardDAO dao;
	
	private static final BoardService instance = new BoardService();

	private BoardService() {
		dao = new BoardDAO();
	}
	
	public static BoardService getInstance() {
		return instance;
	}
}

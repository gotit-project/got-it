package gotit.boardtype;

public class BoardTypeService {
	private BoardTypeDAO dao;
	
	private static final BoardTypeService instance = new BoardTypeService();
	
	private BoardTypeService() {
		dao = new BoardTypeDAO();
	}
	
	public static BoardTypeService getInstance() {
		return instance;
	}
}

package gotit.index;

import gotit.board.BoardDAO;
import gotit.post.PostService;
import gotit.model.Board;
import gotit.model.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexService {
	 private static final IndexService instance = new IndexService();
	    private BoardDAO boardDao;
	    private PostService postService;

	    private IndexService() {
	        boardDao = BoardDAO.getInstance();
	        postService = PostService.getInstance();
	    }

	    public static IndexService getInstance() {
	        return instance;
	    }

	    // ==========================
	    // 전체 보드와 보드별 게시글 가져오기
	    // ==========================
	    public Map<String, Object> getBoardsAndPosts() {
	        Map<String, Object> map = new HashMap<>();

	        try {
	            // 전체 보드 가져오기
	            List<Board> boards = boardDao.getBoards();

	            // boardId 1, 2, 3만 필터
	            List<Board> filteredBoards = boards.stream()
	                    .filter(b -> b.getBoardId() == 1 || b.getBoardId() == 2 || b.getBoardId() == 3)
	                    .toList();

	            map.put("boards", filteredBoards); // "boards" key: 전체 보드 리스트

	            // 각 보드별 게시글 가져오기
	            Map<Integer, List<Post>> boardPostsMap = new HashMap<>();
	            for (Board board : filteredBoards) {
	                int boardId = board.getBoardId();
	                List<Post> posts = postService.getPostsByBoard(boardId); // PostService 호출
	                boardPostsMap.put(boardId, posts);
	            }
	            map.put("boardPostsMap", boardPostsMap); // "boardPostsMap" key: 보드별 게시글

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return map;
	    }
}

package gotit.comment;

import java.util.List;

import gotit.model.Comment;


public class CommentService {
	private CommentDAO commentDao;
	private static final CommentService instance = new CommentService();
	private CommentService() { 
		commentDao = new CommentDAO();
	}
	
	public static CommentService getInstance() {
		return instance;
	}
	
	/* ==========================
   	 * 특정 댓글 1개 조회 
   	 * commentID 기준 
   	 * ========================== */
	public Comment selectS(long commentId) {
	    return commentDao.select(commentId); 
	}

	/* ==========================
   	 * 특정 게시글에 달린 댓글 리스트 조회
   	 * postId 기준
   	 * ========================== */
	public List<Comment> selectListS(long postId) {
        return commentDao.selectList(postId) ; 
    }
	
	/* ==========================
   	 * 댓글 등록
   	 * ========================== */
	public boolean insertS(Comment commentDto) {
		return commentDao.insert(commentDto);	
	}
	
	/* ==========================
   	 * 댓글 수정 
   	 * ========================== */
	public boolean updateS(long commentId, String content) {
	    return commentDao.update(commentId, content);
	}
	
	/* ==========================
   	 * 댓글 삭제 
   	 * ========================== */
	public boolean deleteS(long commentId) {
	    return commentDao.delete(commentId);
	}
	
}


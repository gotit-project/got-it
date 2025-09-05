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
	
	public Comment selectS(long commentId) {
	    return commentDao.select(commentId); // DAO의 commentId 기준 select() 호출
	}

	public List<Comment> selectListS(long postId) {
        return commentDao.selectList(postId) ; // DAO 호출하지않으면 무한반복으로 자기자신만 호출
    }
	
	public boolean insertS(Comment commentDto) {
		return commentDao.insert(commentDto);	
	}
	
	public boolean updateS(long commentId, String content) {
	    return commentDao.update(commentId, content);
	}
	
	public boolean deleteS(long commentId) {
	    return commentDao.delete(commentId);
	}
	
}


package gotit.comment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import gotit.model.Comment;
import gotit.model.Post;
import gotit.comment.CommentDAO;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


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
        return commentDao.selectList(postId); // DAO 호출하지않으면 무한반복으로 자기자신만 호출
    }
	
	public boolean insertS(Comment commentDto) {
		return commentDao.insert(commentDto);	
	}
	
	public boolean updateS(long commentId, String content) {
	    return commentDao.update(commentId, content);
	}
	
}


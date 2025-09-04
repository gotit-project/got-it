package gotit.post;

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
import java.util.ArrayList;

import gotit.model.Post;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class PostService {
	private PostDAO postDao;

	private static final PostService instance = new PostService();
	
	private PostService() { 
		postDao = new PostDAO();
	}
	
	public static PostService getInstance() {
		return instance;
	}
	
	public List<Post> listS(){
		return postDao.postList();
	}
	
	public boolean insertS(Post postDto) {
		return postDao.insert(postDto);
	}
	public boolean deleteS(long postId) {
		return postDao.delete(postId);
	}
	public Post selectS(long postId) {
		return postDao.select(postId);
	}	
	public boolean updateS(Post postDto) {
		return postDao.update(postDto);
	}
	
	// 조회 수
	public boolean addViewCountS(long postId) {
        return postDao.addViewCount(postId);
    }
	
	//페이징
    public List<Post> listPageS(int boardId, int curPage, int pageSize) {
        int start = (curPage - 1) * pageSize;
        try {
            return postDao.listPage(boardId, start, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public int countS(int boardId) {
        try {
            return postDao.countPosts(boardId);
        } catch (Exception e) {
        	throw new RuntimeException("countS failed: boardId=" + boardId, e);
        }
    }
    
}


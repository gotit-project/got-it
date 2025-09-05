package gotit.post;

import java.util.List;
import java.util.ArrayList;

import gotit.model.Page;
import gotit.model.Post;

public class PostService {
	private static final PostService instance = new PostService();
	private PostDAO postDao;
	
	private PostService() { 
		postDao = new PostDAO();
	}
	public static PostService getInstance() {
		return instance;
	}
	
   public List<Post> getMainPosts(int boardId) {
        try {
            return postDao.getMainPosts(boardId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
	
	
   public List<Post> listPageS(int boardId, String orderBy, Page page) {
        try {
            return postDao.listPage(boardId, orderBy, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
   public List<Post> listCatPageS(int boardId, int categoryId, String orderBy, Page page) {
	   try {
		   return postDao.listPage(boardId, categoryId, orderBy, page);
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
    
    public int countCatS(int boardId, int categoryId) {
    	try {
    		return postDao.countCatPosts(boardId, categoryId);
    	} catch (Exception e) {
    		throw new RuntimeException("countS failed: boardId=" + boardId, e);
    	}
    }

	public boolean insertS(Post post) {
		return postDao.insert(post);
	}
	public boolean deleteS(long postId) {
		return postDao.delete(postId);
	}
	public Post selectS(long postId) {
		return postDao.view(postId);
	}	
	public boolean updateS(Post post) {
		return postDao.update(post);
	}

    public List<Post> selectPostsByBoard(long boardId) {
        return postDao.selectPostsByBoard(boardId); // DAO에서 DB 조회
    }


    
}


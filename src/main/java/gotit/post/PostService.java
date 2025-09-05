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

	/* ==========================
	 * 메인에 보여줄 게시글 목록 가져오기
	 * ========================== */
	public List<Post> getMainPosts(int boardId) {
        try {
            return postDao.getMainPosts(boardId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
	
	 /* ==========================
	 * 특정 게시판의 게시글 페이징 처리된 
	 * 목록 조회
	 * ========================== */
    public List<Post> listPageS(int boardId, String orderBy, Page page) {
        try {
            return postDao.listPage(boardId, orderBy, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
     }
   
	/* ==========================
	 * 특정 게시판 + 카테고리 게시글 페이징
	 * 처리된 목록 조회 
	 * ========================== */
    public List<Post> listCatPageS(int boardId, int categoryId, String orderBy, Page page) {
	   try {
		   return postDao.listPage(boardId, categoryId, orderBy, page);
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   return new ArrayList<>();
    }
   
	/* ==========================
	* 게시판 내 전체 게시글 수 조회 
	* ========================== */
    public int countS(int boardId) {
       try {
           return postDao.countPosts(boardId);
       } catch (Exception e) {
       	throw new RuntimeException("countS failed: boardId=" + boardId, e);
       }
    }
    
	/* ==========================
	* 게시판 + 카테고리별 게시글 수 조회 
	* ========================== */
    public int countCatS(int boardId, int categoryId) {
    	try {
    		return postDao.countCatPosts(boardId, categoryId);
    	} catch (Exception e) {
    		throw new RuntimeException("countS failed: boardId=" + boardId, e);
    	}
    }
    
    /* ==========================
    * 게시글 등록 
    * ========================== */
	public boolean insertS(Post post) {
		return postDao.insert(post);
	}
	
	/* ==========================
	* 게시글 삭제 (상태값 변경)
	* ========================== */
	public boolean deleteS(long postId) {
		return postDao.delete(postId);
	}
	
	/* ==========================
	* 게시글 상세보기 (postId 기준 조회) 
	* ========================== */
	public Post selectS(long postId) {
		return postDao.view(postId);
	}	
	
	/* ==========================
	* 게시글 수정 
	* ========================== */
	public boolean updateS(Post post) {
		return postDao.update(post);
	}
}


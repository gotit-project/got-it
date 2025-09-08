package gotit.mypage;

import java.util.ArrayList;
import java.util.List;

import gotit.model.Page;
import gotit.model.Post;

public class MypageService {
	private static final MypageService instance = new MypageService();
	private MypageDAO mypageDao;
	
	private MypageService() { 
		mypageDao = new MypageDAO();
	}
	
	public static MypageService getInstance() {
		return instance;
	}
	
	// 내가 작성한 글 
	public List<Post> getUserPost(long userId, String orderBy, Page page){
        try {
        	return mypageDao.postList(userId, orderBy, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
	}
	
	// 작성한 글 카운팅
    public int countS(long userId) {
        try {
            return mypageDao.countPosts(userId);
        } catch (Exception e) {
        	throw new RuntimeException("countS failed: boardId=" + userId, e);
        }
     }
    
    // 내가 스크랩한 글
    public List<Post> getScrapPost(long userId, String orderBy, Page page) {
        return mypageDao.selectScrapPosts(userId, orderBy, page);
    }

    // 스크랩한 글 카운팅 
    public int countScrapPosts(long userId) {
    	try {
            return mypageDao.countScrapPosts(userId);
    	} catch(Exception e) {
        	throw new RuntimeException("countScrapPosts failed: boardId=" + userId, e);
    	}
    }
}


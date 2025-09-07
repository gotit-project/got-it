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
	
	public List<Post> getUserPost(long userId, String orderBy, Page page){
        try {
        	return mypageDao.postList(userId, orderBy, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
	}
	
    public int countS(long userId) {
        try {
            return mypageDao.countPosts(userId);
        } catch (Exception e) {
        	throw new RuntimeException("countS failed: boardId=" + userId, e);
        }
     }
}


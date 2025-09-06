package gotit.mypage;

import java.util.List;

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
	
	public List<Post> listS(){
		return mypageDao.list();
	}
	
}


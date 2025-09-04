package gotit.mypage;

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

public class MypageService {
	
	private MypageDAO mypageDao;

	private static final MypageService instance = new MypageService();
	
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


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

import gotit.post.Post;
/*import file.mvc.control.FileController;
import file.mvc.fileset.Path;*/
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class PostService {
	//실제 DB접근은 DAO가 담당하고, Sercive는 DAO를 사용
	private PostDAO dao;

	//싱글톤 패턴 : Service객체를 단 하나만 생성해 사용
	//static final이므로 프로그램 시작 시 한번만 만들어
	private static final PostService instance = new PostService();
	
	//외부에서 new BoardServic 호출 못하게 함
	private PostService() {
		//내부에서 DAO를 초기화->Service와 DAO가 1:1로 묶여 항상 같이 움직임 
		dao = new PostDAO();
	}
	
	//DB핸들링 하는 메서드는 이것 호출해서 사용
	public static PostService getInstance() {
		//다른곳에서 쓸수 있도록 
		return instance;
	}
	
	//사용자가 입력한 여러 정보들을 한 객체(Board)에 담아서 한번에 전달함.
	public List<Post> listS(){
		return dao.list();
	}
	
//	public boolean insertS(PostDTO dto) {
//		return dao.insert(dto);
//	}
//	
//	public boolean deleteS(long seq) {
//		return dao.delete(seq);
//	}
//	
	public Post selectS(long postId) {
		return dao.select(postId);
	}
//	
//	public boolean updateS(PostDTO dto) {
//		return dao.update(dto);
//	}
    
}


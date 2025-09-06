package gotit.auth;

import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCrypt;

import gotit.common.util.AuthUtils;
import gotit.model.User;

public class AuthService {
private AuthDAO dao;
	
	private static final AuthService instance = new AuthService();
	private AuthService() {
		dao = AuthDAO.getInstance();
	}
	
	public static AuthService getInstance() {
		return instance;
	}
	
	/* ===========================
	 * 사용자가 입력한 이메일 값을 가지고
	 * 사용자 정보를 조회하기 위해 DAO 호출
	 ============================= */
	public User getUser(String email) {
		User user = dao.getUser(email);
		user.setPwd(""); //비밀번호 정보 제거
		
		return user;
	}
	/* ===========================
	 * 로그인 정보를 비교
	 * 이메일/패스워드
	 ============================= */
	public int check(String email, String pwd) {
		User user = dao.getUser(email);
		if(user == null) {
			return 1;
		}else {
			String dbPwd = user.getPwd();
			if(dbPwd != null) dbPwd.trim();
			
			 // 1) BCrypt 비교 (평문 vs 해시)
		    boolean ok = BCrypt.checkpw(pwd == null ? "" : pwd, dbPwd);
		    return ok ? 3 : 2; // 3=성공, 2=비번 불일치
		}
	}
	
    public String signup(String name, String email, String rawPassword, String alias) {
        if (dao.existsByEmail(email))  return "이미 사용 중인 이메일입니다.";
        if (dao.existsByAlias(alias)) return "이미 사용 중인 닉네임입니다.";

        String hash = AuthUtils.hash(rawPassword);
        
     // 3) 삽입
	    try {
	        int ok = dao.insertUser(name, email, hash, alias);
	        return ok == 1 ? null : "회원가입 처리에 실패했습니다. 잠시 후 다시 시도해주세요.";
	    } catch (SQLException e) {
	        return "회원가입 처리에 실패했습니다. 잠시 후 다시 시도해주세요.";
	    }
    }
	
}

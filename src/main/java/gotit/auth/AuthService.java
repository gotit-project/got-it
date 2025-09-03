package gotit.auth;

import java.sql.SQLException;

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
			
			if(!dbPwd.equals(pwd)) {
				return 2;
			}else {
				return 3;				
			}
		}
	}
	
	public int signup(String name, String email, String passwd, String alias) {
	    AuthDAO dao = AuthDAO.getInstance();

//	    // 1) 중복 검사
//	    if (dao.existsByEmail(dto.getEmail())) return SignUpCode.DUPLICATE_EMAIL;
//	    if (dao.existsByAlias(dto.getAlias())) return SignUpCode.DUPLICATE_ALIAS;

	    // 2) 비번 해시 (BCrypt 권장)
	    //String hashed = BCrypt.hashpw(dto.getRawPassword(), BCrypt.gensalt());

	    // 3) 삽입
	    try {
	        int rows = dao.insertUser(name, email, passwd, alias);
	        return 1; //rows == 1 ? SignUpCode.SUCCESS : SignUpCode.DB_ERROR;
	    } catch (SQLException e) {
	        // unique 제약 위반 등을 세부코드로 매핑해도 됨
	        return -1; //SignUpCode.DB_ERROR;
	    }
	}
	
}

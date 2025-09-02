package gotit.auth;

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
	
	
}

package gotit.auth;

import java.io.IOException;

import gotit.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static gotit.core.ViewPaths.*;

public class AuthController {
	// 싱글톤 패턴 적용
	private static final AuthController instance = new AuthController(); 

	private AuthController() {}
	
    public static AuthController getInstance() {
        return instance;
    }
    
	/* ==========================
	 * URL 파라미터 mode 요청들을 분기함
	 * ========================== */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		if(mode != null) {
			mode = mode.trim();
			switch(mode) {
				case "login-form": loginForm(request, response); break;
				case "login-action": loginAction(request, response); break;
				case "signup-form": signupForm(request, response); break;
				case "signup-action": signupAction(request, response); break;
				case "logout": logout(request, response); break;
				default : {
					RequestDispatcher rd = request.getRequestDispatcher(AUTH_LOGIN);
					rd.forward(request, response);
				}
			}
		}else {
			RequestDispatcher rd = request.getRequestDispatcher(AUTH_LOGIN);
			rd.forward(request, response);
		}
	}
	/* ==========================
	 * 로그인 폼 요청
	 * ========================== */
	public void loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(AUTH_LOGIN);
        rd.forward(request, response);
	}
	/* ==========================
	 * 로그인 액션 요청 처리 
	 * 이메일/패스워드 값
	 * 성공 여부와 user session 응답
	 * ========================== */
	public void loginAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pwd = request.getParameter("passwd");
				
		AuthService service = AuthService.getInstance();
		int result = service.check(email, pwd);
		System.out.println("@LoginController result: " + result); //백엔드에서 출력 코드 확인
		
		if (result == 3) {
			User user = service.getUser(email);
			HttpSession session = request.getSession();
			session.setAttribute("loginOkUser", user);
		}
		request.setAttribute("result", result);
		
		RequestDispatcher rd = request.getRequestDispatcher(AUTH_LOGIN);
		rd.forward(request, response);
	}
	/* ==========================
	 * 로그아웃 요청 처리
	 * 현재 가지고 있는 세션 제거
	 * response 로 index.do 요청
	 * ========================== */
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("loginOkUser");
		session.invalidate();
		
		response.sendRedirect(request.getContextPath() + "/index.do");
		//request.getRequestDispatcher("/index.do").forward(request, response);
	}
	/* ==========================
	 * 회원가입 폼 요청
	 * ========================== */
	public void signupForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(AUTH_SIGNUP);
        rd.forward(request, response);
	}
	/* ==========================
	 * 회원가입 액션 요청 처리
	 * ========================== */
	public void signupAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String name = trim(request.getParameter("name"));
	    String email = trim(request.getParameter("email"));
	    String passwd = trim(request.getParameter("passwd"));
	    String confirm = trim(request.getParameter("passwordConfirm"));
	    String alias = trim(request.getParameter("alias"));
	    String agree = request.getParameter("agreeTerms"); // on / null

//	    // 2) 1차 입력 검증 (컨트롤러 레벨)
//	    int result = 0;
//	    String errorField = null;
//
//	    if (isEmpty(name))           { result = SignUpCode.INVALID_NAME; errorField = "name"; }
//	    else if (isEmpty(email))     { result = SignUpCode.INVALID_EMAIL; errorField = "email"; }
//	    else if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
//	                                  result = SignUpCode.INVALID_EMAIL; errorField = "email"; }
//	    else if (isEmpty(passwd) || passwd.length() < 8) {
//	                                  result = SignUpCode.INVALID_PASSWORD; errorField = "password"; }
//	    else if (!passwd.equals(confirm)) {
//	                                  result = SignUpCode.PASSWORD_MISMATCH; errorField = "passwordConfirm"; }
//	    else if (isEmpty(alias) || alias.length() < 2 || alias.length() > 8) {
//	                                  result = SignUpCode.INVALID_ALIAS; errorField = "alias"; }
//	    else if (agree == null)      { result = SignUpCode.TERMS_REQUIRED; errorField = "agreeTerms"; }
//
//	    if (result != 0) {
//	        // 실패: 입력값 유지 + 어디가 문제인지 JSP에 알려주기
//	        keepFormValues(request, name, email, alias);
//	        request.setAttribute("signupResult", result);
//	        request.setAttribute("signupErrorField", errorField);
//	        request.getRequestDispatcher(AUTH_SIGNUP).forward(request, response);
//	        return;
//	    }

	    // 3) 서비스 호출
	    AuthService service = AuthService.getInstance();
	    int signUpResult = service.signup(name, email, passwd, alias); 
	    // 아래 2) 참고 (중복검사/해시/삽입)

//	    if (signUpResult == SignUpCode.SUCCESS) {
//	        // 성공: PRG 패턴 - 로그인 페이지로 안내(또는 자동로그인 원하면 여기서 세션 생성)
//	        // 1회성 메시지는 flash로
//	        request.getSession(true).setAttribute("flashMessage", "회원가입이 완료되었습니다. 로그인해 주세요.");
	        response.sendRedirect(request.getContextPath() + "/auth.do?mode=login-form");
//	    } else {
//	        // 실패 시 폼으로 되돌리기
//	        keepFormValues(request, name, email, alias);
//	        request.setAttribute("signupResult", signUpResult);
//	        request.setAttribute("signupErrorField", mapErrorField(signUpResult));
//	        request.getRequestDispatcher(AUTH_SIGNUP).forward(request, response);
//	    }
	}

	// 유틸들
	private static String trim(String s){ return s==null? null : s.trim(); }
	private static boolean isEmpty(String s){ return s==null || s.isEmpty(); }
	private static void keepFormValues(HttpServletRequest req, String name, String email, String alias){
	    req.setAttribute("nameValue",  name);
	    req.setAttribute("emailValue", email);
	    req.setAttribute("aliasValue", alias);
	}
//	private static String mapErrorField(int code){
//	    return switch (code){
//	        case SignUpCode.DUPLICATE_EMAIL   -> "email";
//	        case SignUpCode.DUPLICATE_ALIAS   -> "alias";
//	        case SignUpCode.DB_ERROR          -> "form";
//	        default                           -> "form";
//	    };
//	}
}

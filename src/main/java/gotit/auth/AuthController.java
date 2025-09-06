package gotit.auth;

import java.io.IOException;

import gotit.common.util.AuthUtils;
import gotit.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static gotit.core.ViewPaths.*;

public class AuthController {
	private static final AuthController instance = new AuthController(); 
	private final AuthService authService = AuthService.getInstance();
	
	private AuthController() {
		
	}
	
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
	    String confirm = trim(request.getParameter("passwdConfirm"));
	    String alias = trim(request.getParameter("alias"));
	    boolean agree = "on".equalsIgnoreCase(request.getParameter("agreeTerms")) 
                || "true".equalsIgnoreCase(request.getParameter("agreeTerms"));


	    // 1) 유효성
        String vmsg = AuthUtils.validateSignup(name, alias, email, passwd, confirm, agree);
        if (vmsg != null) {
            attachBack(request, name, alias, email);
            request.setAttribute("error", vmsg);
            request.getRequestDispatcher("/WEB-INF/views/auth/signup.jsp").forward(request, response);
            return;
        }

        // 2) 비즈니스(중복/저장)
        try {
            String emsg = authService.signup(name, email, passwd, alias);
            if (emsg != null) {
                attachBack(request, name, alias, email);
                request.setAttribute("error", emsg);
                request.getRequestDispatcher("/WEB-INF/views/auth/signup.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            attachBack(request, name, alias, email);
            request.setAttribute("error", "일시적인 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.");
            request.getRequestDispatcher("/WEB-INF/views/auth/signup.jsp").forward(request, response);
        }

        // 3) 성공 → 로그인 화면으로 리다이렉트 (PRG)
        String ctx = request.getContextPath();
        response.sendRedirect(ctx + "/auth.do?mode=login-form&signup=success");
	}
	
    private void attachBack(HttpServletRequest req, String name, String alias, String email) {
        req.setAttribute("old_name",  AuthUtils.trim(name));
        req.setAttribute("old_alias", AuthUtils.trim(alias));
        req.setAttribute("old_email", AuthUtils.trim(email));
    }

	// 유틸들
	private static String trim(String s){ return s==null? null : s.trim(); }
}

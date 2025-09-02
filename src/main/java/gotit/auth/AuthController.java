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
	private static final AuthController instance = new AuthController(); 

	private AuthController() {
		
	}
	
    public static AuthController getInstance() {
        return instance;
    }
    
	/* ==========================
	 * 파라미터 mode 요청들을 분기함
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
		RequestDispatcher rd = request.getRequestDispatcher(AUTH_SIGNUP);
        rd.forward(request, response);
	}
}

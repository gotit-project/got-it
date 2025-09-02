package gotit.core;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import gotit.auth.AuthController;


@WebServlet(urlPatterns={"*.do"})
@MultipartConfig(
		fileSizeThreshold = 1*1024*1024,	// RAM 저장 범위 : 1MB (초과 시 임시파일 사용)
		maxFileSize = 5*1024*1024, 			// 최대 허용 범위 : 5MB
		maxRequestSize = 15*1024*1024		// 모든 파일을 합쳐 15MB 초과 불가능
)
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// FrontController 내에서 연결할 Controller
	private final AuthController authController = AuthController.getInstance();
 
	@Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
		System.out.println(path);
		
		if (Objects.isNull(path)) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	        rd.forward(request, response);
		} else if (Objects.equals(path, "/index.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	        rd.forward(request, response);
	        return;
		} else if (Objects.equals(path, "/auth.do")) {
			authController.service(request, response);
			
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	        rd.forward(request, response);
		}
    }
}
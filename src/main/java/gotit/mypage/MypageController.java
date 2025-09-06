package gotit.mypage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MypageController extends HttpServlet {
    private static final MypageController INSTANCE = new MypageController();
    private final MypageService mypageService = MypageService.getInstance();
    
    private MypageController(){}

    public static MypageController getInstance(){ 
    	return INSTANCE; 
    }
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 데이터 없이 JSP 바로 보여주기
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/mypage.jsp");
        rd.forward(request, response);
    }
}

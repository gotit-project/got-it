package gotit.mypage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mypage.do")
public class MypageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 데이터 없이 JSP 바로 보여주기
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/mypage.jsp");
        rd.forward(request, response);
    }
}

package gotit.mypage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import static gotit.common.util.SqlUtils.*;

import gotit.model.Page;
import gotit.model.Post;
import gotit.model.User;


public class MypageController extends HttpServlet {
    private static final MypageController INSTANCE = new MypageController();
    private final MypageService mypageService = MypageService.getInstance();
    
    private MypageController(){}

    public static MypageController getInstance(){ 
    	return INSTANCE; 
    }
    private static final long serialVersionUID = 1L;
    
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      	String mode = request.getParameter("tap");
      	
        // 기본값 보정 (null/blank 모두 my로)
        if (mode == null || mode.isBlank()) {
            mode = "my";
        }
        switch (mode) {
            case "my":  myPage(request, response);  break;
            case "scrap":  scrap(request, response);  break;
            default:      myPage(request, response);  break;
        }
    }
    
    public void myPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	int curPage = 1;                      // 1-base
        int pageSize = 10;                    // 기본 페이지 크기
    	
    	HttpSession session = request.getSession(false);
    	User user = (session == null) ? null : (User) session.getAttribute("loginOkUser");
    	if (user == null) {
    	    response.sendRedirect(request.getContextPath() + "/auth.do?mode=login-form");
    	    return;
    	}
    	
    	String sortParam = request.getParameter("sort");
        String orderBy;
    

        // 기본값은 최신순
        if (sortParam == null || sortParam.isBlank()) {
            orderBy = USER_POST_SELECT_CREATE;
        } else {
            switch (sortParam) {
                case "new":      // 최신순
                    orderBy = USER_POST_SELECT_CREATE;
                    break;
                case "like":     // 좋아요순
                    orderBy = USER_POST_SELECT_CREATE;
                    break;
                case "comment":  // 댓글순
                    orderBy = USER_POST_SELECT_CREATE;
                    break;
                case "view":     // 조회순(추가 예시)
                    orderBy = USER_POST_SELECT_CREATE;
                    break;
                default:         // 안전장치 (예상 외 값이면 최신순으로 fallback)
                    orderBy = USER_POST_SELECT_CREATE;
                    break;
            }
        }
        
        String strCurPage = request.getParameter("page");
        if (strCurPage != null) {
        	curPage = Integer.parseInt(strCurPage);
        }
        
        long userId = user.getUserId();
    	int totalCount = mypageService.countS(userId);
    	Page page = new Page(curPage, pageSize, totalCount);
    	
    	List<Post> postList = mypageService.getUserPost(userId, orderBy, page);

       	System.out.println(postList + "마이페이지 작성게시글");

    	
        request.setAttribute("myPosts", postList);
        request.setAttribute("page", page);
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/mypage.jsp");
        rd.forward(request, response);
    }
    
    public void scrap(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	int curPage = 1;                      // 1-base
        int pageSize = 10;                    // 기본 페이지 크기
    	
    	HttpSession session = request.getSession(false);
    	User user = (session == null) ? null : (User) session.getAttribute("loginOkUser");
    	if (user == null) {
    	    response.sendRedirect(request.getContextPath() + "/auth.do?mode=login-form");
    	    return;
    	}
    	
    	String sortParam = request.getParameter("sort");
        String orderBy;
    

        if (sortParam == null || sortParam.isBlank()) {
            orderBy = USER_POST_SELECT_CREATE;
        } else {
            switch (sortParam) {
                case "new":      // 최신순
                    orderBy = USER_POST_SELECT_CREATE;
                    break;
                case "like":     // 좋아요순
                    orderBy = USER_POST_SELECT_CREATE;
                    break;
                case "comment":  // 댓글순
                    orderBy = USER_POST_SELECT_CREATE;
                    break;
                case "view":     // 조회순(추가 예시)
                    orderBy = USER_POST_SELECT_CREATE;
                    break;
                default:         // 안전장치 (예상 외 값이면 최신순으로 fallback)
                    orderBy = USER_POST_SELECT_CREATE;
                    break;
            }
        }

        String strCurPage = request.getParameter("page");
        if (strCurPage != null) {
            curPage = Integer.parseInt(strCurPage);
        }

        long userId = user.getUserId();
        int totalCount = mypageService.countScrapPosts(userId);
        Page page = new Page(curPage, pageSize, totalCount);

        List<Post> scrapList = mypageService.getScrapPost(userId, orderBy, page);

       	System.out.println(scrapList + "마이페이지 스크랩 시글");


        request.setAttribute("scrappedPosts", scrapList);
        request.setAttribute("page", page);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/mypage.jsp");
        rd.forward(request, response);
    }

}

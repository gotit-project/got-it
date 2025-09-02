package gotit.core;

public class ViewPaths {
	 // 인스턴스 생성 방지
    private ViewPaths() {}

	// 공통 베이스 경로
    private static final String BASE = "/WEB-INF/views/";

    // ===== Auth =====
    public static final String AUTH_LOGIN = BASE + "auth/login.jsp";
    public static final String AUTH_SIGNUP = BASE + "auth/signup.jsp";

    // ===== Index/Home =====
    public static final String INDEX = "/index.jsp";

    // ===== 게시판 예시 =====
    public static final String BOARD_LIST = BASE + "board/list.jsp";
    public static final String BOARD_DETAIL = BASE + "board/detail.jsp";

    // ===== 관리자 =====
    public static final String ADMIN_DASHBOARD = BASE + "admin/dashboard.jsp";

   
}

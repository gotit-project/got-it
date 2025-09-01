package gotit.board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gotit.model.Board;


@WebServlet("/boards")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");  // 게시판 이름
		
		if (type != null) {
			 type = type.trim();
			switch (type) {
				case "index" : goIndex(request, response); break; 
				case "QnA" : goQna(request, response); break;
				case "knowledge" : goKnowledge(request, response); break; 
				case "notice" : goNotice(request, response); break;
				default: response.sendRedirect("../");
			}
		}
	}
	
	private void goIndex(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String indexPage = "index.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(indexPage);
		rd.forward(request, response);
	}
	
	private void goQna(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String qnaPage = "/WEB-INF/views/board/qnaList.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(qnaPage);
		rd.forward(request, response);
	}
	
	private void goKnowledge(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String kSPage = "/WEB-INF/views/board/knowledgeList.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(kSPage);
		rd.forward(request, response);
	}
	
	
	private void goNotice(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String noticePage = "/WEB-INF/views/board/noticeList.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(noticePage);
		rd.forward(request, response);
	}
	
	private void changeCategory(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String category = request.getParameter("category");
		
		BoardService service = BoardService.getInstance();
		ArrayList<Board> list = service.list();
		
		request.setAttribute("Category", category);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/qnaList.jsp");
		rd.forward(request, response);
	}
	
}
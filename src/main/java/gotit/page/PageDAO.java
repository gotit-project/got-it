package gotit.page;

import static gotit.common.util.SqlUtils.GET_BOARD_NAME;
import static gotit.common.util.SqlUtils.GET_CATEGORY_NAME;
import static gotit.common.util.SqlUtils.GET_NICKNAME;
import static gotit.common.util.SqlUtils.POST_SELECT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import gotit.board.BoardDAO;
import gotit.model.Post;

public class PageDAO {
    private static final PageDAO INSTANCE = new PageDAO();
    private DataSource ds;

    private PageDAO() {
        try {
            InitialContext ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/jdbc/gotDB");
        } catch (NamingException e) {
            throw new RuntimeException("JNDI DataSource lookup failed: jdbc/gotDB", e);
        }
    }
    public static PageDAO getInstance() { return INSTANCE; }
    
    public List<Post> listPage(int boardId, int categoryId, int offSet, int pageSize) throws SQLException {
        List<Post> list = new ArrayList<>();
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(POST_SELECT)) {

        	pstmt.setInt(1, boardId);
        	pstmt.setInt(2, categoryId);
            pstmt.setInt(3, offSet);
            pstmt.setInt(4, pageSize);
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                long postId = rs.getLong("post_id");
                //int boardId = rs.getInt("board_id");
                long userId = rs.getLong("user_id");
                //int categoryId = rs.getInt("category_id");
                String postTag = rs.getString("post_tag");
                String title = rs.getString("title");
                String rawContent = rs.getString("raw_content");
                String htmlContent = rs.getString("html_content");
                int likeCount = rs.getInt("like_count");
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("comment_count");
                String stateType = rs.getString("state_type");
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp updatedAt = rs.getTimestamp("updated_at");
                
  			    String boardName = getBoardName(boardId);
			    String nickName = getNickName(userId);
			    String categoryName = getCategoryName(categoryId);
			    
			    
			    System.out.println(postId);
			    System.out.println(userId);
			    System.out.println(categoryId);
			    System.out.println(postTag);
			    System.out.println(title);
	
			     
			    list.add(new Post(postId, boardId, userId, categoryId, postTag, title, rawContent,
						htmlContent, likeCount, viewCount, commentCount, stateType, createdAt, updatedAt,
						boardName, nickName, categoryName));
            }
        }catch(SQLException se) {
            se.printStackTrace();
            return null;
        }
        return list;
    }
    
	/* ==========================
	 * 보드 게시글 수 반환 메소드
	 * 페이징 수를 구하기 위함 
	 * ========================== */
    public int countPosts(int boardId) throws SQLException {
    	String sql = "SELECT COUNT(*) FROM posts WHERE state_type='ACTIVE' AND board_id=?";
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, boardId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
                System.out.println("나 찍힘");
            }
        }
        return 0;
    }
	/* ==========================
	 * 유저 닉네임을 가져오기 위한 메서드
	 * 게시글/댓글이 누구꺼인지 알기 위해
	 * ========================== */
    private String getNickName(long userId) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	
	    try {
	        con = ds.getConnection();
	        pstmt = con.prepareStatement(GET_NICKNAME); 
	        pstmt.setLong(1, userId);
	        rs = pstmt.executeQuery();
	
	        if (rs.next()) {
	            String nickname = rs.getString("nickname");
	            return nickname;
	        }
	    } catch (SQLException se) {
	    	return null;
	    } finally {
	        try { if (rs != null) rs.close(); } catch(Exception e) {}
	        try { if (pstmt != null) pstmt.close(); } catch(Exception e) {}
	        try { if (con != null) con.close(); } catch(Exception e) {}
	    }
	    return null;
    }
	/* ==========================
	 * 보드 이름을 가져오기 위한 메서드
	 * 게시글에서 보드 이름을 알기 위해
	 * ========================== */
    private String getBoardName(int boardId) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	
	    try {
	        con = ds.getConnection();
	        pstmt = con.prepareStatement(GET_BOARD_NAME); 
	        pstmt.setInt(1, boardId);
	        rs = pstmt.executeQuery();
	
	        if (rs.next()) {
	            String boardName = rs.getString("board_name");
	            return boardName;
	        }
	    } catch (SQLException se) {
	    	return null;
	    } finally {
	        try { if (rs != null) rs.close(); } catch(Exception e) {}
	        try { if (pstmt != null) pstmt.close(); } catch(Exception e) {}
	        try { if (con != null) con.close(); } catch(Exception e) {}
	    }
	    return null;
    }
	/* ==========================
	 * 카테고리를 가져오기 위한 메서드
	 * 게시글에서 카테고리 이름을 알기 위해
	 * ========================== */
    private String getCategoryName(int categoryId) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	
	    try {
	        con = ds.getConnection();
	        pstmt = con.prepareStatement(GET_CATEGORY_NAME); 
	        pstmt.setInt(1, categoryId);
	        rs = pstmt.executeQuery();
	
	        if (rs.next()) {
	            String categoryName = rs.getString("category_name");
	            return categoryName;
	        }
	    } catch (SQLException se) {
	    	return null;
	    } finally {
	        try { if (rs != null) rs.close(); } catch(Exception e) {}
	        try { if (pstmt != null) pstmt.close(); } catch(Exception e) {}
	        try { if (con != null) con.close(); } catch(Exception e) {}
	    }
	    return null;
    }
}

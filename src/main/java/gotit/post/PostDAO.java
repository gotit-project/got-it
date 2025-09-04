package gotit.post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.*;
import javax.sql.DataSource;

import static gotit.common.util.SqlUtils.*;
import gotit.model.Post;

public class PostDAO {
    private DataSource ds;

    public PostDAO() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/gotDB");
            if(ds == null) throw new RuntimeException("DataSource lookup failed: jdbc/gotDB not found");
        } catch(NamingException ne) {
            throw new RuntimeException("JNDI NamingException", ne);
        }
    }

	/* ==========================
	 * 게시글 Select
	 * 해당하는 보드에 게시글 리스트 가져오기
	 * paging 방식
	 * ========================== */
    public List<Post> listPage(int boardId, int offSet, int pageSize) throws SQLException {
        List<Post> list = new ArrayList<>();
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(POST_SELECT)) {

        	pstmt.setInt(1, boardId);
            pstmt.setInt(2, offSet);
            pstmt.setInt(3, pageSize);
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                long postId = rs.getLong("post_id");
                //long boardId = rs.getLong("board_id");
                long userId = rs.getLong("user_id");
                int categoryId = rs.getInt("category_id");
                String postTag = rs.getString("post_tag");
                String title = rs.getString("title");
                String rawContent = rs.getString("raw_content");
                String htmlContent = rs.getString("html_content");
                int likeCount = rs.getInt("like_count");
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("comment_count");
                String status = rs.getString("status");
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
						htmlContent, likeCount, viewCount, commentCount, status, createdAt, updatedAt,
						boardName, nickName, categoryName));
            }
        }catch(SQLException se) {
            se.printStackTrace();
            return null;
        }
        return list;
    }

	/* ==========================
	 * 게시글 insert
	 * ========================== */
    public boolean insert(Post post) {
        try(Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(POST_INSERT)) {

            pstmt.setLong(1, post.getBoardId());
            pstmt.setLong(2, post.getUserId());
            pstmt.setLong(3, post.getCategoryId());
            pstmt.setString(4, post.getPostTag());
            pstmt.setString(5, post.getTitle());
            pstmt.setString(6, post.getRawContent());
            pstmt.setString(7, post.getHtmlContent());


            return pstmt.executeUpdate() > 0;
        } catch(SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

	/* ==========================
	 * 게시글 view
	 * ========================== */
    public Post view(long postId) {
        try(Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement("")) {

            pstmt.setLong(1, postId);
            
            try(ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()) {
                	//long postId = rs.getLong("post_id");
                    int boardId = rs.getInt("board_id");
                    long userId = rs.getLong("user_id");
                    int categoryId = rs.getInt("category_id");
                    String postTag = rs.getString("post_tag");
                    String title = rs.getString("title");
                    String rawContent = rs.getString("raw_content");
                    String htmlContent = rs.getString("html_content");
                    int likeCount = rs.getInt("like_count");
                    int viewCount = rs.getInt("view_count");
                    int commentCount = rs.getInt("comment_count");
                    String status = rs.getString("status");
                    Timestamp createdAt = rs.getTimestamp("create_at");
                    Timestamp updatedAt = rs.getTimestamp("updated_at");

    			    String boardName = getBoardName(boardId);
    			    String nickName = getNickName(userId);
    			    String categoryName = getCategoryName(categoryId);

                    return new Post(postId, boardId, userId, categoryId, postTag, title, rawContent,
    						htmlContent, likeCount, viewCount, commentCount, status, createdAt, updatedAt,
    						boardName, nickName, categoryName);
                }
            }
        } catch(SQLException se) {
            se.printStackTrace();
        }
        return null;
    }
	/* ==========================
	 * 게시글 삭제
	 * ========================== */
    public boolean delete(long postId) {
        try(Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(POST_DELETE)) {

            pstmt.setLong(1, postId);
            return pstmt.executeUpdate() > 0;
        } catch(SQLException se) {
            se.printStackTrace();
            return false;
        }
    }
	/* ==========================
	 * 게시글 업데이트
	 * ========================== */
    public boolean update(Post post) {
        try(Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(POST_UPDATE)) {

            pstmt.setLong(1, post.getBoardId());
            pstmt.setLong(2, post.getUserId());
            pstmt.setLong(3, post.getCategoryId());
            pstmt.setString(4, post.getTitle());
            pstmt.setString(5, post.getRawContent());
            pstmt.setString(6, post.getHtmlContent());
            pstmt.setString(7, post.getPostTag());

            return pstmt.executeUpdate() > 0;
        } catch(SQLException se) {
            se.printStackTrace();
            return false;
        }
    }
	/* ==========================
	 * 보드 게시글 수 반환 메소드
	 * 페이징 수를 구하기 위함 
	 * ========================== */
    public int countPosts(int boardId) throws SQLException {
    	String sql = "SELECT COUNT(*) FROM posts WHERE status='ACTIVE' AND board_id=?";
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

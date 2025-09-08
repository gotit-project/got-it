package gotit.mypage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.*;
import javax.sql.DataSource;

import static gotit.common.util.SqlUtils.*;

import gotit.model.Page;
import gotit.model.Post;
import gotit.post.PostDAO;


public class MypageDAO {
	private static final MypageDAO instance = new MypageDAO(); 
	private DataSource ds;
	private PostDAO postDao = PostDAO.getInstance();
	
    public static MypageDAO getInstance() {
        return instance;
    }
	
	public MypageDAO() {
		try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/gotDB");
            if(ds == null) throw new RuntimeException("DataSource lookup failed: jdbc/gotDB not found");
        } catch(NamingException ne) {
            throw new RuntimeException("JNDI NamingException", ne);
        }
	}
	
	  public List<Post> postList() {
	        List<Post> list = new ArrayList<>();
	        try(Connection con = ds.getConnection();

	            PreparedStatement pstmt = con.prepareStatement(POST_SELECT);

	            ResultSet rs = pstmt.executeQuery()) {
	            
	            while(rs.next()) {
	          ;
				    
	              
	            }
	        } catch(SQLException se) {
	            se.printStackTrace();
	            return null;
	        }
	        return list;
	    }

	    public List<Post> postList(long userId, String orderBy, Page page) throws SQLException {
	        List<Post> list = new ArrayList<>();
	        try (Connection con = ds.getConnection();
	             PreparedStatement pstmt = con.prepareStatement(orderBy)) {

	        	pstmt.setLong(1, userId);
	            pstmt.setInt(2, page.getOffset());
	            pstmt.setInt(3, page.getPageSize());
	            
	            ResultSet rs = pstmt.executeQuery();
	            while(rs.next()) {
	                long postId = rs.getLong("post_id");
	                int boardId = rs.getInt("board_id");
	                //long userId = rs.getLong("user_id");
	                int categoryId = rs.getInt("category_id");
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
	                
	  			    String boardName = postDao.getBoardName(boardId);
				    String nickName = postDao.getNickName(userId);
				    String categoryName = postDao.getCategoryName(categoryId);
				     
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
		 * 유저 게시글 수 반환 메소드
		 * 페이징 수를 구하기 위함 
		 * ========================== */
	    public int countPosts(long userId) throws SQLException {
	    	String sql = "SELECT COUNT(*) FROM posts WHERE state_type='ACTIVE' AND user_id=?";
	        try (Connection con = ds.getConnection();
	             PreparedStatement pstmt = con.prepareStatement(sql)) {

	            pstmt.setLong(1, userId);
	            try (ResultSet rs = pstmt.executeQuery()) {
	                if (rs.next()) return rs.getInt(1);
	                System.out.println("나 찍힘");
	            }
	        }
	        return 0;
	    }
	    
	    /* ==========================
		 * 스크랩한 게시글
		 * ========================== */
	    public List<Post> selectScrapPosts(long userId, String orderBy, Page page) {
	        List<Post> list = new ArrayList<>();
	        String sql = "SELECT p.*, u.nickname, u.img_name, u.badge_name " +
	                     "FROM posts p " +
	                     "JOIN post_scraps s ON p.post_id = s.post_id " +
	                     "JOIN users u ON p.user_id = u.user_id " +
	                     "WHERE s.user_id = ? AND p.state_type = 'ACTIVE' " +
	                     "ORDER BY " + orderBy + " DESC " +
	                     "LIMIT ?, ?";

	        try (Connection conn = ds.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	            pstmt.setLong(1, userId);
	            pstmt.setInt(2, page.getOffset());
	            pstmt.setInt(3, page.getPageSize());

	            try (ResultSet rs = pstmt.executeQuery()) {
	            	 while(rs.next()) {
	 	                long postId = rs.getLong("post_id");
	 	                int boardId = rs.getInt("board_id");
	 	                //long userId = rs.getLong("user_id");
	 	                int categoryId = rs.getInt("category_id");
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
	 	                
	 	  			    String boardName = postDao.getBoardName(boardId);
	 				    String nickName = postDao.getNickName(userId);
	 				    String categoryName = postDao.getCategoryName(categoryId);
	 				     
	 				    list.add(new Post(postId, boardId, userId, categoryId, postTag, title, rawContent,
	 							htmlContent, likeCount, viewCount, commentCount, stateType, createdAt, updatedAt,
	 							boardName, nickName, categoryName));

	 	            }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return list;
	    }


	    // 스크랩한 글 개수
	    public int countScrapPosts(long userId) {
	        String sql = "SELECT COUNT(*) " +
	                     "FROM post_scraps s " +
	                     "JOIN posts p ON s.post_id = p.post_id " +
	                     "WHERE s.user_id = ? AND p.state_type = 'ACTIVE'";

	        try (Connection conn = ds.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setLong(1, userId);
	            try (ResultSet rs = pstmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt(1);
	                }
	                System.out.println("DAO2");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return 0;
	    }


}

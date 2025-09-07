package gotit.post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.*;
import javax.sql.DataSource;

import static gotit.common.util.SqlUtils.*;

import gotit.model.Page;
import gotit.model.Post;

public class PostDAO {
	private static final PostDAO instance = new PostDAO(); 
	private DataSource ds;
	
    public static PostDAO getInstance() {
        return instance;
    }
   
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
    public List<Post> listPage(int boardId, String orderBy, Page page) throws SQLException {
        List<Post> list = new ArrayList<>();
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(orderBy)) {

        	pstmt.setInt(1, boardId);        	
            pstmt.setInt(2, page.getOffset());
            pstmt.setInt(3, page.getPageSize());
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                long postId = rs.getLong("post_id");
                //int boardId = rs.getInt("board_id");
                long userId = rs.getLong("user_id");
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
                
  			    String boardName = getBoardName(boardId);
			    String nickName = getNickName(userId);
			    String imgName = getImgName(userId);
			    int badgeId = getBadgeId(userId);
			    String badgeName = getBadgeName(badgeId);
			    String categoryName = getCategoryName(categoryId);
			    			     
			    list.add(new Post(postId, boardId, userId, categoryId, postTag, title, rawContent,
						htmlContent, likeCount, viewCount, commentCount, stateType, createdAt, updatedAt,
						boardName, nickName, imgName, badgeName, categoryName));
            }
        }catch(SQLException se) {
            se.printStackTrace();
            return null;
        }
        return list;
    }
    public List<Post> listPage(int boardId, int categoryId, String orderBy, Page page) throws SQLException {
        List<Post> list = new ArrayList<>();
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(orderBy)) {

        	pstmt.setInt(1, boardId);
        	pstmt.setInt(2, categoryId);
            pstmt.setInt(3, page.getOffset());
            pstmt.setInt(4, page.getPageSize());
            
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
			    String imgName = getImgName(userId);
			    int badgeId = getBadgeId(userId);
			    String badgeName = getBadgeName(badgeId);
			    String categoryName = getCategoryName(categoryId);
			    			     
			    list.add(new Post(postId, boardId, userId, categoryId, postTag, title, rawContent,
						htmlContent, likeCount, viewCount, commentCount, stateType, createdAt, updatedAt,
						boardName, nickName, imgName, badgeName, categoryName));
            }
        }catch(SQLException se) {
            se.printStackTrace();
            return null;
        }
        return list;
    }
    

	/* ==========================
	 * 게시글 Select
	 * 해당하는 보드에 게시글 리스트 가져오기
	 * paging 방식
	 * 검색엔진 추가
	 * ========================== */
    public List<Post> listPage(int boardId, String searchStr, String orderBy, Page page) throws SQLException {
        List<Post> list = new ArrayList<>();
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(orderBy)) {

        	pstmt.setInt(1, boardId);        	
        	pstmt.setString(2, "%" + searchStr + "%");
            pstmt.setInt(3, page.getOffset());
            pstmt.setInt(4, page.getPageSize());
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                long postId = rs.getLong("post_id");
                //int boardId = rs.getInt("board_id");
                long userId = rs.getLong("user_id");
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
                
  			    String boardName = getBoardName(boardId);
			    String nickName = getNickName(userId);
			    String imgName = getImgName(userId);
			    int badgeId = getBadgeId(userId);
			    String badgeName = getBadgeName(badgeId);
			    String categoryName = getCategoryName(categoryId);
			    			     
			    list.add(new Post(postId, boardId, userId, categoryId, postTag, title, rawContent,
						htmlContent, likeCount, viewCount, commentCount, stateType, createdAt, updatedAt,
						boardName, nickName, imgName, badgeName, categoryName));
            }
        }catch(SQLException se) {
            se.printStackTrace();
            return null;
        }
        return list;
    }
    public List<Post> listPage(int boardId, int categoryId, String searchStr, String orderBy, Page page) throws SQLException {
        List<Post> list = new ArrayList<>();
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(orderBy)) {

        	pstmt.setInt(1, boardId);
        	pstmt.setInt(2, categoryId);
        	pstmt.setString(3, "%" + searchStr + "%");
            pstmt.setInt(4, page.getOffset());
            pstmt.setInt(5, page.getPageSize());
            
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
			    String imgName = getImgName(userId);
			    int badgeId = getBadgeId(userId);
			    String badgeName = getBadgeName(badgeId);
			    String categoryName = getCategoryName(categoryId);
			    			     
			    list.add(new Post(postId, boardId, userId, categoryId, postTag, title, rawContent,
						htmlContent, likeCount, viewCount, commentCount, stateType, createdAt, updatedAt,
						boardName, nickName, imgName, badgeName, categoryName));
            }
        }catch(SQLException se) {
            se.printStackTrace();
            return null;
        }
        return list;
    }
    
	/* ==========================
	 * 게시글 Select
	 * 단일 보드 가져오기
	 * ========================== */
    public List<Post> getMainPosts(int boardId) throws SQLException {
        List<Post> list = new ArrayList<>();
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(POST_SELECT)) {

        	pstmt.setInt(1, boardId);
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                long postId = rs.getLong("post_id");
                //int boardId = rs.getInt("board_id");
                long userId = rs.getLong("user_id");
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
                
  			    String boardName = getBoardName(boardId);
			    String nickName = getNickName(userId);
			    String imgName = getImgName(userId);
			    int badgeId = getBadgeId(userId);
			    String badgeName = getBadgeName(badgeId);
			    String categoryName = getCategoryName(categoryId);
			    			     
			    list.add(new Post(postId, boardId, userId, categoryId, postTag, title, rawContent,
						htmlContent, likeCount, viewCount, commentCount, stateType, createdAt, updatedAt,
						boardName, nickName, imgName, badgeName, categoryName));
			    
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
            PreparedStatement pstmt = con.prepareStatement(POST_INSERT,  Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setLong(1, post.getBoardId());
            pstmt.setLong(2, post.getUserId());
            pstmt.setLong(3, post.getCategoryId());
            pstmt.setString(4, post.getPostTag());
            pstmt.setString(5, post.getTitle());
            pstmt.setString(6, post.getRawContent());
            pstmt.setString(7, post.getHtmlContent());
            
            
            //DB에서 자동으로 증가된 postId 가져오기
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        post.setPostId(rs.getLong(1)); // DB에서 생성된 postId 세팅
                    }
                }
                return true;
            } else {
                return false;
            }
            
        } catch(SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

	/* ==========================
	 * 게시글 view + 조회수 증가 
	 * ========================== */
    public Post view(long postId) {
        try(Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(POST_VIEW)) {

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
                    String stateType = rs.getString("state_type");
                    Timestamp createdAt = rs.getTimestamp("created_at");
                    Timestamp updatedAt = rs.getTimestamp("updated_at");

    			    String boardName = getBoardName(boardId);
    			    String nickName = getNickName(userId);
    			    String imgName = getImgName(userId);
    			    int badgeId = getBadgeId(userId);
    			    String badgeName = getBadgeName(badgeId);
    			    String categoryName = getCategoryName(categoryId);
    		    	int updatedViewCount = addViewCount(postId);

                    return new Post(postId, boardId, userId, categoryId, postTag, title, rawContent,
    						htmlContent, likeCount, updatedViewCount, commentCount, stateType, createdAt, updatedAt,
    						boardName, nickName, imgName, badgeName, categoryName);
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
            pstmt.setInt(3, post.getCategoryId());
            pstmt.setString(4, post.getPostTag());
            pstmt.setString(5, post.getTitle());
            pstmt.setString(6, post.getRawContent());
            pstmt.setString(7, post.getHtmlContent());
            pstmt.setLong(8, post.getPostId());

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
    public int countCatPosts(int boardId, int categoryId) throws SQLException {
    	String sql = "SELECT COUNT(*) FROM posts WHERE state_type='ACTIVE' AND board_id=? AND category_id=?";
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, boardId);
            pstmt.setInt(2, categoryId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
                System.out.println("나 찍힘");
            }
        }
        return 0;
    }
	/* ==========================
	 * 보드 게시글 수 반환 메소드
	 * 페이징 수를 구하기 위함 
	 * 검색 엔진 추가
	 * ========================== */
    public int countPosts(int boardId, String searchStr) throws SQLException {
    	String sql = "SELECT COUNT(*) FROM posts WHERE state_type='ACTIVE' AND board_id=? AND title LIKE ?";
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, boardId);
            pstmt.setString(2, searchStr);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
                System.out.println("나 찍힘");
            }
        }
        return 0;
    }
    public int countCatPosts(int boardId, int categoryId,  String searchStr) throws SQLException {
    	String sql = "SELECT COUNT(*) FROM posts WHERE state_type='ACTIVE' AND board_id=? AND category_id=? AND title LIKE ?";
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, boardId);
            pstmt.setInt(2, categoryId);
            pstmt.setString(3, searchStr);
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
    public String getNickName(long userId) {
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
    public String getImgName(long userId) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	try {
    		con = ds.getConnection();
    		pstmt = con.prepareStatement("select img_name from users where user_id = ?"); 
    		pstmt.setLong(1, userId);
    		rs = pstmt.executeQuery();
    		
    		if (rs.next()) {
    			String imgName = rs.getString("img_name");
    			return imgName;
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
    public int getBadgeId(long userId) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	try {
    		con = ds.getConnection();
    		pstmt = con.prepareStatement("select badge_id from users where user_id = ?"); 
    		pstmt.setLong(1, userId);
    		rs = pstmt.executeQuery();
    		
    		if (rs.next()) {
    			int badgeId = rs.getInt("badge_id");
    			return badgeId;
    		}
    	} catch (SQLException se) {
    		return -1;
    	} finally {
    		try { if (rs != null) rs.close(); } catch(Exception e) {}
    		try { if (pstmt != null) pstmt.close(); } catch(Exception e) {}
    		try { if (con != null) con.close(); } catch(Exception e) {}
    	}
    	return -1;
    }
    
    public String getBadgeName(int badgeId) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	try {
    		con = ds.getConnection();
    		pstmt = con.prepareStatement("select badge_name from user_badges where badge_id = ?"); 
    		pstmt.setLong(1, badgeId);
    		rs = pstmt.executeQuery();
    		
    		if (rs.next()) {
    			String badgeName = rs.getString("badge_name");
    			return badgeName;
    		}
    	} catch (SQLException se) {
    		return null;
    	} finally {
    		try { if (rs != null) rs.close(); } catch(Exception e) {}
    		try { if (pstmt != null) pstmt.close(); } catch(Exception e) {}
    		try { if (con != null) con.close(); } catch(Exception e) {}
    	}
    	System.out.print("이름이 없음");
    	return null;
    }
    
	/* ==========================
	 * 보드 이름을 가져오기 위한 메서드
	 * 게시글에서 보드 이름을 알기 위해
	 * ========================== */
    public String getBoardName(int boardId) {
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
    public String getCategoryName(int categoryId) {
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
    
    /* ==========================
	 * 메인에서 보여주는 게시판+게시글 
	 * ========================== */
    public List<Post> getPostsByBoard(long boardId) {
    	List<Post> posts = new ArrayList<>();
	
	    try (Connection con = ds.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(BOARD_POST_SELECT)) {
		        pstmt.setLong(1, boardId);
		        ResultSet rs = pstmt.executeQuery();
		
		        while (rs.next()) {

		            long postId = rs.getLong("post_id");
		            long userId = rs.getLong("user_id");
		            String title = rs.getString("title");
		            Timestamp createdAt = rs.getTimestamp("created_at");
		            int likeCount = rs.getInt("like_count");
		            int viewCount = rs.getInt("view_count");
		            int commentCount = rs.getInt("comment_count");
		            String nickName = getNickName(userId);		            
				    String imgName = getImgName(userId);
				    int badgeId = getBadgeId(userId);
	 			    String badgeName = getBadgeName(badgeId);
		            
		            Post post = new Post(postId, boardId, userId, nickName, title, createdAt, likeCount, viewCount, commentCount, imgName, badgeName);
		            
		            posts.add(post); 
		            
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		
		    return posts;
		}
    
    /* ==========================
	 * 조회수 카운트 
	 * ========================== */
	public int addViewCount(long postId) {
		int totalViewCount = -1;
	    try (Connection con = ds.getConnection()) {
	        // 1. 조회수 증가
	        try (PreparedStatement pstmt = con.prepareStatement(POST_VIEW_COUNT_INCREASE)) {
	            pstmt.setLong(1, postId);
	            pstmt.executeUpdate();
	        }
	        // 2. 최신 조회수 가져오기
	        try (PreparedStatement pstmt2 = con.prepareStatement(POST_VIEW_COUNT_SELECT)) {
	            pstmt2.setLong(1, postId);
	            try (ResultSet rs = pstmt2.executeQuery()) {
	                if (rs.next()) {
	                    totalViewCount = rs.getInt("view_count");
	                }
	            }
	        }
	    } catch (SQLException se) {
	        se.printStackTrace();
	    }
	    return totalViewCount;
	}
}


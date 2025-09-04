package gotit.post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.*;
import javax.sql.DataSource;

import gotit.common.util.SqlUtils;
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

    public List<Post> postList() {
        List<Post> list = new ArrayList<>();
        try(Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SqlUtils.POST_LIST);
            ResultSet rs = pstmt.executeQuery()) {
            
            while(rs.next()) {
                long postId = rs.getLong("post_id");
                long boardId = rs.getLong("board_id");
                long userId = rs.getLong("user_id");
                long categorieId = rs.getLong("categorie_id");
                String postTag = rs.getString("post_tag");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String status = rs.getString("status");
                int likeCount = rs.getInt("like_count");
                int viewCount = rs.getInt("view_count");
          
			    String nickName = getNickName(userId);
			     
			    //테이블 특정열에서 데이터 갖고오기 
			    list.add(new Post(
		    	    postId,
		    	    boardId,
		    	    nickName,
		    	    rs.getLong("categorie_id"), 
		    	    rs.getString("post_tag"),  
		    	    rs.getString("title"),     
		    	    rs.getString("content"),   
		    	    rs.getInt("like_count"),
		    	    rs.getInt("view_count"),
		    	    rs.getString("status"),
		    	    rs.getTimestamp("created_at"),
		    	    rs.getTimestamp("updated_at")
		    	  
		    	));
              
            }
        } catch(SQLException se) {
            se.printStackTrace();
            return null;
        }
        return list;
    }


	
    
    public boolean insert(Post post) {
        try(Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SqlUtils.POST_INSERT)) {

            pstmt.setLong(1, post.getBoardId());
            pstmt.setLong(2, post.getUserId());
            pstmt.setLong(3, post.getCategorieId());
            pstmt.setString(4, post.getTitle());
            pstmt.setString(5, post.getContent());
            pstmt.setString(6, post.getPostTag());


            return pstmt.executeUpdate() > 0;
        } catch(SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

    public Post select(long postId) {
        try(Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SqlUtils.POST_SELECT)) {

            pstmt.setLong(1, postId);
            
            try(ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()) {
                    long boardId = rs.getLong("board_id");
                    long userId = rs.getLong("user_id");
                    long categorieId = rs.getLong("categorie_id");
                    String postTag = rs.getString("post_tag");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    int likeCount = rs.getInt("like_count");
                    int viewCount = rs.getInt("view_count");
                    String status = rs.getString("status");



    			    String nickName = getNickName(userId);
    			    
    			    // 조회수 증가
                    getViewCount(postId);
                    // 증가된 조회수 다시 읽어오기
                    viewCount++;

                    		
                 // select()
                    return new Post(
                        rs.getLong("post_id"),
                        rs.getLong("board_id"),
                        rs.getLong("user_id"),
                        rs.getLong("categorie_id"),
                        rs.getString("post_tag"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("like_count"),
                        rs.getInt("view_count"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                        
                    );

                }
            }
        } catch(SQLException se) {
            se.printStackTrace();
        }
        return null;
    }


    public boolean delete(long postId) {
        try(Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SqlUtils.POST_DELETE)) {

            pstmt.setLong(1, postId);
            return pstmt.executeUpdate() > 0;
        } catch(SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

    public boolean update(Post post) {
        try(Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SqlUtils.POST_UPDATE)) {

            pstmt.setLong(1, post.getBoardId());
            pstmt.setLong(2, post.getUserId());
            pstmt.setLong(3, post.getCategorieId());
            pstmt.setString(4, post.getTitle());
            pstmt.setString(5, post.getContent());
            pstmt.setString(6, post.getPostTag());

            return pstmt.executeUpdate() > 0;
        } catch(SQLException se) {
            se.printStackTrace();
            return false;
        }
    }
    
    //닉네임 가져오기
    private String getNickName(long userId) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	
	    try {
	        con = ds.getConnection();
	        pstmt = con.prepareStatement("select nickname from users where user_id = ?"); 
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
    
    //조회수 가져오기 
    public boolean getViewCount(long postId) {
        try(Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SqlUtils.POST_VIEW_COUNT)) {

            pstmt.setLong(1, postId);
            return pstmt.executeUpdate() > 0;
        } catch(SQLException se) {
            se.printStackTrace();
            return false;
        }
    }
    
    //댓글수 가져오기
    public boolean getCommentCount(long postId) {
        try(Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select co")) {

            pstmt.setLong(1, postId);
            return pstmt.executeUpdate() > 0;
        } catch(SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

    // 페이징
    public List<Post> listPage(int boardId, int start, int pageSize) throws SQLException {
        List<Post> list = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE status='ACTIVE'AND board_id=? ORDER BY post_id DESC LIMIT ?, OFFSET ?";
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

        	pstmt.setInt(1, boardId);
            pstmt.setInt(2, start);
            pstmt.setInt(3, pageSize);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Post post = new Post(
                    rs.getLong("post_id"),
                    rs.getLong("board_id"),
                    rs.getLong("user_id"),
                    rs.getInt("categorie_id"),
                    rs.getString("post_tag"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getInt("like_count"),
                    rs.getInt("view_count"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
                );
                list.add(post);
            }
        }
        return list;
    }

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
}

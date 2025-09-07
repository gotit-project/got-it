package gotit.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import static gotit.common.util.SqlUtils.*;
import gotit.model.Comment;

public class CommentDAO {
	 private DataSource ds;
	 public CommentDAO() {
	
	    try {
	        Context initContext = new InitialContext();
	        Context envContext = (Context)initContext.lookup("java:/comp/env");
	        ds = (DataSource)envContext.lookup("jdbc/gotDB");
	        if(ds == null) {
	            throw new RuntimeException("DataSource lookup failed: jdbc/gotDB not found");
	        }
	    } catch(NamingException ne){
	        throw new RuntimeException("JNDI NamingException", ne);
	    } 
	 }
  
	/* ==========================
	 * 게시글에 맞는 댓글 리스트 조회
	 * ========================== */
	public List<Comment> selectList(long postId) {
		 	List<Comment> commentList = new ArrayList<>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		try {    
			 con = ds.getConnection();
			 pstmt = con.prepareStatement(COMMENT_SELECT);
			 pstmt.setLong(1, postId);
			 rs = pstmt.executeQuery();
			 while(rs.next()){
			     long commentId = rs.getLong("comment_id");
			     long userId = rs.getLong("user_id");
			     int badgeId = getBadgeId(userId);
			    String badgeName = getBadgeName(badgeId);
			     String content = rs.getString("content");
			     boolean isAnswer = rs.getBoolean("is_answer");
			     boolean accepted = rs.getBoolean("accepted");
			     Timestamp createdAt = rs.getTimestamp("created_at");
			     Timestamp updatedAt = rs.getTimestamp("updated_at");
			     
			     String nickname = getNickname(userId);
			
			     commentList.add(new Comment(commentId, postId, userId, badgeName, nickname, content, isAnswer, accepted, createdAt, updatedAt));        
			 } 
		}catch(SQLException se){
			return null;
		}finally {
			 try { if (rs != null) rs.close(); } catch(Exception e) {}
			 try { if (pstmt != null) pstmt.close(); } catch(Exception e) {}
			 try { if (con != null) con.close(); } catch(Exception e) {}
		}
		 return commentList;
	} 
	
	/* ==========================
	 * userId로 사용자 닉네임 조회 
	 * ========================== */
	private String getNickname(long userId) {
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
	 * 댓글 등록 
	 * ========================== */
	public boolean insert(Comment commentDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(COMMENT_INSERT);
	        pstmt.setLong(1, commentDto.getPostId());
	        pstmt.setLong(2, commentDto.getUserId());
	        pstmt.setString(3, commentDto.getContent());
	        pstmt.setBoolean(4, commentDto.isIsAnswer());
	        pstmt.setBoolean(5, commentDto.isAccepted());
	        
	        int rowsAffected = pstmt.executeUpdate();
	        return rowsAffected > 0;

		}catch(SQLException se) {
			se.printStackTrace();
			return false;
		}finally {
			try { 
	        	if (pstmt != null) pstmt.close(); 
        	} catch(Exception e) {}
	        try { 
	        	if (con != null) con.close(); 
        	} catch(Exception e) {}
		}
	}
	
	/* ==========================
	 * 댓글 수정 
	 * ========================== */
	public boolean update(long commentId, String content) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    try {
	        con = ds.getConnection();
	        pstmt = con.prepareStatement(COMMENT_UPDATE);
	        pstmt.setString(1, content);
	        pstmt.setLong(2, commentId);
	
	        int rowsAffected = pstmt.executeUpdate();
	        return rowsAffected > 0;
	
	    } catch(SQLException se) {
	        se.printStackTrace();
	        return false;
	    } finally {
	        try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
	        try { if(con != null) con.close(); } catch(Exception e) {}
	    }
	}

	/* ==========================
	 * 특정 댓글 하나 조회 (SELECT_ONE)
	 * ========================== */
	public Comment select(long commentId) {
	    Comment comment = null;
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	
	    try {
	        con = ds.getConnection();
	        pstmt = con.prepareStatement(COMMENT_SELECT_ONE); 
	        pstmt.setLong(1, commentId);
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	            long postId = rs.getLong("post_id");
	            long userId = rs.getLong("user_id");
	            int badgeId = getBadgeId(userId);
			    String badgeName = getBadgeName(badgeId);
	            String content = rs.getString("content");
	            boolean isAnswer = rs.getBoolean("is_answer");
	            boolean accepted = rs.getBoolean("accepted");	     
	            Timestamp createdAt = rs.getTimestamp("created_at");
	            Timestamp updatedAt = rs.getTimestamp("updated_at");

	            String nickname = getNickname(userId);

	            comment = new Comment(
	                commentId, postId, userId, badgeName, content, isAnswer, accepted, createdAt, updatedAt
	            );
	            comment.setNickname(nickname); 
	        }

	    } catch (SQLException se) {
	        se.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch(Exception e) {}
	        try { if (pstmt != null) pstmt.close(); } catch(Exception e) {}
	        try { if (con != null) con.close(); } catch(Exception e) {}
	    }
	    return comment;
	}
	
	/* ==========================
	 * 댓글 삭제 
	 * ========================== */
	public boolean delete(long commentId) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try{    
	        con = ds.getConnection();
	        pstmt = con.prepareStatement(COMMENT_DELETE);
	        pstmt.setLong(1, commentId);
	        int i = pstmt.executeUpdate();
	        if(i > 0) return true;
	        else return false; 
	    }catch(SQLException se){
	        return false;
	    }finally {
	        try { if (pstmt != null) pstmt.close(); } catch(Exception e) {}
	        try { if (con != null) con.close(); } catch(Exception e) {}
	    }
	    
	}
	
	/* ==========================
	 * 댓글 채택  
	 * ========================== */
	public boolean accept(long commentId, long postUserId) {

	    try (Connection con = ds.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(COMMENT_ACCEPTED)) {

	        pstmt.setLong(1, commentId);
	        pstmt.setLong(2, postUserId); 
	        int rows = pstmt.executeUpdate();
	        return rows > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	/* ==========================
	 * 뱃지 아이디 가져오기 
	 * ========================== */
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

	/* ==========================
	 * 뱃지 이름 가져오기
	 * ========================== */
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
    
}
package gotit.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import gotit.common.util.SqlUtils;
import gotit.model.Comment;
import gotit.model.Post;

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
  
	// ======================================
	// 게시글에 맞는 댓글선택해서 보여주기
	// ======================================   	   
	public List<Comment> selectList(long postId) {
	
		 	List<Comment> commentList = new ArrayList<>();
		 
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		try {    
			 con = ds.getConnection();
			 pstmt = con.prepareStatement(SqlUtils.COMMENT_SELECT);
			 pstmt.setLong(1, postId);
			 rs = pstmt.executeQuery();
			 while(rs.next()){
			     long commentId = rs.getLong("comment_id");
			     long userId = rs.getLong("user_id");
			     String content = rs.getString("content");
			     boolean isAnswer = rs.getBoolean("is_answer");
			     boolean accepted = rs.getBoolean("accepted");
			     boolean deleted = rs.getBoolean("deleted");
			     java.sql.Date createdAt = rs.getDate("created_at");
			     java.sql.Date updatedAt = rs.getDate("updated_at");
			
			     commentList.add(new Comment(commentId, postId, userId, content, isAnswer, accepted, deleted, createdAt, updatedAt));        
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
    // ======================================
    // 게시글에 맞는 댓글선택해서 보여주기 
    // ======================================
	
	public boolean insert(Comment commentDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SqlUtils.COMMENT_INSERT);
			 
	        pstmt.setLong(1, commentDto.getPostId());
	        pstmt.setLong(2, commentDto.getUserId());
	        pstmt.setString(3, commentDto.getContent());
	        pstmt.setBoolean(4, commentDto.isIsAnswer());
	        pstmt.setBoolean(5, commentDto.isAccepted());
	        pstmt.setBoolean(6, commentDto.isDeleted());
	        
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
	
	public boolean update(long commentId, String content) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	
	    try {
	        con = ds.getConnection();
	        pstmt = con.prepareStatement(SqlUtils.COMMENT_UPDATE);
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

	
	public Comment select(long commentId) {
	    Comment comment = null;
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	
	    try {
	        con = ds.getConnection();
	        pstmt = con.prepareStatement(SqlUtils.COMMENT_SELECT_ONE); 
	        pstmt.setLong(1, commentId);
	        rs = pstmt.executeQuery();
	
	        if (rs.next()) {
	            long postId = rs.getLong("post_id");
	            long userId = rs.getLong("user_id");
	            String content = rs.getString("content");
	            boolean isAnswer = rs.getBoolean("is_answer");
	            boolean accepted = rs.getBoolean("accepted");
	            boolean deleted = rs.getBoolean("deleted");		     
	            java.sql.Date createdAt = rs.getDate("created_at");
	            java.sql.Date updatedAt = rs.getDate("updated_at");
	
	            comment = new Comment(
	                commentId, postId, userId, content, isAnswer, accepted, deleted, createdAt, updatedAt
	            );
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
	

	public boolean delete(long commentId) {
	    Comment comment = null;
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try{    
	        con = ds.getConnection();
	        pstmt = con.prepareStatement(SqlUtils.COMMENT_DELETE);
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

}
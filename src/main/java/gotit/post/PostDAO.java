package gotit.post;

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
import gotit.model.Post;

public class PostDAO {
	
	 private DataSource ds;
	 
	 public PostDAO() {
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

	    public List<Post> postList() {
	        List<Post> list = new ArrayList<Post>();
	        
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        try{
	            con = ds.getConnection();
	            pstmt = con.prepareStatement(SqlUtils.POST_LIST);
	            rs = pstmt.executeQuery();
	            while(rs.next()){
	                long postId = rs.getLong("post_id");
	                long boardId = rs.getLong("board_id");
	                long userId = rs.getLong("user_id");
	                String title = rs.getString("title");
	                String content = rs.getString("content");
	                boolean deleted = rs.getBoolean("deleted");
	                java.sql.Date createdAt = rs.getDate("created_at");
	                java.sql.Date updatedAt = rs.getDate("updated_at");
	                String tag = rs.getString("tag");
	                long viewCounts = rs.getLong("view_counts");

	                list.add(new Post(postId, boardId, userId, title, content, deleted, createdAt, updatedAt, tag, viewCounts));
	            }
	            return list;
	        }catch(SQLException se){
	            return null;
	        }finally {
	            try { if (rs != null) rs.close(); } catch(Exception e) {}
	            try { if (pstmt != null) pstmt.close(); } catch(Exception e) {}
	            try { if (con != null) con.close(); } catch(Exception e) {}
	        }
	    }
	    
	    public boolean insert(Post postDto) { 
	        Connection con = null;
	        PreparedStatement pstmt = null;
		
		    try{    
		        con = ds.getConnection();
		        pstmt = con.prepareStatement(SqlUtils.POST_INSERT);
		        
		        pstmt.setLong(1, postDto.getBoardId());
		        pstmt.setLong(2, postDto.getUserId());
		        pstmt.setString(3, postDto.getTitle());
		        pstmt.setString(4, postDto.getContent());
		        pstmt.setBoolean(5, postDto.getDeleted());
		        pstmt.setString(6, postDto.getTag());
		        
		        int rowsAffected = pstmt.executeUpdate();
		        return rowsAffected > 0; // 쿼리가 하나 이상의 행에 영향을 미쳤다면 true
		    }catch(SQLException se){
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
				   
		public Post select(long postId) {
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
			try {    
			 con = ds.getConnection();
				 pstmt = con.prepareStatement(SqlUtils.POST_SELECT);
				 pstmt.setLong(1, postId);
				 rs = pstmt.executeQuery();
				 if(rs.next()){
				     long boardId = rs.getLong("board_id");
				     long userId = rs.getLong("user_id");
				     String title = rs.getString("title");
				     String content = rs.getString("content");
				     boolean deleted = rs.getBoolean("deleted");
				     java.sql.Date createdAt = rs.getDate("created_at");
				     java.sql.Date updatedAt = rs.getDate("updated_at");
				     String tag = rs.getString("tag");
				     long viewCounts = rs.getLong("view_counts");
				
				     return new Post(postId, boardId, userId, title, content, deleted, createdAt, updatedAt, tag, viewCounts);
				        
				 } else {
				     return null; 
				 }
			}catch(SQLException se){
				return null;
			}finally {
				 try { if (rs != null) rs.close(); } catch(Exception e) {}
				 try { if (pstmt != null) pstmt.close(); } catch(Exception e) {}
				 try { if (con != null) con.close(); } catch(Exception e) {}
			}
		}

		public boolean delete(long postId) {
		    Connection con = null;
		    PreparedStatement pstmt = null;
		    
		    try{    
		        con = ds.getConnection();
		        pstmt = con.prepareStatement(SqlUtils.POST_DELETE);
		        pstmt.setLong(1, postId);
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
		
		public boolean update(Post postDto) {
			Connection con = null;
		    PreparedStatement pstmt = null;
		    try {    
		    	   con = ds.getConnection();
		           pstmt = con.prepareStatement(SqlUtils.POST_UPDATE);
		           pstmt.setLong(1, postDto.getBoardId());
			       pstmt.setLong(2, postDto.getUserId());
			       pstmt.setString(3, postDto.getTitle());
			       pstmt.setString(4, postDto.getContent());
			       pstmt.setBoolean(5, postDto.getDeleted());
			       pstmt.setString(6, postDto.getTag());
		           
		           int i = pstmt.executeUpdate();
		           return i > 0;
		    }catch(SQLException se){
		    	  return false;
		    }finally {
		        try { if (pstmt != null) pstmt.close(); } catch(Exception e) {}
		        try { if (con != null) con.close(); } catch(Exception e) {}
		    }
		}
		
		// 조회 수 
		public boolean addViewCount(long num) {
			Connection con = null;
		    PreparedStatement pstmt = null;
		    int rowCount = 0;
		    try {
		    	 con = ds.getConnection();
		         pstmt = con.prepareStatement(SqlUtils.POST_VIEW_COUNT);
		         //?에 바인딩
		         pstmt.setLong(1, num);
		         //INSERT or UPDATE or DELETE문을 수행하고 수정,삭제,추가된 row의 개수 리턴
		         rowCount = pstmt.executeUpdate();
		     } catch (SQLException se) {
		         se.printStackTrace();
		     } finally {
		         try {
		             if (pstmt != null)
		                 pstmt.close();
		             if (con != null)
		                 con.close();
		         } catch (SQLException se) {
		        }
		     }

		     if (rowCount > 0) {
		         return true;
		     } else {
		         return false;
		     }
		}
		
}
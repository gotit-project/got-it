package gotit.post;

import static gotit.post.PostSQL.*;

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

public class PostDAO {
	
	//톰캣에 미리 설정된 DB연결 풀을 가져와서 쓰는 객
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

	    public List<Post> list() {
		   /*
			* List는 인터페이스
			* 인터페이스는 **실체(객체)**를 만들 수 없음.
			* ArrayList는 List를 구현한 클래스
			* 실제 데이터를 담을 수 있음
			* 따라서 인터페이스 타입 변수에 구현체 객체를 담는 패턴이 일반
    	 	*/
	        List<Post> list = new ArrayList<Post>();
	        
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        try{
	            con = ds.getConnection();
	            pstmt = con.prepareStatement(LIST);
	            rs = pstmt.executeQuery();
	            while(rs.next()){
	                long post_id = rs.getLong("post_id");
	                long board_id = rs.getLong("board_id");
	                long user_id = rs.getLong("user_id");
	                String title = rs.getString("title");
	                String content = rs.getString("content");
	                boolean deleted = rs.getBoolean("deleted");
	                java.sql.Date created_at = rs.getDate("created_at");
	                java.sql.Date updated_at = rs.getDate("updated_at");
	                String tag = rs.getString("tag");

	                list.add(new Post(post_id, board_id, user_id, title, content, deleted, created_at, updated_at, tag));
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
	    
	    public boolean insert(Post dto) { //msg출력하려면 boolean이 나음. 파라미터는 SQL ? 에 들어갈 데이터라고 생각하면 쉬움
	        Connection con = null;
	        PreparedStatement pstmt = null;
		
		    try{    
		        con = ds.getConnection();
		        pstmt = con.prepareStatement(INSERT);
		        
		        pstmt.setLong(1, dto.getBoard_id());
		        pstmt.setLong(2, dto.getUser_id());
		        pstmt.setString(3, dto.getTitle());
		        pstmt.setString(4, dto.getContent());
		        pstmt.setBoolean(5, dto.getDeleted());
		        pstmt.setString(6, dto.getTag());
		        
		        int rowsAffected = pstmt.executeUpdate();
		        return rowsAffected > 0; // 쿼리가 하나 이상의 행에 영향을 미쳤다면 true
		    }catch(SQLException se){
		    	se.printStackTrace();
		        return false;
		    }finally {
		        try { if (pstmt != null) pstmt.close(); } catch(Exception e) {}
		        try { if (con != null) con.close(); } catch(Exception e) {}
		    }

		}
				   
		public Post select(long post_id) {
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
			try {    
			 con = ds.getConnection();
				 pstmt = con.prepareStatement(SELECT);
				 pstmt.setLong(1, post_id);
				 rs = pstmt.executeQuery();
				 if(rs.next()){
				     long board_id = rs.getLong("board_id");
				     long user_id = rs.getLong("user_id");
				     String title = rs.getString("title");
				     String content = rs.getString("content");
				     boolean deleted = rs.getBoolean("deleted");
				     java.sql.Date created_at = rs.getDate("created_at");
				     java.sql.Date updated_at = rs.getDate("updated_at");
				     String tag = rs.getString("tag");
				
				     return new Post(post_id, board_id, user_id, title, content, deleted, created_at, updated_at, tag);
				        
				 } else {
				     return null; // 글 없음
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
		        pstmt = con.prepareStatement(DELETE);
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
		
		
		public boolean update(Post dto) {
			Connection con = null;
		    PreparedStatement pstmt = null;
		    try {    
		    	   con = ds.getConnection();
		           pstmt = con.prepareStatement(UPDATE);
		           
		           pstmt.setLong(1, dto.getBoard_id());
			       pstmt.setLong(2, dto.getUser_id());
			       pstmt.setString(3, dto.getTitle());
			       pstmt.setString(4, dto.getContent());
			       pstmt.setBoolean(5, dto.getDeleted());
			       pstmt.setString(6, dto.getTag());
		           
		           int i = pstmt.executeUpdate();
		           return i > 0;
		    }catch(SQLException se){
		    	  return false;
		    }finally {
		        try { if (pstmt != null) pstmt.close(); } catch(Exception e) {}
		        try { if (con != null) con.close(); } catch(Exception e) {}
		    }

		}
}
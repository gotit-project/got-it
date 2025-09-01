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
	        Statement stmt = null;
	        ResultSet rs = null;
	        try{
	            con = ds.getConnection();
	            stmt = con.createStatement();
	            rs = stmt.executeQuery(LIST);
	            while(rs.next()){
	                long post_id = rs.getInt(1);
	                String title = rs.getString("title");
	                String content = rs.getString("content");
	                String writer = rs.getString("writer");
	                long views = rs.getInt("views");
	                long likes = rs.getInt("likes");
	                long bookmarks = rs.getInt("bookmarks");
	                java.sql.Date created_at = rs.getDate("created_at");

	                list.add(new Post(post_id, title, content, writer, views, likes, bookmarks, created_at));
	            }
	            return list;
	        }catch(SQLException se){
	            return null;
	        }finally {
	            try { if (rs != null) rs.close(); } catch(Exception e) {}
	            try { if (stmt != null) stmt.close(); } catch(Exception e) {}
	            try { if (con != null) con.close(); } catch(Exception e) {}
	        }

	    }
//	    public boolean insert(PostDTO dto) { //msg출력하려면 boolean이 나음. 파라미터는 SQL ? 에 들어갈 데이터라고 생각하면 쉬움
//	        Connection con = null;
//	        PreparedStatement pstmt = null;
//		
//		    try{    
//		        con = ds.getConnection();
//		        pstmt = con.prepareStatement(INSERT);
//		        pstmt.setString(1, dto.getWriter());
//		        pstmt.setString(2, dto.getEmail());
//		        pstmt.setString(3, dto.getSubject());
//		        pstmt.setString(4, dto.getContent());
//		        pstmt.setString(5, dto.getFname());
//		        int i = pstmt.executeUpdate();
//		        if(i > 0) return true;
//		        else return false; 
//		    }catch(SQLException se){
//		        return false;
//		    }finally {
//		        try { if (pstmt != null) pstmt.close(); } catch(Exception e) {}
//		        try { if (con != null) con.close(); } catch(Exception e) {}
//		    }
//
//		}
//		public boolean delete(long seq) {
//		    Connection con = null;
//		    PreparedStatement pstmt = null;
//		    
//		    try{    
//		        con = ds.getConnection();
//		        pstmt = con.prepareStatement(DELETE);
//		        pstmt.setLong(1, seq);
//		        int i = pstmt.executeUpdate();
//		        if(i > 0) return true;
//		        else return false; 
//		    }catch(SQLException se){
//		        return false;
//		    }finally {
//		        try { if (pstmt != null) pstmt.close(); } catch(Exception e) {}
//		        try { if (con != null) con.close(); } catch(Exception e) {}
//		    }
//
//		}
//							   
//		public PostDTO select(long seq) {
//			Connection con = null;
//		    PreparedStatement pstmt = null;
//		    ResultSet rs = null;
//		    try {    
//		        con = ds.getConnection();
//		        pstmt = con.prepareStatement(SELECT);
//		        pstmt.setLong(1, seq);
//		        rs = pstmt.executeQuery();
//		        if(rs.next()){
//		        	String writer = rs.getString(2);
//	                String email = rs.getString(3);
//	                String subject = rs.getString(4);
//	                String content = rs.getString(5);
//	                String fname = rs.getString(6);
//	                java.sql.Date rdate = rs.getDate(8);
//		        	return new PostDTO(seq, writer, email, subject, content, fname, rdate); 
//		        } else {
//		            return null; // 글 없음
//		        }
//	              
//		    }catch(SQLException se){
//		        return null;
//		    }finally {
//		        try { if (rs != null) rs.close(); } catch(Exception e) {}
//		        try { if (pstmt != null) pstmt.close(); } catch(Exception e) {}
//		        try { if (con != null) con.close(); } catch(Exception e) {}
//		    }
//
//		}
//		
//		public boolean update(PostDTO dto) {
//			Connection con = null;
//		    PreparedStatement pstmt = null;
//		    try {    
//		    	   con = ds.getConnection();
//		           pstmt = con.prepareStatement(UPDATE);
//		           pstmt.setString(1, dto.getEmail());
//		           pstmt.setString(2, dto.getSubject());
//		           pstmt.setString(3, dto.getContent());
//		           pstmt.setLong(4, dto.getSeq());
//		           
//		           int i = pstmt.executeUpdate();
//		           return i > 0;
//		    }catch(SQLException se){
//		    	  return false;
//		    }finally {
//		        try { if (pstmt != null) pstmt.close(); } catch(Exception e) {}
//		        try { if (con != null) con.close(); } catch(Exception e) {}
//		    }
//
//		}
}
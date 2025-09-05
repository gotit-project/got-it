package gotit.reaction;

import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;

import static gotit.common.util.SqlUtils.*;
import gotit.model.Reaction;


public class ReactionDAO {
	private DataSource ds;
	public ReactionDAO() {
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
   	 * 좋아요 추가 
   	 * ========================== */
	public boolean likeInsert(Reaction reaction) {
		try(Connection con = ds.getConnection();
	        PreparedStatement pstmt = con.prepareStatement(REACTION_LIKE_INSERT)) {
	
	        pstmt.setLong(1, reaction.getPostId());
	        pstmt.setLong(2, reaction.getUserId());
	
	        return pstmt.executeUpdate() > 0;
	    } catch(SQLException se) {
	        se.printStackTrace();
	        return false;
	    }
	}
	
	/* ==========================
   	 * 좋아요 삭
   	 * ========================== */
	public boolean likeDelete(Reaction reaction) {
	    try(Connection con = ds.getConnection();
	        PreparedStatement pstmt = con.prepareStatement(REACTION_LIKE_DELETE)) {
	        pstmt.setLong(1, reaction.getPostId());
	        pstmt.setLong(2, reaction.getUserId());
	        return pstmt.executeUpdate() > 0;
	    } catch(SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	/* ==========================
   	 * 특정 사용자가 해당 게시글에 
   	 * 좋아요 눌렀는지 확인 
   	 * ========================== */
	public boolean likeExists(Reaction reaction) {
	    try(Connection con = ds.getConnection();
	        PreparedStatement pstmt = con.prepareStatement(REACTION_LIKE_CHECK)) {
	        pstmt.setLong(1, reaction.getPostId());
	        pstmt.setLong(2, reaction.getUserId());
	        try(ResultSet rs = pstmt.executeQuery()) {
	            if(rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	/* ==========================
   	 * 스크랩 추가 
   	 * ========================== */
	public boolean scrapInsert(Reaction reaction) {
		try(Connection con = ds.getConnection();
	        PreparedStatement pstmt = con.prepareStatement(REACTION_SCRAP_INSERT)) {
	        pstmt.setLong(1, reaction.getPostId());
	        pstmt.setLong(2, reaction.getUserId());
	
	        return pstmt.executeUpdate() > 0;
	    } catch(SQLException se) {
	        se.printStackTrace();
	        return false;
	    }
	}
	
	/* ==========================
   	 * 스크랩 삭제 
   	 * ========================== */
	public boolean scrapDelete(Reaction reaction) {
	    try(Connection con = ds.getConnection();
	        PreparedStatement pstmt = con.prepareStatement(REACTION_SCRAP_DELETE)) {
	        pstmt.setLong(1, reaction.getPostId());
	        pstmt.setLong(2, reaction.getUserId());
	        return pstmt.executeUpdate() > 0;
	    } catch(SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	/* ==========================
   	 * 특정 사용자가 해당 게시글에
   	 * 스크랩 눌렀는지 확인 
   	 * ========================== */
	public boolean scrapExists(Reaction reaction) {
	    try(Connection con = ds.getConnection();
	        PreparedStatement pstmt = con.prepareStatement(REACTION_SCRAP_CHECK)) {
	        pstmt.setLong(1, reaction.getPostId());
	        pstmt.setLong(2, reaction.getUserId());
	        try(ResultSet rs = pstmt.executeQuery()) {
	            if(rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	/* ==========================
   	 * 좋아요 상태 확인 
   	 * (postId, userId 기준)
   	 * ========================== */
    public boolean hasUserLiked(long postId, long userId) {
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(REACTION_LIKE_CHECK)) {
            ps.setLong(1, postId);
            ps.setLong(2, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
          System.out.println("hasUserLiked실행");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ==========================
   	 * 스크 상태 확인 
   	 * (postId, userId 기준)
   	 * ========================== */
    public boolean hasUserScrapped(long postId, long userId) {
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(REACTION_SCRAP_CHECK)) {
            ps.setLong(1, postId);
            ps.setLong(2, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
	

}

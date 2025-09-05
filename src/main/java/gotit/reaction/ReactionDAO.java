package gotit.reaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
	

    // 좋아요 체크
    public boolean hasUserLiked(long postId, long userId) {
        // DB에서 postId, userId로 좋아요 존재 여부 확인
        String sql = "SELECT COUNT(*) FROM post_likes WHERE post_id = ? AND user_id = ?";
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
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

    // 스크랩 체크
    public boolean hasUserScrapped(long postId, long userId) {
        String sql = "SELECT COUNT(*) FROM post_scraps WHERE post_id = ? AND user_id = ?";
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
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

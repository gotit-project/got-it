package gotit.auth;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import gotit.model.User;

import static gotit.common.util.SqlUtils.*;



public class AuthDAO {
	private static final AuthDAO instance = new AuthDAO(); 
	private DataSource ds;
	
	private AuthDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/gotDB");
		}catch(NamingException ne){}
	}
	
    public static AuthDAO getInstance() {
        return instance;
    }
	
	/* ===========================
	 * 사용자가 입력한 이메일 값을 가지고
	 * DB에서 사용자 정보 조회
	 * User 객체 생성 후 User 반환
	 ============================= */
	User getUser(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(AUTH_LOGIN);
			
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				long userId = rs.getLong(1);
				String userName = rs.getString(2);
				String pwd= rs.getString(4);
				String imgName = rs.getString(5);
				String nickname = rs.getString(6);
				int point = rs.getInt(7);
				int badge = rs.getInt(8);
				String status = rs.getString(9);
				Date createDate = rs.getDate(10);
				Date updateDate = rs.getDate(11);
				
				String badgeName = getBadgeName(badge);
				
				return new User(userId, userName, email, pwd, imgName, nickname, 
						point, badge, badgeName, status, createDate, updateDate);
			}else {
				return null;
			}
		}catch(SQLException se) {
			return null;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	
	/* ===========================
	 * 이메일 중복 여부 확인
	 ============================= */
	public boolean existsByEmail(String email) {
	    String sql = "SELECT 1 FROM users WHERE email = ? LIMIT 1";
	    try (Connection con = ds.getConnection();
	    		PreparedStatement pstmt = con.prepareStatement(sql)) {
	        	pstmt.setString(1, email);
	        try (ResultSet rs = pstmt.executeQuery()) { return rs.next(); }
	    } catch (SQLException e) { return true; } // 안전하게 막기
	}
	
	/* ===========================
	 * 닉네임 중복 여부 확인 
	 ============================= */
	public boolean existsByAlias(String alias) {
	    String sql = "SELECT 1 FROM users WHERE nickname = ? LIMIT 1";
	    try (Connection c = ds.getConnection();
	         PreparedStatement ps = c.prepareStatement(sql)) {
	        ps.setString(1, alias);
	        try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
	    } catch (SQLException e) { return true; }
	}
	
	/* ===========================
	 * 회원가입 insert
	 ============================= */
	public int insertUser(String name, String email, String hashedPwd, String alias) throws SQLException {
	    try (Connection c = ds.getConnection();
	         PreparedStatement ps = c.prepareStatement(AUTH_SIGNUP)) {
	        ps.setString(1, name);
	        ps.setString(2, email);
	        ps.setString(3, hashedPwd);
	        ps.setString(4, alias);
	        return ps.executeUpdate();
	    }
	}
	
	/* ==========================
	 * 뱃지 이름을 가져오기 위한 메서드
	 * 이름 별로 css + 프론트 텍스트 변경
	 * ========================== */
    private String getBadgeName(int badge) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	
	    try {
	        con = ds.getConnection();
	        pstmt = con.prepareStatement(GET_BADGE_NAME); 
	        pstmt.setInt(1, badge);
	        rs = pstmt.executeQuery();
	
	        if (rs.next()) {
	            String badgeName = rs.getString("badge_name");
	            return badgeName;
	        }
	    } catch (SQLException se) {
	    	 se.printStackTrace(); 
	    	return null;
	    } finally {
	        try { if (rs != null) rs.close(); } catch(Exception e) {}
	        try { if (pstmt != null) pstmt.close(); } catch(Exception e) {}
	        try { if (con != null) con.close(); } catch(Exception e) {}
	    }
	    return null;
    }
    
	/* ==========================
	 * 유저 이미지 업데이트
	 * ========================== */
    public int updateImgName(long userId, String imgName) throws Exception {
        String sql = "UPDATE users SET img_name=? WHERE user_id=?";
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
          ps.setString(1, imgName);
          ps.setLong(2, userId);
          return ps.executeUpdate();
        }
      }
    
    /* ==========================
     * 유저 단건 조회
     * ========================== */
    public User findById(long userId) throws Exception {
        String sql = "SELECT user_id, username, email, password_hash, img_name, nickname, total_points, badge_id, status " +
                     "FROM users WHERE user_id=?";
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setUserId(rs.getLong("user_id"));
                    u.setUserName(rs.getString("username"));
                    u.setEmail(rs.getString("email"));
                    u.setPwd(rs.getString("password_hash"));
                    u.setImgName(rs.getString("img_name"));
                    u.setNickname(rs.getString("nickname"));
                    u.setPoint(rs.getInt("total_points"));
                    u.setBadge(rs.getInt("badge_id"));
                    u.setStatus(rs.getString("status"));
                    return u;
                }
            }
        }
        return null;
    }
}

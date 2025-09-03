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
				
				return new User(userId, userName, email, pwd, imgName, nickname, 
						point, badge, status, createDate, updateDate);
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
}

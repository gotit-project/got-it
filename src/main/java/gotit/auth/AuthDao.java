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
}

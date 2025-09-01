package gotit.board;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import gotit.model.Board;
import static gotit.common.util.SqlUtils.*;

public class BoardDAO {
	private DataSource ds;
	
	public BoardDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/gotit");
		} catch (NamingException ne) {
			System.out.println("네이밍 예외 발생 : " + ne);
		}
	}
	
	public ArrayList<Board> getList() {
		ArrayList<Board> list = new ArrayList<Board>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			
			String sql = "";
			
			rs = stmt.executeQuery(sql);
				
			while(rs.next()) {
				long postId = rs.getLong(1);
				long boardId = rs.getLong(2);
				long userId = rs.getLong(3);
				String title = rs.getString(4);
				String content = rs.getString(5);
				int deleted = rs.getInt(6);
				java.sql.Date createdDate = rs.getDate(7);
				java.sql.Date updatedDate = rs.getDate(8);
				
				//list.add(new Board(postId, boardId, userId, title, content, ));
			}
			return list;
		} catch (SQLException se) {
			System.out.println("SQL 조회 예외 발생 : " + se);
			return null;
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
					stmt.close();
					conn.close();
				}
			} catch (SQLException se) {}
		}
	}
}

// gotit.board.BoardDAO.java
package gotit.board;

import gotit.model.Board;
import gotit.model.Categorie;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import static gotit.common.util.SqlUtils.*;

import java.sql.*;
import java.util.ArrayList;

public class BoardDAO {
    private static final BoardDAO INSTANCE = new BoardDAO();
    private DataSource ds;

    private BoardDAO() {
        try {
            InitialContext ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/jdbc/gotDB");
        } catch (NamingException e) {
            throw new RuntimeException("JNDI DataSource lookup failed: jdbc/gotDB", e);
        }
    }
    public static BoardDAO getInstance() { return INSTANCE; }
    
    

    public Board findByBoard(String boardName) throws SQLException {
    	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(BOARD_SELECT);
			
			pstmt.setString(1, boardName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				long boardId = rs.getLong(1);
				//String boardName = rs.getString(2);
				String description = rs.getString(2);
				int postCount = rs.getInt(2);
				
				ArrayList<Categorie> categorie = findByCategorie(boardName);
				
				return new Board(boardId, boardName, description, postCount, null);
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
    
    private ArrayList<Categorie> findByCategorie(String boardName) {
    	ArrayList<Categorie> list = new ArrayList<Categorie>();
    	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CATEGORIE_SELECT);
			
			pstmt.setString(1, boardName);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				int categorieId = rs.getInt(1);
	            int boardId = rs.getInt(2);
	            String categorieName = rs.getString(3);
				list.add(new Categorie(categorieId, boardId, categorieName));
			}
			return list;
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
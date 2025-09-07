// gotit.board.BoardDAO.java
package gotit.board;

import gotit.model.Board;
import gotit.model.Category;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import static gotit.common.util.SqlUtils.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    
    public List<Board> getBoards() {
        List<Board> list = new ArrayList<>();
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(BOARD_ALL_SELECT)) {
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
				int boardId = rs.getInt(1);
				String boardName = rs.getString(2);
				String description = rs.getString(3);
				int postCount = rs.getInt(4);
	
			     
			    list.add(new Board(boardId, boardName, description, postCount, null));
            }
        }catch(SQLException se) {
            se.printStackTrace();
            return null;
        }
        return list;
    }

    public Board findByBoard(int boardId) throws SQLException {
    	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(BOARD_SELECT);
			pstmt.setInt(1, boardId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//int boardId = rs.getInt(1);
				String boardName = rs.getString(2);
				String description = rs.getString(3);
				int postCount = rs.getInt(4);
				
				List<Category> categories = findByCategories(boardId);
				return new Board(boardId, boardName, description, postCount, categories);
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
    
    private List<Category> findByCategories(int boardId) {
    	List<Category> list = new ArrayList<Category>();
    	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CATEGORIE_SELECT);
			
			pstmt.setInt(1, boardId);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				int categoryId = rs.getInt(1);
	            //int boardId = rs.getInt(2);
	            String categoryName = rs.getString(3);
				list.add(new Category(categoryId, boardId, categoryName));
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
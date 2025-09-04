package gotit.mypage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.*;
import javax.sql.DataSource;

import static gotit.common.util.SqlUtils.*;

import gotit.common.util.SqlUtils;
import gotit.model.Post;
import gotit.model.Reaction;


public class MypageDAO {
	private DataSource ds;
	
	public MypageDAO() {
		try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/gotDB");
            if(ds == null) throw new RuntimeException("DataSource lookup failed: jdbc/gotDB not found");
        } catch(NamingException ne) {
            throw new RuntimeException("JNDI NamingException", ne);
        }
	}
	
	  public List<Post> list() {
	        List<Post> list = new ArrayList<>();
	        try(Connection con = ds.getConnection();
	            PreparedStatement pstmt = con.prepareStatement(SqlUtils.POST_SELECT);
	            ResultSet rs = pstmt.executeQuery()) {
	            
	            while(rs.next()) {
	          ;
				    
	              
	            }
	        } catch(SQLException se) {
	            se.printStackTrace();
	            return null;
	        }
	        return list;
	    }


	

}

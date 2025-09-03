package gotit.post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.*;
import javax.sql.DataSource;

import gotit.common.util.SqlUtils;
import gotit.model.Post;

public class PostDAO {
    private DataSource ds;

    public PostDAO() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/gotDB");
            if(ds == null) throw new RuntimeException("DataSource lookup failed: jdbc/gotDB not found");
        } catch(NamingException ne) {
            throw new RuntimeException("JNDI NamingException", ne);
        }
    }

    public List<Post> postList() {
        List<Post> list = new ArrayList<>();
        try(Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SqlUtils.POST_LIST);
            ResultSet rs = pstmt.executeQuery()) {
            
            while(rs.next()) {
                long postId = rs.getLong("post_id");
                long boardId = rs.getLong("board_id");
                long userId = rs.getLong("user_id");
                long categorieId = rs.getLong("categorie_id");
                String postTag = rs.getString("post_tag");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String status = rs.getString("status");
                int likeCount = rs.getInt("like_count");
                int viewCount = rs.getInt("view_count");

             // list()
                list.add(new Post(
                    rs.getLong("post_id"),
                    rs.getLong("board_id"),
                    rs.getLong("user_id"),
                    rs.getLong("categorie_id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getString("post_tag"),
                    rs.getInt("like_count"),
                    rs.getInt("view_count"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
                ));

            }
        } catch(SQLException se) {
            se.printStackTrace();
            return null;
        }
        return list;
    }

    public boolean insert(Post post) {
        try(Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SqlUtils.POST_INSERT)) {

            pstmt.setLong(1, post.getBoardId());
            pstmt.setLong(2, post.getUserId());
            pstmt.setLong(3, post.getCategorieId());
            pstmt.setString(4, post.getTitle());
            pstmt.setString(5, post.getContent());
            pstmt.setString(6, post.getPostTag());

            return pstmt.executeUpdate() > 0;
        } catch(SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

    public Post select(long postId) {
        try(Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SqlUtils.POST_SELECT)) {

            pstmt.setLong(1, postId);
            try(ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()) {
                    long boardId = rs.getLong("board_id");
                    long userId = rs.getLong("user_id");
                    long categorieId = rs.getLong("categorie_id");
                    String postTag = rs.getString("post_tag");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    int likeCount = rs.getInt("like_count");
                    int viewCount = rs.getInt("view_count");
                    String status = rs.getString("status");

                 
                    		
                 // select()
                    return new Post(
                        rs.getLong("post_id"),
                        rs.getLong("board_id"),
                        rs.getLong("user_id"),
                        rs.getLong("categorie_id"),
                        rs.getString("post_tag"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("like_count"),
                        rs.getInt("view_count"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                    );

                }
            }
        } catch(SQLException se) {
            se.printStackTrace();
        }
        return null;
    }

    public boolean delete(long postId) {
        try(Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SqlUtils.POST_DELETE)) {

            pstmt.setLong(1, postId);
            return pstmt.executeUpdate() > 0;
        } catch(SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

    public boolean update(Post post) {
        try(Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SqlUtils.POST_UPDATE)) {

            pstmt.setLong(1, post.getBoardId());
            pstmt.setLong(2, post.getUserId());
            pstmt.setLong(3, post.getCategorieId());
            pstmt.setString(4, post.getTitle());
            pstmt.setString(5, post.getContent());
            pstmt.setString(6, post.getPostTag());

            return pstmt.executeUpdate() > 0;
        } catch(SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

    public boolean addViewCount(long postId) {
        try(Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SqlUtils.POST_VIEW_COUNT)) {

            pstmt.setLong(1, postId);
            return pstmt.executeUpdate() > 0;
        } catch(SQLException se) {
            se.printStackTrace();
            return false;
        }
    }
}

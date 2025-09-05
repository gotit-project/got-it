package gotit.common.util;

public class SqlUtils {
	
	//AuthDAO SQL
	public static final String AUTH_LOGIN = "select * from users where email=? ";
	public static final String AUTH_SIGNUP = "insert into users (username, email, password_hash, nickname) values ( ?, ?, ?, ? )";
	
	//BoardDAO SQL
	public static final String BOARD_SELECT = "select * from boards where board_id=? ";
	public static final String BOARD_ALL_SELECT = "select * from boards ";
	public static final String CATEGORIE_SELECT = "select * from board_categories where board_id=? ";
	
	//PostDAO SQL
	public final static String POST_SELECT = "SELECT * FROM posts WHERE state_type='ACTIVE' AND board_id=? ORDER BY ? LIMIT ?, ?";
	public final static String POST_CAT_SELECT = "SELECT * FROM posts WHERE state_type='ACTIVE' AND board_id=? AND category_id=? ORDER BY ? LIMIT ?, ?";
	public final static String POST_INSERT = "INSERT INTO posts (board_id, user_id, category_id, post_tag, title, raw_content, html_content) VALUES (?, ?, ?, ?, ?, ?, ?)";
	public final static String POST_VIEW = "select * from POSTS where POST_ID=?";
	public final static String POST_DELETE = "update POSTS set state_type = 'DELETED' where POST_ID =?";
	public final static String POST_UPDATE = "update  POSTS set board_id=?, user_id=?, category_id=?, post_tag=?, title=?, raw_content=?, html_content=? where post_id=?";
	public final static String POST_UPDATE_COUNT = "update POSTS set view_count = view_count+1 where POST_ID=?";
	
	//CommentDAO SQL
	public final static String COMMENT_SELECT = "select * from post_comments where post_id=?";
	public final static String COMMENT_INSERT = "insert into POST_COMMENTS(post_id, user_id, content, is_answer, accepted, created_at, updated_at) values (?, ?, ?, ?, ?, now(), now())";
	public final static String COMMENT_UPDATE = "update POST_COMMENTS set content=? where comment_id=?";
	public static final String COMMENT_SELECT_ONE = "SELECT * FROM  POST_COMMENTS where comment_id = ?";
	public final static String COMMENT_DELETE = "delete from POST_COMMENTS where COMMENT_ID=?";

	//ReactionDAO SQL - LIKE
	public final static String REACTION_LIKE_INSERT = "insert into POST_LIKES (post_id, user_id) values(?, ?)";
	public final static String REACTION_LIKE_DELETE  = "delete from POST_LIKES where post_id=? and user_id=?";
	public final static String REACTION_LIKE_CHECK ="SELECT COUNT(*) FROM POST_LIKES WHERE post_id=? AND user_id=?";
	
	//ReactionDAO SQL - SCRAP
	public final static String REACTION_SCRAP_INSERT = "insert into POST_SCRAPS (post_id, user_id) values(?, ?)";
	public final static String REACTION_SCRAP_DELETE  = "delete from POST_SCRAPS where post_id=? and user_id=?";
	public final static String REACTION_SCRAP_CHECK ="SELECT COUNT(*) FROM POST_SCRAPS WHERE post_id=? AND user_id=?";
  
	//ID -> GET DATA
	public final static String GET_BOARD_NAME = "select board_name from boards where board_id = ?";
	public final static String GET_NICKNAME = "select nickname from users where user_id = ?";
	public final static String GET_CATEGORY_NAME = "select category_name from board_categories where category_id = ?";
}

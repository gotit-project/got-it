package gotit.common.util;

public class SqlUtils {
	
	//AuthDAO SQL
	public static final String AUTH_LOGIN = "select * from users where email=? ";
	public static final String AUTH_SIGNUP = "insert into users (username, email, password_hash, nickname) values ( ?, ?, ?, ? )";
	
	//BoardDAO SQL
	public static final String BOARD_SELECT = "select * from boards where board_name=? ";
	public static final String CATEGORIE_SELECT = "select * from board_categories where board_id=? ";
	
	//PostDAO SQL
	public final static String POST_LIST = "select * from POSTS where status = 'active'";
	public final static String POST_INSERT = "insert into POSTS(board_id, user_id, categorie_id, post_tag, title, content) values(?, ?, ?, ?, ?, ?)";
	public final static String POST_SELECT = "select * from POSTS where POST_ID=?";
	public final static String POST_DELETE = "update POSTS set status = 'DELETED' where POST_ID =?";
	public final static String POST_UPDATE = "update  POSTS set board_id=?, user_id=?, categorie_id=?, post_tag=?, title=?, content=?, where POST_ID=?";
	public final static String POST_VIEW_COUNT = "update POSTS set view_count = view_count+1 where POST_ID=?";
	
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
	public final static String REACTION_LIKE_COUNT ="SELECT COUNT(*) FROM POST_LIKES WHERE post_id=?";
	
	//ReactionDAO SQL - SCRAP
	public final static String REACTION_SCRAP_INSERT = "insert into POST_SCRAPS (post_id, user_id) values(?, ?)";
	public final static String REACTION_SCRAP_DELETE  = "delete from POST_SCRAPS where post_id=? and user_id=?";
	public final static String REACTION_SCRAP_CHECK ="SELECT COUNT(*) FROM POST_SCRAPS WHERE post_id=? AND user_id=?";
	public final static String REACTION_SCRAP_COUNT ="SELECT COUNT(*) FROM POST_SCRAPS WHERE post_id=?";
	
}

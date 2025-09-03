package gotit.common.util;

public class SqlUtils {
	
	//AuthDAO SQL
	public static final String AUTH_LOGIN = "select * from users where email=? ";
	public static final String AUTH_SIGNUP = "insert into users (username, email, password_hash, nickname) values ( ?, ?, ?, ? )";
	
	//BoardDAO SQL
	public static final String BOARD_SELECT = "select * from boards where board_name=? ";
	public static final String CATEGORIE_SELECT = "select * from boards where board_id=? ";
	
	
	//PostDAO SQL
	public final static String POST_LIST = "select * from POSTS";
	public final static String POST_INSERT = "insert into POSTS(board_id, user_id, title, content, deleted, created_at, updated_at, tag) values(?, ?, ?, ?, ?, now(), now(), ?)";
	public final static String POST_SELECT = "select * from POSTS where POST_ID=?";
	public final static String POST_DELETE = "delete from POSTS where POST_ID=?";
	public final static String POST_UPDATE = "update  POSTS set board_id=?, user_id=?, title=?, content=?, deleted=?, created_at=?, updated_at=?, tag=? where POST_ID=?";
	public final static String POST_VIEW_COUNT = "update POSTS set view_counts = view_counts+1 where POST_ID=?";
	public final static String COMMENT_SELECT = "select * from post_comments where post_id=?";
	public final static String COMMENT_INSERT = "insert into POST_COMMENTS(post_id, user_id, content, is_answer, accepted, deleted, created_at, updated_at) values (?, ?, ?, ?, ?, ?, now(), now())";
	public final static String COMMENT_UPDATE = "update POST_COMMENTS set content=? where comment_id=?";
	public static final String COMMENT_SELECT_ONE = "SELECT * FROM  POST_COMMENTS where comment_id = ?";
	public final static String COMMENT_DELETE = "delete from POST_COMMENTS where COMMENT_ID=?";

}

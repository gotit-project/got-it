package gotit.post;

public class PostSQL {
	static final java.lang.String LIST = "select * from POSTS";
	static final java.lang.String INSERT = "insert into POSTS(board_id, user_id, title, content, deleted, created_at, updated_at, tag) values(?, ?, ?, ?, ?, now(), now(), ?)";
	static final java.lang.String SELECT = "select * from POSTS where POST_ID=?";
	static final java.lang.String DELETE = "delete from POSTS where POST_ID=?";
	static final java.lang.String UPDATE = "update set POSTS board_id=?, user_id=?, title=?, content=?, deleted=?, created_at=?, updated_at=?, tag=? where POST_ID=?";

}

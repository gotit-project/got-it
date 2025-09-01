package gotit.post;

import java.sql.Date;


public class Post {
	private long post_id;
	private long board_id;
	private long user_id;
	private String title;
	private String content;
	private boolean deleted;
	private Date created_at;
	private Date updated_at;
	private String tag;
	
	
	public Post() {};
	
	public Post(long post_id, long board_id, long user_id, String title, String content, boolean deleted, Date created_at, Date updated_at, String tag) {
		this.post_id = post_id;
		this.board_id = board_id;
		this.user_id = user_id;
		this.title = title;
		this.content = content;
		this.deleted = deleted;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.tag = tag;
	}

	public long getPost_id() {
		return post_id;
	}

	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}

	public long getBoard_id() {
		return board_id;
	}

	public void setBoard_id(long board_id) {
		this.board_id = board_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}


	
}
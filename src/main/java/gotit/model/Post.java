package gotit.model;

import java.sql.Date;


public class Post {
	private long postId;
	private long boardId;
	private long userId;
	private String title;
	private String content;
	private boolean deleted;
	private Date createdAt;
	private Date updatedAt;
	private String tag;
	private long viewCounts; 
	
	
	public Post() {};
	
	public Post(long postId, long boardId, long userId, String title, String content, boolean deleted, Date createdAt, Date updatedAt, String tag, long viewCounts) {
		this.postId = postId;
		this.boardId = boardId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.deleted = deleted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.tag = tag;
		this.viewCounts = viewCounts;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public long getBoardId() {
		return boardId;
	}

	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date created_at) {
		this.createdAt = created_at;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdated_at(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}

	public long getViewCounts() {
		return viewCounts;
	}

	public void setViewCounts(long viewCounts) {
		this.viewCounts = viewCounts;
	}


	
}
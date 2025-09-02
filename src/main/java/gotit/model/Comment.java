package gotit.model;

import java.sql.Date;

public class Comment {
	private long commentId;
	private long postId;
	private long userId;
	private String content;
	private boolean isAnswer;
	private boolean accepted;
	private boolean deleted;
	private Date createdAt;
	private Date updatedAt; 
	
	public Comment() {};
	
	public Comment(long commentId, long postId, long userId, String content, boolean isAnswer, boolean accepted, boolean deleted, Date createdAt, Date updatedAt) {
		this.commentId = commentId;
		this.postId = postId;
		this.userId = userId;
		this.content = content;
		this.isAnswer = isAnswer;
		this.accepted = accepted;
		this.deleted = deleted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isIsAnswer() {
		return isAnswer;
	}

	public void setIsAnswer(boolean isAnswer) {
		this.isAnswer = isAnswer;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
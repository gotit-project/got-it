package gotit.model;

import java.sql.Timestamp;

public class Post {
	// DB정보
    private long postId;
    private int boardId;
    private long userId;
    private int categoryId;
    private String postTag;
    private String title;
    private String rawContent;
    private String htmlContent;
    private int likeCount;
    private int viewCount;
    private int commentCount;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    // view 정보
    private String boardName;
    private String nickName;
    private String categoryName;
    
    // DTO    
    public Post(long postId, int boardId, long userId, int categoryId, String postTag, String title, String rawContent,
    		String htmlContent, String status) {
    	super();
    	this.postId = postId;
    	this.boardId = boardId;
    	this.userId = userId;
    	this.categoryId = categoryId;
    	this.postTag = postTag;
    	this.title = title;
    	this.rawContent = rawContent;
    	this.htmlContent = htmlContent;
    	this.status = status;
    }

    // view 생성자
	public Post(long postId, int boardId, long userId, int categoryId, String postTag, String title, String rawContent, 
			String htmlContent,int likeCount, int viewCount, int commentCount, String status, Timestamp createdAt, 
			Timestamp updatedAt, String boardName, String nickName, String categoryName) {
		this.postId = postId;
		this.boardId = boardId;
		this.userId = userId;
		this.categoryId = categoryId;
		this.postTag = postTag;
		this.title = title;
		this.rawContent = rawContent;
    	this.htmlContent = htmlContent;
		this.likeCount = likeCount;
		this.viewCount = viewCount;
		this.commentCount = commentCount;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.boardName = boardName;
		this.nickName = nickName;
		this.categoryName = categoryName;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getPostTag() {
		return postTag;
	}

	public void setPostTag(String postTag) {
		this.postTag = postTag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRawContent() {
		return rawContent;
	}

	public void setRawContent(String rawContent) {
		this.rawContent = rawContent;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
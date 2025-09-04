package gotit.model;

import java.sql.Timestamp;

public class Post {
    private long postId;
    private long boardId;
    private long userId;
    private String nickName;
    private long categorieId;
    private String postTag;
    private String title;
    private String content;
    private int likeCount;
    private int viewCount;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int commentCount;

    // 모든 필드를 포함하는 생성자 하나
    public Post(long postId, long boardId, long userId, long categorieId, String postTag, String title, String content,
                int likeCount, int viewCount, String status, Timestamp createdAt, Timestamp updatedAt) {
        this.postId = postId;
        this.boardId = boardId;
        this.userId = userId;
        this.categorieId = categorieId;
        this.postTag = postTag;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.commentCount = commentCount;
    }

    // Insert용: likeCount, viewCount, createdAt, updatedAt 생략 가능
    // 셍성자 체이닝 
    public Post(long postId, long boardId, long userId, long categorieId, String postTag, String title, String content) {
        this(postId, boardId, categorieId, userId, postTag, title, content, 0, 0, null, null, null);
    }
    
    //user정보 받는용
    public Post(long postId, long boardId, String nickName, long categorieId, String postTag, String title, String content,     int likeCount, int viewCount, String status, Timestamp createdAt, Timestamp updatedAt) {
    						//userName은 아직 0
    	this(postId, boardId, 0L, categorieId, postTag, title, content, 0, 0, null, null, null);
    	this.nickName= nickName; //여기서 지정 
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

	public String getPostTag() {
		return postTag;
	}

	public void setPostTag(String postTag) {
		this.postTag = postTag;
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

	public long getCategorieId() {
		return categorieId;
	}

	public void setCategorieId(long categorieId) {
		this.categorieId = categorieId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	
	

	
}
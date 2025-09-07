package gotit.model;

import java.sql.Timestamp;

public class Comment {
	private long commentId;
	private long postId;
	private long userId;
	private String content;
	private boolean isAnswer;
	private boolean accepted;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	private String nickname;
	private String imgName;
	private String badgeName;
	
	public Comment() {};
	

   	/* ==========================
   	 * nickname 포함 
   	 * 댓글 + 작성자 닉네임 필요할 떄
   	 * ========================== */
	public Comment(long commentId, long postId, String nickname, String content, boolean isAnswer, boolean accepted, Timestamp createdAt, Timestamp updatedAt) {
		this.commentId = commentId;
		this.postId = postId;
		this.nickname = nickname;
		this.content = content;
		this.isAnswer = isAnswer;
		this.accepted = accepted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
   	/* ==========================
   	 * userId 포함 생성자 
   	 * 닉네임 필요 없을 때
   	 * ========================== */
	public Comment(long commentId, long postId, long userId, String content, boolean isAnswer, boolean accepted, Timestamp createdAt, Timestamp updatedAt) {
		this.commentId = commentId;
		this.postId = postId;
		this.userId = userId;
		this.content = content;
		this.isAnswer = isAnswer;
		this.accepted = accepted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
   	/* ==========================
   	 * userId + nickname 포함 생성자 
   	 * 유저아이디, 닉네임 둘다 필요할 때
   	 * ========================== */
	public Comment(long commentId, long postId, long userId, String nickname, String content, boolean isAnswer, boolean accepted, Timestamp createdAt, Timestamp updatedAt, String imgName, String badgeName) {
		this.commentId = commentId;
		this.postId = postId;
		this.userId = userId;
		this.nickname = nickname;
		this.content = content;
		this.isAnswer = isAnswer;
		this.accepted = accepted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.imgName = imgName;
		this.badgeName = badgeName;
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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


	public String getImgName() {
		return imgName;
	}


	public void setImgName(String imgName) {
		this.imgName = imgName;
	}


	public String getBadgeName() {
		return badgeName;
	}


	public void setBadgeName(String badgeName) {
		this.badgeName = badgeName;
	}
	
}
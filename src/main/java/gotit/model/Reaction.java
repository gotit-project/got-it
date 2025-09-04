package gotit.model;

public class Reaction {
	private long postId;
	private long userId;
	
	public Reaction(long postId, long userId) {
		this.postId = postId;
		this.userId = userId;
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
	
	
}

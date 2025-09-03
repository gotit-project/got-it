// gotit.board.model.Board.java
package gotit.model;

import java.time.LocalDateTime;

public class Board {
    private long boardId;
    private BoardType boardType;  // 조인해서 채움
    private String name;
    private String description;
    private boolean isPublic;
    private int postCount;
    private LocalDateTime lastPostAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
//	public Board(long boardId, BoardType boardType, String name, String description, boolean isPublic, int postCount,
//			LocalDateTime lastPostAt, LocalDateTime createdAt, LocalDateTime updatedAt) {
//		super();
//		this.boardId = boardId;
//		this.boardType = boardType;
//		this.name = name;
//		this.description = description;
//		this.isPublic = isPublic;
//		this.postCount = postCount;
//		this.lastPostAt = lastPostAt;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//	}

	public long getBoardId() {
		return boardId;
	}

	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}

	public BoardType getBoardType() {
		return boardType;
	}

	public void setBoardType(BoardType boardType) {
		this.boardType = boardType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}

	public LocalDateTime getLastPostAt() {
		return lastPostAt;
	}

	public void setLastPostAt(LocalDateTime lastPostAt) {
		this.lastPostAt = lastPostAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

    
}
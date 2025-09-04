// gotit.board.model.Board.java
package gotit.model;

import java.util.ArrayList;

public class Board {
    private int boardId;
    private String boardName;
    private String description;
    private int postCount;
    private ArrayList<Category> categories;
    
	public Board(int boardId, String boardName, String description, int postCount, ArrayList<Category> categories) {
		super();
		this.boardId = boardId;
		this.boardName = boardName;
		this.description = description;
		this.postCount = postCount;
		this.categories = categories;
	}
	
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPostCount() {
		return postCount;
	}
	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}
	public ArrayList<Category> getCategories() {
		return categories;
	}
	public void setCategorie(ArrayList<Category> categories) {
		this.categories = categories;
	}
    
}
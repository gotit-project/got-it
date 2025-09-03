// gotit.board.model.Board.java
package gotit.model;

import java.util.ArrayList;

public class Board {
    private long boardId;
    private String boardName;
    private String description;
    private int postCount;
    private ArrayList<Categorie> categorie;
    
	public Board(long boardId, String boardName, String description, int postCount, ArrayList<Categorie> categorie) {
		super();
		this.boardId = boardId;
		this.boardName = boardName;
		this.description = description;
		this.postCount = postCount;
		this.categorie = categorie;
	}
	
	public long getBoardId() {
		return boardId;
	}
	public void setBoardId(long boardId) {
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
	public ArrayList<Categorie> getCategorie() {
		return categorie;
	}
	public void setCategorie(ArrayList<Categorie> categorie) {
		this.categorie = categorie;
	}
    
}
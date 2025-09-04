package gotit.model;

public class Category {
    private int categoryId;
    private int board_id;
    private String categoryName;
    
	public Category(int categoryId, int board_id, String categoryName) {
		this.categoryId = categoryId;
		this.board_id = board_id;
		this.categoryName = categoryName;
	}
	
	public int getcategoryId() {
		return categoryId;
	}
	public void setcategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}

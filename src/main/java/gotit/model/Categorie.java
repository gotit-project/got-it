package gotit.model;

public class Categorie {
    private int categorieId;
    private int board_id;
    private String categorieName;
    
	public Categorie(int categorieId, int board_id, String categorieName) {
		super();
		this.categorieId = categorieId;
		this.board_id = board_id;
		this.categorieName = categorieName;
	}
	
	public int getCategorieId() {
		return categorieId;
	}
	public void setCategorieId(int categorieId) {
		this.categorieId = categorieId;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getCategorieName() {
		return categorieName;
	}
	public void setCategorieName(String categorieName) {
		this.categorieName = categorieName;
	}
}

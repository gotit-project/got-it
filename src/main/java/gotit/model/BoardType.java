package gotit.model;

public class BoardType {
    private byte boardTypeId;     // TINYINT
    private String code;          // QNA/KNOW/FREE/NOTICE
    private String name;
    private String description;
    
//	public BoardType(byte boardTypeId, String code, String name, String description) {
//		super();
//		this.boardTypeId = boardTypeId;
//		this.code = code;
//		this.name = name;
//		this.description = description;
//	}
	public byte getBoardTypeId() {
		return boardTypeId;
	}
	public void setBoardTypeId(byte boardTypeId) {
		this.boardTypeId = boardTypeId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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

    
}
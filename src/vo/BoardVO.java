package vo;

public class BoardVO {
	private int boardNo;
	private String boardName;
	
	public BoardVO(){
		super();
	}
	
	public BoardVO(String boardName) {
		super();
		this.boardName = boardName;
	}

	public BoardVO(int boardNo, String boardName) {
		super();
		this.boardNo = boardNo;
		this.boardName = boardName;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", boardName=" + boardName + "]";
	}
	
}

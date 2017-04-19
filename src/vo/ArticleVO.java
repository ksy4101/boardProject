package vo;

public class ArticleVO {

	private int boardNo;
	private int artNo;
	private String memId;
	private String subject;
	private String content;
	private String writeDate;

	public ArticleVO() {
		super();
	}

	public ArticleVO(String subject, String content) {
		super();
		this.subject = subject;
		this.content = content;
	}

	public ArticleVO(int boardNo, String memId, String subject, String content) {
		super();
		this.boardNo = boardNo;
		this.memId = memId;
		this.subject = subject;
		this.content = content;
	}

	public ArticleVO(int boardNo, int artNo, String memId, String subject, String content, String writeDate) {
		super();
		this.boardNo = boardNo;
		this.artNo = artNo;
		this.memId = memId;
		this.subject = subject;
		this.content = content;
		this.writeDate = writeDate;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getArtNo() {
		return artNo;
	}

	public void setArtNo(int artNo) {
		this.artNo = artNo;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	@Override
	public String toString() {
		return "ArticleVO [boardNo=" + boardNo + ", artNo=" + artNo + ", memId=" + memId + ", subject=" + subject
				+ ", content=" + content + ", writeDate=" + writeDate + "]";
	}

}
package vo;

public class ReplyVO {
	private int reNo;
	private int artNo;
	private String memId;
	private String reContent;
	private String reDate;
	

	public ReplyVO() {
		super();
		
	}


	public ReplyVO(int reNo, int artNo, String memId, String reContent, String reDate) {
		super();
		this.reNo = reNo;
		this.artNo = artNo;
		this.memId = memId;
		this.reContent = reContent;
		this.reDate = reDate;
	}




	public int getReNo() {
		return reNo;
	}


	public void setReNo(int reNo) {
		this.reNo = reNo;
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


	public String getReContent() {
		return reContent;
	}


	public void setReContent(String reContent) {
		this.reContent = reContent;
	}


	public String getReDate() {
		return reDate;
	}


	public void setReDate(String reDate) {
		this.reDate = reDate;
	}


	@Override
	public String toString() {
		return "ReplyVO [reNo=" + reNo + ", artNo=" + artNo + ", memId=" + memId + ", reContent=" + reContent
				+ ", reDate=" + reDate + "]";
	}

	
}

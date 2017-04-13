package vo;

public class ReplyVO {
	private int reNo;
	private int artNO;
	private String memId;
	private String reContent;
	private String reDate;
	

	public ReplyVO() {
		super();
		
	}

	public ReplyVO(int reNo, int artNO, String memId, String reContent, String reDate) {
		super();
		this.reNo = reNo;
		this.artNO = artNO;
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


	public int getArtNO() {
		return artNO;
	}


	public void setArtNO(int artNO) {
		this.artNO = artNO;
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
		return "ReplyVO [reNo=" + reNo + ", artNO=" + artNO + ", memId=" + memId + ", reContent=" + reContent
				+ ", reDate=" + reDate + "]";
	}

	
}

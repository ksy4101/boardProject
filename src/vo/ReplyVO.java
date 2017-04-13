package vo;

public class replyVO {
	private int reNo;
	private int artNO;
	private int memId;
	private int reContent;
	private int reDate;
	

	public replyVO() {
		super();
		
	}

	
	
	public replyVO(int reNo, int artNO, int memId, int reContent, int reDate) {
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


	public int getMemId() {
		return memId;
	}


	public void setMemId(int memId) {
		this.memId = memId;
	}


	public int getReContent() {
		return reContent;
	}


	public void setReContent(int reContent) {
		this.reContent = reContent;
	}


	public int getReDate() {
		return reDate;
	}


	public void setReDate(int reDate) {
		this.reDate = reDate;
	}


	@Override
	public String toString() {
		return "reply [reNo=" + reNo + ", artNO=" + artNO + ", memId=" + memId + ", reContent=" + reContent
				+ ", reDate=" + reDate + "]";
	}
}

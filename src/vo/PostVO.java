package vo;

public class PostVO {
	private int postNo;
	private String sido;
	private String gugun;
	private String dong;
	private String bunzi;
	private String zipcode;
	private String ri;
	private String bldg;
	
	public PostVO() {
		super();
	}

	public PostVO(int postNo, String sido, String gugun, String dong, String bunzi, String zipcode, String ri,
			String bldg) {
		super();
		this.postNo = postNo;
		this.sido = sido;
		this.gugun = gugun;
		this.dong = dong;
		this.bunzi = bunzi;
		this.zipcode = zipcode;
		this.ri = ri;
		this.bldg = bldg;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getBunzi() {
		return bunzi;
	}

	public void setBunzi(String bunzi) {
		this.bunzi = bunzi;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getRi() {
		return ri;
	}

	public void setRi(String ri) {
		this.ri = ri;
	}

	public String getBldg() {
		return bldg;
	}

	public void setBldg(String bldg) {
		this.bldg = bldg;
	}

	@Override
	public String toString() {
		return "PostVO [postNo=" + postNo + ", sido=" + sido + ", gugun=" + gugun + ", dong=" + dong + ", bunzi="
				+ bunzi + ", zipcode=" + zipcode + ", ri=" + ri + ", bldg=" + bldg + "]";
	}
	
	
	



	
	
	
	
	
}

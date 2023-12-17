package model;

public class Location {
	private String ward;
	private String distrist;
	private String province;
	public Location(String ward, String distrist, String province) {
		super();
		this.ward = ward;
		this.distrist = distrist;
		this.province = province;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getDistrist() {
		return distrist;
	}
	public void setDistrist(String distrist) {
		this.distrist = distrist;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	
	
	

}

package model.api;

public class Ward {
	private String id;
	private String name;
	private String districtId;
	
	public Ward(String id, String name, String districtId) {
		super();
		this.id = id;
		this.name = name;
		this.districtId = districtId;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDistrictId() {
		return districtId;
	}
	
	
	
	
}

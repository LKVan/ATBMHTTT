package model.api;

public class District {
	private String name;
	private String id;
	private String id_province;
	public District( String id,String name, String id_province) {
		super();
		this.name = name;
		this.id = id;
		this.id_province = id_province;
	}
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	public String getId_province() {
		return id_province;
	}
	@Override
	public String toString() {
		return "District [name=" + name + ", id=" + id + ", id_province=" + id_province + "]";
	}

	
	
	
	

}

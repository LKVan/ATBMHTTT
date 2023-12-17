package model.api;

public class Province {
	private String id;
	private String name;
	public Province(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID " + id +",Name: "+ name;
	}
	
	
	
	
	

}

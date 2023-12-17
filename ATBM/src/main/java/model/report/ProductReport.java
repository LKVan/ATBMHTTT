package model.report;

public class ProductReport {
	private int id;
	private String name;
	private int price;
	private int quanliSoldOut;

	public ProductReport(int id, String name, int price, int quanliSoldOut) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quanliSoldOut = quanliSoldOut;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuanliSoldOut() {
		return quanliSoldOut;
	}
	public void setQuanliSoldOut(int quanliSoldOut) {
		this.quanliSoldOut = quanliSoldOut;
	}
	
	
	
	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID:" + id+", Name: " + name +",Quanlity:" + quanliSoldOut;
	}
	
	
}

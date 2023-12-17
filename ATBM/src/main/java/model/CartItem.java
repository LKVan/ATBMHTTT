package model;

public class CartItem {
//	private String id;
//	private String name;
//	private String description;
//	private int price;
//	private String image;
//	private int catId;
	private Product product;
	private int quantity;

//	public CartItem(String id, String name, String description, int price, String image, int catId, int quantity) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.description = description;
//		this.price = price;
//		this.image = image;
//		this.catId = catId;
//		this.quantity = quantity;
//	}
	
	public CartItem(Product product, int quantity) {
	super();
	this.product = product;
	this.quantity = quantity;
}

	// Phuong thuc tinh tong tien
	public int getTotalCost() {
		return this.quantity * this.product.getPrice();
	}



	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void increment() {
		this.quantity++;
	}

	public void descrement() {
		this.quantity--;
	}

	// Phuong thuc format gia de hien thi
	public String formatPrice() {
		String fm = product.getPrice()+ "";
		String result = "";
		int count = 0;
		for (int i = fm.length() - 1; i >= 0; i--) {
			result = fm.charAt(i) + result;
			count++;
			if (count == 3 && i != 0) {
				result = "." + result;
				count = 0;
			}
		}
		return result;
	}

	// Phuong thuc format tong gia de hien thi
	public String formatTotal() {
		String fm = getTotalCost() + "";
		String result = "";
		int count = 0;
		for (int i = fm.length() - 1; i >= 0; i--) {
			result = fm.charAt(i) + result;
			count++;
			if (count == 3 && i != 0) {
				result = "." + result;
				count = 0;
			}
		}
		return result;
	}

	public CartItem() {
		super();
	}

//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public int getPrice() {
//		return price;
//	}
//
//	public void setPrice(int price) {
//		this.price = price;
//	}
//
//	public String getImage() {
//		return image;
//	}
//
//	public void setImage(String image) {
//		this.image = image;
//	}
//
//	public int getCatId() {
//		return catId;
//	}
//
//	public void setCatId(int catId) {
//		this.catId = catId;
//	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
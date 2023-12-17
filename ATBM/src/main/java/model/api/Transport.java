package model.api;

import model.Product;

public class Transport {
	private int order_id;
	private String id;
	private Product product;
	private int fee;
	private Date create;
	private Date update;
	private boolean active;

	public Transport(String id, int fee, Date create, Date update) {
		super();
		this.id = id;
		this.fee = fee;
		this.create = create;
		this.update = update;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getId() {
		return id;
	}

	public int getFee() {
		return fee;
	}

	public Date getCreate() {
		return create;
	}
	
	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Transport [, id=" + id + ", fee=" + fee + ", create=" + create + ", update=" + update +"]" ;
	}

	
}

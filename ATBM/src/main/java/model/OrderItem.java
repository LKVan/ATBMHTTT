package model;

import java.security.NoSuchAlgorithmException;

import support.HashOrder;

public class OrderItem {
	private Product product;
	private int quanlity;
	private String hash;

	public OrderItem(Product product, int quanlity) {
		super();
		this.product = product;
		this.quanlity = quanlity;
	}

	public OrderItem(Product product, int quanlity, String hash) {
		super();
		this.product = product;
		this.quanlity = quanlity;
		this.hash = hash;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuanlity() {
		return quanlity;
	}

	public void setQuanlity(int quanlity) {
		this.quanlity = quanlity;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public boolean verify() {
		try {
			if (HashOrder.confirm("" + product.getId() + product.getPrice() + quanlity, hash)) {
				return true;
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String toString() {
		return "OrderItem [product=" + product + ", quanlity=" + quanlity + "]";
	}

}

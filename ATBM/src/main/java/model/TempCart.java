package model;

public class TempCart {
	private int userId;
	private int proId;
	private int quantity;

	public TempCart(int userId, int proId, int quantity) {
		this.proId = proId;
		this.userId = userId;
		this.quantity = quantity;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
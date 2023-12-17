package model.report;

import model.api.Date;

public class RevenueReport {
	private Date date;
	private int quanlity;
	private int totalPrice;
	public RevenueReport(Date date, int quanlity, int totalPrice) {
		super();
		this.date = date;
		this.quanlity = quanlity;
		this.totalPrice = totalPrice;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getQuanlity() {
		return quanlity;
	}
	public void setQuanlity(int quanlity) {
		this.quanlity = quanlity;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "RevenueReport [date=" + date + ", quanlity=" + quanlity + ", totalPrice=" + totalPrice + "]";
	}
	
	

}

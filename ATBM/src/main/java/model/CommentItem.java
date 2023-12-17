package model;

public class CommentItem {

	private Product product;
	private String comment;

	public CommentItem(Product product, String comment) {
		super();
		this.product = product;
		this.comment = comment;
	}
	
	public CommentItem() {
		super();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}

package model;

import java.util.ArrayList;

public class Comment {
	private ArrayList<CommentItem> items = new ArrayList<>();
	
	public void addComment(Product product, String comment) {
		
		CommentItem commentItem =new CommentItem();
		boolean temp = false;
		try {
			if (comment == null) {
				for (CommentItem item : items) {
					if (item.getProduct().getId()==  product.getId()) {											
						temp = true;
					}
				}
				if (temp == false) {
					commentItem.setProduct(product);
					commentItem.setComment(comment);
					items.add(commentItem);
				}
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Error while parsing from String to primitive types: " + nfe.getMessage());
			nfe.printStackTrace();
		}
	}
	
	public ArrayList<CommentItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<CommentItem> items) {
		this.items = items;
	}

	

}

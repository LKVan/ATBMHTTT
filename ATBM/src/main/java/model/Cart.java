package model;

import java.util.ArrayList;

public class Cart {
	private ArrayList<CartItem> items = new ArrayList<>();
	private int total;

	// Xoa cart khi nhan dat hang thanh cong
	public void deleteCart() {
		try {
			items.removeAll(items);
			calculateOrderTotal();
		} catch (NumberFormatException nfe) {
			System.out.println("Error while deleting cart item: " + nfe.getMessage());
			nfe.printStackTrace();
		}
	}
	
	// Xoa san pham khoi gio
	public void deleteProduct(String stt) {
		int iSTT = 0;
		try {
			iSTT = Integer.parseInt(stt);
			items.remove(iSTT - 1);
			calculateOrderTotal();
		} catch (NumberFormatException nfe) {
			System.out.println("Error while deleting cart item: " + nfe.getMessage());
			nfe.printStackTrace();
		}
	}

	public int updateQuanlity(String stt, int status) {
		int iSTT = Integer.parseInt(stt);
		CartItem cartItem = (CartItem) items.get(iSTT - 1);
		try {
			if (status > 0) {
				cartItem.increment();
			} else {
				cartItem.descrement();
			}
			if (cartItem.getQuantity() == 0) {
				items.remove(iSTT - 1);
			}
			calculateOrderTotal();
			System.out.println(cartItem.getQuantity());
			return cartItem.getQuantity();
		} catch (NumberFormatException nfe) {
			System.out.println("Error while updating cart: " + nfe.getMessage());
			nfe.printStackTrace();
		}
		return 0;
	}

//	public void addCart(String pro_id, String name, String image, String desciption, int price, String quantity) {
//		int iQuantity = Integer.parseInt(quantity);
//		CartItem cartItem = new CartItem();
//		boolean temp = false;
//		try {
//			if (iQuantity > 0) {
//				for (CartItem item : items) {
//					if (item.getId().equals(pro_id)) {
//						item.setQuantity(iQuantity + item.getQuantity());
//						calculateOrderTotal();
//						temp = true;
//					}
//				}
//				if (temp == false) {
//					cartItem.setId(pro_id);
//					cartItem.setName(name);
//					cartItem.setPrice(price);
//					cartItem.setImage(image);
//					cartItem.setDescription(desciption);
//					cartItem.setQuantity(iQuantity);
//					// cartItem.setTotalCost(price * iQuantity);
//					items.add(cartItem);
//					calculateOrderTotal();
//				}
//			}
//		} catch (NumberFormatException nfe) {
//			System.out.println("Error while parsing from String to primitive types: " + nfe.getMessage());
//			nfe.printStackTrace();
//		}
//	}
	
	public void addCart(Product product, String quantity) {
		int iQuantity = Integer.parseInt(quantity);
		CartItem cartItem = new CartItem();
		boolean temp = false;
		try {
			if (iQuantity > 0) {
				for (CartItem item : items) {
					if (item.getProduct().getId()==  product.getId()) {
						item.setQuantity(iQuantity + item.getQuantity());
						calculateOrderTotal();
						temp = true;
					}
				}
				if (temp == false) {
//					cartItem.setId(pro_id);
//					cartItem.setName(name);
//					cartItem.setPrice(price);
//					cartItem.setImage(image);
//					cartItem.setDescription(desciption);
					cartItem.setProduct(product);
					cartItem.setQuantity(iQuantity);
					// cartItem.setTotalCost(price * iQuantity);
					items.add(cartItem);
					calculateOrderTotal();
				}
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Error while parsing from String to primitive types: " + nfe.getMessage());
			nfe.printStackTrace();
		}
	}

	protected void calculateOrderTotal() {
		int plus = 0;
		for (int i = 0; i < items.size(); i++) {
			CartItem cartItem = (CartItem) items.get(i);
			plus += cartItem.getTotalCost();

		}
		setTotal(plus);
	}

	public String formatTotal() {
		String fm = total + "";
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

	// public void updateCart(String stt, String quantity) {
	// int iSTT = Integer.parseInt(stt);
	// CartItem cartItem = (CartItem) list.get(iSTT - 1);
	// int iPrice = cartItem.getPrice();
	// int iQuantity = Integer.parseInt(quantity);
	// try {
	// if (iQuantity > 0) {
	// cartItem.setQuantity(iQuantity);
	// cartItem.setTotalCost(iPrice * iQuantity);
	// calculateOrderTotal();
	// }
	// } catch (NumberFormatException nfe) {
	// System.out.println("Error while updating cart: " + nfe.getMessage());
	// nfe.printStackTrace();
	// }
	//
	// }

	public ArrayList<CartItem> getList() {
		return items;
	}

	public void setList(ArrayList<CartItem> list) {
		this.items = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getLineItemCount() {
		return items.size();
	}
}
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Order;
import model.OrderItem;
import model.Product;
import model.User;
import support.DateTime;

public class OrderDAO {
	Connection connect = null;
	PreparedStatement ps = null;
	ResultSet result = null;

	
	public List<OrderItem> getOrderItemByOrderId(int orderId){
		List<OrderItem> list = new ArrayList<>();
		DBContext db = DBContext.getInstance();
		
		try {
			connect = db.getConnection();
			String query = "SELECT products.id,products.name,products.descreption,cthd.price,"
					+ "products.image,products.height,products.length,products.width,products.weigth,"
					+ "products.DanhMuc_id,cthd.quanlity FROM products JOIN cthd ON cthd.product_id = products.id "
					+ "JOIN `order` ON `order`.id = cthd.oder_id WHERE `order`.id = ?";
			PreparedStatement ps = connect.prepareStatement(query);
			ps.setInt(1, orderId);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				Product product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getString(5), result.getInt(6),result.getInt(7),result.getInt(8),result.getInt(9),result.getInt(10));
				OrderItem item = new OrderItem(product, result.getInt(11));
				list.add(item);
			}
			ps.close();
			connect.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return list;
	}

	public List<OrderItem> getOrderItemByOrderId1(){
		List<OrderItem> list = new ArrayList<>();
		DBContext db = DBContext.getInstance();

		try {
			connect = db.getConnection();
			String query = "SELECT products.id, COUNT(cthd.quanlity) FROM products JOIN cthd ON " +
					"products.id = cthd.product_id " +
					"GROUP BY products.id " +
					"order BY COUNT(cthd.quanlity)  LIMIT ?";
			PreparedStatement ps = connect.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				Product product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getString(5), result.getInt(6),result.getInt(7),result.getInt(8),result.getInt(9),result.getInt(10));
				OrderItem item = new OrderItem(product, result.getInt(11));
				list.add(item);
			}
			ps.close();
			connect.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Order> getOrderByStatus(int status){
		List<Order> list = new ArrayList<>();
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "SELECT (order.id),user.userName,(order.orderDate),(order.status) FROM `order` join user on `order`.user_id = user.id"
						+ " WHERE  (order.status) = ?";
			ps = connect.prepareStatement(query);
			ps.setInt(1, status);
			result = ps.executeQuery();
			while (result.next()) {
				int orderId = result.getInt(1);
				List<OrderItem> listItem = getOrderItemByOrderId(orderId);
				Order order = new Order(orderId, result.getString(2), listItem, DateTime.formatDate(result.getDate(3).toString()),result.getInt(4));
				list.add(order);
			}
			ps.close();
			connect.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}
	
	public Order getOrderByID(int id) {
		DBContext db = DBContext.getInstance();
		Order order = null;
		try {
			connect = db.getConnection();
			String query = "SELECT (order.id),user.userName,(order.orderDate),(order.status) FROM `order` join user on `order`.user_id = user.id"
						+ " WHERE  (order.id) = ?";
			ps = connect.prepareStatement(query);
			ps.setInt(1, id);
			result = ps.executeQuery();
			while (result.next()) {
				int orderId = result.getInt(1);
				List<OrderItem> listItem = getOrderItemByOrderId(orderId);
				order = new Order(orderId, result.getString(2), listItem, DateTime.formatDate(result.getDate(3).toString()),result.getInt(4));

			}
			ps.close();
			connect.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;

	}


	//Lấy danh sách người dùng theo id
	public List<Order> getOrderByUserID(int id) {
		List<Order> list = new ArrayList<>();
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "SELECT (order.id),user.userName,(order.orderDate),(order.status) FROM `order` join user on `order`.user_id = user.id"
						+ " WHERE  user.id = ?";
			ps = connect.prepareStatement(query);
			ps.setInt(1, id);
			result = ps.executeQuery();
			while (result.next()) {
				int orderId = result.getInt(1);
				List<OrderItem> listItem = getOrderItemByOrderId(orderId);
				Order order = new Order(orderId, result.getString(2), listItem, DateTime.formatDate(result.getDate(3).toString()),result.getInt(4));
				list.add(order);
			}
			ps.close();
			connect.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	// Lấy ra người dung order
	public User getUserOfOrder(int orderId) {
		
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "SELECT user.fullName,user.phone,user.address,user.userName,user.password,user.rol_id,user.email,user.status "
					+ "FROM `order` JOIN user ON `order`.`user_id` = user.id WHERE `order`.`id` = ?";
			ps = connect.prepareStatement(query);
			ps.setInt(1, orderId);
			result = ps.executeQuery();
			while (result.next()) {
				return new User(result.getString(1),result.getString(2), result.getString(3), result.getString(4), result.getString(5),
						result.getInt(6), result.getString(7),result.getInt(8));
			}
			ps.close();
			connect.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

		
		
		
		
	}
//	public List<Order> getOrderByUserID(int id) {
//		List<Order> list = new ArrayList<>();
//		DBContext db = DBContext.getInstance();
//		try {
//			connect = db.getConnection();
//			String query = "SELECT (order.id),user.userName,user.email,products.name,cthd.price,cthd.quanlity,(order.status) FROM `order`,user,products,cthd\r\n"
//					+ "WHERE (order.user_id) = user.id AND (order.id) = cthd.oder_id AND cthd.product_id = products.id AND (order.user_id) = ?";
//			ps = connect.prepareStatement(query);
//			ps.setInt(1, id);
//			result = ps.executeQuery();
//			while (result.next()) {
//				list.add(new Order(result.getInt(1),
//								result.getString(2),
//								result.getString(3),
//								result.getString(4),
//								result.getInt(5),
//								result.getInt(6),
//								result.getInt(7)));
//			}
//			ps.close();
//			connect.close();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

	public int changeStatusOrder(int orderId, int status) {
		DBContext db = DBContext.getInstance();
		int numberRowChange = 0;
		try {
			connect = db.getConnection();
			String query = "UPDATE `order` SET `status` = ? WHERE (order.id) = ?; ";
			ps = connect.prepareStatement(query);
			ps.setInt(1, status);
			ps.setInt(2, orderId);
			numberRowChange = ps.executeUpdate();
			ps.close();
			connect.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numberRowChange;
	}

	public int delete(int orderId) {
		DBContext db = DBContext.getInstance();
		int numberRowChange = 0;
		try {
			connect = db.getConnection();
			String deleteCTHD = "Delete from `cthd` WHERE (cthd.oder_id) = ?;";
			ps = connect.prepareStatement(deleteCTHD);
			ps.setInt(1, orderId);
			ps.executeUpdate();
			ps.clearParameters();
			
			String deletePriceTransport = "Delete from `price_transport` WHERE (price_transport.order_id) = ?;";
			ps = connect.prepareStatement(deletePriceTransport);
			ps.setInt(1, orderId);
			ps.executeUpdate();
			ps.clearParameters();

			String query = "Delete from `order` WHERE (order.id) = ?; ";
			ps = connect.prepareStatement(query);
			ps.setInt(1, orderId);
			numberRowChange = ps.executeUpdate();
			ps.close();
			connect.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numberRowChange;
	}

	//Lấy top 4 sản phẩm bán chạy nhất
	public List<OrderItem> getProductSellMost(){
		List<OrderItem> list = new ArrayList<>();
		DBContext db = DBContext.getInstance();

		try {
			connect = db.getConnection();
			String query = "SELECT products.id,products.name, products.price, products.image, COUNT(cthd.quanlity) FROM products JOIN cthd ON\n" +
					"products.id = cthd.product_id\n" +
					"GROUP BY products.id\n" +
					"order BY COUNT(cthd.quanlity)  LIMIT 4";
			PreparedStatement ps = connect.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				Product product = new Product(result.getInt(1),
						                      result.getString(2),
						                      result.getInt(3),
						                      result.getString(4));
				OrderItem item = new OrderItem(product, result.getInt(5));
				list.add(item);
			}
			ps.close();
			connect.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}



	public static void main(String[] args) {
		OrderDAO orderDAO = new OrderDAO();
//		List<Order> order = orderDAO.getOrderByUserID(3);
//		List<OrderItem> list = orderDAO.getIDProductSellMost();
//		for (OrderItem orderItem : list) {
//			System.out.println(orderItem);
//		}
		
//		for (Order order2 : order) {
//			System.out.println(order2);
//		}
		
//		System.out.println(orderDAO.getOrderByID(15));
		
//		System.out.println(orderDAO.getUserOfOrder(15));
		
		
	}
}
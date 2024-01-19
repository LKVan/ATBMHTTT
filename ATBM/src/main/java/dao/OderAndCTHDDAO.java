package dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import context.DBContext;
import support.HashOrder;

public class OderAndCTHDDAO {
	Connection connect = null;
	PreparedStatement ps = null;
	ResultSet result = null;

	public void insertCTHD(int order_id, int product_id, int price, int quantity,String hash) {
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "INSERT INTO `cthd`(`oder_id`,`product_id`, `price`, `quanlity`,`hash`) " + "VALUES (?,?,?,?,?);";
			ps = connect.prepareStatement(query);

			ps.setInt(1, order_id);
			ps.setInt(2, product_id);
			ps.setInt(3, price);
			ps.setInt(4, quantity);
			ps.setString(5, hash);
			int numberRowUpdate = ps.executeUpdate();

			ps.close();
			connect.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int insertOder(int user_id, String note, Date orderDate, int status) {
		DBContext db = DBContext.getInstance();
		int temp = 0;
		try {
			connect = db.getConnection();
			String query = "INSERT INTO `order`(`user_id`, `note`, `orderDate`, `status`) VALUES (?,?,?,?);";
			ps = connect.prepareStatement(query);

			ps.setInt(1, user_id);
			ps.setString(2, note);
			ps.setDate(3, orderDate);
			ps.setInt(4, status);
			ps.executeUpdate();
			ps.clearParameters();

			ps = connect.prepareStatement("select `id` from `order` order by `id` desc limit 1;");
			result = ps.executeQuery();
			result.next();
			temp = result.getInt(1);

			// int numberRowUpdate = ps.executeUpdate();

			ps.close();
			connect.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return temp;
	}

	public int getOderId() {
		DBContext db = DBContext.getInstance();
		int temp = 0;
		try {
			connect = db.getConnection();
			String query = "select `id` from `order` order by `id` desc limit 1;";
			ps = connect.prepareStatement(query);

			result = ps.executeQuery();
			temp = result.getInt(1);
			System.out.println(temp);

			ps.close();
			connect.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return temp;
	}

	public static void main(String[] args) {
		 OderAndCTHDDAO test = new OderAndCTHDDAO();
		 String hash;
		try {
			hash = HashOrder.SHA(""+9+20000+1);
			test.insertCTHD(87, 9, 20000, 1, hash);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.out.println("éo được");
			e.printStackTrace();
		}
		// Long time = System.currentTimeMillis();
		// System.out.println(test.insertOder(3, "", new java.sql.Date(time),
		// 0));
		// System.out.println(test.getOderId());
	}
}
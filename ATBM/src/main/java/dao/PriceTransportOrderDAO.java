package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import context.DBContext;
import model.Address;

public class PriceTransportOrderDAO {
	Connection connect = null;
	PreparedStatement ps = null;
	ResultSet result = null;
	
	private static PriceTransportOrderDAO instance = null;
	
	private PriceTransportOrderDAO() {
		
	}
	
	public static PriceTransportOrderDAO getInstance() {
		if(instance == null) {
			instance = new PriceTransportOrderDAO();
		}
		return instance;
	}
	
	
	public int insert(int orderId,int priceTransport) {
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "INSERT INTO price_transport(order_id,price) VALUES (?,?)";
			ps = connect.prepareStatement(query);
			ps.setInt(1, orderId);
			ps.setInt(2, priceTransport);
			

			int row = ps.executeUpdate();
			ps.close();
			connect.close();
			return row;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}
	
	public int getPriceTransportOfOrder(int orderId) {
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "Select price from price_transport where order_id = ?";
			ps = connect.prepareStatement(query);
			ps.setInt(1, orderId);
			
			result = ps.executeQuery();
			while (result.next()) {
				return result.getInt(1);
			}
			ps.close();
			connect.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	

}

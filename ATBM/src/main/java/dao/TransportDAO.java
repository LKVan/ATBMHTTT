package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import context.DBContext;
import model.api.Transport;

public class TransportDAO {
	Connection connect = null;
	PreparedStatement ps = null;
	ResultSet result = null;
	
	private static TransportDAO instance = null;
	
	private TransportDAO() {
		
	}
	
	public static TransportDAO getInstance() {
		if(instance == null) {
			instance = new TransportDAO();
		}
		return instance;
	}
	
	public int insert(Transport item) {
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "INSERT INTO transport(id,order_id,product_id,fee,create_at,update_at,status) VALUES(?,?,?,?,?,?,?);";
			ps = connect.prepareStatement(query);
			
			String create = item.getCreate().getYear() +"-" +item.getCreate().getMonth() +"-"+ item.getCreate().getDay();
			String update = item.getUpdate().getYear() +"-" +item.getUpdate().getMonth() +"-"+ item.getUpdate().getDay();

			ps.setString(1, item.getId());
			ps.setInt(2, item.getOrder_id());
			ps.setInt(3, item.getProduct().getId());
			ps.setInt(4, item.getFee());
			ps.setString(5, create);
			ps.setString(6, update);
			ps.setInt(7, item.isActive() ? 1:0 );
			
			int numberRowUpdate = ps.executeUpdate();

			ps.close();
			connect.close();
			return numberRowUpdate;
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

}

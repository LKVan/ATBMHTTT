package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Product;
import model.report.ProductReport;
import model.report.RevenueReport;
import support.DateTime;

public class ProductReportDAO {
	Connection connect = null;
	PreparedStatement ps = null;
	ResultSet result = null;
	
	
	public List<ProductReport> getAllProductReport(){
		List<ProductReport> list = new ArrayList<>();	
		DBContext db = DBContext.getInstance();
		
		try {
			connect = db.getConnection();

			String query = "SELECT products.id,products.name,SUM(cthd.quanlity),SUM(cthd.price) FROM products JOIN cthd\r\n"
					+ "ON products.id = cthd.product_id\r\n"
					+ "JOIN `order` ON order.id = cthd.oder_id\r\n"
					+ "WHERE `order`.`status` = 4\r\n"
					+ "GROUP BY products.id,products.name\r\n"
					+ "UNION SELECT products.id,products.name,0,0 FROM products\r\n"
					+ "WHERE products.id NOT IN (SELECT cthd.product_id FROM cthd)\r\n"
					+ "GROUP BY products.id,products.name";
			ps = connect.prepareStatement(query);

			result = ps.executeQuery();
			while (result.next()) {
				ProductReport proRe = new ProductReport(result.getInt(1), result.getString(2), result.getInt(4),result.getInt(3));
				list.add(proRe);
			}
			result.close();
			ps.close();
			connect.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<ProductReport> getProductReportByDate(String start,String end){
		List<ProductReport> list = new ArrayList<>();	
		DBContext db = DBContext.getInstance();
		
		try {
			connect = db.getConnection();

			String query = "SELECT products.id,products.name,SUM(cthd.quanlity),SUM(cthd.price) FROM products JOIN cthd\r\n"
					+ "ON products.id = cthd.product_id\r\n"
					+ "JOIN `order` ON order.id = cthd.oder_id\r\n"
					+ "WHERE `order`.`status` = 4 AND orderDate BETWEEN ? AND ?"
					+ "GROUP BY products.id,products.name\r\n"
					+ "UNION SELECT products.id,products.name,0,0 FROM products\r\n"
					+ "WHERE products.id NOT IN (SELECT cthd.product_id FROM cthd)\r\n"
					+ "GROUP BY products.id,products.name";
			ps = connect.prepareStatement(query);
			ps.setString(1, start);
			ps.setString(2, end);

			result = ps.executeQuery();
			while (result.next()) {
				ProductReport proRe = new ProductReport(result.getInt(1), result.getString(2), result.getInt(4),result.getInt(3));
				list.add(proRe);
			}
			result.close();
			ps.close();
			connect.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<RevenueReport> getRevenueByDate(String start,String end){
		List<RevenueReport> list = new ArrayList<>();	
		DBContext db = DBContext.getInstance();
		
		try {
			connect = db.getConnection();

			String query = "SELECT `order`.orderDate,SUM(cthd.quanlity),SUM(cthd.price) FROM `order` JOIN cthd\r\n"
					+ "ON `order`.id = cthd.oder_id\r\n"
					+ "WHERE `order`.`status` = 4 AND orderDate BETWEEN ? AND ?\r\n"
					+ "GROUP BY `order`.orderDate";
			ps = connect.prepareStatement(query);
			ps.setString(1, start);
			ps.setString(2, end);

			result = ps.executeQuery();
			while (result.next()) {
				System.out.println(result.getDate(1));
				RevenueReport item = new RevenueReport(DateTime.formatDate(result.getDate(1).toString()), result.getInt(2), result.getInt(3));
				list.add(item);
			}
			result.close();
			ps.close();
			connect.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static void main(String[] args) {
		System.out.println(new ProductReportDAO().getRevenueByDate("2022-12-16","2022-12-18"));
	}

}

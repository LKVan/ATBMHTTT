package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import context.DBContext;
import model.TempComment;

public class CommentDAO {
	Connection connect = null;
	PreparedStatement ps = null;
	ResultSet result = null;

	// Thêm comment dựa vào user_id, product_id
	public void insertTempComment(int user_id, int pro_id, String name, String comment) {
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "INSERT INTO `comment`(`userId`, `proId`, username, `comment`) VALUES (?,?,?,?);";
			ps = connect.prepareStatement(query);
			ps.setInt(1, user_id);
			ps.setInt(2, pro_id);
			ps.setString(3, name);
			ps.setString(4, comment);
			int numberRowUpdate = ps.executeUpdate();
			ps.close();
			connect.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Lấy comment dựa vào user_id
	public List<TempComment> getCommentByUserId(int id) {
		List<TempComment> list = new ArrayList<>();
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "SELECT * FROM `comment` WHERE userId = ?";
			ps = connect.prepareStatement(query);
			ps.setInt(1, id);
			result = ps.executeQuery();
			while (result.next()) {
				list.add(new TempComment(result.getInt(2)
			               , result.getInt(4)
			               , result.getString(3)
			               , result.getString(5)
			               , result.getString(6)
						   , result.getString(7))) ;
			}
			result.close();
			ps.close();
			connect.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// Lấy comment dựa vào product_id
	public List<TempComment> getCommentByProductId(int productId) {
		List<TempComment> list = new ArrayList<>();
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "SELECT id,userId,username,proId,comment,time,image FROM `comment` WHERE proId = ?";
			ps = connect.prepareStatement(query);
			ps.setInt(1, productId);
			result = ps.executeQuery();
			while (result.next()) {
				list.add(new TempComment(result.getInt(2)
						               , result.getInt(4)
						               , result.getString(3)
						               , result.getString(5)
						               , result.getString(6)
						               , result.getString(7))) ;
			} 
			result.close();
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
		CommentDAO commentDAO = new CommentDAO();
//		commentDAO.insertTempComment(1, 1, "Phú", "abc");
//      commentDAO.getCommentByUserIdAndProductId(1,0);
//		List<TempComment> list = commentDAO.getCommentByUserIdAndProductId(1, 1);
	    List<TempComment> list1 = commentDAO.getCommentByUserId(18);
//		List<TempComment> list2 = commentDAO.getCommentByProductId(18);
//		List<TempComment> list3 = commentDAO.getAllComment();
		for (TempComment o : list1) {
			System.out.println(o);
		}
	}
}

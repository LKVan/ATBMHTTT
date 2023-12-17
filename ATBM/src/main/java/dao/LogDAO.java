package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Log;

public class LogDAO {
	Connection connect = null;
	PreparedStatement ps = null;
	ResultSet result = null;

	public int insert(Log log) {
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "INSERT INTO log (level, user, src, content, createAt, status) VALUES(?,?,?,?,NOW(),?)";
			ps = connect.prepareStatement(query);

			ps.setInt(1, log.getLevel());
			ps.setInt(2, log.getUserId());
			ps.setString(3, log.getContent());
			ps.setString(4, log.getSrc());

			ps.setInt(5, log.getStatus());
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
	public List<Log> getAllLog(){
		DBContext db = DBContext.getInstance();
		List<Log> list = new ArrayList<>();
		try {
			connect = db.getConnection();
			String query = "SELECT log.id,`level`,user.userName,`src`,`content`,`createAt`,log.status FROM `log` JOIN `user`\r\n"
					+ "ON log.user = user.id\r\n"
					+ "UNION\r\n"
					+ "SELECT `id`,`level`,\"Unknown\",`src`,`content`,`createAt`,log.status FROM `log` \r\n"
					+ "WHERE user = 0\r\n"
					+ "order BY createAt DESC";
			ps = connect.prepareStatement(query);
			result = ps.executeQuery();

			Log log;
			while(result.next()) {
				log = new Log(result.getInt(1),result.getInt(2), result.getString(4), result.getString(3), result.getString(5), result.getDate(6), result.getInt(7));
				list.add(log);
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

	public Log getLogById(int id) {
		DBContext db = DBContext.getInstance();
		Log log = null;
		try {
			connect = db.getConnection();
			String query = "SELECT log.id,`level`,user.userName,`src`,`content`,`createAt`,log.status FROM `log` JOIN `user`\r\n"
					+ "					ON log.user = user.id where log.id = ?\r\n"
					+ "					UNION\r\n"
					+ "					SELECT `id`,`level`,\"Unknown\",`src`,`content`,`createAt`,log.status FROM `log` \r\n"
					+ "					WHERE user = 0 and id = ?";
			ps = connect.prepareStatement(query);
			ps.setInt(1, id);
			ps.setInt(2, id);
			result = ps.executeQuery();

			while(result.next()) {
				log = new Log(result.getInt(1),result.getInt(2), result.getString(4), result.getString(3), result.getString(5), result.getDate(6), result.getInt(7));
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
		return log;
	}

	public static void main(String[] args) throws SQLException {
		LogDAO logDB = new LogDAO();
		Log log = new Log(Log.DANGER, 001, "DoMinhPhu", "abc", 0);
		logDB.insert(log);
	}
}

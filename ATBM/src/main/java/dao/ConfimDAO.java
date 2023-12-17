package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import context.DBContext;
import model.Confim;

public class ConfimDAO {
	Connection connect = null;
	PreparedStatement ps = null;
	ResultSet result = null;

	public int insert(String userName, String code) {
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "INSERT INTO confim(userName,code,dateCreate,dateEnd) VALUES (?,?,?,?)";
			ps = connect.prepareStatement(query);
			ps.setString(1, userName);
			ps.setString(2, code);
			
			//Thoi gian de nhap ma xac thuc la 5 phut = 300 000 mili giay
			Calendar calendar = Calendar.getInstance();
			Timestamp dateCreate = new Timestamp(calendar.getTimeInMillis());
			Timestamp dateEnd = new Timestamp(calendar.getTimeInMillis() + 300000);

			ps.setTimestamp(3, dateCreate);
			ps.setTimestamp(4, dateEnd);

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

	public Confim getConfimNew(String userName) {
		DBContext db = DBContext.getInstance();
		Confim confim = null;
		try {
			connect = db.getConnection();
			String query = "select *  from `confim` where username = ? order by datecreate desc limit 1";
			ps = connect.prepareStatement(query);
			ps.setString(1, userName);
			result = ps.executeQuery();

			if (result.next()) {
				confim = new Confim(result.getInt(1), result.getString(2), result.getString(3), result.getTimestamp(4),
						result.getTimestamp(5));
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
		return confim;

	}

	public static void main(String[] args) {
		Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
		Timestamp time1 = new Timestamp(Calendar.getInstance().getTimeInMillis() + 300000);

		System.out.println(time1.getTime() - time.getTime());

	}

}

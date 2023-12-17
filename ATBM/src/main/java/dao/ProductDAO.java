
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Category;
import model.OrderItem;
import model.Product;
import model.TempComment;

public class ProductDAO {

	Connection connect = null;
	PreparedStatement ps = null;
	ResultSet result = null;

	// Lấy sản phẩm theo id sản phẩm
	public Product getProductById(int id) {
		DBContext db = DBContext.getInstance();
		Product product = null;
		try {
			connect = db.getConnection();

			String query = "SELECT id,name,descreption,price,image,height,length,width,weigth,DanhMuc_id " +
					"FROM `products` WHERE products.id = ? ;";
			ps = connect.prepareStatement(query);
			ps.setInt(1, id);
			result = ps.executeQuery();
			
			while (result.next()) {
				product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getString(5), result.getInt(6),result.getInt(7),result.getInt(8),result.getInt(9),result.getInt(10));
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
		return product;

	}

	// Lấy sản phẩm theo trạng thái
	public List<Product> getProductSell(int status) {
		List<Product> list = new ArrayList<>();
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "SELECT id,name,descreption,price,image,height,length,width,weigth,DanhMuc_id " +
					"FROM `products`where status = ? ;";
			ps = connect.prepareStatement(query);
			ps.setInt(1, status);
			result = ps.executeQuery();
			while (result.next()) {
				Product product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getString(5), result.getInt(6),result.getInt(7),result.getInt(8),result.getInt(9),result.getInt(10));
				list.add(product);
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

	// Lay tat ca san pham
	public List<Product> getAllProduct(int status) {
		List<Product> list = new ArrayList<>();
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "SELECT id,name,descreption,price,image,height,length,width,weigth,DanhMuc_id FROM `products` where status = ?;";
			ps = connect.prepareStatement(query);
			ps.setInt(1, status);
			result = ps.executeQuery();
			while (result.next()) {
				Product product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getString(5), result.getInt(6),result.getInt(7),result.getInt(8),result.getInt(9),result.getInt(10));
				list.add(product);
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

	// Lay tat ca san pham
	public List<Product> getAllProduct() {
		List<Product> list = new ArrayList<>();
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "SELECT id,name,descreption,price,image,height,length,width,weigth,DanhMuc_id FROM `products`;";
			ps = connect.prepareStatement(query);
			result = ps.executeQuery();
			while (result.next()) {
				Product product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getString(5), result.getInt(6),result.getInt(7),result.getInt(8),result.getInt(9),result.getInt(10));
				list.add(product);
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

	// Lay theo danh muc
	public List<Product> getProductByCategory(int catId,int status) {
		List<Product> list = new ArrayList<>();
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "SELECT id,name,descreption,price,image,height,length,width,weigth,DanhMuc_id FROM products WHERE products.DanhMuc_id = ? and products.status = ?";
			ps = connect.prepareStatement(query);
			ps.setInt(1, catId);
			ps.setInt(2, status);
			result = ps.executeQuery();
			while (result.next()) {
				Product product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getString(5), result.getInt(6),result.getInt(7),result.getInt(8),result.getInt(9),result.getInt(10));
				list.add(product);
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

	// Lay ra san pham ban chay
//	public List<Product> getProductMostSell() {
//		List<Product> list = new ArrayList<>();
//		DBContext db = DBContext.getInstance();
//		try {
//			connect = db.getConnection();
//			String query = "SELECT * FROM `products`;";
//			ps = connect.prepareStatement(query);
//			result = ps.executeQuery();
//			while (result.next()) {
//				Product product = new Product(result.getInt(1), result.getString(2), result.getString(3),
//						result.getInt(4), result.getString(5), result.getInt(6));
//				list.add(product);
//			}
//			ps.close();
//			connect.close();
//
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return list;
//	}

	// Lay ra san pham moi
	public List<Product> getNewProduct(int status) {
		List<Product> list = new ArrayList<>();
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "SELECT * FROM `products` where products.status = ? ORDER BY id DESC LIMIT 4;";
			ps = connect.prepareStatement(query);
			ps.setInt(1, status);
			result = ps.executeQuery();
			while (result.next()) {
				Product product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getString(5), result.getInt(6),result.getInt(7),result.getInt(8),result.getInt(9),result.getInt(10));
				list.add(product);
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

	// Lay san pham theo ten
	public List<Product> getProductByName(String name,int status) {
		List<Product> list = new ArrayList<>();
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "SELECT id,name,descreption,price,image,height,length,width,weigth,DanhMuc_id FROM products WHERE products.name Like ? and products.status = ?";
			ps = connect.prepareStatement(query);
			ps.setString(1, "%" + name + "%");
			ps.setInt(2, status);
			result = ps.executeQuery();
			while (result.next()) {
				Product product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getString(5), result.getInt(6),result.getInt(7),result.getInt(8),result.getInt(9),result.getInt(10));
				list.add(product);
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
	

	public int update(Product product) {
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "UPDATE products SET products.name = ?,products.descreption = ?,products.price = ?,products.image =?,products.DanhMuc_id = ?,products.height = ?, products.length = ?,products.width = ?,products.weigth = ? WHERE id = ?;";
			ps = connect.prepareStatement(query);

			ps.setString(1, product.getName());
			ps.setString(2, product.getDescreption());
			ps.setInt(3, product.getPrice());
			ps.setString(4, product.getImage());
			ps.setInt(5, product.getCatId());
			ps.setInt(6, product.getHeight());
			ps.setInt(7, product.getLength());
			ps.setInt(8, product.getWidth());
			ps.setInt(9, product.getWeigth());
			ps.setInt(10, product.getId());

			int numberRowUpdate = ps.executeUpdate();
			
			System.out.println("Row:" +numberRowUpdate);

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

	public int changeStatus(int id, int status) {
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "UPDATE products SET status = ? WHERE id = ?;";
			ps = connect.prepareStatement(query);
			ps.setInt(1, status);
			ps.setInt(2, id);

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

	public int delete(int id) {
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "Delete from products where id = ?;";
			ps = connect.prepareStatement(query);

			ps.setInt(1, id);
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

	public int insert(String name, String des, int price, String image, int catId,int height,int length,int width,int weigth) {
		DBContext db = DBContext.getInstance();
		try {
			connect = db.getConnection();
			String query = "INSERT INTO products(name,descreption,price,image,DanhMuc_id,status,height,length,width,weigth) VALUES(?,?,?,?,?,0,?,?,?,?);";
			ps = connect.prepareStatement(query);

			ps.setString(1, name);
			ps.setString(2, des);
			ps.setInt(3, price);
			ps.setString(4, image);
			ps.setInt(5, catId);
			ps.setInt(6, height);
			ps.setInt(7, length);
			ps.setInt(8, width);
			ps.setInt(9, weigth);
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

    // Lấy ra tổng số sản phẩm
	public int getTotalProduct(int status) {
		DBContext db = DBContext.getInstance();
		String query = "select COUNT(*) from products where products.status = ?";
		try {
			connect = db.getConnection();
			ps = connect.prepareStatement(query);
			ps.setInt(1, status);
			result = ps.executeQuery();
			while (result.next()) {
				return result.getInt(1);
			}
			result.close();
			ps.close();
			connect.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;

	}
    // Lấy ra tổng số thức ăn
	public int getTotalFood(int status ) {
		DBContext db = DBContext.getInstance();
		String query = "select COUNT(*) from products WHERE products.DanhMuc_id = 3 and products.status = ?";
		try {
			connect = db.getConnection();
			ps = connect.prepareStatement(query);
			ps.setInt(1, status);
			result = ps.executeQuery();
			while (result.next()) {
				return result.getInt(1);
			}
			result.close();
			ps.close();
			connect.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;

	}
	// Lấy ra tổng số thức uống
	public int getTotalDrink(int status) {
		DBContext db = DBContext.getInstance();
		String query = "select COUNT(*) from products WHERE products.DanhMuc_id = 2 and product.status = ?";
		try {
			connect = db.getConnection();
			ps = connect.prepareStatement(query);
			ps.setInt(1, status);
			result = ps.executeQuery();
			while (result.next()) {
				return result.getInt(1);
			}
			result.close();
			ps.close();
			connect.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;

	}
	// Lấy ra tổng số bánh ngọt
	public int getTotalCake(int status) {
		DBContext db = DBContext.getInstance();
		String query = "select COUNT(*) from products WHERE products.DanhMuc_id = 1 and products.status = ?";
		try {
			connect = db.getConnection();
			ps = connect.prepareStatement(query);
			ps.setInt(1, status);
			result = ps.executeQuery();
			while (result.next()) {
				return result.getInt(1);
			}
			result.close();
			ps.close();
			connect.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;

	}

	public List<Product> pagingProduct(int index,int status) {
		List<Product> list = new ArrayList<>();
		DBContext db = DBContext.getInstance();
		String query = "SELECT id,name,descreption,price,image,height,length,width,weigth,DanhMuc_id FROM products where products.status = ? LIMIT ?,8;";
		try {
			connect = db.getConnection();
			ps = connect.prepareStatement(query);
			ps.setInt(1, status);
			ps.setInt(2, (index - 1) * 8);
			result = ps.executeQuery();
			while (result.next()) {
				list.add(new Product(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getString(5), result.getInt(6),result.getInt(7),result.getInt(8),result.getInt(9),result.getInt(10)));
			}
			result.close();
			ps.close();
			connect.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public List<Product> pagingFood(int index) {
		List<Product> list = new ArrayList<>();
		DBContext db = DBContext.getInstance();
		String query = "SELECT id,name,descreption,price,image,height,length,width,weigth,DanhMuc_id FROM products WHERE products.DanhMuc_id = 3 and products.status = 0  LIMIT ?,12";
		try {
			connect = db.getConnection();
			ps = connect.prepareStatement(query);
			ps.setInt(1, (index - 1) * 12);
			result = ps.executeQuery();
			while (result.next()) {
				list.add(new Product(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getString(5), result.getInt(6),result.getInt(7),result.getInt(8),result.getInt(9),result.getInt(10)));
			}
			result.close();
			ps.close();
			connect.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public List<Product> pagingDrink(int index) {
		List<Product> list = new ArrayList<>();
		DBContext db = DBContext.getInstance();
		String query = "SELECT id,name,descreption,price,image,height,length,width,weigth,DanhMuc_id FROM products WHERE products.DanhMuc_id = 2 and products.status = 0  LIMIT ?,12";
		try {
			connect = db.getConnection();
			ps = connect.prepareStatement(query);
			ps.setInt(1, (index - 1) * 12);
			result = ps.executeQuery();
			while (result.next()) {
				list.add(new Product(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getString(5), result.getInt(6),result.getInt(7),result.getInt(8),result.getInt(9),result.getInt(10)));
			}
			result.close();
			ps.close();
			connect.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public List<Product> pagingCake(int index) {
		List<Product> list = new ArrayList<>();
		DBContext db = DBContext.getInstance();
		String query = "SELECT id,name,descreption,price,image,height,length,width,weigth,DanhMuc_id FROM products WHERE products.DanhMuc_id = 1 and products.status = 0  LIMIT ?,12";
		try {
			connect = db.getConnection();
			ps = connect.prepareStatement(query);
			ps.setInt(1, (index - 1) * 12);
			result = ps.executeQuery();
			while (result.next()) {
				list.add(new Product(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getString(5), result.getInt(6),result.getInt(7),result.getInt(8),result.getInt(9),result.getInt(10)));
			}
			result.close();
			ps.close();
			connect.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	// Lấy ra top 8 sản phẩm
	public List<Product> getTop8Product() {
		List<Product> list = new ArrayList<>();
		DBContext db = DBContext.getInstance();
		String query = "SELECT id,name,descreption,price,image,height,length,width,weigth,DanhMuc_id FROM products WHERE  products.status = 0 LIMIT 8";
		try {
			connect = db.getConnection();
			ps = connect.prepareStatement(query);
			result = ps.executeQuery();
			while (result.next()) {
				list.add(new Product(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getString(5), result.getInt(6),result.getInt(7),result.getInt(8),result.getInt(9),result.getInt(10)));
			}
			result.close();
			ps.close();
			connect.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	// Lấy ra 4 sản phẩm tiếp theo trong danh sách sản phẩm
	public List<Product> getNext4Product(int amount) {
		List<Product> list = new ArrayList<>();
		DBContext db = DBContext.getInstance();
		String query = "SELECT id,name,descreption,price,image,height,length,width,weigth,DanhMuc_id FROM products WHERE  products.status = 0 LIMIT ?,4";
		try {
			connect = db.getConnection();
			ps = connect.prepareStatement(query);		
			ps.setInt(1, amount);
			result = ps.executeQuery();
			while (result.next()) {
				list.add(new Product(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getString(5), result.getInt(6),result.getInt(7),result.getInt(8),result.getInt(9),result.getInt(10)));
			}
			result.close();
			ps.close();
			connect.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}


	public static void main(String[] args) {
		ProductDAO productDAO = new ProductDAO();
		List<Product> list2 = productDAO.getNewProduct(0);
		List<Product> list3= productDAO.getAllProduct(0);
		for (Product o : list2) {
			System.out.println(o);
		}
	}


}
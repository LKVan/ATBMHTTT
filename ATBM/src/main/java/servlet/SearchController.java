package servlet;

//import bean.Log;
//import dao.DB;
import dao.OrderDAO;
import dao.ProductDAO;
import dao.UserDAO;
import model.OrderItem;
import model.Product;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		ProductDAO productDAO = new ProductDAO();

		//Dữ liệu người dùng
		UserDAO userDAO = new UserDAO();
		OrderDAO orderDAO = new OrderDAO();
		HttpSession session = request.getSession(true);
		String userName = request.getParameter("userName");
		User user = userDAO.getUser(userName);

		String txtSearch = request.getParameter("txt");
		String result = "";
		
		List<Product> listProductNew = productDAO.getNewProduct(0);
		List<OrderItem> listProductSellMost = orderDAO.getProductSellMost();
		List<Product> list = productDAO.getProductByName(txtSearch, 0);

        //Log hệ thống search
//		Log log = new Log(Log.INFO, -1,"" ,"", 0);

		if (list.size() > 0) {
			result = "Kết quả tìm kiếm cho sản phẩm '" + txtSearch + "'";
//			log.setSrc("Trang Chủ");
//			log.setContent("Tìm kiếm "+ txtSearch);
//			log.setLevel(Log.INFO);
		}else {
			result = "Không tìm thấy sản phẩm nào cho '" + txtSearch + "'";
//			log.setSrc("Trang Chủ");
//			log.setContent("Tìm kiếm không có kết quả nào cho "+txtSearch);
//			log.setLevel(Log.ALERT);
		}
//		DB.me().insert(log);
		
		request.setAttribute("listProductNew", listProductNew);
		request.setAttribute("listProductSellMost", listProductSellMost);
		request.setAttribute("maintitle", result);
		request.setAttribute("ListAllProduct", list);
		request.setAttribute("txts", txtSearch);
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

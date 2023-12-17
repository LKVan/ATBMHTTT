package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import contanst.MyAddress;
import dao.LogDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Location;
import model.Log;
import model.OrderItem;
import model.Product;

@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProductDAO productDAO = new ProductDAO();
		OrderDAO orderDAO = new OrderDAO();
		LogDAO logDB = new LogDAO();
		HttpSession session = request.getSession(true);
		session.setAttribute("message", null);
		List<Product> listProductNew = productDAO.getNewProduct(0);
		List<OrderItem> listProductSellMost = orderDAO.getProductSellMost();
		List<Product> list = productDAO.getTop8Product();		
		
		Location location = new Location(MyAddress.WARD, MyAddress.DISTRIST, MyAddress.PROVINCE);
		session.setAttribute("shopLocation", location);
		request.setAttribute("listProductNew", listProductNew);
		request.setAttribute("listProductSellMost", listProductSellMost);

		request.setAttribute("maintitle", "Tất cả sản phẩm");
		request.setAttribute("ListAllProduct", list);
		request.getRequestDispatcher("home.jsp").forward(request, response);
//		logDB.insert(new Log(Log.INFO, 0, getServletName(), getServletInfo(), 0));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

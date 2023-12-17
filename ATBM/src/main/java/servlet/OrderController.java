package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDAO;
import model.Order;
import model.User;

@WebServlet("/orderUser")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		OrderDAO orderDAO = new OrderDAO();

		User user = (User) request.getSession().getAttribute("user");
		String action = request.getParameter("action");
		
		if(action == null) {
			List<Order> listOrder = orderDAO.getOrderByUserID(user.getId());
			System.out.println(listOrder.toString());
			request.setAttribute("listOrder", listOrder);
			request.getRequestDispatcher("/order.jsp").forward(request, response);
		}else {
			if(action.trim().equals("detail")) {
				String orderId = request.getParameter("orderId");
				Order order = orderDAO.getOrderByID(Integer.parseInt(orderId));
				request.setAttribute("order", order);
				request.getRequestDispatcher("/orderDetail.jsp").forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
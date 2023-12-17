package admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import contanst.TransportStatus;
import dao.OrderDAO;
import dao.PriceTransportOrderDAO;
import model.Order;

/**
 * Servlet implementation class ManagerOrder
 */
@WebServlet("/order")
public class ManagerOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagerOrder() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		OrderDAO orderDAO = new OrderDAO();
		List<Order> listOrder;

		if (action != null) {
			if (action.trim().equals("accept")) {
				listOrder = orderDAO.getOrderByStatus(TransportStatus.ACCEPT); // Status = 1 la cho xac nhan
				request.setAttribute("listOrder", listOrder);
				request.getRequestDispatcher("/admin/orderAccept.jsp").forward(request, response);

			} else if (action.trim().equals("wating")) {				
				listOrder = orderDAO.getOrderByStatus(TransportStatus.WATTING_MOVE);// Status = 2 la cho van chuyen
				request.setAttribute("listOrder", listOrder);
				request.getRequestDispatcher("/admin/orderWatting.jsp").forward(request, response);
			} else if (action.trim().equals("move")) {
				listOrder = orderDAO.getOrderByStatus(TransportStatus.MOVE);// Status = 3 la dang van chuyen
				request.setAttribute("listOrder", listOrder);
				request.getRequestDispatcher("/admin/orderMoving.jsp").forward(request, response);
			}
			else if (action.trim().equals("finish")) {
				listOrder = orderDAO.getOrderByStatus(TransportStatus.FISNISH);// Status = 4 la da giao thanh cong
				request.setAttribute("listOrder", listOrder);
				request.getRequestDispatcher("/admin/orderFinish.jsp").forward(request, response);
			}else if (action.trim().equals("destroy")) {
				listOrder = orderDAO.getOrderByStatus(TransportStatus.CANCEL);// Status = 0 don bi huy
				request.setAttribute("listOrder", listOrder);
				request.getRequestDispatcher("/admin/orderTrash.jsp").forward(request, response);
			}else if(action.trim().equals("detail")) { //Xem thong tin don hang
				String orderId = request.getParameter("orderId");
				Order order = orderDAO.getOrderByID(Integer.parseInt(orderId));
				String previous = request.getParameter("previous");
				PriceTransportOrderDAO priceTransportOrderDAO = PriceTransportOrderDAO.getInstance();
				int priceTransport = priceTransportOrderDAO.getPriceTransportOfOrder(Integer.parseInt(orderId));
				
				request.setAttribute("order", order);
				request.setAttribute("priceTransport", priceTransport);
				request.setAttribute("previous", previous);

				request.getRequestDispatcher("/admin/detailOrder.jsp").forward(request, response);			
			}
			else {
				response.sendRedirect(request.getContextPath()+"/404.html");
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

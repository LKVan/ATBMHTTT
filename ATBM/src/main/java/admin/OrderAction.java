//package admin;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import api.LogicticAPI;
//import bean.Log;
//import contanst.Status;
//import dao.DB;
//import dao.OrderDAO;
//import dao.TransportDAO;
//import model.Location;
//import model.Order;
//import model.OrderItem;
//import model.Product;
//import model.User;
//import model.api.Transport;
//import support.SendEmail;
//
///**
// * Servlet implementation class OrderAction
// */
//@WebServlet("/oderAction")
//public class OrderAction extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * @see HttpServlet#HttpServlet()
//	 */
//	public OrderAction() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String action = request.getParameter("action");
//		OrderDAO orderDAO = new OrderDAO();
//		SendEmail mail = new SendEmail();
//		User userCurrent =(User) request.getSession().getAttribute("user");
//		Log log = new Log(Log.INFO, -1,"" ,"", 0);
//
//		if (action != null) {
//			if (action.trim().equals("confim")) {
//				String orderId = request.getParameter("orderID");
//				orderDAO.changeStatusOrder(Integer.parseInt(orderId), 2);
//				Order order = orderDAO.getOrderByID(Integer.parseInt(orderId));
//				String subject = "Shop Đồ Ăn Vặt Handmade";
//
//				String listProduct = "";
//				int count = 1;
//
//				for(OrderItem item:order.getListOrderItem()) {
//					listProduct+= "<p>"+count +" " + item.getProduct().getName() +". Số lượng:" + item.getQuanlity() +". </p>";
//					count++;
//				}
//
//				String content = "<p><b>Dear: </b> " + order.getUserName() +"</p>"
//									+ "<p><b>Đơn hàng (ID order):</b>" + order.getOrderId() + "</p><p><b>Sản phẩm : </b></p>"
//						+ listProduct + " <p>đã được xác nhận.</p>"
//						+ "<p>Đơn hàng sẽ được giao đến quý khách một ngày sớm nhất.\n\nCảm ơn quý khách đã tin tưởng!. <3 </p>";
//
//				mail.sendEmail(orderDAO.getUserOfOrder(order.getOrderId()).getEmail(), subject, content);
//
//
//
//				log.setSrc("Trang Manager Order");
//				log.setContent(userCurrent.getUserName() +" đã xác nhận đơn hàng #" + orderId);
//				DB.me().insert(log);
//
//				request.setAttribute("access", "yes");
//				request.getRequestDispatcher("/order?action=accept").forward(request, response);
//			} else if (action.trim().equals("delete")) {
//				String orderId = request.getParameter("orderID");
//				orderDAO.changeStatusOrder(Integer.parseInt(orderId), 0);
//
//				log.setLevel(Log.DANGER);
//				log.setSrc("Trang Manager Order");
//				log.setContent(userCurrent.getUserName() +" đã xoá đơn hàng #" + orderId);
//				DB.me().insert(log);
//
//				request.setAttribute("delete", "yes");
//				request.getRequestDispatcher("/order?action=accept").forward(request, response);
//			} else if (action.trim().equals("ship")) {
//				String orderId = request.getParameter("orderID");
//				HttpSession session = request.getSession();
//				LogicticAPI logictic = LogicticAPI.getInstance();
//				TransportDAO transportDAO = TransportDAO.getInstance();
//				Location location = (Location)session.getAttribute("shopLocation");
//
//
//				Order order = orderDAO.getOrderByID(Integer.parseInt(orderId));
//				User user = orderDAO.getUserOfOrder(Integer.parseInt(orderId));
//
//				String address = user.getAddress();
//				String[] splitAddress = address.split(",");
//
//				String shopProvinceId = logictic.getProvinceByName(location.getProvince()).getId();
//				String shopDistristId = logictic.getDistrictByName(location.getDistrist(), shopProvinceId).getId();
//				String shopWardId = logictic.getWardByName(location.getWard(), shopDistristId).getId();
//
//				String userProvinceId = logictic.getProvinceByName(splitAddress[3]).getId();
//				String userDistristId = logictic.getDistrictByName(splitAddress[2], userProvinceId).getId();
//				String userWardId = logictic.getWardByName(splitAddress[1], userDistristId).getId();
//
//
//				boolean check = true;
//
//				for (OrderItem item : order.getListOrderItem()) {
//					Product product = item.getProduct();
//					Transport transport = logictic.registerTransport(shopDistristId, shopWardId, userDistristId, userWardId,product.getHeight(),product.getLength(), product.getWidth(), product.getWeigth());
//					if(transport == null){
//						check = false;
//						break;
//					}else {
//						transport.setOrder_id(order.getOrderId());
//						transport.setProduct(product);
//						transportDAO.insert(transport);
//					}
//				}
//
//				if(!check){
//
//					log.setLevel(Log.WARNING);
//					log.setSrc("Trang Manager Order");
//					log.setContent(userCurrent.getUserName() +"  xác nhận giao cho vận chuyển đơn hàng #" + orderId + "thất bại");
//
//					request.setAttribute("error", "yes");
//					request.getRequestDispatcher("/order?action=wating").forward(request, response);
//				}else {
//					orderDAO.changeStatusOrder(Integer.parseInt(orderId), 3);
//
//					log.setLevel(Log.ALERT);
//					log.setSrc("Trang Manager Order");
//					log.setContent(userCurrent.getUserName() +" đã xác nhận giao cho vận chuyển đơn hàng #" + orderId);
//
//
//					request.setAttribute("access", "yes");
//					request.getRequestDispatcher("/order?action=wating").forward(request, response);
//
//				}
//				DB.me().insert(log);
//
//			} else if (action.trim().equals("destroy")) {
//				String orderId = request.getParameter("orderID");
//				orderDAO.delete(Integer.parseInt(orderId));
//				request.setAttribute("access", "yes");
//				request.getRequestDispatcher("/order?action=destroy").forward(request, response);
//			}
//		}
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}

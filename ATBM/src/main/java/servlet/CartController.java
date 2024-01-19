package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

//import api.LogicticAPI;
import dao.OderAndCTHDDAO;
import dao.PriceTransportOrderDAO;
import dao.CartDAO;
import dao.ProductDAO;
import model.Cart;
import model.CartItem;
import model.Location;
import model.Product;
import model.TempCart;
import model.User;
import support.CKDT;
import support.HashOrder;

@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		String iAction = request.getParameter("action");
		HttpSession session = request.getSession();
		Object objUser = session.getAttribute("user");
		String signature = request.getParameter("signature");
		Map<String, String> message = new HashMap<>();
		// LogicticAPI logictic = LogicticAPI.getInstance();
		System.out.println(iAction);

		if (iAction != null && !iAction.equals("")) {
			// Xoa mot san pham khoi gio
			if (iAction.equals("delete")) {
				deleteProduct(request);
				response.sendRedirect("cart.jsp");
			}

			// Bam submit Dat hang, luu CTHD dong thoi xoa cart
			else if (iAction.equals("oder")) {
				User user = (User) objUser;
				if (user.getPublicKey() != null) {
					if (CKDT.verifySignature(signature, user.getPublicKey(), user.getSignatureData())) {
						try {
							addToCTHDandOder(request, response);
						} catch (NoSuchAlgorithmException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						deleteCart(request);
						message.put("ok", "Thành công");
						session.setAttribute("message", message);
						response.sendRedirect("cart.jsp");
					} else {
						message.put("signatureError", "Sai chữ kí");
						session.setAttribute("message", message);
						request.getRequestDispatcher("/submitCart.jsp").forward(request, response);
					}
				} else {
					message.put("signatureError", "Key của bạn đã bị vô hiệu hóa");
					session.setAttribute("message", message);
					request.getRequestDispatcher("/submitCart.jsp").forward(request, response);

				}
			}

			// Hien thi Cart cua nguoi dung
			else if (iAction.equals("usercart")) {
				reCart(request);
				response.sendRedirect("cart.jsp");
			}

			// Update so luong va tong gia khi nguoi dung thao tac
			else if (iAction.equals("update")) {
				updateCart(request);
				response.sendRedirect("cart.jsp");
			} else if (iAction.equals("submit")) {
				Cart cart = (Cart) session.getAttribute("cart");
				User user = (User) session.getAttribute("user");
				Location location = (Location) session.getAttribute("shopLocation");

				System.out.println("Cart:" + cart);
				System.out.println("User:" + user);

				if (cart != null && user != null) {
					String address = user.getAddress();
					String[] splitAddress = address.split(",");
					int priceTransport = 0;

//					String shopProvinceId = logictic.getProvinceByName(location.getProvince()).getId();
//					String shopDistristId = logictic.getDistrictByName(location.getDistrist(), shopProvinceId).getId();
//					String shopWardId = logictic.getWardByName(location.getWard(), shopDistristId).getId();
//					
//					String userProvinceId = logictic.getProvinceByName(splitAddress[3]).getId();
//					String userDistristId = logictic.getDistrictByName(splitAddress[2], userProvinceId).getId();
//					String userWardId = logictic.getWardByName(splitAddress[1], userDistristId).getId();
//					
//					for (CartItem item : cart.getList()) {
//						Product product = item.getProduct();
//						priceTransport += logictic.getPrice(shopDistristId, shopWardId, userDistristId, userWardId,product.getHeight(),product.getLength(), product.getWidth(), product.getWeigth()) * item.getQuantity();						
//					}
					Product product = cart.getList().get(0).getProduct();

					request.setAttribute("priceTransport", priceTransport);
//					request.setAttribute("dateSend", logictic.getDateSend(shopDistristId, shopWardId, userDistristId, userWardId, product.getHeight(),product.getLength(), product.getWidth(), product.getWeigth()));
					System.out.println(priceTransport);
//					response.sendRedirect("submitCart.jsp");
					request.getRequestDispatcher("/submitCart.jsp").forward(request, response);
				} else {
					response.sendRedirect("404.html");
				}

			}
		} else {
			if (objUser != null) {
				reCart(request);
				addToCart(request);
				response.sendRedirect("cart.jsp");
			} else {
				response.sendRedirect("login.jsp");
			}
		}
	}

	protected void addToCTHDandOder(HttpServletRequest request, HttpServletResponse response) throws IOException, NoSuchAlgorithmException {
		HttpSession session = request.getSession();
		OderAndCTHDDAO db = new OderAndCTHDDAO();
		PriceTransportOrderDAO priceTransportOrderDAO = PriceTransportOrderDAO.getInstance();
		int price_Transport = Integer.parseInt(request.getParameter("priceTransport"));

		User user = null;
		CartDAO cartdb = new CartDAO();
		ProductDAO pd = new ProductDAO();

		Object objUser = session.getAttribute("user");

		if (objUser != null) {
			user = (User) objUser;
		}
		Cart cartBean = null;

		Object iObject = session.getAttribute("cart");

		if (iObject != null) {
			cartBean = (Cart) iObject;
		} else {
			cartBean = new Cart();
		}

		List<TempCart> items = cartdb.getCartByUserId(user.getId());

		Long time = System.currentTimeMillis();
		int temp = db.insertOder(user.getId(), "", new java.sql.Date(time), 1);

		priceTransportOrderDAO.insert(temp, price_Transport);
		for (TempCart item : items) {
			int proId = item.getProId();
			Product product = pd.getProductById(proId);
			int quantity = item.getQuantity();
			int price = product.getPrice();
			String hash = HashOrder.SHA("" + proId + price + quantity);

			db.insertCTHD(temp, proId, price, quantity,hash);
		}
	}

	// Xoa cart khi nhan dat hang thanh cong
	protected void deleteCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		CartDAO cartdb = new CartDAO();
		Cart cartBean = null;
		Object iObject = session.getAttribute("cart");

		if (iObject != null) {
			cartBean = (Cart) iObject;
		} else {
			cartBean = new Cart();
		}

		User user = null;
		Object objUser = session.getAttribute("user");

		if (objUser != null) {
			user = (User) objUser;
		}
		cartBean.deleteCart();
		cartdb.deleteCart(user.getId());
	}

	// Xoa san pham khoi gio
	private void deleteProduct(HttpServletRequest request) {
		HttpSession session = request.getSession();
		CartDAO cartdb = new CartDAO();
		Cart cartBean = null;

		Object iObject = session.getAttribute("cart");

		String iSTT = request.getParameter("stt");
		String pro_id = request.getParameter("cart_pro_id");

		if (iObject != null) {
			cartBean = (Cart) iObject;
		} else {
			cartBean = new Cart();
		}
		User user = null;

		Object objUser = session.getAttribute("user");

		if (objUser != null) {
			user = (User) objUser;
		}
		System.out.println(iSTT);
		cartBean.deleteProduct(iSTT);
		cartdb.deleteProduct(user.getId(), Integer.parseInt(pro_id));
	}

	protected void addToCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		CartDAO cartdb = new CartDAO();

		String pro_id = request.getParameter("proId");

		ProductDAO pd = new ProductDAO();
		Product product = pd.getProductById(Integer.parseInt(pro_id));
		System.out.println(pro_id);
		String iname = product.getName();
		String iimage = product.getImage();
		String idescription = product.getDescreption();
		int iPrice = product.getPrice();
		String iQuantity = request.getParameter("inputQuantity");

		Cart cartBean = null;

		User user = null;

		Object objCartBean = session.getAttribute("cart");

		Object objUser = session.getAttribute("user");

		if (objUser != null) {
			user = (User) objUser;
		}
		if (objCartBean != null) {
			cartBean = (Cart) objCartBean;

		} else {
			cartBean = new Cart();
			session.setAttribute("cart", cartBean);
		}
		// Old addCart
//		cartBean.addCart(pro_id, iname, iimage, idescription, iPrice, iQuantity);

		// new AddCart
		cartBean.addCart(product, iQuantity);
		if (cartdb.getCartByUserIdAndProductId(user.getId(), Integer.parseInt(pro_id)).size() > 0) {
			cartdb.updateTempcart(user.getId(), Integer.parseInt(pro_id), Integer.parseInt(iQuantity));
		} else {
			cartdb.insertTempcart(user.getId(), Integer.parseInt(pro_id), Integer.parseInt(iQuantity));
		}
	}

	protected void reCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		CartDAO cartdb = new CartDAO();
		ProductDAO pd = new ProductDAO();

		Cart cartBean = null;
		User user = null;

		Object objUser = session.getAttribute("user");

		Object objCartBean = session.getAttribute("cart");

		if (objUser != null) {
			user = (User) objUser;
		} else {
			System.out.println("ncc");
		}
		if (objCartBean != null) {
			objCartBean = null;
			cartBean = new Cart();
			session.setAttribute("cart", cartBean);
		} else {
			cartBean = new Cart();
			session.setAttribute("cart", cartBean);
		}
		List<TempCart> list = cartdb.getCartByUserId(user.getId());
		for (TempCart tempCart : list) {
			Product product = pd.getProductById(tempCart.getProId());
//			String ipro_id = tempCart.getProId() + "";
//			String iname = product.getName();
//			String iimage = product.getImage();
//			String idescription = product.getDescreption();
//			int iPrice = product.getPrice();
			String iQuantity = "" + tempCart.getQuantity();
			// Old AddCart
//			cartBean.addCart(ipro_id, iname, iimage, idescription, iPrice, iQuantity);

			// newAddCart
			cartBean.addCart(product, iQuantity);
		}
	}

	protected void updateCart(HttpServletRequest request) {
		HttpSession session = request.getSession();

		CartDAO db = new CartDAO();
		User user = null;
		String pro_id = request.getParameter("cart_pro_id");
		Object objUser = session.getAttribute("user");

		if (objUser != null) {
			user = (User) objUser;
		}

		String iQuantity = request.getParameter("quan");
		String iSTT = request.getParameter("stt");

		Cart cartBean = null;

		Object objCartBean = session.getAttribute("cart");
		if (objCartBean != null) {
			cartBean = (Cart) objCartBean;
		} else {
			cartBean = new Cart();
		}
		int status = Integer.parseInt(iQuantity);// Sua loi
		// cartBean.updateCart(iSTT, iQuantity);
		// db.updateTempcart(user.getId(), Integer.parseInt(pro_id),
		// Integer.parseInt(iQuantity));
		int quanlity = cartBean.updateQuanlity(iSTT, status);
		// Kiem tra xem neu so luong = 0 thi xoa khoi gio hang
		if (quanlity == 0) {
			db.deleteProduct(user.getId(), Integer.parseInt(pro_id));
		} else
			db.updateTempcart(user.getId(), Integer.parseInt(pro_id), quanlity);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
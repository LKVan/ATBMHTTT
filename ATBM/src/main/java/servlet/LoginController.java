package servlet;

//import bean.Log;
import contanst.MyAddress;
import contanst.Role;
import contanst.Status;
//import dao.DB;
import dao.UserDAO;
import model.Location;
import model.User;
import support.Endcoding;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO userDAO = new UserDAO();
		HttpSession session = request.getSession(true);
		Map<String, String> message = new HashMap<>();

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String logOut = request.getParameter("logOut");
		String error = request.getParameter("error");
		String access = request.getParameter("access");

		//Lay ra User co status = 0 la con quyen duoc vao he thong
		User user = userDAO.getUser(userName,Status.ACTIVE);
		User userTrash = userDAO.getUser(userName, Status.ENABLE);

		System.out.println(userName);
		request.setAttribute("message", message);

        //Ghi log hệ thống
//		Log log = new Log(Log.INFO, -1,"" ,"", 0);

		if (logOut != null) {
//			request.removeAttribute("message");
//			session.removeAttribute("user");
			session.invalidate();
			response.sendRedirect("HomeController");
            //Cấp phép user

		} else if (error != null) {
			request.setAttribute("error", error);
			request.getRequestDispatcher("/login.jsp").forward(request, response);

		} else if (access != null) {
			request.setAttribute("access","Đổi mật khẩu thành công!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			// Logging chức năng đổi mật khẩu
//			log.setUserId(user.getId());
//			log.setSrc("Trang Login");
//			log.setContent(user.getUserName() + " Đổi mật khẩu thành công");
//			log.setLevel(Log.WARNING);
		} else if (user != null) {
			System.out.println(user);
			password = Endcoding.encrypt(password);
			if (user.getPassword().equals(password)) {
				//Tạo địa chỉ mặc định của shop lưu vào session
				Location location = new Location(MyAddress.WARD, MyAddress.DISTRIST, MyAddress.PROVINCE);
				session.setAttribute("shopLocation", location);

				session.setAttribute("user", user);
				CartController cart = new CartController();
				cart.reCart(request);
				session.setMaxInactiveInterval(5000);
				if (user.getRolId() == Role.ADMIN)
					// request.getRequestDispatcher("/admin/adminHeader.jsp").forward(request,
					// response);
					response.sendRedirect(request.getContextPath()+"/admin");
				else
					response.sendRedirect("HomeController");
				    //Logging user đăng nhập
//				    log.setUserId(user.getId());
//				    log.setSrc("Trang Login");
//					log.setContent(user.getUserName() + " Đăng nhập thành công");
//					log.setLevel(Log.INFO);
			} else {
				message.put("passwordError", "Sai Mật Khẩu");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
                //Logging user đăng nhập sai mật khẩu
				response.getWriter().println("Tài khoản sai mật khẩu");
//				log.setUserId(user.getId());
//				log.setSrc("Trang Login");
//				log.setContent(user.getUserName() + " Tài khoản sai mật khẩu");
//				log.setLevel(Log.ALERT);
			}
		} else {
			if(userTrash != null) {
				message.put("userError", "Tài đã bị khoá ");
				//Logging khóa tài khoản user
//				log.setContent("Tài khoản đã bị khóa");
//				log.setSrc("Trang Login");
//				log.setLevel(Log.DANGER);
			}else {
				message.put("userError", "Tài khoản không tồn tại");
				//Logging user spam
//				log.setContent(" Tài khoản không tồn tại");
//				log.setSrc("Trang Login");
//				log.setLevel(Log.WARNING);
			}
			request.getRequestDispatcher("/login.jsp").forward(request, response);

		}

//		DB.me().insert(log);
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

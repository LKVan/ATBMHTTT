package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;
import support.CKDT;
import support.Endcoding;

/**
 * Servlet implementation class DisableKeyController
 */
@WebServlet("/disableKey")
public class DisableKeyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisableKeyController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDAO userDAO = new UserDAO();
		HttpSession session = request.getSession(true);
		Map<String, String> message = new HashMap<>();

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		String signature = request.getParameter("signature");

		User user = (User) session.getAttribute("user");
		request.setAttribute("message", message);

		if (user.getPublicKey() != null) {
			if (user.getUserName().equals(userName)) {
				if (Endcoding.encrypt(password).equals(user.getPassword())) {
					if (CKDT.verifySignature(signature, user.getPublicKey(), user.getSignatureData())) {
						User userUpdate = new User(user.getId(), user.getFullName(), user.getNumberPhone(),
								user.getAddress(), user.getUserName(), user.getPassword(), user.getRolId(),
								user.getEmail(), null, user.getSignatureData());
						userDAO.disablePublicKey(user.getId());
						session.setAttribute("user", userUpdate);

						response.sendRedirect(request.getContextPath() + "/HomeController");
					} else {
						message.put("signatureError", "Sai chữ kí");
						request.getRequestDispatcher("/disableKey.jsp").forward(request, response);
					}
				} else {
					message.put("passwordError", "Sai Mật Khẩu");
					request.getRequestDispatcher("/disableKey.jsp").forward(request, response);
				}
			} else {
				message.put("userError", "Sai tên đăng nhập");
				request.getRequestDispatcher("/disableKey.jsp").forward(request, response);
			}
		} else {
			message.put("keyError", "Không thể vô hiệu hóa,chữ kí của bạn đã bị vô hiệu hóa rồi.");
			request.getRequestDispatcher("/disableKey.jsp").forward(request, response);
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

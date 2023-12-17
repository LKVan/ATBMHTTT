package servlet;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
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
 * Servlet implementation class CreateKeyController
 */
@WebServlet("/createKey")
public class CreateKeyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateKeyController() {
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

		User user = (User) session.getAttribute("user");
		request.setAttribute("message", message);

		if (user.getPublicKey() != null) {
			message.put("keyError", "Không thể tạo key,chữ kí bạn chưa bị vô hiệu hóa");
			request.getRequestDispatcher("/createKey.jsp").forward(request, response);

		} else {
			String[] ck = new String[3];
			if (user.getUserName().equals(userName)) {
				if (Endcoding.encrypt(password).equals(user.getPassword())) {
					try {
						ck = CKDT.createSignature(user.getSignatureData());
						String publicKey = ck[1];

						User updateUser = new User(user.getFullName(), user.getNumberPhone(), user.getAddress(),
								user.getUserName(), user.getPassword(), user.getRolId(), user.getEmail(),
								user.getStatus(), publicKey, user.getSignatureData());
						updateUser.setId(user.getId());
						userDAO.changeKey(user.getId(), publicKey);
						session.setAttribute("ckdt", ck);
						session.setAttribute("user", updateUser);
						response.sendRedirect(request.getContextPath() + "/signature.jsp");
					} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException
							| SignatureException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					message.put("passwordError", "Sai Mật Khẩu");
					request.getRequestDispatcher("/createKey.jsp").forward(request, response);
				}
			} else {
				message.put("userError", "Sai tên đăng nhập");
				request.getRequestDispatcher("/createKey.jsp").forward(request, response);
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

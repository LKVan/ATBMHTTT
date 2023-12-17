package servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.ConfimDAO;
import dao.UserDAO;
import model.User;
import support.Endcoding;
import support.RandomString;
import support.SendEmail;

/**
 * Servlet implementation class ForgetController
 */
@WebServlet("/forget")
public class ForgetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgetController() {
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

		String userName = request.getParameter("userName");
		User user = userDAO.getUser(userName);

		if (user == null) {
			request.setAttribute("message", "Tài khoản không tồn tại");
			request.getRequestDispatcher("forget.jsp").forward(request, response);
		} else {
			String email = user.getEmail();
			Endcoding endCode = new Endcoding();
			RandomString rdString = new RandomString();
			String code = rdString.randomAlphaNumeric(4);
			ConfimDAO confimDAO = new ConfimDAO();
			confimDAO.insert(userName, code);

			SendEmail send = new SendEmail();
			String content = 
					
					"<div style=\"background:#f4f4f4;padding:5px 15px 15px 15px;text-align:center\">\r\n"
					+ "		<h2 style=\"text-align:center;padding-bottom:10px\">Shop Đồ Ăn Vặt Handmade</h2>\r\n"
					+ "		<p>Xin chào, mã xác thực tài khoản của quý khách là:</p>\r\n"
					+ "		<h4 style=\"text-align:center;font-size:30px\"><strong>" + code + "</strong></h4>\r\n"
					+ "		<p>Thời gian xác thực 5 phút, quý khách vui lòng không chia sẽ mã này cho bất kì ai.</p><div class=\"yj6qo\"></div><div class=\"adL\">\r\n"
					+ "	</div></div>";

			send.sendEmail(email, "Shop Đồ Ăn Vặt Handmae", content);

			String endCodeEmail = endCode.decodeEmail(email);
			request.setAttribute("endCodeEmail", endCodeEmail);
			request.setAttribute("userName", userName);
			request.getRequestDispatcher("confim.jsp").forward(request, response);
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

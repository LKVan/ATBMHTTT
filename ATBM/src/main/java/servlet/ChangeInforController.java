package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AddressDAO;
import dao.UserDAO;
import model.Address;
import model.User;

@WebServlet("/changeInfor")
public class ChangeInforController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangeInforController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		// request.setCharacterEncoding("UTF-8");
		UserDAO userDAO = new UserDAO();
		AddressDAO addressDAO = new AddressDAO();
		String action = request.getParameter("action");
		String update = request.getParameter("updateUser");
		String access = request.getParameter("access");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (access != null) {
			request.setAttribute("access", access);
		}

		if (action != null) {
			if (action.equals("edit")) {
				request.setAttribute("user", user);
				request.getRequestDispatcher("/editUser.jsp").forward(request, response);
			}
		} else if (update != null) {
			String userFullname = request.getParameter("userFullname");
			String userPhone = request.getParameter("userPhone");

			Address cityNew = addressDAO.getProvince(request.getParameter("province"));
			Address ditrictsNew = addressDAO.getDistrict(request.getParameter("district"));
			Address wardNew = addressDAO.getWard(request.getParameter("ward"));

			String addressNew = request.getParameter("addressDetail") + "," + wardNew.toString() + ","
					+ ditrictsNew.toString() + "," + cityNew.toString();

			User userUpdate = new User(user.getId(), userFullname, userPhone, addressNew, user.getUserName(),
					user.getPassword(), user.getRolId(), user.getEmail());
			userDAO.updateUser(userUpdate);
			request.getSession().setAttribute("user", userUpdate);
			System.out.println(userUpdate);

			response.sendRedirect(request.getContextPath()+"/detailInfor.jsp?access=yes");
		} else {
			request.getSession().setAttribute("langeName", "vi_VN");
			request.getRequestDispatcher("/detailInfor.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public static void main(String[] args) {

	}
}
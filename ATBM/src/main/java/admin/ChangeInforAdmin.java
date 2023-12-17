package admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;
import support.Endcoding;

/**
 * Servlet implementation class ChangeInforAdmin
 */
@WebServlet("/change_admin")
public class ChangeInforAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeInforAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oldPass = request.getParameter("oldPass");
		
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		UserDAO userDAO = new UserDAO();
		User user = (User)request.getSession().getAttribute("user");
		
		//Kiem tra nguoi dung co thay doi mat khau hay khong
		if(oldPass != null && !oldPass.equals("")) {
			String newPass = request.getParameter("newPass");
			System.out.println(newPass);
			newPass = Endcoding.encrypt(newPass);
			user.setFullName(fullName);
			user.setEmail(email);
			user.setNumberPhone(phone);
			user.setPassword(newPass);
		}else {
			user.setFullName(fullName);
			user.setEmail(email);
			user.setNumberPhone(phone);
			
		}
		//Kiem tra xem update thanh cong hay that bai
		if(userDAO.updateUser(user) > 0) {
			request.setAttribute("message", "Thay đổi thành công");
		}else {
			request.setAttribute("error", "Thay đổi thất bại");
		}
		request.getRequestDispatcher("/admin/adminProfile.jsp").forward(request, response);
		
		
		
	}

}

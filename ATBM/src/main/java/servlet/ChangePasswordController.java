package servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import support.Endcoding;

/**
 * Servlet implementation class ChangePasswordController
 */
@WebServlet("/change")
public class ChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("check", true);
		request.getRequestDispatcher("forget.jsp").forward(request, response);
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		request.setAttribute("check", true);
		
		String userName = request.getParameter("userName");
		String newPass = request.getParameter("password");
		String rePass = request.getParameter("repass");
		UserDAO userDAO = new UserDAO();
		
		
		System.out.println(userName);
		if(newPass.equals(rePass)) {
			newPass = Endcoding.encrypt(newPass);
			System.out.println("Change Password:" + userDAO.changPassword(userName,newPass));
			response.sendRedirect(request.getContextPath()+"/login?access=Đổi mật khẩu thành công");
		}else {
			request.setAttribute("message", "Nhập lại mật khẩu không chính xác");
			request.getRequestDispatcher("forget.jsp").forward(request, response);
		}
	}

}

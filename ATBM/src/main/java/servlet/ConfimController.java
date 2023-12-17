package servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ConfimDAO;
import model.Confim;

/**
 * Servlet implementation class ConfimController
 */
@WebServlet("/confim")
public class ConfimController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfimController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String box1 = request.getParameter("box1");
		String box2 = request.getParameter("box2");
		String box3 = request.getParameter("box3");
		String box4 = request.getParameter("box4");
		
		String userName = request.getParameter("userName");
		ConfimDAO confimDAO = new ConfimDAO();
		Confim confim = confimDAO.getConfimNew(userName);
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		
		if(confim.getDateEnd().after(now)) {
			String inputCode = box1 + box2 + box3 + box4;
			if(confim.getCode().equals(inputCode)) {
				System.out.println(userName +" check");
				request.setAttribute("userName", userName);
				request.getRequestDispatcher("/change").forward(request, response);
				
			}else {
				request.setAttribute("message", "Mã xác thực không hợp lệ vui lòng kiểm tra lại.");
				request.setAttribute("userName", userName);
				request.getRequestDispatcher("confim.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("message", "Mã xác thực đã hết hạn vui lòng gửi lại yêu cầu.");
			request.setAttribute("userName", userName);
			request.getRequestDispatcher("confim.jsp").forward(request, response);	
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductReportDAO;
import model.report.RevenueReport;

/**
 * Servlet implementation class RevenueController
 */
@WebServlet("/revenue")
public class RevenueController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RevenueController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductReportDAO prReportDAO = new ProductReportDAO();
		List<RevenueReport> listRevenue;
		String dateStart = request.getParameter("date_start_revenue");
		String dateEnd = request.getParameter("date_end_revenue");
		if (dateStart != null && dateEnd != null) {
			if (!dateStart.equals("") && !dateEnd.equals("")) {

				request.setAttribute("date_start_revenue", dateStart);
				request.setAttribute("date_start_revenue", dateEnd);

				listRevenue = prReportDAO.getRevenueByDate(dateStart, dateEnd);
				request.setAttribute("listRevenue", listRevenue);
			} else {
				request.setAttribute("message", "Vui lòng chọn thời gian chính xác");		
			}
			request.getRequestDispatcher("/admin/manager_revenue.jsp").forward(request, response);

		} else {
			response.sendRedirect(request.getContextPath()+"/admin/manager_revenue.jsp");
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

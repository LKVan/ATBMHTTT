package admin;


import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductReportDAO;
import model.report.ProductReport;
import model.report.RevenueReport;
import support.ExportExcel;

/**
 * Servlet implementation class StatisticalController
 */
@WebServlet("/statistical")
public class StatisticalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatisticalController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductReportDAO productReportDAO = new ProductReportDAO();
		
		List<ProductReport> listProductReport = null;
		String page  = request.getParameter("page");
		String action = request.getParameter("action");

		
		if(page == null) {
			listProductReport = productReportDAO.getAllProductReport();
			request.setAttribute("listProductReport", listProductReport);
			request.getRequestDispatcher("/admin/manager_statistical.jsp").forward(request, response);
		}else {
			//Kiem tra page duoc chon
			if(page.equals("sta_product")) {
				String dateStart = request.getParameter("date_start");
				String dateEnd = request.getParameter("date_end");
				//Kiem tra neu date == null se chuyen thang den trang
				if(dateStart != null && dateEnd != null) {
					//Kiem tra neu nguoi dung khong chon ngay
					if((!dateStart.equals("")) && !dateEnd.equals("")) {
						request.setAttribute("date_start", dateStart);
						request.setAttribute("date_end", dateEnd);
						// Neu ngay dau lon hon ngay sau thi se thong bao loi
						if(dateStart.compareToIgnoreCase(dateEnd) > 0) {
							//Hien thi lai tat ca cac dong report
							listProductReport= productReportDAO.getAllProductReport();
							request.setAttribute("listProductReport", listProductReport);
							request.setAttribute("message", "Vui lòng chọn thời gian xem thống kê chính xác");
							request.getRequestDispatcher("/admin/manager_statistical.jsp").forward(request, response);			
						}else {
							//Lay ra cac dong report theo  ngay nguoi dung chon xem thong ke
							listProductReport= productReportDAO.getProductReportByDate(dateStart,dateEnd);
							request.setAttribute("listProductReport", listProductReport);
							request.getRequestDispatcher("/admin/manager_statistical.jsp").forward(request, response);
						}
				
					}else {
						listProductReport= productReportDAO.getAllProductReport();
						request.setAttribute("listProductReport", listProductReport);
						request.setAttribute("message", "Vui lòng chọn thời gian xem thống kê");
						request.getRequestDispatcher("/admin/manager_statistical.jsp").forward(request, response);
					}
				}else {
					listProductReport= productReportDAO.getAllProductReport();
					request.setAttribute("listProductReport", listProductReport);
					request.getRequestDispatcher("/admin/manager_statistical.jsp").forward(request, response);
				}
			}else if(page.equals("revenue")) {					
					List<RevenueReport> listRevenue;
					String dateStart = request.getParameter("date_start_revenue");
					String dateEnd = request.getParameter("date_end_revenue");
					
					//Lay ra cac dong thong ke theo ngay duoc chi dinh
				if(dateStart != null && dateEnd != null ) {
						if(!dateStart.equals("") && !dateEnd.equals("")&& (dateEnd.compareTo(dateStart) > 0)) {
							
							request.setAttribute("date_start_revenue", dateStart);
							request.setAttribute("date_end_revenue", dateEnd);
							
							listRevenue = productReportDAO.getRevenueByDate(dateStart, dateEnd);
							request.setAttribute("listRevenue", listRevenue);
							request.getRequestDispatcher("/admin/manager_revenue.jsp").forward(request, response);
						}else { //Thong bao nguoi dung phai chon ngay xem thong ke
							request.setAttribute("message", "Vui lòng chọn thời gian chính xác");
							request.getRequestDispatcher("/admin/manager_revenue.jsp").forward(request, response);
						}
					
				}else {
					response.sendRedirect(request.getContextPath()+"/admin/manager_revenue.jsp");
				}
			}else {
				response.sendRedirect("404.html");
			}
		}
		
//		if(action != null) {
//			String path = request.getServletContext().getRealPath("/excel");
//			if(!Files.exists(Path.of(path))) {
//				Files.createDirectories(Path.of(path));
//			}
//			
//			OutputStream out = response.getOutputStream();
//			if(page.equals("sta_product")) {
//				String file = path+File.separator+"thongke_product.xlsx";
//				ExportExcel.writeExcel(null, file);
//				
//				response.setContentType("text/html");
//				response.setContentType("APPLICATION/OCTET-STREAM");
//				response.setHeader("Content-Disposition", "attachment;filename=thongke_product.xlsx");
//				
//				FileInputStream in = new FileInputStream(file);
//				byte[] buffer = new byte[4096];
//				int length;
//				while((length = in.read(buffer)) > 0) {
//					out.write(buffer, 0, length);
//					out.flush();
//				}
//				in.close();
//				out.close();
//			}
//		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

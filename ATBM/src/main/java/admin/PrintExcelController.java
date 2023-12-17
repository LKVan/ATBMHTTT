package admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductReportDAO;
import model.report.RevenueReport;
import support.ExportExcel;

/**
 * Servlet implementation class PrintExcelController
 */
@WebServlet("/print")
public class PrintExcelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintExcelController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String page = request.getParameter("page");
		ProductReportDAO productReportDAO = new ProductReportDAO();
		ExportExcel export = new ExportExcel();
		
		//Lay ra duong dan real path o server
		String path = request.getServletContext().getRealPath("/excel");
		
		//Kiem tra thu muc neu chua co thi se tao o thu muc server
		if(!Files.exists(Path.of(path))) {
			Files.createDirectories(Path.of(path));
		}	
		OutputStream out = response.getOutputStream();
		if(page.equals("sta_product")) {
			String dateStart = request.getParameter("date_start");
			String dateEnd = request.getParameter("date_end");
			String[] pararam = {"Mã","Tên sản phẩm","Số lượng bán ra","Tổng tiền bán ra"};
			String file = path+File.separator+"thongke_product.xlsx";
			//Kiem tra dieu kien de ghi ra file o server
			if(!dateStart.equals("") && !dateEnd.equals("")) {
				//Neu ngay duoc chon
				export.writeExcel(productReportDAO.getProductReportByDate(dateStart,dateEnd), file,pararam);
			}else {
				//Neu ngay khong duoc chon thi se 
				export.writeExcel(productReportDAO.getAllProductReport(), file,pararam);
			}
			
			response.setContentType("text/html");
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment;filename=thongke_product.xlsx");
			
			//Su dung input stream de doc file o server và xuat file ra cho nguoi dung
			FileInputStream in = new FileInputStream(file);
			byte[] buffer = new byte[4096];
			int length;
			while((length = in.read(buffer)) > 0) {
				//ghi ra file cho nguoi dung
				out.write(buffer, 0, length);
				out.flush();
			}
			//dong stream
			in.close();
			out.close();
			
		}else if (page.equals("revenue")) {			
			String dateStart = request.getParameter("date_start");
			String dateEnd = request.getParameter("date_end");
			String[] pararam = {"Thời gian","Số lượng sản phẩm bán ra","Tổng tiền bán ra"};
			String file = path+File.separator+"thongke_doanhthu.xlsx";
			//Kiem tra dieu kien de ghi ra file o server
			if(!dateStart.equals("") && !dateEnd.equals("")) {
				List<RevenueReport> list = productReportDAO.getRevenueByDate(dateStart,dateEnd);
				System.out.println(list);
				//Neu ngay duoc chon
				export.writeExcelRevenue(list, file,pararam);
			}else {
				//Neu ngay khong duoc chon thi se 
				export.writeExcelRevenue(new ArrayList<>(), file,pararam);
			}
			
			response.setContentType("text/html");
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment;filename=thongke_doanhthu.xlsx");
			
			//Su dung input stream de doc file o server và xuat file ra cho nguoi dung
			FileInputStream in = new FileInputStream(file);
			byte[] buffer = new byte[4096];
			int length;
			while((length = in.read(buffer)) > 0) {
				//ghi ra file cho nguoi dung
				out.write(buffer, 0, length);
				out.flush();
			}
			//dong stream
			in.close();
			out.close();
			
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

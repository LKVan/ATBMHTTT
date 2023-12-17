//package admin;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import bean.Log;
//import contanst.Status;
//import dao.DB;
//import dao.ProductDAO;
//import model.User;
//
///**
// * Servlet implementation class ChangeStatusProduct
// */
//@WebServlet("/change_status")
//public class ChangeStatusProduct extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public ChangeStatusProduct() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
////	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////		int proid =  Integer.parseInt(request.getParameter("id"));
////		int status = Integer.parseInt(request.getParameter("status"));
////		
////		ProductDAO productDAO = new ProductDAO();
////		
////		int row = productDAO.changeStatus(proid, status);
////		System.out.println(row);
////	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int proid =  Integer.parseInt(request.getParameter("id"));
//		int status = Integer.parseInt(request.getParameter("status"));
//		
//		ProductDAO productDAO = new ProductDAO();
//		
//		int row = productDAO.changeStatus(proid, status);
//
//		User user =(User) request.getSession().getAttribute("user");
//		Log log = new Log(Log.INFO, -1,"" ,"", 0);
//
//		if(status == Status.ACTIVE){
//			log.setLevel(Log.WARNING);
//			log.setSrc("Trang Manager Product");
//			log.setContent(user.getUserName() +" thay đôỉ trạng thái  " + proid +" sang active");
//		}else {
//			log.setLevel(Log.WARNING);
//			log.setSrc("Trang Manager Product");
//			log.setContent(user.getUserName() +" thay đôỉ trạng thái  " + proid +" sang enable");
//		}
//		DB.me().insert(log);
//	}
//
//}

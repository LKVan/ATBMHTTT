package admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import contanst.Status;
import dao.CategoryDAO;
import dao.CommentDAO;
import dao.ProductDAO;
import model.Category;
import model.Product;

/**
 * Servlet implementation class ManagerProduct
 */
@MultipartConfig()
@WebServlet("/manager_product")
public class ManagerProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagerProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ProductDAO productDAO = new ProductDAO();
		CategoryDAO cateDAO = new CategoryDAO();
		
		String action = request.getParameter("action");

		List<Product> listProduct ;
		String access = request.getParameter("access");
		
		System.out.println(request.getServletContext().getRealPath(""));
		
		if(access != null) {
			request.setAttribute("access", access);
		}
		
		if(action != null) {
			
			request.setAttribute("categories", cateDAO.getAllCategory());
			
			if(action.equals("main")) {
				//Lay ra cac san pham khong o trang thai xoa
				listProduct = productDAO.getAllProduct(Status.ACTIVE);
				request.setAttribute("listProduct", listProduct);
				request.getRequestDispatcher("/admin/manager_product.jsp").forward(request, response);
			}else if(action.equals("trash")) {
				//Lay ra cac san pham o trang thai xoa
				listProduct = productDAO.getAllProduct(Status.ENABLE);
				request.setAttribute("listProduct", listProduct);
				request.getRequestDispatcher("/admin/trash_product.jsp").forward(request, response);
			}else if(action.equals("comment")) {
				String id = request.getParameter("proId");
				request.setAttribute("product", productDAO.getProductById(Integer.parseInt(id)));
				CommentDAO commentDAO = new CommentDAO();
				System.out.println(commentDAO.getCommentByProductId(Integer.parseInt(id)));
				request.setAttribute("listComment", commentDAO.getCommentByProductId(Integer.parseInt(id)));
				request.getRequestDispatcher("/admin/rate_product.jsp").forward(request, response);

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

package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDAO;
import dao.ProductDAO;
import model.Product;
import model.TempComment;

@WebServlet("/product")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("proId"));
		ProductDAO productDAO = new ProductDAO();
		CommentDAO commentDAO = new CommentDAO();
		Product product = productDAO.getProductById(productId);

		List<TempComment> listComment = commentDAO.getCommentByProductId(productId);

		request.setAttribute("listComment", listComment);
		request.setAttribute("product", product);
		request.setAttribute(getServletInfo(), product);
		request.getRequestDispatcher("/detail.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		int productId = Integer.parseInt(request.getParameter("proId"));
//		ProductDAO productDAO = new ProductDAO();
//		CommentDAO commentDAO = new CommentDAO();
//		Product product = productDAO.getProductById(productId);
//
//		List<TempComment> listComment = commentDAO.getCommentByProductId(productId);
//
//		request.setAttribute("listComment", listComment);
//		request.setAttribute("product", product);
//		request.setAttribute(getServletInfo(), product);
//		request.getRequestDispatcher("/detail.jsp").forward(request, response);
         doGet(request, response);
	}

}

package servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDAO;
import dao.CommentDAO;
import dao.ProductDAO;
import model.Cart;
import model.Comment;
import model.Product;
import model.TempCart;
import model.TempComment;
import model.User;

@WebServlet("/CommentController")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int productId = Integer.parseInt(request.getParameter("proId"));
		ProductDAO productDAO = new ProductDAO();
		CommentDAO commentDAO = new CommentDAO();

		Product product = productDAO.getProductById(productId);
		List<TempComment> listComment = commentDAO.getCommentByProductId(productId);

		request.setAttribute("product", product);
		request.setAttribute("listComment", listComment);
		request.setAttribute(getServletInfo(), product);

		HttpSession session = request.getSession();
		Object objUser = session.getAttribute("user");

		if (objUser != null) {
			reComment(request);
			addToComment(request);
//			request.getRequestDispatcher("/detail.jsp").forward(request, response);
			response.sendRedirect("product?proId="+product.getId());
		} else {
		//	reComment(request);
			response.sendRedirect("login.jsp");
		}
	}

	// thêm bình luận
	protected void addToComment(HttpServletRequest request) {
		HttpSession session = request.getSession();
		CommentDAO commentDAO = new CommentDAO();

		String pro_id = request.getParameter("proId");
		ProductDAO pd = new ProductDAO();
		Product product = pd.getProductById(Integer.parseInt(pro_id));
		String comment = request.getParameter("comment");
		Comment commentBean = null;
		User user = null;

		Object objCommentBean = session.getAttribute("comment");
		Object objUser = session.getAttribute("user");

		// Xét điều kiện
		if (objUser != null) {
			user = (User) objUser;
		}
		if (objCommentBean != null) {
			commentBean = (Comment) objCommentBean;
		} else {
			commentBean = new Comment();
			session.setAttribute("comment", commentBean);
		}

		// tạo một comment mới
		commentBean.addComment(product, comment);
		commentDAO.insertTempComment(user.getId(), product.getId(), user.getFullName(), comment);
	}

	// tải lại trang bình luận
	protected void reComment(HttpServletRequest request) {
		HttpSession session = request.getSession();
		CommentDAO commentDAO = new CommentDAO();
		ProductDAO pd = new ProductDAO();

		Comment commentBean = null;
		User user = null;

		Object objUser = session.getAttribute("user");
		Object objCommentBean = session.getAttribute("comment");

		if (objUser != null) {
			user = (User) objUser;
		} else {
			System.out.println("Sai");
		}
		if (objCommentBean != null) {
			objCommentBean = null;
			commentBean = new Comment();
			session.setAttribute("comment", commentBean);
		} else {
			commentBean = new Comment();
			session.setAttribute("comment", commentBean);
		}
		List<TempComment> list = commentDAO.getCommentByUserId(user.getId());
		for (TempComment tempComment : list) {
			Product product = pd.getProductById(tempComment.getProductId());
			String icmt = "" + tempComment.getComment();
			commentBean.addComment(product, icmt);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

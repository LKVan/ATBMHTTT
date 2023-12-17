package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Product;

@WebServlet("/FoodController")
public class FoodController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FoodController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		ProductDAO productDAO = new ProductDAO();
		String indexPage = request.getParameter("index");
		if(indexPage==null) {
			indexPage="1";
		}
		int index = Integer.parseInt(indexPage);
		
		int count = productDAO.getTotalFood(0);
		int endPage = count / 12;
		if (count % 12 != 0) {
			endPage++;
		}
		
		List<Product> listAllFood = productDAO.pagingFood(index);
		
		request.setAttribute("listAllFood", listAllFood);
		request.setAttribute("endPFood", endPage);
		request.setAttribute("tag", index);
		request.getRequestDispatcher("/food.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

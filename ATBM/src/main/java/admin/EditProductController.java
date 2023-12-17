package admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.CategoryDAO;
import dao.ProductDAO;
import model.Category;
import model.Product;
import support.Endcoding;

/**
 * Servlet implementation class EditProductController
 */
@MultipartConfig(maxFileSize = 1024*1200)
@WebServlet("/edit_product")
public class EditProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO productDAO = new ProductDAO();
		CategoryDAO cateDAO = new CategoryDAO();

		String proId = request.getParameter("proId");
		System.out.println(proId);
		Product product = productDAO.getProductById(Integer.parseInt(proId));
		List<Category> listCate = cateDAO.getAllCategory();

		request.setAttribute("product", product);
		request.setAttribute("listCate", listCate);
		request.getRequestDispatcher("/admin/productEdit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		ProductDAO productDAO = new ProductDAO();
		
		String proId = request.getParameter("productId");
		String proName = request.getParameter("productName");
		String proImage = request.getParameter("productImage");
		String proDes = request.getParameter("productDes");
		String proPrice = request.getParameter("productPrice");
		String proKind = request.getParameter("productKind");
		String proHeight = request.getParameter("height");
		String proLength = request.getParameter("length");
		String proWidth = request.getParameter("width");
		String proWeigth = request.getParameter("weigth");
		

		//Lay realpath cua server																			
		String upLoadFolder = request.getServletContext().getRealPath("/Image");																									// dan thu																									// muc luu
																											
		Path upLoadPath = Paths.get(upLoadFolder);
		Part image = request.getPart("uploadImage");
		System.out.println("Image:" + image);
		String imageFileName = null;
		if(image != null) {
			imageFileName = Path.of(image.getSubmittedFileName()).getFileName().toString();
		}

		if (imageFileName != null && !imageFileName.equals("")) {
			//Doi ten file theo ten cua san pham
			String fileName = Endcoding.convertUTF_8(proName) +".png";
			File file = new File(upLoadFolder + File.separator + fileName);
			System.out.println(file.getAbsolutePath());
//			if (!file.exists()) {
			
				//Lay duong dan de xoa file cu
				File deleteFile = new File(
						upLoadFolder+ File.separator + proImage);
				proImage = "Image/" + fileName;
				boolean check = deleteFile.delete();
				System.out.print("check " + check);
				
				//Ghi file moi
				image.write(Paths.get(upLoadPath.toString(), fileName).toString());
			
				
		
		}
		Product product = new Product(Integer.parseInt(proId), proName, proDes, Integer.parseInt(proPrice),
				proImage,Integer.parseInt(proHeight),Integer.parseInt(proLength),Integer.parseInt(proWidth),Integer.parseInt(proWeigth) ,Integer.parseInt(proKind));
		productDAO.update(product);
		response.sendRedirect(request.getContextPath()+"/manager_product?action=main&update=yes");
		

	}
}

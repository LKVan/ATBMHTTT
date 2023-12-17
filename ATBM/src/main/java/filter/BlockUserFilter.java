package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import contanst.LoginError;
import contanst.Role;
import dao.UserDAO;
import model.User;

/**
 * Servlet Filter implementation class BlockUserFilter
 */
@WebFilter("/*")
public class BlockUserFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public BlockUserFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest req = (HttpServletRequest) request;
	     HttpServletResponse resp = (HttpServletResponse) response;
	     
	     UserDAO userDAO = new UserDAO();
	     
	     HttpSession session = req.getSession();
	     User user = (User) session.getAttribute("user");
	     
	     if(user != null) {
	    	 if(user.getStatus() != userDAO.getUser(user.getUserName()).getStatus()) {
	    		 resp.sendRedirect(req.getContextPath()+"/login?error=" + LoginError.LOGIN_AGAINN);
	    	 }else if(user.getRolId() == Role.ADMIN &&  (req.getRequestURI().endsWith("HomeController") ||req.getRequestURI().endsWith("/"))){
				 resp.sendRedirect(req.getContextPath()+"/admin");
			 }else {
				 chain.doFilter(req, resp);
			 }
	     }else {
			 chain.doFilter(req, resp);
		 }
	     
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

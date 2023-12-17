package filter;

import java.io.IOException;
import java.net.http.HttpResponse;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import contanst.Role;
import model.User;

@WebFilter("/admin/*")
public class SecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest rq = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String uri = rq.getRequestURI();
		User user = (User) rq.getSession().getAttribute("user");
//		String error = "";
		int error = 0;
		if (user == null) {
//			error = resp.encodeURL("Vui lòng đăng nhập");
			error = 1; //Vui long dang nhap khi vao url admin
		} else if (user.getRolId() != Role.ADMIN ) { //&& uri.contains("/amin/")
//			error = resp.encodeURL("Vui lòng đăng nhập với vai trò admin");
			error = 2;//Dang nhap voi tai khoan vai tro admin
		}

		if (error != 0) {//!error.isEmpty()
			rq.setAttribute("securi", uri);
			resp.sendRedirect(rq.getContextPath()+"/login?error=" + error);
		} else {
			chain.doFilter(rq, resp);
		}

	}

}

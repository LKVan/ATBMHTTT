package admin;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LogDAO;

/**
 * Servlet implementation class ManagerLog
 */
@WebServlet("/manager_log")
public class ManagerLog extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerLog() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        LogDAO logDAO = new LogDAO();
        if(action == null) {
            request.setAttribute("list", logDAO.getAllLog());
            request.getRequestDispatcher("/admin/manager_log.jsp").forward(request, response);
        }else {
            String id = request.getParameter("id");
            request.setAttribute("log", logDAO.getLogById(Integer.parseInt(id)));
            request.getRequestDispatcher("/admin/detail_log.jsp").forward(request, response);
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


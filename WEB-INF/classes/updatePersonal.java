import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class updatePersonal extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		HttpSession session = req.getSession(false);
		String username = (String)session.getAttribute("login");
        toClient.println(Utils.managerheader(username));
        ManagerData personalActualizar = new ManagerData(
                   0,
					null,
					null,
                    Integer.parseInt(req.getParameter("Telephone")),
                    username,
                    null,
					req.getParameter("BankNumber"),
					Integer.parseInt(req.getParameter("PostalCode")),
					req.getParameter("Direction")           
                );
				
        int n = ManagerData.updateManagerPersonal(connection,personalActualizar);
		res.sendRedirect("AccountManager");
		//Nos recarga la pagina inicial 
    }
}
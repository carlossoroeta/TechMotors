import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class updateAccount extends HttpServlet {
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
        ManagerData accountActualizar = new ManagerData(
                   0,
					null,
					null,
                    0,
                    req.getParameter("Username"),
                    req.getParameter("Password"),
					null,
					0,
					null           
                );
				
        int n = ManagerData.updateManagerAccount(connection,accountActualizar, username);
		res.sendRedirect("AccountManager");
		//Nos recarga la pagina inicial 
    }
}
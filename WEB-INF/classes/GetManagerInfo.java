import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class GetManagerInfo extends HttpServlet {
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
        String productJson = req.getParameter("json");

        if (productJson != null) {
            ManagerData manager = ManagerData.getManagerDetail(connection, username);
            toClient.print("{");
            toClient.print("\"IDManager\":\"" + manager.IDManager + "\"");
            toClient.print(",\"Name\":\"" + manager.Name + "\"");
            toClient.print(",\"Location\":\"" + manager.Location + "\"");
			toClient.print(",\"Telephone\":\"" + manager.Telephone + "\"");
			toClient.print(",\"Username\":\"" + manager.Username + "\"");
            toClient.print(",\"Password\":\"" + manager.Password + "\"");
            toClient.print(",\"BankNumber\":\"" + manager.BankNumber + "\"");
			toClient.print(",\"PostalCode\":\"" + manager.PostalCode + "\"");
            toClient.print(",\"Direction\":\"" + manager.Direction + "\"");
            toClient.print("}");
        } else {

        }
        toClient.close();
    }
}
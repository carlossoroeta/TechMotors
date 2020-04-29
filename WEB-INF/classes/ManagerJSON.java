import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;


@SuppressWarnings("serial")
public class ManagerJSON extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		String productJson=req.getParameter("json");
		String usern=req.getParameter("user");
		if (productJson != null) {
		   
		    String u=ManagerData.checkManagers(connection,usern);
		 

			toClient.print("{");
		    toClient.print("\"Username\":\"" + u + "\"");
			toClient.print("}");
			
	      
		} else {
			
		}

        
        toClient.close();
    }
}
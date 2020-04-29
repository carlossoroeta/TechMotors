import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;


@SuppressWarnings("serial")
public class LicensePlateJSON extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		String productJson=req.getParameter("json");
		String usern=req.getParameter("LicensePlate");
		if (productJson != null) {
		   
		    String u=MyVehiclesData.checkLicensePlate(connection,usern);
		 

			toClient.print("{");
		    toClient.print("\"LicensePlate\":\"" + u + "\"");
			toClient.print("}");
			
	      
		} else {
			
		}

        
        toClient.close();
    }
}
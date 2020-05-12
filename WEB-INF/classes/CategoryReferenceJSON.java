import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;


@SuppressWarnings("serial")
public class CategoryReferenceJSON extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		String productJson=req.getParameter("json");
		//HttpSession session = req.getSession(false);
        //String username = (String)session.getAttribute("login");
		String reference = req.getParameter("reference"); 		
		
		if (productJson != null) {
		    ShowCategoryData app=ShowCategoryData.getPrice(connection,reference);
			    toClient.print("{");
				toClient.print("\"PrecioVenta\":\"" + app.UnitPriceSell + "\"");    
				toClient.print("}");
			
	        
		} else {
			
		}

        
        toClient.close();
    }
}
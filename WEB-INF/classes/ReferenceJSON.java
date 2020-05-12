import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;


@SuppressWarnings("serial")
public class ReferenceJSON extends HttpServlet {
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
        String type = req.getParameter("type");
		String category = req.getParameter("category"); 		
		
		if (productJson != null) {
		    toClient.print("[");
		    Vector<ShowCategoryData> vec=ShowCategoryData.getPosibleReferences(connection,category,type);
		    for (int i=0;i<vec.size();i++) {
			    ShowCategoryData app=vec.elementAt(i);
				if (i!=0) {
				    toClient.print(",");
				}
			    toClient.print("{");
				toClient.print("\"Reference\":\"" + app.Reference + "\"");    
				toClient.print("}");
			
	        }
		    toClient.print("]");
		} else {
			
		}

        
        toClient.close();
    }
}
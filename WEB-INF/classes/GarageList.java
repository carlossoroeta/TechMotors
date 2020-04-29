import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.*;

@SuppressWarnings("serial")
public class GarageList extends HttpServlet {
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
        toClient.println(Utils.header(username)); 
		toClient.println("<h1 font-size='7' align='center' >OUR GARAGES</h1>");
		toClient.println("<div class='w3-container w3-padding-64 ' >");
		toClient.println("<table style ='margin-right:300px' align='center' class='w3-table' style='border-collapse:separate;margin:10px;width:1450px' border='1' >");
        toClient.println("<tr class='w3-grey' ><td><B>ID<B></td><td><B>Location</B></td><td><B>Manager</B></td><td><B>Opening Time</B></td><td><B>Closing Time</B></td></tr>");
		Vector<GaragesData> GarageList;
		GarageList = GaragesData.getGarageList(connection);
        for(int i=0; i< GarageList.size(); i++){
                GaragesData garage = GarageList.elementAt(i);
                toClient.println("<tr>");
                toClient.println("<td>" + garage.IDGarage + " </td>");
                toClient.println("<td>" + garage.Location + " </td>");
                toClient.println("<td>" + garage.Manager + " </td>");
                toClient.println("<td>" + garage.OpeningTime + " </td>");
				toClient.println("<td>" + garage.ClosingTime + " </td>");
                toClient.println("</tr>");
        }

        toClient.println("</table>");
		toClient.println("<p> WARNING: All four garages work 7 days a week, except the one that is located in San Sebastian, which is opened only from monday to saturday</p>");
		toClient.println("</div>");
        toClient.println(Utils.footer());
        toClient.close();
    }
	
}
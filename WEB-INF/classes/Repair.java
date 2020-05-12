import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")

public class Repair extends HttpServlet {
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
		String fecha = req.getParameter("Fecha");
		String appointment = req.getParameter("Appointment");
		String vehicle = req.getParameter("Vehicle");
		String garage = req.getParameter("Garage");
		String hour = req.getParameter("Hour");
		toClient.println(Utils.header(username));
		toClient.println("<body><center><h1>DETAILS OF THE APPOINTMENT</h1></center><br/><br/>");
		toClient.println("<form action='Update' method='GET'>");
		toClient.println("<input type='hidden' id='fecha' name='Fecha' value=" + fecha + ">");
		toClient.println("<input type='hidden' id='appointment' name='Appointment' value=" + appointment + ">");
		toClient.println("<input type='hidden' id='vehicle' name='Vehicle' value=" + vehicle + ">");
		toClient.println("<input type='hidden' id='garage' name='Garage' value=" + garage + ">");
		toClient.println("<input type='hidden' id='hour' name='Hour' value=" + hour + ">");
		toClient.println("<table class='w3-table w3-center' border='1'>");	
		toClient.println("<tr><td><b>Appointment</b></td>");
        toClient.println("<td><input name='appointment' value='" + appointment + "'disabled></td></tr>");
		toClient.println("<tr><td><b>Vehicle</b></td>");
        toClient.println("<td><input name='vehicle' value='" + vehicle + "'disabled></td></tr>");
		toClient.println("<tr><td><b>Garage</b></td>");
        toClient.println("<td><input name='garage' value='" + garage + "'disabled></td></tr>");
		toClient.println("<tr><td><b>Date</b></td>");
        toClient.println("<td><input name='fecha' value='" + fecha + "'disabled></td></tr>");
		toClient.println("<tr><td><b>Hour</b></td>");
        toClient.println("<td><input name='hour' value='" + hour + "'disabled></td></tr>");
		toClient.println("</table>");
        toClient.println("<br><center><button onclick='alertar()' type='submit'>CONFIRM</button></center></br></form>");
        toClient.println("</form>");
		toClient.println("<SCRIPT SRC='alert.js'></SCRIPT>");
        toClient.println(Utils.footer());
        toClient.close();
    }
}
		
		
		
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class Calendario extends HttpServlet {
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
		String appointment = req.getParameter("Appointment");
		String vehicle = req.getParameter("Vehicle");
		String garage = req.getParameter("Garage");
		toClient.println(Utils.header(username));
		toClient.println("<head><title>Calendar</title>");
		toClient.println("<input type='hidden' id='appointment' name='Appointment' value=" + appointment + ">");
		toClient.println("<input type='hidden' id='vehicle' name='Vehicle' value=" + vehicle + ">");
		toClient.println("<input type='hidden' id='garage' name='Garage' value=" + garage + ">");
		toClient.println("<link rel='stylesheet' type='text/css' href='calendario.css' media='all' /><script type='text/javascript' src='calendario.js'></script></head>");
		toClient.println("<body><h1>SELECT YOUR APPOINTMENT DATE</h1><br/><br/>");
		toClient.println("<div id='calendario'><div id='anterior' onclick='mesantes()'></div><div id='posterior' onclick='mesdespues()'></div>");
		toClient.println("<h2 id='titulos'></h2><link rel='stylesheet' href='w3.css'>");
		toClient.println("<table id='diasc'>");
		toClient.println("<tr class0'w3-grey' id='fila0'><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>");
		toClient.println("<tr id='fila1'><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
		toClient.println("<tr id='fila2'><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
		toClient.println("<tr id='fila3'><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
		toClient.println("<tr id='fila4'><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
		toClient.println("<tr id='fila5'><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
		toClient.println("<tr id='fila6'><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
		toClient.println("</table><div id='fechaactual'><i onclick='actualizar()'>TODAY: </i></div></div>");
		toClient.println(Utils.footer());
        toClient.close();
    }
}
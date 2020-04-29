import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class filtroAppointment extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		HttpSession session = req.getSession(false);
		String username = (String)session.getAttribute("login");
        toClient.println(Utils.header(username));
        toClient.println("<div class='w3-bar-item' style='letter-spacing:4px;'>");
		toClient.println("<form id='formulario'><center><fieldset class='w3-fieldset' style='margin-top:20px'>");
		toClient.println("<legend align='center'>Query of appointments</legend>");
		toClient.println("<p><input type='radio' name='Query' value='Revision'>Revision<br></p>");
		toClient.println("<p><input type='radio' name='Query' value='Exchange'>Exchange<br></p>");
		toClient.println("<p><input type='radio' name='Query' value='Tunning'>Customization<br></p>");
		toClient.println("<p><input type='radio' name='Query' value='All'>All<br></p>");
		toClient.println("<button class='w3-button w3-light-grey w3-section' type='submit' onclick='cambioaction()'>Consult</button></fieldset></center>");
		toClient.println("</form></div>");
		toClient.println("<SCRIPT SRC='actionappointments.js'></SCRIPT>");
        toClient.println(Utils.footer());
        toClient.close();
    }
}
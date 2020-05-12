import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class TypeOfAppointment extends HttpServlet {
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
		toClient.println("<body><div class='w3-bar-item' style='letter-spacing:4px;'><form action='Calendario' method='GET'>");
		toClient.println("<p><fieldset class='w3-fieldset' style='margin-right:700px'><legend align='center'>Select your appointment</legend><input type='radio' name='Appointment' value='Exchange'>Exchange<br><input type='radio' name='Appointment' value='Repair'>Repair<br><input type='radio' name='Appointment' value='Customization'>Customization<br></fieldset>");
		toClient.println("<fieldset class='w3-fieldset' style='margin-left:700px'><legend align='center'>Vehicle</legend>");
		toClient.println("<table class='w3-table'>");	
		Vector<TypeOfAppointmentData> vehicleList;
		vehicleList = TypeOfAppointmentData.getVehicleList(connection, username);
		for(int i=0; i< vehicleList.size(); i++){
                TypeOfAppointmentData vehicle = vehicleList.elementAt(i);
				toClient.println("<tr>");
                toClient.println("<td><input type='radio' name='Vehicle' value=" + vehicle.model + ">" + vehicle.model + "</td>");
                toClient.println("</tr>");
		}
		toClient.println("</table>");
		toClient.println("</fieldset></p>");
		toClient.println("<fieldset class='w3-fieldset'><legend align='center'>Select garage</legend><table class='w3-table'><tr><td><input type='radio' name='Garage' value='SanSebastian'>San Sebastian<br></td><td><input type='radio' name='Garage' value='Oiartzun'>Oiartzun<br></td></tr><tr><td><input type='radio' name='Garage' value='Renteria'>Renteria<br></td><td><input type='radio' name='Garage' value='Zarautz'>Zarautz<br></td></tr></table></fieldset>");
		toClient.println("<p><center><button class='w3-button w3-display w3-light-grey w3-section' type='submit'>Send</button></center></p></form></div>");
		toClient.println(Utils.footer());
        toClient.close();
    }
}
